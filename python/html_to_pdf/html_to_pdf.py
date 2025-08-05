import base64
import os

from selenium import webdriver
from selenium.webdriver.edge.options import Options

# 地址
urls = []
with open("url.txt", "r", encoding="utf-8") as file:
    for line in file:
        url = line.strip()
        if len(url) > 0:
            urls.append(url)

# 脚本
script = ""
with open("js.txt", "r", encoding="utf-8") as file:
    script = file.read()

# 输出
output = "output/"
if not os.path.exists(output):
    os.mkdir(output)


# 保存文件
def save_pdf(url: str):
    # 设置浏览器的打印选项
    edge_options = Options()
    edge_options.add_argument('--disable-gpu')  # 禁用GPU加速
    edge_options.add_argument('--print-to-pdf')  # 设置保存为PDF

    # 创建Chrome浏览器实例
    driver = webdriver.Edge(options=edge_options)

    # 窗口最小化
    driver.minimize_window()

    # 打开目标网页
    driver.get(url)

    # 模拟用户操作，例如点击按钮、填写表单等
    driver.execute_script(script)

    # 保存为pdf
    pdf_options = {
        'landscape': False,
        'displayHeaderFooter': False,
        'printBackground': True,
        'preferCSSPageSize': True
    }
    result = driver.execute_cdp_cmd('Page.printToPDF', pdf_options)

    with open(output + driver.title + ".pdf", "wb") as f:
        f.write(base64.b64decode(result['data']))

    # 关闭浏览器
    driver.quit()


# 保存所有地址
for url in urls:
    try:
        save_pdf(url)
    finally:
        print("文件保存执行完成：" + url)

print("程序执行完毕")
