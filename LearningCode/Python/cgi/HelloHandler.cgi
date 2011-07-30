#!/usr/bin/python
import cgi

formHandle = cgi.FieldStorage()
print "Content-Type: text/plain \n\n"
print "UserName sent is: "+formHandle['un'].value+"\n\n"