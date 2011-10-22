dir_from_which_user_kiosk_will_take_files='/home/kartik/Desktop'

import sys
import base64
import os
import time
import socket
from xml.dom.minidom import parse

s=socket.socket(socket.AF_INET,socket.STREAM)#TODO
s.bind(('192.168.1.4',2002))
s.listen(5)
s_socket,s_address=s.accept()

os.chdir(dir_from_which_user_kiosk_will_take_files)
os.chdir("./doctor_update/upload")#made changes here

list_of_dir=os.listdir("./")
len_of_dir=len(list_of_dir)

s_socket.send(len_of_dir)

if len_of_dir > 0:
	
	i=0
	
	while i< len_of_dir:
	
		par=parse(list_of_dir[i])
		node=par.getElementsByTagName("filename")
		filename=node[0].firstChild.nodeValue
		
		
		f=open(list_of_dir[i],'rb')
		data=""
		
		while True:
			line=f.readline()
			if len(line)==0:
				s_socket.send("END_OF_FILE")#TODO
				break
			s_socket.send(line)#TODO
			data = data + line
			
		f.close()
		
		s_socket.send(filename)#TODO
                os.remove(filename)
                		
		i = i +1	
s.close()
