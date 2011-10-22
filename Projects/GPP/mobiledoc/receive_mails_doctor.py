#! /etc/bin/python

dir_to_save_the_mails=#'/home/kartik/Desktop/proxy_doctor_download'
 
import imaplib

ser=imaplib.IMAP4_SSL('imap.gmail.com')
ser.login('shashirustagi2008@gmail.com','skrustagi')
ser.select('INBOX')
t=ser.search(None,'ALL')#Extract individual Mail numbers from this 
l=str.split(t[1][0]) #List of all message ID in inbox
i=1

import os 
os.chdir(dir_to_save_the_mails)

while i <= len(l):
	
	r, data = ser.fetch('%s'%(i), '(UID BODY[TEXT])')
	print i
	print data[0][1]
	f = file('mail_%s'%i, 'w')
	f.write(data[0][1])
	f.close()
	i=i+1 


