dir_from_which_user_kiosk_will_take_files=#'/home/kartik/Desktop'

import sys
import base64
import os
import time
from xml.dom.minidom import parse

os.chdir(dir_from_which_user_kiosk_will_take_files)
os.chdir("./proxy_doctor_download")

list_of_dir=os.listdir("./")
len_of_dir=len(list_of_dir)

if len_of_dir > 0:
	
	i=0
	
	while i< len_of_dir:
	
		par=parse(list_of_dir[i])
		
		node=par.getElementsByTagName("filename")
		filename=node[0].firstChild.nodeValue
		
		node=par.getElementsByTagName("form_id")
		form_id=node[0].firstChild.nodeValue
		
		f=open(list_of_dir[i],'rb')
		data=""
		
		while True:
			line=f.readline()
			if len(line)==0:
				break
			data = data + line
		f.close()
		
                
                if form_id == "1":
                        print"complaint"
                        os.chdir(dir_from_which_user_kiosk_will_take_files)
                        os.chdir("./doctor_update/download/complaint")
                        
			f=open(filename,'wb')
		        f.write(data)
			f.close()
			print "file written"
			os.chdir(dir_from_which_user_kiosk_will_take_files)
			os.chdir("./proxy_doctor_download")
			os.remove(list_of_dir[i])
			print filename
			print"removed"
			
		elif form_id=="3":
		 	print"emergency"
		 	os.chdir(dir_from_which_user_kiosk_will_take_files)
		 	os.chdir("./doctor_update/download/emergency")
			
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
			os.chdir("./doctor_update/download/other")
			
			f=open(filename,'wb')
		        f.write(data)
			f.close()
		        print "file written"
			os.chdir(dir_from_which_user_kiosk_will_take_files)
			os.chdir("./proxy_doctor_download")
			os.remove(list_of_dir[i])
			print filename
			print"removed"
		i = i +1	
