

import socket
import asyncore
class clientsocket(asyncore.dispatcher):
    def __init__(self,port):
        asyncore.dispatcher.__init__(self)
        hostname=socket.gethostname()
        self.create_socket(socket.AF_INET,socket.SOCK_STREAM)
        self.connect((hostname,port))
        print"0"
    def handle_connect(self):
        print"connected"
     
    def handle_write(self):
        print 'ready to send'
        dat="<xml><TYPE>LOGIN</TYPE><LOGIN>1</LOGIN></xml>"+"j"
        self.sendall(dat)
        data="<xml><TYPE>ANS</TYPE><ANS><a1>buck</a1><a2>garuda</a2><a3>air deccan</a3><a4>old</a4><a5>asdasd</a5><a6>asdas</a6></ANS><TID>1</TID><PID>1</PID></xml>" +"j"
        
        r=raw_input()
        self.sendall(data)
        d=raw_input()
        data3="<xml><TYPE>NAVIGATION</TYPE><NAVIGATION>top</NAVIGATION><TID>1</TID><PID>1</PID></xml>"+"j"
        self.sendall(data3)
        o=raw_input()
        
        
    def handle_read(self):
        pass
        #print 'receiving'
        #data=self.recv(1000000)
        #print data
        #dat="<xml><TYPE>ANS</TYPE><ANS><a1>buck</a1><a2>garuda</a2><a3>air deccan</a3><a4>old</a4><a5>asdasd</a5><a6>asdas</a6></ANS></xml>"    
        #self.sendall(dat)
        
        #print 'in recv'
        #data=self.recv(1000000)
        #print data
        #dat="<xml><TYPE>ANS</TYPE><ANS><a1>buck</a1><a2>garuda</a2><a3>air deccan</a3><a4>old</a4><a5>asdasd</a5><a6>asdas</a6></ANS></xml>"    
        #self.sendall(dat)        
        #r=raw_input()
        #self.handle_close()
    def handle_close(self):
        pass
clientsocket(2000)
asyncore.loop()
        
        
        
        
