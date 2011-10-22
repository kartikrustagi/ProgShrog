#!/usr/bin/python

import smtplib 
import os
import email
from xml.dom.minidom import parse
import gdata.youtube
import gdata.youtube.service
import base64
import gdata.photos.service
import gdata.media
import gdata.geo

dir_from_which_doctors_proxy_will_take_files=#'/home/kartik/Desktop/proxy_doctor_upload' 

server=smtplib.SMTP("smtp.gmail.com",587) #An smtp object to estabilish connection with gmail's smtp server
server.ehlo() # THIS is necessary: Exact purpose still unknown
server.starttls() #Put the conection in TLS ie Transport Layer Security mode, now all messages will be sent encrypted :Required by gmail 
server.ehlo() #Identifying it self to extended SMTP server of gmail: Necessary

ldir=os.listdir(dir_from_which_doctors_proxy_will_take_files)
lendir=len(ldir)
flag=0
os.chdir(dir_from_which_doctors_proxy_will_take_files)
if(lendir!=0):
	for i in range(0,lendir):
		#BEFORE proceding further with the connection estabilishment, we need to extract the kiosk_id feild from the XML, IT will the email  id we  			will use to send messages
		os.system('pwd')
		print ldir[i]
		par=parse(ldir[i]) #This will be replaced by the location of the file in the UPLOAD directory
		if flag==0:
			node=par.getElementsByTagName("kiosk_id") #Doctor's email address in this case
			from_email_id=node[0].firstChild.nodeValue
			node=par.getElementsByTagName("kiosk_id_password") #TODO: ADD THIS TO XML
			from_email_id_password=node[0].firstChild.nodeValue 
			server.login(from_email_id,from_email_id_password) #The purpose of this command is obvious. Will delete the password before showing 				any one the file. ALSO the email id will be that of the kiosk 
			flag=1

		# Till now we are takin only the case where doctor can send an email to only one person at a time, which will be embedded in the 'To' field
		# READY TO SEND MAIL
		# Next task is to get an 'email' object representing the file
		
		f=file(ldir[i],'r')
		msg_obj=email.message_from_file(f) #Now this is a message object. Our next task will be to set the To,From and Subject field
		msg_obj.__setitem__('From',from_email_id)
		node=par.getElementsByTagName("to")
		destination_email_id=node[0].firstChild.nodeValue
		node=par.getElementsByTagName("subject")
		subject=node[0].firstChild.nodeValue

		if destination_email_id == 'youtube':
		
			yt_service = gdata.youtube.service.YouTubeService()
		
			yt_service.email = from_email_id
			yt_service.password = from_email_id_password
			yt_service.source = "Mobile Doctor"
			yt_service.developer_key = "AI39si5Tyq561-8r_jUP6ew6BR2iusHa12WTl-KzBpvCBOZjappX-XadIIWCZQRjhii53okx40u-zvyZJl_MZE63xyY5uhDw5w"
			yt_service.client_id = "ytapi-kanikavats-MobileDoctor-90ndpdag-0"
			yt_service.ProgrammaticLogin()
		
			node=par.getElementsByTagName("details")
			details=node[0].firstChild.nodeValue
		
			# prepare a media group object to hold our video's meta-data
			my_media_group = gdata.media.Group(
			  title=gdata.media.Title(text=subject),
			  description=gdata.media.Description(description_type='plain',
						              text=details),
			  keywords=gdata.media.Keywords(text='mobile, doctor, kioskNet, health'),
			  category=gdata.media.Category(
			      text='Nonprofit',
			      scheme='http://gdata.youtube.com/schemas/2007/categories.cat',
			      label='Nonprofits'),
			  player=None
			)
		
			# prepare a geo.where object to hold the geographical location
			# of where the video was recorded
			node=par.getElementsByTagName("x")
			lattitude=node[0].firstChild.nodeValue
			node=par.getElementsByTagName("y")
			longitude=node[0].firstChild.nodeValue
			where = gdata.geo.Where()
			where.set_location((float(lattitude),float(longitude)))

	 		# create the gdata.youtube.YouTubeVideoEntry to be uploaded
			video_entry = gdata.youtube.YouTubeVideoEntry(media=my_media_group, geo=where)
	
			node=par.getElementsByTagName("upload_file_name")
			attached_file_name=node[0].firstChild.nodeValue
			node=par.getElementsByTagName("upload_file")				
			attached_file=node[0].firstChild.nodeValue
			attached_file=base64.decodestring(attached_file)
			fa=file(attached_file_name,'wb') #TODO: Binary mode
			fa.write(attached_file)
			fa.close()
		
			# set the path for the video file binary
			video_file_location = './%s'%attached_file_name

			new_entry = yt_service.InsertVideoEntry(video_entry, video_file_location)
			os.system('rm ./%s'%attached_file_name)
					
		elif destination_email_id == 'picasa':
	
			gd_client = gdata.photos.service.PhotosService()
			gd_client.email = None
			gd_client.password = None
			gd_client.source = 'Mobile Doctor'
			gd_client.ProgrammaticLogin()
		
			album = gd_client.InsertAlbum(title='Mobile Doctor', summary='Some of the medical cases which needs YOUR help')
			
			node=par.getElementsByTagName("upload_file_name")
			attached_file_name=node[0].firstChild.nodeValue
			node=par.getElementsByTagName("upload_file")				
			attached_file=node[0].firstChild.nodeValue
			attached_file=base64.decodestring(attached_file)
			fa=file(attached_file_name,'wb') #TODO: Binary mode
			fa.write(attached_file)
			fa.close()
			
			node=par.getElementsByTagName("details")
			details=node[0].firstChild.nodeValue

			username = 'kashes911'
			filename = './'+attached_file_name
			album_url = '/data/feed/api/user/%s/albumid/%s' % (username, album.gphoto_id.text)
			print album_url
			photo = gd_client.InsertPhotoSimple(album_url, subject , details, filename, content_type='image/jpg')
			os.system('rm ./%s'%attached_file_name)
	
		else:
			msg_obj.__setitem__('To',destination_email_id)
			msg_obj.__setitem__('Subject',subject)
			server.sendmail(from_email_id,destination_email_id,msg_obj.as_string())
		
		os.remove(ldir[i])
	
	
server.quit()


