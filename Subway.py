import socket
import re
import hashlib
import Queue
lis="1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
global mem
def check(data):
	a="L"
	for i in data:
		if i == ' ':
			a='R'
		if i != a:
			return False
	return True
def backtrack(t):
	t=1
def getHash(td):
	lp=0
	mm=0
	for i in range(0,len(td)):
		if td[i] == ' ':
			lp=i
		if td[i] == 'R':
			mm=mm<<1
		if td[i] == 'L':
			mm=mm<<1|1
	mm=mm*100+lp
	return mm
def solve(data):
	hs=[]
	ans=[]
	mem=[]
	tmem=[]
	cntR=0
	cntL=0
	sp=0
	l=len(data)
	for i in range(0,l):		
		if data[i] == 'R':
			cntR=cntR+1
		elif data[i] == 'L':
			cntL=cntL+1
		else:
			sp=i
	tar=""	
	for i in range(0,cntR):
		tar=tar+'R'
	tar=tar+" "
	for i in range(0,cntL):
		tar=tar+'L'
	
	q=Queue.Queue()
	ts=0
	while data[ts] == 'R':
		ts=ts+1
	te=l-1
	while data[te] == 'L':
		te=te-1
	mem.append((data,sp,-1,ts,te))
	q.put(0)
	
	while q.empty() == False:
		t=q.get()
		pa=t
		sp=mem[t][1]
		data=mem[t][0]
		ts=mem[t][3]
		te=mem[t][4]
		#print data,l,t
		#raw_input()
		if cmp(data,tar) == 0:
			ans.append(mem[t][1]+1)
			t=mem[t][2]
			while True:
				ans.append(mem[t][1]+1)
				if mem[t][2] == -1:
					break				
				t=mem[t][2]
			break
		if sp != ts:
			td=""
			for i in range(0,sp-1):
				td=td+data[i]
			td=td+' '+data[sp-1]
			for i in range(sp+1,l):
				td=td+data[i]
			sp=sp-1
			mm=getHash(td)
			if mm not in tmem:
				tmem.append(mm)
				tss=0
				while td[tss] == 'R':
					tss=tss+1
				tee=l-1
				while td[tee] == 'L':
					tee=tee-1
				mem.append((td,sp,pa,tss,tee))
				q.put(len(mem)-1)	
			sp=sp+1
		if sp != te:
			td=""
			for i in range(0,sp):
				td=td+data[i]		
			td=td+data[sp+1]+' '
			for i in range(sp+2,l):
				td=td+data[i]
			sp=sp+1
			mm=getHash(td)
			if mm not in tmem:
				tmem.append(mm)
				tss=0
				while td[tss] == 'R':
					tss=tss+1
				tee=l-1
				while td[tee] == 'L':
					tee=tee-1
				mem.append((td,sp,pa,tss,tee))
				q.put(len(mem)-1)
			sp=sp-1
		if sp < te-1:
			td=""
			for i in range(0,sp):
				td=td+data[i]
			td=td+data[sp+2]+data[sp+1]+' '
			for i in range(sp+3,l):
				td=td+data[i]
			sp=sp+2
			mm=getHash(td)
			if mm not in tmem:
				tmem.append(mm)
				tss=0
				while td[tss] == 'R':
					tss=tss+1
				tee=l-1
				while td[tee] == 'L':
					tee=tee-1
				mem.append((td,sp,pa,tss,tee))
				q.put(len(mem)-1)
			sp=sp-2
		if sp > ts+1:
			td=""
			for i in range(0,sp-2):
				td=td+data[i]
			td=td+' '+data[sp-1]+data[sp-2]
			for i in range(sp+1,l):
				td=td+data[i]
			sp=sp-2
			
			mm=getHash(td)
			if mm not in tmem:
				tmem.append(mm)
				tss=0
				while td[tss] == 'R':
					tss=tss+1
				tee=l-1
				while td[tee] == 'L':
					tee=tee-1
				mem.append((td,sp,pa,tss,tee))
				q.put(len(mem)-1)
			sp=sp+2
	print ans
	return ans
"""
ditc={}
def dfs(t,sp,dt):
	if t == 15 and sp == True:
		print dt
		ditc[dt]=solve(dt)
		return 0
	elif t == 15 and sp == False:
		return 0
	if sp == False:
		d=dt[:]
		dfs(t+1,True,d+' ')
		dfs(t+1,False,d+'R')
		dfs(t+1,False,d+'L')
	else:
		d=dt[:]
		dfs(t+1,True,d+'R')
		dfs(t+1,True,d+'L')
dfs(0,False,"")

for (k,v) in  ditc.items(): 
	print k
	print v
"""
print hashlib.sha1("GlaqzghhRHOtOzQESCzi").hexdigest()
print "bc5b62455241161bcee24786f0e4bbe5d9d5e423"
address = ('218.2.197.243', 6000)
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(address)

data = s.recv(512)
data = s.recv(512)
m=data.split('"')
print m[1],m[3]
for i in lis:
	for j in lis:
		for k in lis:
			for l in lis:
				if hashlib.sha1(m[1]+i+j+k+l).hexdigest() == m[3]:
					s.send("%c%c%c%c\n" % (i,j,k,l));
					data = s.recv(512)
					print data
					while True:
						data = s.recv(512)
						
						data=data.split('\n')
						print data,len(data)
						if len(data) == 3:
							sd=solve(data[1])
						else:
							sd=solve(data[2])
						for o in range(len(sd)-2,-1,-1):
							s.send(str(sd[o])+"\n")
							print sd[o]
							print s.recv(512)
												

s.close()
