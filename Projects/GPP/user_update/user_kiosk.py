import sys
import base64
import gtk
import pango
import os
import time
from xml.dom.minidom import parse

class kiosk:

	def on_window_destroy(self,widget,data=None):
		gtk.main_quit()	
		
	def on_user_next_clicked(self,widget,data=None):
		
		if self.i < self.len_of_dir-1 :
			self.i=self.i+1
			self.get_file_inbox()	
		else :
			pass
			
	
	def on_user_previous_clicked(self,widget,data=None):
		
		if self.i > 0 :
			self.i=self.i-1
			self.get_file_inbox()
		else:
			pass
			

	def get_file_inbox(self):
	
		os.chdir("/home/kanika/Desktop/user_update/download")
		list_of_dir=os.listdir("./")
		self.len_of_dir=len(list_of_dir)
		
		if self.len_of_dir > 0:
		
			print list_of_dir[self.i]
			par=parse(list_of_dir[self.i])
			node=par.getElementsByTagName("form_id")
			if node[0].firstChild.nodeValue == "4" :
		
				data="REPLY \n\n"
				
				buff=self.user_inbox_textview.get_buffer()
				buff.set_text("")
				iter_start=buff.get_start_iter()
				buff.insert_with_tags(iter_start,data,buff.create_tag(weight=pango.WEIGHT_BOLD,foreground="black",size_points=12))
				iter_end=buff.get_end_iter()
				
				node=par.getElementsByTagName("day")
				date=node[0].firstChild.nodeValue
				date=date+"/"
				node=par.getElementsByTagName("month")
				date=date+node[0].firstChild.nodeValue
				node=par.getElementsByTagName("year")
				date=date+"/"
				date=date+node[0].firstChild.nodeValue
				data ="Date: %s \n"%(date)
				node=par.getElementsByTagName("hours")
				time=node[0].firstChild.nodeValue
				time=time+":"
				node=par.getElementsByTagName("minutes")
				time=time+node[0].firstChild.nodeValue
				data = data + "Time: %s \n"%(time)
				
				node=par.getElementsByTagName("kiosk_address")
				kiosk_address=node[0].firstChild.nodeValue
	      			data = data + "Kiosk Address: %s \n"%(kiosk_address)
	      			
	      			node=par.getElementsByTagName("upload_file_name")
				self.attached_file_name=node[0].firstChild.nodeValue
	      			
	      			node=par.getElementsByTagName("upload_file")
				self.attached_file=node[0].firstChild.nodeValue
				
	      			node=par.getElementsByTagName("filename")
				self.filename=node[0].firstChild.nodeValue
				
				node=par.getElementsByTagName("user_name")
				user_name=node[0].firstChild.nodeValue
				data = data + "From: %s \n\n"%(user_name)
		
				node=par.getElementsByTagName("subject")
				subject=node[0].firstChild.nodeValue
				data = data + "Subject: %s \n\n"%(subject)
			
				node=par.getElementsByTagName("details")
				details=node[0].firstChild.nodeValue
				data = data + "%s \n"%(details)
			
				buff.insert_with_tags(iter_end,data,buff.create_tag(weight=pango.WEIGHT_NORMAL,foreground="black",size_points=10))
				
				os.chdir("..")
		else:
			os.chdir("..")
			return
			
	def on_inbox_attached_clicked(self,widget,data=None):
		
		if self.attached_file_name==None:
			message = "There is no Attached File"
	   		print "attachment dialog being generated"
                	dialog = gtk.MessageDialog(self.window,
                        	                   gtk.DIALOG_MODAL | gtk.DIALOG_DESTROY_WITH_PARENT,
                        	                   gtk.MESSAGE_INFO, gtk.BUTTONS_OK, 
                        	                   message)
			dialog.run()
			dialog.destroy()
			
		if self.attached_file_name=="None":	
			message = "There is no Attached File"
	   		print "attachment dialog being generated"
                	dialog = gtk.MessageDialog(self.window,
                        	                   gtk.DIALOG_MODAL | gtk.DIALOG_DESTROY_WITH_PARENT,
                        	                   gtk.MESSAGE_INFO, gtk.BUTTONS_OK, 
                        	                   message)
			dialog.run()
			dialog.destroy()
		else:
			os.chdir("/home/kanika/Desktop")
			os.mkdir(self.attached_file_name)
			os.chdir(self.attached_file_name)
			
			f=open(self.attached_file_name,'wb')
			self.attached_file=base64.decodestring(self.attached_file)
			f.write(self.attached_file)
			f.close()
			
			os.chdir("/home/kanika/Desktop/user_update")
			
			message = "File Attached "
	   		print "attachment dialog being generated"
                	dialog = gtk.MessageDialog(self.window,
                        	                   gtk.DIALOG_MODAL | gtk.DIALOG_DESTROY_WITH_PARENT,
                        	                   gtk.MESSAGE_INFO, gtk.BUTTONS_OK, 
                        	                   message)
			dialog.run()
			dialog.destroy()
			
	def on_inbox_delete_clicked(self,widget,data=None):
		
		if self.filename==None:
			message = "There are no Files to Delete"
	   		print "delete dialog being generated"
                	dialog = gtk.MessageDialog(self.window,
                        	                   gtk.DIALOG_MODAL | gtk.DIALOG_DESTROY_WITH_PARENT,
                        	                   gtk.MESSAGE_INFO, gtk.BUTTONS_OK, 
                        	                   message)
			dialog.run()
			dialog.destroy()
		else:
		
				os.chdir("./download")
				os.remove(self.filename)
				os.chdir("..")
				message = "File Deleted"
	 	  		print "delete dialog being generated"
                		dialog = gtk.MessageDialog(self.window,
                	        	                   gtk.DIALOG_MODAL | gtk.DIALOG_DESTROY_WITH_PARENT,
                	        	                   gtk.MESSAGE_INFO, gtk.BUTTONS_OK, 
                	        	                   message)
				dialog.run()
				dialog.destroy()
				buff=self.user_inbox_textview.get_buffer()
				buff.set_text("")
				
	def on_submit_home_clicked(self,widget,data=None):
	
		now = time.localtime(time.time()) 
		file_name1=time.asctime(now)+".xml"
		print file_name1
		
		par=parse("comp_form.xml")
		
		node=par.getElementsByTagName("day")
		node[0].firstChild.nodeValue=now[2]
		
		node=par.getElementsByTagName("month")
		node[0].firstChild.nodeValue=now[1]
		
		node=par.getElementsByTagName("year")
		node[0].firstChild.nodeValue=now[0]
		
		node=par.getElementsByTagName("hours")
		node[0].firstChild.nodeValue=now[3]

		node=par.getElementsByTagName("minutes")
		node[0].firstChild.nodeValue=now[4]
		
		node=par.getElementsByTagName("user_name")
		node[0].firstChild.nodeValue=self.cf_name.get_text()
		
		node=par.getElementsByTagName("filename")
		node[0].firstChild.nodeValue=file_name1
		
		node=par.getElementsByTagName("name")
		node[0].firstChild.nodeValue=self.cf_name.get_text()
		
		node=par.getElementsByTagName("age_sex")
		node[0].firstChild.nodeValue=self.cf_age_sex.get_text()
		
		node=par.getElementsByTagName("occupation")
		node[0].firstChild.nodeValue=self.cf_occupation.get_text()

		node=par.getElementsByTagName("pr_comp")
		buff = self.cf_present_comp.get_buffer()
		node[0].firstChild.nodeValue=buff.get_text(buff.get_start_iter(), buff.get_end_iter())
		
		node=par.getElementsByTagName("med_taken")
		buff = self.cf_med_taken.get_buffer()
		node[0].firstChild.nodeValue=buff.get_text(buff.get_start_iter(), buff.get_end_iter())
		
		node=par.getElementsByTagName("b_s")
		node[0].firstChild.nodeValue=self.cf_blad_sleep.get_text()
		
		node=par.getElementsByTagName("upload_file_type")
		node[0].firstChild.nodeValue="file"
		
		if self.fn != None:
			string=self.fn
			list1=string.split("/")
			l=len(list1)
		
			node=par.getElementsByTagName("upload_file_name")
			node[0].firstChild.nodeValue=list1[l-1]
		
			node=par.getElementsByTagName("upload_file")
			node[0].firstChild.nodeValue=self.file
		
		os.chdir("./upload_store/complaint")
		file_new=open(file_name1,"w")
		par.writexml(file_new,encoding="utf-8")
		file_new.close()
		os.chdir("..")
		os.chdir("..")
		
		os.chdir("./upload")
		file_new=open(file_name1,"w")
		par.writexml(file_new,encoding="utf-8")
		file_new.close()
		os.chdir("..")

		message = "YOUR MAIL IS IN THE SENDING QUEUE "
	        print "mail sent dialog being generated"
                dialog = gtk.MessageDialog(self.window,
                                           gtk.DIALOG_MODAL | gtk.DIALOG_DESTROY_WITH_PARENT,
                                           gtk.MESSAGE_INFO, gtk.BUTTONS_OK, 
                                           message)
		dialog.run()
		dialog.destroy()

	def on_reset_home_clicked(self,widget,data=None):
		
		self.cf_name.set_text("")
		self.cf_age_sex.set_text("")
		self.cf_blad_sleep.set_text("")
		self.cf_occupation.set_text("")
		
		buff = self.cf_present_comp.get_buffer()
		buff.set_text("")
				
		buff = self.cf_med_taken.get_buffer()	
		buff.set_text("")

	def on_upload_pic_file_set(self,widget,data=None):
		
		self.fn=self.upload_pic.get_filename()
		print self.fn
		self.file=base64.encodestring(open(self.fn,'rb').read())
		print self.file
		

	def on_ef_submit_clicked(self,widget,data=None):

		now = time.localtime(time.time()) 
		print now
		file_name2=time.asctime(now)+".xml"
		print file_name2
		
		par=parse("emergency_form.xml")
		
		node=par.getElementsByTagName("day")
		print node
		node[0].firstChild.nodeValue=now[2]
		
		node=par.getElementsByTagName("month")
		node[0].firstChild.nodeValue=now[1]
		
		node=par.getElementsByTagName("year")
		node[0].firstChild.nodeValue=now[0]
		
		node=par.getElementsByTagName("hours")
		node[0].firstChild.nodeValue=now[3]

		node=par.getElementsByTagName("minutes")
		node[0].firstChild.nodeValue=now[4]
		
		node=par.getElementsByTagName("filename")
		node[0].firstChild.nodeValue=file_name2

		node=par.getElementsByTagName("name")
		node[0].firstChild.nodeValue=gtk.Entry.get_text(self.ef_name)

		node=par.getElementsByTagName("address")
		node[0].firstChild.nodeValue=gtk.Entry.get_text(self.ef_adrs)

		node=par.getElementsByTagName("phone")
		node[0].firstChild.nodeValue=gtk.Entry.get_text(self.ef_phn)

		# accident description is a textview,all the others are text entry
		buff=self.ef_sd.get_buffer()
		node=par.getElementsByTagName("sit_desc")
		node[0].firstChild.nodeValue=buff.get_text(buff.get_start_iter(),buff.get_end_iter(),include_hidden_chars=True) 
		# this is used to return the text present in the text buffer contained 								
		# in the specific range given by the pointers.

		node=par.getElementsByTagName("blood_loss")
		node[0].firstChild.nodeValue=self.ef_combobox1.get_active_text()

		node=par.getElementsByTagName("concious")
		node[0].firstChild.nodeValue=self.ef_combobox2.get_active_text()

		node=par.getElementsByTagName("loabp")
		node[0].firstChild.nodeValue=gtk.Entry.get_text(self.ef_loabp)

		node=par.getElementsByTagName("avgi")
		node[0].firstChild.nodeValue=gtk.Entry.get_text(self.ef_avgi)
		
		os.chdir("./upload_store/emergency")
		file_new=open(file_name2,"w")
		par.writexml(file_new,encoding="utf-8")
		file_new.close()
		os.chdir("..")
		os.chdir("..")
		
		os.chdir("./upload")
		file_new=open(file_name2,"w")
		par.writexml(file_new,encoding="utf-8")
		file_new.close()
		os.chdir("..")
		
		message = "YOUR MAIL IS IN THE SENDING QUEUE"
	        print "mail sent dialog being generated"
                dialog = gtk.MessageDialog(self.window,
                                           gtk.DIALOG_MODAL | gtk.DIALOG_DESTROY_WITH_PARENT,
                                           gtk.MESSAGE_INFO, gtk.BUTTONS_OK, 
                                           message)
		dialog.run()
		dialog.destroy()

		print "submit button of emergency clicked"

	def on_ef_reset_clicked(self,widget,data=None):
		self.ef_name.set_text("")
		self.ef_adrs.set_text("")
		self.ef_phn.set_text("")
		buff=self.ef_sd.get_buffer()
		buff.set_text("")
		self.ef_combobox1.set_active(-1)
		self.ef_combobox2.set_active(-1)
		self.ef_loabp.set_text("")
		self.ef_avgi.set_text("")

	def __init__ (self):
		
		builder=gtk.Builder()
		builder.add_from_file("user_kiosk.xml")
		
		self.fn=None
		self.file=""
		self.attached_file_name=None
		self.attached_file=None
		self.file_type=None
		self.filename=None
		self.i=0
		self.window=builder.get_object("window")
		self.cf_name=builder.get_object("cf_name")
		self.cf_occupation=builder.get_object("cf_occupation")
		self.cf_age_sex=builder.get_object("cf_age_sex")
		self.cf_present_comp=builder.get_object("cf_present_comp")
		self.cf_blad_sleep=builder.get_object("cf_blad_sleep")
		self.cf_med_taken=builder.get_object("cf_med_taken")
		self.upload_pic=builder.get_object("upload_pic")
		self.ef_name=builder.get_object("ef_name")
		self.ef_adrs=builder.get_object("ef_adrs")
		self.ef_phn=builder.get_object("ef_phn")
		self.ef_sd=builder.get_object("ef_sd")
		self.ef_loabp=builder.get_object("ef_loabp")
		self.ef_avgi=builder.get_object("ef_avgi")
		self.ef_combobox1=builder.get_object("ef_combobox1")
		self.ef_combobox2=builder.get_object("ef_combobox2")
		self.user_inbox_textview=builder.get_object("user_inbox_textview")
		self.get_file_inbox()
		builder.connect_signals(self)

if __name__ == "__main__":
	kiosk1=kiosk()
	kiosk1.window.show()
	gtk.main()

