#Sending Side
dir_from_which_user_kiosk_will_take_files='/home/kartik/Desktop'
user_proxy_ip_address=''
user_proxy_socket=2001

import sys
import base64
import os
import time
from xml.dom.minidom import parse
import socket

s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
s.connect((user_proxy_ip_address,user_proxy_socket))

os.chdir(dir_from_which_user_kiosk_will_take_files)
os.chdir("./user_update/upload")#made changes here

list_of_dir=os.listdir("./")
len_of_dir=len(list_of_dir)

s.send('%s'%len_of_dir)

if len_of_dir > 0:
	
	i=0
	
	while i< len_of_dir:
	
		f=open(list_of_dir[i],'rb')
		
		while True:
			line=f.readline()
			if len(line)==0:
				break
			s.send(line)
		f.close()
		
		s.send("END_OF_FILE")
		s.send(list_of_dir[i]) #During upload operations file names are not altered 
		
		os.remove(list_of_dir[i])
		i = i +1			

s.close()
