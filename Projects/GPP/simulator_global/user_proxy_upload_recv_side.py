#Receving side : Start first
dir_from_which_user_kiosk_will_take_files='/home/kartik/Desktop'
ip_to_bind=''
socket_to_bind=2001

import sys
import base64
import os
import time
from xml.dom.minidom import parse
import socket

s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
s.bind((ip_to_bind,socket_to_bind))
s.listen(1)
(sk,ad)=s.accept()

os.chdir(dir_from_which_user_kiosk_will_take_files)
os.chdir("./proxy_user_upload")#made changes here
num_of_files=sk.recv(1024)
#print num_of_files
i=0

while i < num_of_files:

	#print i
	file_content=''
	
	while True:
		data = sk.recv(1024)
		if data=="END_OF_FILE":
			break	
		file_content=file_content+data
	
	filename=data=sk.recv(1024)
	#print filename
	f=open(filename,'wb')
	f.write(file_content)
	f.close()
	
	i=i+1


s.close()
