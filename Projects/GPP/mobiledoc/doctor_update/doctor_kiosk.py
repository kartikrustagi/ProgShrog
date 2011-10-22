dir_from_which_user_kiosk_will_take_files=#'/home/kanika/Desktop'
import sys
import base64
import gtk
import pango
import os
import time
from xml.dom.minidom import parse

class kiosk_doctor:

	def on_window_destroy(self,widget,data=None):
		gtk.main_quit()

		
	def on_next_inbox_clicked(self,widget,data=None):
		
		if self.i < self.len_of_dir_ef+self.len_of_dir_cf+self.len_of_dir_other-1:
			 
			if self.i < self.len_of_dir_ef-1:
				self.i = self.i+1
				self.get_file_inbox_emergency()
				
			elif self.i < self.len_of_dir_ef+self.len_of_dir_cf-1:
				self.i=self.i+1
				self.get_file_inbox_complaint()
			
			elif self.i >= self.len_of_dir_ef+self.len_of_dir_cf-1:
				self.i=self.i+1
				self.get_file_inbox_other()
							
		else:
			pass
				
	def on_prev_inbox_clicked(self,widget,data=None):
				
		if self.i > 0 :
		
			if self.i > self.len_of_dir_ef + self.len_of_dir_cf:
				self.i=self.i-1
				self.get_file_inbox_other()
				
			elif self.i > self.len_of_dir_ef:
		        	
		        	if self.len_of_dir_cf==0:
		        		pass
		        	else:
					self.i=self.i-1
					self.get_file_inbox_complaint()
					
			elif self.i <= self.len_of_dir_ef:
				self.i=self.i-1
				self.get_file_inbox_emergency()			
		else :
			pass
			
			
	def on_reply_inbox_clicked(self,widget,data=None):
	
		if self.i >= self.len_of_dir_ef:
			
			if self.len_of_dir_cf==0:
				os.chdir("./download/other")
		
				list_of_dir=os.listdir("./")
				self.doctor_notebook.next_page()
				par=parse(list_of_dir[self.i-self.len_of_dir_ef-self.len_of_dir_cf])
						
				node=par.getElementsByTagName("from")
				self.cm_to.set_text(node[0].firstChild.nodeValue)
			
				self.cm_subject.set_text("Reply:")
			
				buff=self.cm_textview.get_buffer()
				buff.set_text("\n\n\n\n"+"_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  "+"\n"+self.data_g)
				os.chdir("..")
				os.chdir("..")
				
			elif self.i>= self.len_of_dir_ef + self.len_of_dir_cf:	
				
				if self.i < self.len_of_dir_ef+self.len_of_dir_cf+self.len_of_dir_other:
					os.chdir("./download/other")
		
					list_of_dir=os.listdir("./")
					self.doctor_notebook.next_page()
					par=parse(list_of_dir[self.i-self.len_of_dir_ef-self.len_of_dir_cf])
							
					node=par.getElementsByTagName("from")
					self.cm_to.set_text(node[0].firstChild.nodeValue)
				
					self.cm_subject.set_text("Reply:")
				
					buff=self.cm_textview.get_buffer()
					buff.set_text("\n\n\n\n"+"_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  "+"\n"+self.data_g)
					os.chdir("..")
					os.chdir("..")
				else:
					pass
			else:
				os.chdir("./download/complaint")
		
				list_of_dir=os.listdir("./")
				self.doctor_notebook.next_page()
				par=parse(list_of_dir[self.i-self.len_of_dir_ef])
			
				node=par.getElementsByTagName("kiosk_id")
				self.cm_to.set_text(node[0].firstChild.nodeValue)
			
				self.cm_subject.set_text("Reply to your medical complaint:")
			
				buff=self.cm_textview.get_buffer()
				buff.set_text("\n\n\n\n"+"_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  "+"\n"+self.data_g)
				os.chdir("..")
				os.chdir("..")
		else :
		
			os.chdir("./download/emergency")
		
			list_of_dir=os.listdir("./")
			self.doctor_notebook.next_page()
			par=parse(list_of_dir[self.i])
			
			node=par.getElementsByTagName("kiosk_id")
			self.cm_to.set_text(node[0].firstChild.nodeValue)
			
			self.cm_subject.set_text("Reply to your medical complaint:")
			
			buff=self.cm_textview.get_buffer()
			buff.set_text("\n\n\n\n"+"_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  "+"\n"+self.data_g)
			os.chdir("..")
			os.chdir("..") 
			
	def get_file_inbox_other(self):
	
		os.chdir(dir_from_which_user_kiosk_will_take_files)
		os.chdir("./doctor_update")
		os.chdir("./download/other")
		
		list_of_dir=os.listdir("./")
		self.len_of_dir_other=len(list_of_dir)
		
		if self.len_of_dir_other > 0:
			par=parse(list_of_dir[self.i-self.len_of_dir_ef-self.len_of_dir_cf])
			
			data="INBOX MAIL \n\n"
								
			buff=self.textview_inbox.get_buffer()
			buff.set_text("")
			iter_start=buff.get_start_iter()
			buff.insert_with_tags(iter_start,data,buff.create_tag(weight=pango.WEIGHT_BOLD,foreground="black",size_points=14))
			iter_end=buff.get_end_iter()
			
					
			node=par.getElementsByTagName("filename")
			self.filename=node[0].firstChild.nodeValue
				
			node=par.getElementsByTagName("from")
			fr=node[0].firstChild.nodeValue
			data = "From: %s \n\n"%(fr)
				
			node=par.getElementsByTagName("upload_file_name")
			self.attached_file_name=node[0].firstChild.nodeValue
	      			
      			node=par.getElementsByTagName("upload_file")
			self.attached_file=node[0].firstChild.nodeValue
		
			node=par.getElementsByTagName("subject")
			subject=node[0].firstChild.nodeValue
			data = data + "Subject: %s \n\n"%(subject)
			
			node=par.getElementsByTagName("details")
			details=node[0].firstChild.nodeValue
			data = data + "%s \n"%(details)
			
			buff.insert_with_tags(iter_end,data,buff.create_tag(weight=pango.WEIGHT_NORMAL,foreground="black",size_points=10))
			
			self.data_g=data
				
			os.chdir("..")
			os.chdir("..")
			
		else:
			os.chdir("..")
			os.chdir("..")
			return

	def get_file_inbox_complaint(self):
	
		os.chdir(dir_from_which_user_kiosk_will_take_files)
		os.chdir("./doctor_update")
		os.chdir("./download/complaint")
		list_of_dir=os.listdir("./")
		self.len_of_dir_cf=len(list_of_dir)
		
		if self.len_of_dir_cf > 0:
			par=parse(list_of_dir[self.i-self.len_of_dir_ef])
			
			data="COMPLAINT FORM \n\n"
			
			#gtk.TExtTAG,gtk.TextBuffer.create_tag used for edditing font
			
			buff=self.textview_inbox.get_buffer()
			buff.set_text("")
			iter_start=buff.get_start_iter()
			buff.insert_with_tags(iter_start,data,buff.create_tag(weight=pango.WEIGHT_BOLD,foreground="black",size_points=14))
			iter_end=buff.get_end_iter()
			
			node=par.getElementsByTagName("day")
			date=node[0].firstChild.nodeValue
			date=date+"/"
			node=par.getElementsByTagName("month")
			date=date+node[0].firstChild.nodeValue
			node=par.getElementsByTagName("year")
			date=date+"/"
			date=date+node[0].firstChild.nodeValue
			data = "Date: %s \n"%(date)
		
			node=par.getElementsByTagName("hours")
			time=node[0].firstChild.nodeValue
			time=time+":"
			node=par.getElementsByTagName("minutes")
			time=time+node[0].firstChild.nodeValue
			data = data + "Time: %s \n"%(time)
		
			node=par.getElementsByTagName("kiosk_address")
			kiosk_address=node[0].firstChild.nodeValue
	      		data = data + "Kiosk Address: %s \n\n"%(kiosk_address)
	      		
	      		node=par.getElementsByTagName("upload_file_name")
			self.attached_file_name=node[0].firstChild.nodeValue
	      		
	      		node=par.getElementsByTagName("upload_file")
			self.attached_file=node[0].firstChild.nodeValue
	      		
	      		node=par.getElementsByTagName("filename")
			self.filename=node[0].firstChild.nodeValue
			
			self.file_type="complaint"
				
			node=par.getElementsByTagName("name")
			name=node[0].firstChild.nodeValue
			data = data + "From: %s \n"%(name)
		
			node=par.getElementsByTagName("age_sex")
			age_sex=node[0].firstChild.nodeValue
			data = data + "Age and Sex: %s \n"%(age_sex)
				
			node=par.getElementsByTagName("occupation")
			occupation=node[0].firstChild.nodeValue
			data = data + "Occupation: %s \n"%(occupation)
				
			node=par.getElementsByTagName("pr_comp")
			pr_comp=node[0].firstChild.nodeValue
			data = data + "Present Complaints: %s \n"%(pr_comp)
				
			node=par.getElementsByTagName("med_taken")
			med_taken=node[0].firstChild.nodeValue
			data = data + "Medication Taken: %s \n"%(med_taken)
				
			node=par.getElementsByTagName("b_s")
			b_d=node[0].firstChild.nodeValue
			data = data + "Bladder and Sleeping: %s \n"%(b_d)
			
			buff.insert_with_tags(iter_end,data,buff.create_tag(weight=pango.WEIGHT_NORMAL,foreground="black",size_points=12))
			
			self.data_g=data

			os.chdir("..")
			os.chdir("..")
			
			if self.z == 0:
			
				os.chdir(dir_from_which_user_kiosk_will_take_files)
				os.chdir("./doctor_update")
				os.chdir("./download/other")
				
				list_of_dir=os.listdir("./")
				self.len_of_dir_other=len(list_of_dir)
				
				self.z = self.z + 1
			
				os.chdir("..")
				os.chdir("..")
				
		else :
			os.chdir("..")
			os.chdir("..")
			self.get_file_inbox_other()

	def get_file_inbox_emergency(self):
	
		os.chdir(dir_from_which_user_kiosk_will_take_files)
		os.chdir("./doctor_update")
		os.chdir("./download/emergency")
		list_of_dir=os.listdir("./")
		self.len_of_dir_ef=len(list_of_dir)
		
		if self.len_of_dir_ef > 0 :
		
			par=parse(list_of_dir[self.i])
		
			data="EMERGENCY FORM \n\n"
			
			buff=self.textview_inbox.get_buffer()
			buff.set_text("")
			iter_start=buff.get_start_iter()
			buff.insert_with_tags(iter_start,data,buff.create_tag(weight=pango.WEIGHT_BOLD,foreground="red",size_points=14))
			iter_end=buff.get_end_iter()
			
			node=par.getElementsByTagName("day")
			date=node[0].firstChild.nodeValue
			date=date+"/"
			node=par.getElementsByTagName("month")
			date=date+node[0].firstChild.nodeValue
			node=par.getElementsByTagName("year")
			date=date+"/"
			date=date+node[0].firstChild.nodeValue
			data = "Date: %s \n"%(date)
			node=par.getElementsByTagName("hours")
			time=node[0].firstChild.nodeValue
			time=time+":"
			node=par.getElementsByTagName("minutes")
			time=time+node[0].firstChild.nodeValue
			data = data + "Time: %s \n"%(time)
				
			node=par.getElementsByTagName("kiosk_address")
			kiosk_address=node[0].firstChild.nodeValue
	      		data = data + "Kiosk Address: %s \n\n"%(kiosk_address)
				
			node=par.getElementsByTagName("filename")
			self.filename=node[0].firstChild.nodeValue
			
			self.file_type="emergency"
			
			node=par.getElementsByTagName("name")
			name=node[0].firstChild.nodeValue
			data = data + "From: %s \n"%(name)
		
			node=par.getElementsByTagName("address")
			adrs=node[0].firstChild.nodeValue
			data = data + "Address: %s \n"%(adrs)
			
			node=par.getElementsByTagName("phone")
			phone=node[0].firstChild.nodeValue
			data = data + "Phone: %s \n"%(phone)
			
			node=par.getElementsByTagName("sit_desc")
			sit_desc=node[0].firstChild.nodeValue
			data = data + "Situation Description: %s \n"%(sit_desc)
				
			node=par.getElementsByTagName("blood_loss")
			blood_loss=node[0].firstChild.nodeValue
			data = data + "Blood Loss: %s \n"%(blood_loss)
			
			node=par.getElementsByTagName("concious")
			concious=node[0].firstChild.nodeValue
			data = data + "Concious: %s \n"%(concious)
		
			node=par.getElementsByTagName("loabp")
			loabp=node[0].firstChild.nodeValue
			data = data + "Loss of Any Body Part: %s \n"%(loabp)
		
			node=par.getElementsByTagName("avgi")
			avgi=node[0].firstChild.nodeValue
			data = data + "Any Visible Gross Injury: %s \n"%(avgi)
			
			buff.insert_with_tags(iter_end,data,buff.create_tag(weight=pango.WEIGHT_NORMAL,foreground="red",size_points=12))
			
			self.data_g=data
				
			os.chdir("..")
			os.chdir("..")
			
			if self.z == 0:
				
				os.chdir(dir_from_which_user_kiosk_will_take_files)
				os.chdir("./doctor_update/download/complaint")
				
				list_of_dir=os.listdir("./")
				self.len_of_dir_cf=len(list_of_dir)
				
				os.chdir(dir_from_which_user_kiosk_will_take_files)
				os.chdir("./doctor_update/download/other")
				
				list_of_dir=os.listdir("./")
				self.len_of_dir_other=len(list_of_dir)
				
				self.z = self.z + 1
			
				os.chdir("..")
				os.chdir("..")
							
		else:
			os.chdir("..")
			os.chdir("..")
			self.get_file_inbox_complaint()
			
			
	def on_cl_view_category_changed(self,widget,data=None):	
	
		os.chdir("./Contact_Lists")
		list_of_dir=os.listdir("./")
		self.len_of_dir=len(list_of_dir)
		
		cat_type=self.cl_view_category.get_active_text()
		
		for j in range(0,self.len_of_dir):
				
				par=parse(list_of_dir[j])
				node=par.getElementsByTagName("category")
				
				if node[0].firstChild.nodeValue == cat_type:
					self.k=self.k+1	
					if self.k==1:	
						node=par.getElementsByTagName("organization_name")
						organization_name=node[0].firstChild.nodeValue
						data ="Organization Name: %s \n"%(organization_name)
		
						node=par.getElementsByTagName("name")
						name=node[0].firstChild.nodeValue
						data = data + "Name: %s \n"%(name)
					
						node=par.getElementsByTagName("designation")
						designation=node[0].firstChild.nodeValue
						data = data + "Designation: %s \n"%(designation)
					
						node=par.getElementsByTagName("phn_email")
						phn_email=node[0].firstChild.nodeValue
						data = data + "Phone no,Email: %s \n"%(phn_email)
						
						node=par.getElementsByTagName("office_address")
						office_address=node[0].firstChild.nodeValue
						data = data + "Office Address: %s \n\n"%(office_address)
						
						buff=self.cl_view_contacts.get_buffer()
				        	buff.set_text(data)
				        	
				        	
				        else :
				        	node=par.getElementsByTagName("organization_name")
						organization_name=node[0].firstChild.nodeValue
						data = data + "Organization Name: %s \n"%(organization_name)
		
						node=par.getElementsByTagName("name")
						name=node[0].firstChild.nodeValue
						data = data + "Name: %s \n"%(name)
					
						node=par.getElementsByTagName("designation")
						designation=node[0].firstChild.nodeValue
						data = data + "Designation: %s \n"%(designation)
					
						node=par.getElementsByTagName("phn_email")
						phn_email=node[0].firstChild.nodeValue
						data = data + "Phone no,Email: %s \n"%(phn_email)
						
						node=par.getElementsByTagName("office_address")
						office_address=node[0].firstChild.nodeValue
						data = data + "Medication Taken: %s \n\n"%(office_address)
						
						buff=self.cl_view_contacts.get_buffer()
				        	buff.set_text(data)
				        	
		if self.k==0:
			buff=self.cl_view_contacts.get_buffer()
			buff.set_text("")

		self.k=0
		os.chdir("..")		
		
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
			if self.file_type=="complaint":
				os.chdir("./download/complaint")
				os.remove(self.filename)
				os.chdir("..")
				os.chdir("..")
				message = "File Deleted"
	 	  		print "delete dialog being generated"
                		dialog = gtk.MessageDialog(self.window,
                	        	                   gtk.DIALOG_MODAL | gtk.DIALOG_DESTROY_WITH_PARENT,
                	        	                   gtk.MESSAGE_INFO, gtk.BUTTONS_OK, 
                	        	                   message)
				dialog.run()
				dialog.destroy()
				buff=self.textview_inbox.get_buffer()
				buff.set_text("")
				
			elif self.file_type=="emergency":
				os.chdir("./download/emergency")
				os.remove(self.filename)
				os.chdir("..")
				os.chdir("..")
				message = "File Deleted"
	   			print "delete dialog being generated"
                		dialog = gtk.MessageDialog(self.window,
                        		                   gtk.DIALOG_MODAL | gtk.DIALOG_DESTROY_WITH_PARENT,
                        		                   gtk.MESSAGE_INFO, gtk.BUTTONS_OK, 
                        		                   message)
				dialog.run()
				dialog.destroy()
				buff=self.textview_inbox.get_buffer()
				buff.set_text("")
				
			elif self.file_type==None:   
				os.chdir("./download/other")
				os.remove(self.filename)
				os.chdir("..")
				os.chdir("..")
				message = "File Deleted"
	   			print "delete dialog being generated"
                		dialog = gtk.MessageDialog(self.window,
                        		                   gtk.DIALOG_MODAL | gtk.DIALOG_DESTROY_WITH_PARENT,
                        		                   gtk.MESSAGE_INFO, gtk.BUTTONS_OK, 
                        		                   message)
				dialog.run()
				dialog.destroy()
				buff=self.textview_inbox.get_buffer()
				buff.set_text("")
	
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
			os.chdir(dir_from_which_user_kiosk_will_take_files)
			os.mkdir(self.attached_file_name)
			os.chdir(self.attached_file_name)
			
			f=open(self.attached_file_name,'wb')
			self.attached_file=base64.decodestring(self.attached_file)
			f.write(self.attached_file)
			f.close()
			
			os.chdir(dir_from_which_user_kiosk_will_take_files)
			os.chdir("./doctor_update")
			
			message = "File Attached "
	   		print "attachment dialog being generated"
                	dialog = gtk.MessageDialog(self.window,
                        	                   gtk.DIALOG_MODAL | gtk.DIALOG_DESTROY_WITH_PARENT,
                        	                   gtk.MESSAGE_INFO, gtk.BUTTONS_OK, 
                        	                   message)
			dialog.run()
			dialog.destroy()
						
	def on_cl_submit_clicked(self,widget,data=None):
	
		now = time.localtime(time.time()) 
		file_name1=time.asctime(now)+".xml"
		
		os.chdir("./Templates")
		par=parse("./contact_list.xml")
		
		node=par.getElementsByTagName("name")
		node[0].firstChild.nodeValue=gtk.Entry.get_text(self.cl_name)
		
		node=par.getElementsByTagName("designation")
		node[0].firstChild.nodeValue=gtk.Entry.get_text(self.cl_dsgntn)
		
		node=par.getElementsByTagName("organization_name")
		node[0].firstChild.nodeValue=gtk.Entry.get_text(self.cl_org_name)
		
		node=par.getElementsByTagName("category")
		node[0].firstChild.nodeValue=self.cl_add_category.get_active_text()
		
		node=par.getElementsByTagName("phn_email")
		node[0].firstChild.nodeValue=gtk.Entry.get_text(self.cl_phn_email)
		
		node=par.getElementsByTagName("office_address")
		node[0].firstChild.nodeValue=gtk.Entry.get_text(self.cl_off_adrs)
		
		os.chdir("..")
		
		os.chdir("./Contact_Lists")
		file_new=open(file_name1,"w")
		par.writexml(file_new,encoding="utf-8")
		file_new.close()
		os.chdir("..")

	def on_cl_reset_clicked(self,widget,data=None):
		self.cl_name.set_text("")
		self.cl_dsgntn.set_text("")
		self.cl_org_name.set_text("")
		self.cl_add_category.set_active(-1)
		self.cl_phn_email.set_text("")
		self.cl_off_adrs.set_text("")

	def on_cm_submit_clicked(self,widget,data=None):
	
		now = time.localtime(time.time()) 
			
		file_name=time.asctime(now)+".xml"
		
		os.chdir("./Templates")
		
		par=parse("compose_mail.xml")
	  	
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
		
		node=par.getElementsByTagName("to")
		node[0].firstChild.nodeValue=gtk.Entry.get_text(self.cm_to)
		
		node=par.getElementsByTagName("filename")
		node[0].firstChild.nodeValue=file_name
		
		node=par.getElementsByTagName("subject")
		node[0].firstChild.nodeValue=gtk.Entry.get_text(self.cm_subject)
		
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
		
		buff=self.cm_textview.get_buffer()
		node=par.getElementsByTagName("details")
		if buff.get_text(buff.get_start_iter(),buff.get_end_iter(),include_hidden_chars=True)!="": 
			node[0].firstChild.nodeValue=buff.get_text(buff.get_start_iter(),buff.get_end_iter(),include_hidden_chars=True)
		else:
			node[0].firstChild.nodeValue=None 
			
		os.chdir("..")
		
		os.chdir("./xml_send_by_doctor")
		file_new=open(file_name,"w")
		par.writexml(file_new,encoding="utf-8")
		file_new.close()
		os.chdir("..")		
		
		os.chdir("./upload")
		file_new=open(file_name,"w")
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
				

	def on_cm_reset_clicked(self,widget,data=None):
		self.cm_to.set_text("")
		self.cm_subject.set_text("")
		buff=self.cm_textview.get_buffer()
		buff.set_text("")

	def on_cm_upload_folder_file_set(self,widget,data=None):
		
		self.fn=self.cm_upload_folder.get_filename()
		
		if self.fn!=None:
			self.file=base64.encodestring(open(self.fn,'rb').read())
		
	def __init__ (self):

		builder=gtk.Builder()
		
		os.chdir(dir_from_which_user_kiosk_will_take_files)
		os.chdir("./doctor_update/User_interface")
		
		builder.add_from_file("doctor_kiosk.xml")
		os.chdir("..")
		
		self.attached_file_name=None
		self.attached_file=None
		self.fn=None
		self.file=""
		self.file_type=None
		self.filename=None
		self.z=0
		self.i=0
		self.k=0
		self.len_of_dir_other=0
		self.len_of_dir_cf=0
		self.len_of_dir_ef=0
		self.data_g=None
		self.window=builder.get_object("window")
		self.textview_inbox=builder.get_object("textview_inbox")
		self.cl_name=builder.get_object("cl_name")
		self.cl_dsgntn=builder.get_object("cl_dsgntn")
		self.cl_org_name=builder.get_object("cl_org_name")
		self.cl_category=builder.get_object("cl_category")
		self.cl_phn_email=builder.get_object("cl_phn_email")
		self.cl_off_adrs=builder.get_object("cl_off_adrs")
		self.cm_to=builder.get_object("cm_to")
		self.cm_subject=builder.get_object("cm_subject")
		self.cm_textview=builder.get_object("cm_textview")
		self.cm_upload_folder=builder.get_object("cm_upload_folder")
		self.cl_add_category=builder.get_object("cl_add_category")
		self.cl_view_category=builder.get_object("cl_view_category")
		self.cl_view_contacts=builder.get_object("cl_view_contacts")
		self.doctor_notebook=builder.get_object("doctor_notebook")
	        self.get_file_inbox_emergency()
		builder.connect_signals(self)

if __name__=="__main__":
	kiosk_doc=kiosk_doctor()
	kiosk_doc.window.show()
	gtk.main()
