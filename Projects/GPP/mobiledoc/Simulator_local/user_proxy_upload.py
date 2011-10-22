dir_from_which_user_kiosk_will_take_files=#'/home/kartik/Desktop'
import sys
import base64
import os
import time
from xml.dom.minidom import parse

os.chdir(dir_from_which_user_kiosk_will_take_files)
os.chdir("./user_update/upload")

list_of_dir=os.listdir("./")
len_of_dir=len(list_of_dir)

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
				break
			data = data + line
		f.close()

		os.chdir(dir_from_which_user_kiosk_will_take_files)
		os.chdir("./proxy_user_upload")
		
		f=open(filename,'wb')
	        f.write(data)
		f.close()
		
		os.chdir(dir_from_which_user_kiosk_will_take_files)
		os.chdir("./user_update/upload")
		os.remove(filename)
		
		i = i +1	
