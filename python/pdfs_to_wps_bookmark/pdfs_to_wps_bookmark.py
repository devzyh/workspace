import os

import PyPDF2


# 获取页数
def get_pdf_page_count(file_path):
    print(file_path)
    with open(file_path, 'rb') as file:
        reader = PyPDF2.PdfReader(file)
        num_pages = len(reader.pages)
    return num_pages


file_dir = input("请输入文件夹：")
if not file_dir.endswith(os.sep):
    file_dir += os.sep

entries = os.listdir(file_dir)
files = [entry for entry in entries if os.path.isfile(os.path.join(file_dir, entry)) and entry.endswith(".pdf")]

index = 0
xml = """<?xml version = '1.0' encoding='UTF-8'?>
<BOOKMARKS>
    <INFO PRODUCER="WPS PDF"/>
"""
item_format = """    <ITEM OPEN="1" INDENT="1" PARMA="-9.910931,799.436646,1.816176,0.000000" PAGE="@page" COLOR="4278190080" ZOOMMODE="0" FONTSTYLE="0" NAME="@name"/>\n"""

for f in files:
    count = get_pdf_page_count(file_dir + f)
    item = item_format.replace("@page", str(index))
    item = item.replace("@name", f.replace(".pdf", ""))
    xml += item
    index += count

xml += "</BOOKMARKS>"

with open(file_dir + "WPS PDF Bookmarks.xml", 'w', encoding="UTF-8") as file:
    file.write(xml)

input("按下任意键退出程序")
