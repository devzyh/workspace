import threading
from time import sleep

import paramiko

from .sse import send_message_to_client


# 处理SSH命令执行并推送结果
def handle_ssh_command(client_id, command):
    ssh_client = None
    try:
        host = command.host
        # 建立SSH连接
        ssh_client = paramiko.SSHClient()
        ssh_client.set_missing_host_key_policy(paramiko.AutoAddPolicy())
        ssh_client.connect(host.host, port=host.port, username=host.username, password=host.password)

        # 保存SSH客户端实例
        send_message_to_client({"ssh_client": ssh_client}, client_id)

        # 执行命令
        send_message_to_client({"status": "running", "message": f"正在执行命令: {command.name}"}, client_id)
        stdin, stdout, stderr = ssh_client.exec_command(command.shell, bufsize=1, timeout=86400)

        # 实时读取并推送标准输出
        for line in stdout:
            send_message_to_client({"status": "output", "message": line.strip()}, client_id)
            sleep(0.01)  # 小延迟确保输出有序

        # 读取并推送标准错误
        for line in stderr:
            send_message_to_client({"status": "error", "message": line.strip()}, client_id)
            sleep(0.01)

        # 命令执行完成
        exit_status = stdout.channel.recv_exit_status()
        send_message_to_client({"status": "completed", "message": f"命令执行完成，退出状态: {exit_status}"}, client_id)

    except Exception as e:
        send_message_to_client({"status": "error", "message": f"执行出错: {str(e)}"}, client_id)
    finally:
        # 关闭SSH连接
        if ssh_client:
            ssh_client.close()
            print(f"SSH命令执行完成，连接已关闭: {client_id}")

        # 标记客户端连接为已完成
        send_message_to_client({"completed": True}, client_id)


# 在新线程中执行SSH命令
def execute_ssh_command(client_id, command):
    thread = threading.Thread(
        target=handle_ssh_command,
        args=(client_id, command)
    )
    thread.daemon = True
    thread.start()
