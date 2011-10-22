#!/usr/bin/python
#We will have tags to differentiate messages from file transfer,IF REQUIRED
#functions....
#f1=will recieve all the data strings and redirect data strings on the basis of tags to approporiate tags
#f2=will handle new connections....store sockets/ips of system online in a list/tupple
#f3=will handle search request,will let the peer which searched that it has/not the file requested... the requesting peer will wait till this peer replies yes or no...
#f4= if it has the item...then open a seperate tcp client for communication with that peer /or maybe use the same UDP socket.....setting a semaphore mechanism...that is havint a variable set to 1 when it is busy..
#f5= also have the mechanism for this peer to initiate its own search...

import socket
import time

s=socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
s.bind(('localhost',2001))
data='<np>I am online'

for i in range(1,10):
	x=2000+i
	if x==2001:
		continue
	s.sendto(data,('localhost',x))

while 1:
	data2,address=s.recvfrom(1024)
	print data2
	

s.close()
	
