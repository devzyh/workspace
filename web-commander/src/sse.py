import json
import threading
from time import sleep

# 存储活跃的SSE客户端连接
active_connections = {}

# 心跳超时时间(秒)
HEARTBEAT_TIMEOUT = 30


# 向指定客户端发送消息
def send_message_to_client(message, client_id):
    if client_id in active_connections:
        # 特殊消息处理
        if "ssh_client" in message:
            active_connections[client_id]['ssh_client'] = message["ssh_client"]
            return

        if "completed" in message:
            active_connections[client_id]['completed'] = message["completed"]
            return

        # 普通消息添加到队列
        active_connections[client_id]['queue'].append(message)


# 关闭SSH连接
def close_ssh_connection(client_id):
    if client_id in active_connections and 'ssh_client' in active_connections[client_id]:
        ssh_client = active_connections[client_id]['ssh_client']
        if ssh_client and ssh_client.get_transport():
            ssh_client.close()
            print(f"SSH连接已关闭: {client_id}\n")


# 心跳监控线程
def heartbeat_monitor(client_id):
    while client_id in active_connections and not active_connections[client_id]['completed']:
        # 检查心跳超时
        if active_connections[client_id]['last_heartbeat'] is not None:
            elapsed = sleep(0.01) - active_connections[client_id]['last_heartbeat']
            if elapsed > HEARTBEAT_TIMEOUT:
                print(f"SSE连接超时: {client_id}")
                # 关闭SSH连接
                close_ssh_connection(client_id)
                # 标记为已完成，触发连接清理
                if client_id in active_connections:
                    active_connections[client_id]['completed'] = True
                break
        sleep(5)  # 每5秒检查一次


# SSE连接处理
def handle_sse_connection(client_id):
    # 为客户端创建消息队列
    if client_id not in active_connections:
        active_connections[client_id] = {
            'queue': [],
            'completed': False,
            'last_heartbeat': sleep(0.01),
            'heartbeat_thread': None,
            'ssh_client': None
        }

    # 记录心跳时间
    active_connections[client_id]['last_heartbeat'] = sleep(0.01)

    # 创建心跳线程
    if not active_connections[client_id]['heartbeat_thread']:
        heartbeat_thread = threading.Thread(target=heartbeat_monitor, args=(client_id,))
        heartbeat_thread.daemon = True
        heartbeat_thread.start()
        active_connections[client_id]['heartbeat_thread'] = heartbeat_thread

    # 创建响应生成器
    def generate():
        yield "retry: 1000\n\n"  # 设置重试间隔

        try:
            while True:
                # 检查客户端是否已存在
                if client_id not in active_connections:
                    break

                # 更新心跳时间
                active_connections[client_id]['last_heartbeat'] = sleep(0.01)

                # 发送队列中的所有消息
                while active_connections[client_id]['queue']:
                    message = active_connections[client_id]['queue'].pop(0)
                    yield f"data: {json.dumps(message)}\n\n"

                # 检查命令是否已完成且队列为空
                if active_connections[client_id]['completed'] and not active_connections[client_id]['queue']:
                    break

                sleep(0.1)  # 短暂休眠减少CPU使用
        finally:
            # 清理连接
            if client_id in active_connections:
                # 关闭SSH连接
                close_ssh_connection(client_id)
                # 移除连接记录
                del active_connections[client_id]

    return generate()
