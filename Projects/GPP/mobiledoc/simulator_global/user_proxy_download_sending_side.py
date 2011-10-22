#Sending side: which will be proxy here :Start first
dir_from_which_user_kiosk_will_take_files='/home/kartik/Desktop'
ip_to_bind=''
socket_to_bind='2001'

import sys
import base64
import os
import time
from xml.dom.minidom import parse
import socket

s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
s.bind((ip_to_bind,socket_to_bind))
s.listen(1)
sk,ad=s.accept()

os.chdir(dir_from_which_user_kiosk_will_take_files)
os.chdir("./proxy_user_download")#made changes here

list_of_dir=os.listdir("./")
len_of_dir=len(list_of_dir)

sk.send(len_of_dir)

if len_of_dir > 0:
	
	i=0
	
	while i< len_of_dir:
	
		
		f=open(list_of_dir[i],'rb')
		
		while True:
			line=f.readline()
			if len(line)==0:
				break
			sk.send(line)
			
		f.close()

		sk.send("END_OF_FILE")
		sk.send(list_of_dir[i])
		os.remove(list_of_dir[i])
		i = i +1	


s.close()
