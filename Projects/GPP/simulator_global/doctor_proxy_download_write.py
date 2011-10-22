dir_from_which_user_kiosk_will_take_files='/home/kartik/Desktop'#

doctor_proxy_ipaddress=''
doctor_proxy_socket=2000

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

for j in range(0,list_of_dir):

	while True:
		line=s.recv(1024)#TODO
		if line=="END_OF_LINE":
			break
		else:
			data=data+line

	filename=s.recv(1024)#TODO
	form_id=s.recv(1024)


	if form_id == "1":
		print"complaint            
		os.chdir(dir_from_which_user_kiosk_will_take_files)
		os.chdir("./doctor_update/download/complaint")#made changes here
     	
        	f=open(filename,'wb')
        	f.write(data)
		f.close()
		print "file written"
		os.chdir(dir_from_which_user_kiosk_will_take_files)
		os.chdir("./proxy_doctor_download")#made changes here
		os.remove(list_of_dir[i])
		print filename
		print"removed"
				
	elif form_id=="3":
		print"emergency"
 		os.chdir(dir_from_which_user_kiosk_will_take_files)
 		os.chdir("./doctor_update/download/emergency")#made changes here
				
		f=open(filename,'wb')
        	f.write(data)
		f.close()
		print "file written"
		os.chdir(dir_from_which_user_kiosk_will_take_files)
		os.chdir("./proxy_doctor_download")
		os.remove(list_of_dir[i])
		print filename
		print"removed"
				
	elif form_id=="5":
      
		os.chdir(dir_from_which_user_kiosk_will_take_files)
		os.chdir("./doctor_update/download/other")#made changes here
				
		f=open(filename,'wb')
        	f.write(data)
		f.close()
        	print "file written"
		os.chdir(dir_from_which_user_kiosk_will_take_files)#made changes here
		os.chdir("./proxy_doctor_download")
		os.remove(list_of_dir[i])
		print filename
		print"removed"
s.close()
