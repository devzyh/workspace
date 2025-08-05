# 主机信息
import os
from dataclasses import dataclass
from typing import List

import yaml

file_path = 'config.yml'


@dataclass
class Host:
    name: str
    host: str
    port: int
    username: str
    password: str


@dataclass
class Command:
    name: str
    color: str
    descript: str
    host: Host
    shell: str
    secret: str


# 解析主机列表
def parse_config(yaml_config):
    hosts = {}
    secret = yaml_config.get('secret', '')
    for host_data in yaml_config.get('hosts', []):
        host = Host(
            name=host_data.get('name', ''),
            host=host_data.get('host', ''),
            port=host_data.get('port', 22),  # 默认SSH端口
            username=host_data.get('username', ''),
            password=host_data.get('password', '')
        )
        hosts[host.name] = host

    # 解析命令列表
    commands = []
    for cmd_data in yaml_config.get('commands', []):
        host_name = cmd_data.get('host', '')
        host = hosts.get(host_name)

        if not host:
            print(f"警告: 命令 '{cmd_data.get('name', '')}' 引用了不存在的主机 '{host_name}'")
            continue

        command = Command(
            name=cmd_data.get('name', ''),
            descript=cmd_data.get('descript', ''),
            color=cmd_data.get('color', ''),
            host=host,
            shell=cmd_data.get('shell', ''),
            secret=cmd_data.get('secret', '')
        )
        if command.secret is None or len(str(command.secret)) == 0:
            command.secret = secret
        commands.append(command)

    return commands


# 读取YAML配置
def read_config():
    if not os.path.exists(file_path):
        raise FileNotFoundError(f"配置文件不存在: {file_path}")

    with open(file_path, 'r', encoding='utf-8') as file:
        try:
            yaml_config = yaml.safe_load(file)
            return parse_config(yaml_config)
        except yaml.YAMLError as e:
            print(f"解析YAML文件时出错: {e}")
            return None


# 配置对象
config: List[Command] = None
if config is None:
    config = read_config()
