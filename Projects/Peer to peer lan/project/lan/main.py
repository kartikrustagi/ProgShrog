#!/usr/bin/python
import os
import socket
import string
import sys

peers=[]
STATUS=0


online='<pr1>I am online'
reply="<pr2>I am online too"
close="<Close>I am going offline"


class main:
	
	def __init__(self):
		pass
	
	def update_peers(self,data1):
		global STATUS
		global peers
		z=string.find(data1,'<pr1>')
		if z!=-1:
			client.sendto(reply,addrs)
			peers.append(addrs)
                	prlength=len(peers)
			print peers," are online"
		elif z==-1:
			k=string.find(data1,'<pr2>')
			if k!=-1:
				peers.append(addrs)
				prlength=len(peers)
				print peers," are online"
		
		
	def initiate_find(self):
		global STATUS
		global peers
		print 'Enter the file you want to find.'
		self.strn=raw_input('->')
		self.strn='<srr>'+self.strn
		flag=0
		for i in range(0,len(peers)+1):
			client.sendto(self.strn,peers[i])
			rep,addrs=client.recvfrom(1024)
			temp=rep.split('<srs>')
			print 'Reply is ',rep
			print 'Reply is from ',addrs
			rep=temp[1]
			if rep=='Yes':
				flag=1
				break
		if flag==0:
			print 'File not present in peers\'s directory.'
		else:
			m.filerecieve(addrs)
		
			
		
	def ifsearch(self,strn):
		#str is the string that has to be searched
		global STATUS
		global peers
		self.strn=strn
		print 'String to be searched is ',self.strn
		if STATUS==1:
			client.sendto('<srs>No',addrs)
			print 'STATUS is busy, neglecting search request'
			pass
			
		STATUS=1
		os.chdir(self.path)
		ldir=os.listdir(self.path)
		flag=0
		lendir=len(ldir)
		if(lendir!=0):
			for i in range(0,lendir):
				if ldir[i]==self.strn:
					flag=1
					print 'File found'
					client.sendto('<srs>Yes',addrs)
					break
		
		if(lendir==0 or flag==0):
			print 'File not found'
			client.sendto('<ft>No',addrs)
		
		STATUS=0
				
		
		
	def direc(self):
		global STATUS
		global peers
		login=os.getlogin()
		self.path='/home/'+login+'/p2p'
		#Now to check whether the directory already exist or not
		ldir=os.listdir('/home/'+login)
		flag=0
		lendir=len(ldir)
		if(lendir!=0):
			for i in range(0,lendir):
				if ldir[i]=='p2p':
					flag=1
					print 'Directory already created'
					break
		
		if(lendir==0 or flag==0):
			os.mkdir(self.path)
			print 'Directory created at ',self.path
			
				
	
	def filesend(self,strn):
		print 'Sending file'
		global STATUS
		global peers
		STATUS=1
		self.strn=strn
		fstr=self.path+'/'+self.strn
		f=file(fstr,'r')
		sys.stdin=f
		while 1:
			data=raw_input()
			if not data : 
				break
			client.sendto(data,addrs)
		client.sendto('<End>',addrs)
		STATUS=0
		print 'File sent'
		print '\n'
		
	
	def filerecieve(self,addr):
		print 'Recieving file....'
		global STATUS
		global peers
		temp=self.strn.split('<srr>')
		print 'Temp is ',temp
		print 'Peer[i] is', addr 
		client.sendto('<Begin>'+temp[1],addr)
		STATUS=1
		os.chdir(self.path)
		temp=self.strn.split('<srr>')
		f=file(temp[1],'w')
		while 1:
			data,address=client.recvfrom(1024)
			if data=='<End>':
				break
			if not data:
				break
			f.write(data)
		
		f.close()
		STATUS=0
		print 'File recieved'
		print '\n'

			
		
m=main()
m.direc()
client=socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
client.bind((sys.argv[1],2000))	
			
port=client.getsockname()

for i in range(0,10):
	ladr=('192.168.1.%d'%i,2000)
	if ladr==port:
        	continue
	client.sendto(online,ladr)
	
	
while 1:
	try:
		data1,addrs=client.recvfrom(1024)
		
		if(string.find(data1,'<pr1>')==0 or string.find(data1,'<pr2>')==0):
			m.update_peers(data1)
		elif(string.find(data1,'<srr>')==0):
			#string to be searched (without tags)
			l=data1.split('<srr>')
			print 'Incoming request for search of file ',l[1]
			m.ifsearch(l[1])	
		elif(string.find(data1,'<Begin>')==0):
			temp=data1.split('<Begin>')
			m.filesend(temp[1])
		elif(string.find(data1,'<Close>')==0):
			peers.remove(addrs)
			print peers," are online"
				
		
		
	except KeyboardInterrupt:
		STATUS=1
		print 'Enter the operation you want to do.'
		print '1.Search \n2.Close Connection \n'
		data1=raw_input()
		if data1=='1':
			m.initiate_find()
		elif data1=='2':
			for i in range(0,len(peers)):
				client.sendto(close,peers[i])
			client.close()
			sys.exit()
					
			
		
