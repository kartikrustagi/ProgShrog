#!/usr/bin/python
import socket 
import sys

s=socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
#instead of changing the standard input from trminal, try to do it within the script only
s.bind(('localhost',2001))
f=file('bigfile.txt','r')
sys.stdin=f
while 1:
	data=raw_input(1024)
	if not data : 
		break
	s.sendto(data,('localhost',2000))
print 'hello'
	

s.sendto(data,('localhost',2000))
	
s.close()

	

