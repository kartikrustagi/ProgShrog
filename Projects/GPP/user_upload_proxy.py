#!/usr/bin/python
dir_from_which_users_proxy_will_take_files='/home/kartik/Desktop/proxy_user_upload' #Kanika yaha pai tumharey user ke proxy ke upload directory ayegee
import smtplib 
import os
import email
from xml.dom.minidom import parse

server=smtplib.SMTP("smtp.gmail.com",587) #An smtp object to estabilish connection with gmail's smtp server
server.ehlo() # THIS is necessary: Exact purpose still unknown
server.starttls() #Put the conection in TLS ie Transport Layer Security mode, now all messages will be sent encrypted :Required by gmail 
server.ehlo() #Identifying it self to extended SMTP server of gmail: Necessary

os.chdir('/home/kartik/Desktop/proxy_user_upload')
ldir=os.listdir(dir_from_which_users_proxy_will_take_files)
lendir=len(ldir)
flag=0
if(lendir!=0):
	for i in range(0,lendir):
		#BEFORE proceding further with the connection estabilishment, we neserver.quit()ed to extract the kiosk_id feild from the XML, IT will the 			email id we will use to send messages
		print ldir[i]
		par=parse(ldir[i]) #This will be replaced by the location of the file in the UPLOAD directory
		if flag==0:
			node=par.getElementsByTagName("kiosk_id")
			from_email_id=node[0].firstChild.nodeValue
			node=par.getElementsByTagName("kiosk_id_password") #TODO: ADD THIS TO XML
			from_email_id_password=node[0].firstChild.nodeValue 
			server.login(from_email_id,from_email_id_password) #The purpose of this command is obvious. Will delete the password before showing 				any one the file. ALSO the email id will be that of the kiosk 
			flag=1
			#Email_id of the receiver, which will be doctor in case of complaint form and doctor+Hospital in case of emergency form. Will mail 				this email to every one in the list

		# READY TO SEND MAIL
		# Next task is to get an 'email' object representing the file
		f=file(ldir[i],'r')
		msg_obj=email.message_from_file(f) #Now this is a message object. Our next task will be to set the To,From and Subject field
		msg_obj.__setitem__('From',from_email_id)
		node=par.getElementsByTagName("form_id")
		form_id = node[0].firstChild.nodeValue

		if form_id == '3':
	
			msg_obj.__setitem__('Subject','Emergency')
			node=par.getElementsByTagName("destination_id_doctor")
			destination_id_doctor=node[0].firstChild.nodeValue
			msg_obj.__setitem__('To',destination_id_doctor)
			server.sendmail(from_email_id,destination_id_doctor,msg_obj.as_string())
			node=par.getElementsByTagName("destination_id_hospital")
			destination_id_hospital=node[0].firstChild.nodeValue
			msg_obj.__setitem__('To',destination_id_hospital)
			server.sendmail(from_email_id,destination_id_hospital,msg_obj.as_string())
			
	
		if form_id == '1':

			msg_obj.__setitem__('Subject','Complaint')
			node=par.getElementsByTagName("destination_id_doctor")
			destination_id_doctor=node[0].firstChild.nodeValue
			msg_obj.__setitem__('To',destination_id_doctor)
			server.sendmail(from_email_id,destination_id_doctor,msg_obj.as_string())
			
		
		os.remove(ldir[i]) 
		

server.quit()

