import socket
import asyncore
import MySQLdb
from xml.dom import minidom

#global channel
#global tscore
#global tid
#global bpid
#global cpid #just to show whether its
#Wglobal cid #category id
#global pid
#global qids
#global qstatus
#global junkbit
#global ANS
#global parray
#global num_of_clients_on_planet
#global planet_num #aka planet name
#global target_planet
channel={}
ANS={}
cpid=0
tscore=0
junkbit='a'
target_planet=0
planet_rec={}
tid=0
cid=0
pid=0
qids=()
pscore=0
qstatus=()
parray={}
num_of_clients_on_planet={}
num_of_crossfire_player=0

#RELOGIN
#loginfn will be called when the server recvs TID from client
def loginfn(channel,message):
        global pid
        global cid
        global bpid
        global num_of_clients_on_planet
        global channel
        global target_planet
        global tid
        global parray
        global planet_rec
        global cpid
        global tscore
        print 'in loginfn'
        t_id = message.getElementsByTagName('LOGIN')
        tid=t_id[0].firstChild.nodeValue
        print tid
        parray[tid]=channel
        print parray[tid]
        con.execute("select * from Team where tid='%s'" %tid)#type1
        result=con.fetchall()
        print 'type1'
        print result#single element tuple consisting of field data types ((1,2,'abc'),)
        result=result[0]
        tscore=result[1]
        print 'Tscore is',tscore
        bpid=result[2]
        print 'bpid is',bpid
        #con.execute('select * from Planet')
        #result2=con.fetchall()
        cpid=result[3]
        print 'cpid is',cpid
        cid=result[4]    
        print 'cid is',cid
        
        if (cpid==bpid):
            pid=bpid
            num_of_clients_on_planet[pid]=1    
        else :
            pid=cpid
            num_of_clients_on_planet[pid]=num_of_clients_on_planet[pid]+1                    

        print num_of_clients_on_planet[pid]


        
        #TIDS on A PID
        flag=0
        for value in planet_rec.keys() :
                if (pid==value) :
                        flag=1

        if (flag==1) :
                lst=planet_rec[pid]
                lst.append(tid)

        else :
                lst=[tid]
                planet_rec={pid : lst}
                        


        print 'pid is', pid
                       
        
        #con.execute("update Team set cid='%s' where tid='%s'" %(cid,tid))
        con.execute("update status set planet_status=1 where tid='%s' and pid='%s'" %(tid,pid))
        
def qids():
        global pid
        global cid
        global qids
        print 'pid is',pid
        con.execute("select * from questionair where pid='%s' and cid='%s'" %(pid,cid))#type 1 again
        qids=con.fetchall()
        print qids
        qids=qids[0]
        qids=qids[0:6]#coz 6th is crossfire 
        print 'dooone'
        print qids
        
        
def questionsend():
        
        global qids
        global tid
        global junkbit
        global pid
        global pscore
        global tscore
        #global channel
        global parray
        con.execute("select question from question where q_id='%s' or q_id='%s' or q_id='%s' or q_id='%s' or q_id='%s' or q_id='%s'"%(qids[0],qids[1],qids[2],qids[3],qids[4],qids[5]))
        questiontupple=con.fetchall()#type2
        #question_list=list(questiontupple)
        question_list=questiontupple
        print 'type2'
        print question_list#returns a tuple of consisting of single element tuple((abc,)(bcd,)(def,))
        print question_list[0][0]
        print question_list[1][0]
        print question_list[2][0]
        print question_list[3][0]
        print question_list[4][0]
        print question_list[5][0]
        
        #send these questions as xml tags D
        #send tids,pids etc along with this D
        print 'tid is', tid
        print 'pid is', pid
        
        con.execute("select server_name from Planet where pid=%s"%pid)#type 3
        server=con.fetchall()#returns a single element tuple ((troika1,),)
        print server
        server=server[0][0]
        print server
        #retrieve tscore n pscore frm database
        con.execute("select p_score from status where tid='%s' and pid='%s'"%(tid,pid))
        pscore=con.fetchall()
        pscore=pscore[0][0]
        print 'a'
        con.execute("select tscore from Team where tid='%s'"%tid)
        tscore=con.fetchall()
        tscore=tscore[0][0]
        tpid_xml="<xml><TID>%s</TID><PID>%s</PID><Tscore>%s</Tscore><pscore>%s</pscore></xml>"%(tid,pid,tscore,pscore)
        parray[tid].sendall(tpid_xml+junkbit)
        server_xml="<xml><TYPE>SERVER</TYPE><server>%s</server></xml>"%server
        print 'fg'
        parray[tid].sendall(server_xml+junkbit)
        ques_xml="<xml><TYPE>QUES</TYPE><q1>%s</q1><q2>%s</q2><q3>%s</q3><q4>%s</q4><q5>%s</q5><q6>%s</q6></xml>"%(question_list[0][0],question_list[1][0],question_list[2][0],question_list[3][0],question_list[4][0],question_list[5][0])
        parray[tid].sendall(ques_xml+junkbit)    
        print '2done'


def questioncheck(message):
        #   will recieve answers as xml tags D
        print 'in question check'
        global qids
        global tid
        global pid
        global qstatus
        global ANS
        ans= message.getElementsByTagName('ANS')
        t_id= message.getElementsByTagName('TID')
        tid=t_id[0].firstChild.nodeValue
        p_id= message.getElementsByTagName('PID')
        pid=p_id[0].firstChild.nodeValue
       
        print ans 
        
        ANS[0]=ans[0].childNodes[0].nodeValue
        ANS[1]=ans[0].childNodes[1].nodeValue
        ANS[2]=ans[0].childNodes[2].nodeValue
        ANS[3]=ans[0].childNodes[3].nodeValue
        ANS[4]=ans[0].childNodes[4].nodeValue
        ANS[5]=ans[0].childNodes[5].nodeValue
        print ANS
        
        con.execute("select ans from question where q_id='%s' or q_id='%s' or q_id='%s' or q_id='%s' or q_id='%s' or q_id='%s'"%(qids[0],qids[1],qids[2],qids[3],qids[4],qids[5]))
        anstupple=con.fetchall()#type2
        print '3done till here'
        print anstupple 
        con.execute("select q_status from status where tid='%s' and pid='%s'" %(tid,pid))
        qstatus=con.fetchall()#type 3
        qstatus=qstatus[0][0]
        print qstatus

        ANS[0]=ANS[0].lower()
        ANS[1]=ANS[1].lower()
        ANS[2]=ANS[2].lower()
        ANS[3]=ANS[3].lower()
        ANS[4]=ANS[4].lower()
        ANS[5]=ANS[5].lower()
        anstupple[0][0]=anstupple[0][0].lower()
        anstupple[1][0]=anstupple[1][0].lower()
        anstupple[2][0]=anstupple[2][0].lower()
        anstupple[3][0]=anstupple[3][0].lower()
        anstupple[4][0]=anstupple[4][0].lower()
        anstupple[5][0]=anstupple[5][0].lower()
        
        if ANS[0]==anstupple[0][0] and qstatus[0]==0 :
                qstatus[0]=1
        if ANS[1]==anstupple[1][0] and qstatus[1]==0 :
                qstatus[1]=1        
        if ANS[2]==anstupple[2][0] and qstatus[2]==0 :
                qstatus[2]=1
        if ANS[3]==anstupple[3][0] and qstatus[3]==0 :
                qstatus[3]=1 
        if ANS[4]==anstupple[4][0] and qstatus[4]==0 :
                qstatus[4]=1
        if ANS[5]==anstupple[5][0] and qstatus[5]==0 :
                qstatus[5]=1

        con.execute("update status set q_status='%s' where tid='%s' and pid='%s'"%(qstatus,tid,pid))

def evaluate():
        #pscore will be updated every time acc to the qstatus   D
        global qids
        global cpid
        global pid
        global tid
        global pscore
        global qstatus
        global tscore
        global junkbit
        global parray
        con.execute("select points from question where q_id='%s' or q_id='%s' or q_id='%s' or q_id='%s' or q_id='%s' or q_id='%s' " %(qids[0],qids[1],qids[2],qids[3],qids[4],qids[5]))
        points=con.fetchall()
        pscore=qstatus[0]*points[0][0] +  qstatus[1]*points[1][0] + qstatus[2]*points[2][0] + qstatus[3]*points[3][0] + qstatus[4]*points[4][0] + qstatus[5]*points[5][0]
        
        con.execute("select p_score from status where tid='%s' and pid='%s'"%(tid,pid))
        pscore_previous=con.fetchall()
        pscore_previous=pscore_previous[0][0]
        con.execute("select tscore from team where tid='%s'"%tid)
        tscore=con.fetchall()
        tscore=tscore[0][0]
        tscore = tscore + pscore - pscore_previous
        
        con.execute("update status set p_score='%s' where tid='%s' and pid='%s'"%(pscore,tid,pid))
        con.execute("update Team set tscore='%s' where tid='%s' and pid='%s'"%(tscore,tid,pid))


        #send tscore,pscore(for evaluation by client)D
        eval_xml="<xml><TYPE>EVALSCORE</TYPE><TID>%s</TID><PID>%s</PID><Tscore>%s</Tscore><pscore>%s</pscore></xml>"%(tid,pid,tscore,pscore)
        parray[tid].sendall(eval_xml+junkbit)
        #SEND QSTATUS
        #con.execute("select q_status from status where tid='%s' and pid='%s'" %(tid,pid))
        #qstatus=con.fetchall()#type 3
        #qstatus=qstatus[0][0]
        qstatus_xml="<xml><TYPE>QSTATUS</TYPE><qstatus>%s</qstatus></xml>"%qstatus
        parray[tid].sendall(qstatus_xml+junkbit)
        #either loop(incase he wants to answer the remaining questions) or recieve navigation tag  
        
def crossfire(message):
        global qstatus
        global tid
        global pid
        global junkbit
        global tscore
        global planet_rec
        global num_of_crossfire_players
        #doubt qstatus
        num_of_crossfire_players=0
        lst=planet_rec[pid]
        for value in range (0,len(lst)-1) :
                        cross_xml="<xml><TYPE>crossfire</TYPE></xml>"
                        con.execute("select q_status from status where tid='%s' and pid='%s'"%(lst[value],pid))
                        qstatus=con.fetchall()
                        qstatus=qstatus[0]
                        if qstatus[6]==0 :
                                num_of_crossfire_players=num_of_crossfire_players+1
                                parray[lst[value]].sendall(cross_xml+junkbit) #send flash notifictn of crossfire

                #recieve the answr 0 or 1
                #evaluating crossfire
                #evaluate pscore and tscore


def crossans(message) :

        global qstatus
        global tid
        global pid
        global junkbit
        global tscore
        global pscore     
        global num_of_crossfire_players
        global parray
        
        crossans = message.getElementsByTagName('CROSS_ANS')
        cross_ans = crossans[0].firstChild.nodeValue
        t_id= message.getElementsByTagName('TID')
        tid=t_id[0].firstChild.nodeValue
        p_id= message.getElementsByTagName('PID')
        pid=p_id[0].firstChild.nodeValue
        con.execute("select p_score from status where tid='%s' and pid='%s'"%(tid,pid))
        pscore_previous=con.fetchall()
        pscore_previous=pscore_previous[0][0]
        con.execute("select tscore from team where tid='%s'"%tid)
        tscore=con.fetchall()
        tscore=tscore[0][0]

        if (cross_ans)==1 :
                num_of_crossfire_players=(num_of_crossfire_players-1)       
                pscore_previous=con.fetchall()
                pscore = pscore_previous[0] + 30
                tscore = tscore + 30
                #update  database
                con.execute("update status set p_score='%s' where tid='%s' and pid='%s'"%(pscore,tid,pid))
                con.execute("update Team set tscore='%s' where tid='%s' and pid='%s'"%(tscore,tid,pid))
                tpid_xml="<xml><TID>%s</TID><PID>%s</PID><Tscore>%s</Tscore><pscore>%s</pscore></xml>"%(tid,pid,tscore,pscore)
                parray[tid].sendall(tpid_xml+junkbit)
        
        lst=planet_rec[pid]
        for value in range (0,len(lst)-1) :
                        cross_xml="<xml><TYPE>crossback</TYPE></xml>"
                        parray[lst[value]].sendall(cross_xml+junkbit)



        con.execute("select q_status from status")
        result2=con.fetchone()
        qstatus=list(result2)
        qstatus[6]=1
        "".join(qstatus)
        con.execute("update status set q_status='%s'" %(qstatus))
                

           
                #check whether no of clients are < 2 ......leave the crossfire......??????????????i
                #mpliment this by compairing the answer and stop as soon as server recieves a correct answer


def navigation(message):


        global pid
        global parray
        global cpid
        global num_of_clients_on_planet
        global target_planet
        global planet_rec
        global tid
        
        num_of_clients_on_planet[pid]=(num_of_clients_on_planet[pid]-1)
        t_id= message.getElementsByTagName('TID')
        tid=t_id[0].firstChild.nodeValue
        p_id= message.getElementsByTagName('PID')
        pid=p_id[0].firstChild.nodeValue
       

        #REMOVING RECORD OF THIS TID FROM planet_rec dict
        for i in range (0,(len(panet_rec[pid])-1)) :
                if planet_rec[pid][i]==tid :
                        n=i
                        break

        del planet_rec[pid][n]



        

        navigation_id = message.getElementsByTagName('NAVIGATION')
        navigation_xml=navigation_id[0].firstChild.nodeValue            
                #we recieve top as xml tag
        if navigation_xml=='top' :
                        if pid>20 :
                                target_planet=(pid-20)
                        else:
                                target_planet=(pid+5)

                #we recieve right as xml tag
        if navigation_xml=='right' :
                        if (pid%5)==0 :
                                target_planet=(pid-4)
                        else :
                                target_planet=(pid+1) 

                #we recieve left as xml tag
        if navigation_xml=='left' :

                        if (pid%5)==1 :
                                target_planet=(pid+4)

                        else :
                                target_planet=(pid-1)
                                        


        con.execute("update Team set cpid = %s where tid = %s "%(target_planet,tid))#doubt??
        con.execute("select server_name from Planet where pid='%s'"%(target_planet))#doubt
        newservername=con.fetchall()
        newservername=newservername[0][0]
        tpid_xml="<xml><TID>%s</TID><PID>%s</PID></xml>"%(tid,pid)
        parray[tid].sendall(tpid_xml+junkbit)
        server_xml="<xml><TYPE>SERVER</TYPE><newserver>%s</newserver></xml>"%newservername
        parray[tid].sendall(server_xml+junkbit)
        #con.execute("select server_name from planet where pid='%s'"%pid)#doubt
                                
class mainserver(asyncore.dispatcher):
        
        def __init__(self,port,con):
                self.con=con
                con.execute("show tables")
                result=con.fetchone()
                print result
                asyncore.dispatcher.__init__(self)
                self.port=port
                self.create_socket(socket.AF_INET,socket.SOCK_STREAM)
                self.bind(('',port))
                self.listen(5)
                print "listening on port",self.port

        def handle_accept(self):
                channel,addr=self.accept()
                print "accepted ",addr
                secondaryserver(channel)
                



class secondaryserver(asyncore.dispatcher_with_send):

        def handle_read(self):
                print 'ready to read \n'
                data=self.recv(1024)    
                #a tag with the data will let the server know whether its an answer,TID....so as to call the appr functn
                print 'data recieved'
                print data
                global junkbit
                junkbit=data[len(data)-1]
                data1=data[0:len(data)-1]
                if(data[0:5]=="<xml>"):
                        print 'in if'
                        message=minidom.parseString(data1)
                        type_of = message.getElementsByTagName('TYPE')                 
                        print type_of[0].firstChild.nodeValue
                        if(type_of[0].firstChild.nodeValue=='LOGIN'):
                            loginfn(self,message)
                            qids()
                            questionsend()
                            #questioncheck(message)
                            if num_of_clients_on_planet[pid]>1 :
                                            crossfire(message)#send pid
                                
                            print 'aaaaa'                      
                                        
                        elif (type_of[0].firstChild.nodeValue=='ANS'):
                                print 'checking answers'
                                questioncheck(message)
                                evaluate()
                                        
                        elif (type_of[0].firstChild.nodeValue=='NAVIGATION'):
                            navigation(message)
                            loginfn(message)
                            qids()
                            questionsend()
                            if num_of_clients_on_planet[pid]>1 :
                                            crossfire(message)
                    
                        elif(type_of[0].firstchild.nodeValue=='CROSS_ANS'):
                            crossans(message)

                            
        def handle_close(self):                        
            for value in parray.keys():
                if(parray[value]==self):
                    parray.__delitem__(value)                 


                                                                

connection=MySQLdb.connect(user="root",passwd="",db="ether")
con=connection.cursor()
mainserver(2000,con)
asyncore.loop()



