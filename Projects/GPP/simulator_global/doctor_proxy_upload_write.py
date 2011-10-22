dir_from_which_user_kiosk_will_take_files='/home/kartik/Desktop'

doctor_proxy_ipaddress=''
doctor_proxy_socket=2002

import sys
import base64
import os
import time
import socket
from xml.dom.minidom import parse

s=socket.socket(socket.AF_INET,socket.STREAM)#TODO
s.connect((doctor_proxy_socket,doctor_proxy_ipaddress))

list_of_dir=s.recv(1024)#TODO

data=""

os.chdir(dir_from_which_user_kiosk_will_take_files)
os.chdir("./proxy_doctor_upload")

for j in range(0,list_of_dir):

	while True:
		line=s.recv(1024)#TODO
		if line=="END_OF_LINE":
			break
		else:
			data=data+line
		
		filename=s.recv(1024)#TODO
		
		f=open(filename,'wb')
	        f.write(data)
		f.close()

s.close()		
