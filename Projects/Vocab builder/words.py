import MySQLdb as M
import random as R

class methods:
	
	def __init__(self):
		self.conn = M.connect("","root","rustagi","dictionary")
		self.cur = self.conn.cursor()
		self.cur.execute("SELECT COUNT(*) FROM entries;")
		self.conn.commit()
		temp = self.cur.fetchone()
		self.seen_list = []
		self.num_entries = temp[0]
		print 'Number of entries is %d'%(self.num_entries)
		
	def enter_words(self):
		word = raw_input('''Enter the word: ''')
		defination = raw_input('''Enter the defination: ''')
		example = raw_input('''Enter example if any: ''')
		#print self.num_entries
		self.num_entries = (self.num_entries + 1)
		#print '''INSERT INTO entries VALUES(%d,"%s","%s","%s");"%(self.num_entries,word,defination,example)
		self.cur.execute('''INSERT INTO entries VALUES(%d,"%s","%s","%s");'''%(self.num_entries,word,defination,example))
		self.conn.commit()
		self.seen_list.append(self.num_entries)
		print 'Exectution complete'
		
	def produce_random(self):
		if (self.num_entries == len(self.seen_list)):
			i = raw_input('All words revised, press 1 to restart else 0: ')
			i = int(i)
			if i == 1:
				self.seen_list = []
			else:
				return
		
		ran = R.randint(1,self.num_entries)
		while (ran in self.seen_list):
			ran = R.randint(1,self.num_entries)
		self.seen_list.append(ran)
		self.cur.execute('SELECT * FROM entries WHERE id = %d;'%(ran))
		self.conn.commit()
		#now present the word ask for meaning before showing it and example
		result = self.cur.fetchall()
		#print result
		if result == ():
			print 'Retry'
			return;
		print 'Word: %s'%(result[0][1])		
		raw_input('Proceed.. ')
		print '\nDefination:   %s'%(result[0][2])
		print 'Example:      %s'%(result[0][3])
		
	def review_words(self):
		for i in range (0,len(self.seen_list)):
			self.cur.execute('SELECT * FROM entries WHERE id = %d;'%(self.seen_list[i]))
			self.conn.commit()
			result = self.cur.fetchall()	
			if result == ():
				continue
			print '\n\nWord: %s          (%d)'%(result[0][1],result[0][0])
			raw_input('Proceed.. ')
			print '\nDefination:   %s'%(result[0][2])
			print 'Example:      %s'%(result[0][3])
		print 'Revision complete'
	
	def reverse_review(self):
		for i in range (0,len(self.seen_list)):
			self.cur.execute('SELECT * FROM entries WHERE id = %d;'%(self.seen_list[i]))
			self.conn.commit()
			result = self.cur.fetchall()	
			if result == ():
				continue
			print '\n\nDefination:   %s'%(result[0][2])
			raw_input('Proceed.. ')
			print '\nWord: %s          (%d)'%(result[0][1],result[0][0])
			print 'Example:      %s'%(result[0][3])
		print 'Revision complete'
		
		
	def exit(self):
		self.cur.close()
		self.conn.close()
		
		
if __name__ == '__main__':
		m = methods()
		while 1:

				#print 'Press cntrl+c to exit'
				print '\nSelect Option:'
				print '		1. Enter word'
				print '		2. Random revision'
				print '		3. Review revision'
				print '		4. Reverse revision'
				print '		5. Exit' 
				opt = raw_input('Option: ')
				try:
					opt = int(opt)
				except ValueError:
					continue
				if opt == 1:
					m.enter_words()
				elif opt == 2:
					m.produce_random()
				elif opt == 3:
					m.review_words()
				elif opt == 4:
					m.reverse_review()
				elif opt == 5:
					m.exit()
					print '\nBye'
					break
			
				
		
