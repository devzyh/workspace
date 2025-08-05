#################################################
####################注意事项#######################
# 设置数据库参数binlog_row_image值为FULL
# 设置从库数据库参数binlog_format值为ROW
# 数据库剩余空间大于变更表目前占用空间的一倍
# 被变更的表必须拥有主键或不为空的唯一索引列
# SQL里不要带有`注释使用''包裹，否则SQL会执行失败
# 一行一条SQL结尾不要带;可以一个SQL执行多个列变更
# 带#的行会被脚本标识为注释行，不会被脚本执行
# 执行成功的SQL会被脚本自动注释，避免重复执行
#################################################
#################################################

# 单头SQL
ALTER TABLE tmp_header  DROP COLUMN test, ADD COLUMN test varchar(255) NULL COMMENT '测试单头'
# 明细SQL
ALTER TABLE tmp_detail  DROP COLUMN test, ADD COLUMN test varchar(255) NULL COMMENT '测试明细'
