#!/bin/bash

# 业务低峰时间段
# 时间小于10需要向左补一个0
START_TIME="19:00"
END_TIME="08:00"
# 切换标识文件路径
FLAG_PATH=/home/debian/gh-ost

# 判断当前时间是否在指定时间段内
current_time=$(date +"%H:%M")
flag_file=$FLAG_PATH/cut-over.flag
if [[ "$current_time" > "$START_TIME" && "$current_time" < "$END_TIME" ]]; then
    rm -rf $flag_file
else
    if [ ! -f "$flag_file" ]; then
        touch $flag_file
        chmod 755 $flag_file
    fi
fi