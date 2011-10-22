#!/usr/bin/python
import socket 
s=socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
#instead of changing the standard input from trminal, try to do it within the script only
s.bind(('localhost',2000))
f=file('temp.txt','w')
while 1:
	data,address=s.recvfrom(1024)
	if not data:
		break
	f.write(data)
	
f.close()
s.close()

