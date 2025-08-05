import json

from flask import Blueprint, render_template, request, Response

from .config import config
from .sse import handle_sse_connection
from .ssh import execute_ssh_command

main = Blueprint('main', __name__)


@main.route('/', methods=['GET'])
def index():
    return render_template('index.html', config=config)


@main.route('/ssh/sse', methods=['GET'])
def ssh_sse():
    client_id = request.args.get('clientId')
    if not client_id:
        return json.dumps({"error": "clientId为空"}), 400

    return Response(handle_sse_connection(client_id), mimetype='text/event-stream')


@main.route('/ssh/execute', methods=['POST'])
def execute_command():
    params = json.loads(request.data)
    client_id = params.get('clientId')
    shell = params.get('command')
    secret = params.get('secret')

    command = None
    for cmd in config:
        if cmd.name == shell:
            command = cmd
            break

    if client_id is None or command is None:
        return json.dumps({"error": "请求参数错误"})

    if command.secret is not None and len(str(command.secret)) != 0 and str(secret) != str(command.secret):
        return json.dumps({"error": "执行密钥错误"})

    execute_ssh_command(client_id, command)
    return json.dumps({"status": "started", "clientId": client_id})
