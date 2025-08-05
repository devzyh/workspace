#!/bin/bash

# 基本信息
WORK_PATH=/home/debian/gh-ost
DB_HOST="localhost"
DB_PORT=3306
DB_USER="test"
DB_PASS="test"
DB_NAME="test"


# 清理历史后台日志
rm -rf $PATH/nohup.out


# 处理SQL文件
sql_file=$WORK_PATH/alter.sql
num=0
while IFS= read -r sql; do
    ((num++))
    echo "$num: $sql"

    # 跳过注释和空行
    if [[ "$sql" =~ ^#.* ]] || [[ -z "$sql" ]]; then
        continue
    fi

    # 以下是运行参数，谨慎修改
    $WORK_PATH/gh-ost \
    --max-load="Threads_running=100,Threads_connected=600" \
    --critical-load="Threads_running=150,Threads_connected=800" \
    --chunk-size=3000 \
    --initially-drop-old-table \
    --initially-drop-ghost-table \
    --initially-drop-socket-file \
    --ok-to-drop-table \
    --allow-on-master \
    --allow-master-master \
    --default-retries=60 \
    --azure \
    --assume-rbr \
    --assume-master-host="$DB_HOST" \
    --master-user="$DB_USER" \
    --master-password="$DB_PASS" \
    --host="$DB_HOST" \
    --ssl \
    --port=$DB_PORT \
    --user="$DB_USER" \
    --password="$DB_PASS" \
    --database="$DB_NAME" \
    --alter="$sql" \
    --panic-flag-file=$WORK_PATH/panic.flag \
    --postpone-cut-over-flag-file=$WORK_PATH/cut-over.flag \
    --serve-socket-file=$WORK_PATH/socket.sock \
    --verbose \
    --execute

    # 注释当前SQL
    sed -i "${num}s/^/# /" $sql_file

    echo "Alter Table Success"

done < $sql_file