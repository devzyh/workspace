# 使用轻量级的Python基础镜像
FROM python:3.9-slim

# 设置工作目录
WORKDIR /app

# 安装Python依赖
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# 复制项目文件
COPY . .

# 暴露端口
EXPOSE 5000

# 启动应用
CMD ["gunicorn","-b","0.0.0.0:5000", "app:app"]
