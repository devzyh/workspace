import logging
import os
import subprocess
from datetime import datetime

import boto3
import yaml

# 配置日志
logging.basicConfig(
    filename='backup.log',
    encoding='utf-8',
    level=logging.INFO,
    format='%(asctime)s - %(levelname)s - %(message)s'
)


def read_config():
    """读取 YAML 配置文件"""
    try:
        with open('config.yaml', 'r', encoding='utf-8') as f:
            return yaml.safe_load(f)
    except FileNotFoundError:
        logging.error("未找到配置文件 'config.yaml'")
        return None


def export_mysql_database(mysql_config):
    """导出 MySQL 数据库"""
    command = [
        'mysqldump',
        f"--host={mysql_config['host']}",
        f"--port={mysql_config['port']}",
        f"--user={mysql_config['user']}",
        f"--password={mysql_config['password']}",
        mysql_config['database']
    ]
    timestamp = datetime.now().strftime("%Y%m%d%H%M%S")
    output_file = f"{mysql_config['database']}_{timestamp}.sql"
    try:
        with open(output_file, 'w') as f:
            subprocess.run(command, stdout=f, check=True)
        logging.info("数据库导出成功！")
        return output_file
    except (subprocess.CalledProcessError, OSError) as e:
        if os.path.exists(output_file):
            os.remove(output_file)
        logging.error(f"数据库导出失败: {e}")
        return None


def upload_to_s3(file_path, s3_config):
    """上传文件到 S3"""
    try:
        s3_client = boto3.client(
            's3',
            aws_access_key_id=s3_config['access_key'],
            aws_secret_access_key=s3_config['secret_key'],
            region_name=s3_config['region'],
            endpoint_url=s3_config['endpoint']
        )
        file_name = os.path.basename(file_path)
        s3_client.upload_file(file_path, s3_config['bucket'], file_name)
        logging.info("文件上传到 S3 成功！")
    except Exception as e:
        logging.error(f"文件上传到 S3 失败: {e}")


if __name__ == "__main__":
    logging.info("程序：MySQL数据库备份到S3云存储，版本：v1.0.0")

    # 读取配置
    config = read_config()
    if not config:
        logging.error("无法读取配置文件，程序终止")
        exit(1)

    mysql_config = config['mysql']
    s3_config = config['s3']

    # 导出数据库
    exported_file = export_mysql_database(mysql_config)
    if not exported_file:
        logging.error("数据库导出失败，程序终止")
        exit(1)

    # 上传到 S3
    upload_to_s3(exported_file, s3_config)
    # 删除本地导出的文件
    try:
        os.remove(exported_file)
        logging.info("本地导出的文件已删除")
    except Exception as e:
        logging.error(f"删除本地导出的文件失败: {e}")

    logging.info("程序执行完毕")
