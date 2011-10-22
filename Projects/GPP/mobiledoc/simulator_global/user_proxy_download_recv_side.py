dir_from_which_user_kiosk_will_take_files='/home/kartik/Desktop'
user_proxy_ip_address=''
user_proxy_socket='2001'

import sys
import base64
import os
import time
from xml.dom.minidom import parse
import socket

s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
s.connect(user_proxy_ip_address,user_proxy_socket)

os.chdir(dir_from_which_user_kiosk_will_take_files)
os.chdir("./user_update/download")#made changes here

num_of_files=s.recv(1024)
i=0

while i < num_of_files:

	file_content=''
	
	while True:
		data = s.recv(1024)
		if data=="END_OF_FILE":
			break	
		file_content=file_content+data
	
	filename=data=sk.recv(1024)
	
	f=open(filename,'wb')
	f.write(file_content)
	f.close()
	
	i=i+1

s.close()





