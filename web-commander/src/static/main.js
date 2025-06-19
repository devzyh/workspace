let eventSource = null;
let clientId = null;
let isExecuting = false; // 新增：标记是否正在执行命令

function execute(command) {
    // 如果正在执行命令，直接返回
    if (isExecuting) {
        return;
    }

    // 增加确认弹窗
    let secret = prompt(`确定要执行命令 "${command}" 吗？（输入密钥继续执行，没有则忽略）`);
    if (secret === null) {
        return;
    }

    // 清空输出区域
    document.getElementById('output').innerHTML = '';

    // 生成客户端ID
    clientId = Date.now().toString();

    // 启动SSE连接
    eventSource = new EventSource(`/ssh/sse?clientId=${clientId}`);

    // 处理SSE消息
    eventSource.onmessage = function (event) {
        try {
            const data = JSON.parse(event.data);
            displayMessage(data);

            // 检查命令是否完成
            if (data.status === 'completed' || data.status === 'error') {
                isExecuting = false;
                enableAllButtons();
            }
        } catch (e) {
            console.error('解析消息出错:', e);
        }
    };

    eventSource.onerror = function (error) {
        console.error('SSE连接出错:', error);
        if (eventSource.readyState !== EventSource.CLOSED) {
            eventSource.close();
        }
        document.getElementById('output').innerHTML += '<div class="status-error">SSE连接出错</div>';

        // 发生错误时恢复按钮状态
        isExecuting = false;
        enableAllButtons();
    };

    // 发送执行命令请求前禁用所有按钮
    isExecuting = true;
    disableAllButtons();

    // 发送执行命令请求
    fetch('/ssh/execute', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            clientId: clientId,
            secret: secret,
            command: command
        })
    })
        .then(response => response.json())
        .then(data => {
            if (data.status === 'started') {
            } else {
                document.getElementById('output').innerHTML += '<div class="status-error">启动命令执行失败: ' + data.error + '</div>';

                // 请求失败时恢复按钮状态
                isExecuting = false;
                enableAllButtons();
            }
        })
        .catch(error => {
            document.getElementById('output').innerHTML += '<div class="status-error">网络错误: ' + error + '</div>';
            console.error('请求出错:', error);

            // 请求出错时恢复按钮状态
            isExecuting = false;
            enableAllButtons();
        });
}

// 新增：禁用所有按钮的函数
function disableAllButtons() {
    const buttons = document.querySelectorAll('button');
    buttons.forEach(button => {
        button.disabled = true;
        button.classList.add('disabled'); // 添加禁用样式
    });
}

// 新增：启用所有按钮的函数
function enableAllButtons() {
    const buttons = document.querySelectorAll('button');
    buttons.forEach(button => {
        button.disabled = false;
        button.classList.remove('disabled'); // 移除禁用样式
    });
}

function displayMessage(message) {
    const outputDiv = document.getElementById('output');
    const div = document.createElement('div');

    switch (message.status) {
        case 'running':
            div.className = 'status-running';
            break;
        case 'output':
            div.className = 'status-output';
            break;
        case 'error':
            div.className = 'status-error';
            break;
        case 'completed':
            div.className = 'status-completed';
            break;
    }

    div.textContent = message.message;
    outputDiv.appendChild(div);
    outputDiv.scrollTop = outputDiv.scrollHeight; // 滚动到底部
}
