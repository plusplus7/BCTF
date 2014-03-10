import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Scanner;
import java.io.*;
public class Main 
{
	public static int getHash(String td)
	{
		int lp=0;
		int mm=0;
		for (int i=0; i<td.length(); i++)
		{
			if (td.charAt(i) == ' ')
				lp=i;
			if (td.charAt(i) == 'R')
				mm=mm<<1;
			if (td.charAt(i) == 'L')
				mm=mm<<1|1;
		}
		mm=mm*100+lp;
		return mm;
	}
	public static ArrayList<Integer> solve(String st)
	{
		ArrayList<Integer> ans=new ArrayList<Integer>();
		ArrayList<Node> mem=new ArrayList<Node>();
		Set<Integer> s = new HashSet<Integer>();
		Queue<Integer> q = new LinkedList<Integer>(); 
		int l=st.length();
		int cntR=0,cntL=0,sp=0;
		for (int i=0; i<l; i++)
		{
			if (st.charAt(i) == 'R')
				cntR++;
			else if (st.charAt(i) == 'L')
				cntL++;
			else
				sp=i;
		}
		String tar="";
		for (int i=0; i<cntR; i++)
			tar=tar+"R";
		tar=tar+" ";
		for (int i=0; i<cntL; i++)
			tar=tar+"L";
		q.clear();
		s.clear();
		mem.clear();
		int ts=0,te=0;
		while (st.charAt(ts) == 'R')
			ts++;
		te=l-1;
		while (st.charAt(te) == 'L')
			te--;
		mem.add(new Node(st,sp,-1,ts,te));
		q.offer(0);
		while (!q.isEmpty())
		{
			int t=q.poll();
			int pa=t;
			sp=mem.get(t).sp;
			
			String data=mem.get(t).data;
			ts=mem.get(t).ts;
			te=mem.get(t).te;
			if (data.compareTo(tar) == 0)
			{
				ans.clear();
				ans.add(mem.get(t).sp+1);
				t=mem.get(t).pa;
				while (true)
				{
					ans.add(mem.get(t).sp+1);
					if (mem.get(t).pa == -1)
							break;
					t=mem.get(t).pa;
				}
			}
			if (sp != 0)
			{
				String td="";
				for (int i=0; i<sp-1; i++)
					td=td+data.charAt(i);
				td=td+" "+data.charAt(sp-1);
				for (int i=sp+1; i<l; i++)
					td=td+data.charAt(i);
				
				sp=sp-1;
				int mm=getHash(td);
				if (s.contains(mm) == false)
				{
					s.add(mm);
					int tss=0,tee=0;
					while (data.charAt(tss) == 'R')
						tss++;
					tee=l-1;
					while (data.charAt(tee) == 'L')
						tee--;
					mem.add(new Node(td,sp,pa,tss,tee));
					q.offer(mem.size()-1);
				}
				sp=sp+1;
			}
			if (sp != l-1)
			{
				String td="";
				for (int i=0; i<sp; i++)
					td=td+data.charAt(i);
				td=td+data.charAt(sp+1)+" ";
				for (int i=sp+2; i<l; i++)
					td=td+data.charAt(i);
				sp=sp+1;
				int mm=getHash(td);
				if (s.contains(mm) == false)
				{
					s.add(mm);
					int tss=0,tee=0;
					while (data.charAt(tss) == 'R')
						tss++;
					tee=l-1;
					while (data.charAt(tee) == 'L')
						tee--;
					mem.add(new Node(td,sp,pa,tss,tee));
					q.offer(mem.size()-1);
				}
				sp=sp-1;
			}
			
			if (sp < l-2)
			{
				String td="";
				for (int i=0; i<sp; i++)
					td=td+data.charAt(i);
				td=td+data.charAt(sp+2)+data.charAt(sp+1)+" ";
				for (int i=sp+3; i<l; i++)
					td=td+data.charAt(i);
				sp=sp+2;
				int mm=getHash(td);
				if (s.contains(mm) == false)
				{
					s.add(mm);
					int tss=0,tee=0;
					while (data.charAt(tss) == 'R')
						tss++;
					tee=l-1;
					while (data.charAt(tee) == 'L')
						tee--;
					mem.add(new Node(td,sp,pa,tss,tee));
					q.offer(mem.size()-1);
				}
				sp=sp-2;
			}
			if (sp > 1)
			{
				String td="";
				for (int i=0; i<sp-2; i++)
					td=td+data.charAt(i);
				td=td+" "+data.charAt(sp-1)+data.charAt(sp-2);
				for (int i=sp+1; i<l; i++)
					td=td+data.charAt(i);
				sp=sp-2;
				int mm=getHash(td);
				if (s.contains(mm) == false)
				{
					s.add(mm);
					int tss=0,tee=0;
					while (data.charAt(tss) == 'R')
						tss++;
					tee=l-1;
					while (data.charAt(tee) == 'L')
						tee--;
					mem.add(new Node(td,sp,pa,tss,tee));
					q.offer(mem.size()-1);
				}
				sp=sp+2;
			}
		}
		return ans;	
	}
	public static void main(String[] args) throws UnknownHostException, IOException 
	{
		String mui="1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int lm=mui.length();
		String host = "218.2.197.242";  //要连接的服务端IP地址  
		int port = 6000;   //要连接的服务端对应的监听端口  
		//与服务端建立连接  
		Socket client = new Socket(host, port);  
		//建立连接后就可以往服务端写数据了  

		BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		PrintWriter printWriter =new PrintWriter(client.getOutputStream(),true);

		System.out.println("1."+in.readLine()); 
		System.out.println("2."+in.readLine()); 
		System.out.println("3."+in.readLine()); 
		String ts=in.readLine();
		String[] tmp=ts.split("\"");
		String sha=new String("");
		for (int i=0; i<lm; i++)
			for (int j=0; j<lm; j++)
				for (int k=0; k<lm; k++)
					for (int l=0; l<lm; l++)
					{
						if (EncryptUtil.encrypt(tmp[1]+mui.charAt(i)+mui.charAt(j)+mui.charAt(k)+mui.charAt(l), "SHA-1").compareTo(tmp[3]) == 0)
						{
							System.out.println(EncryptUtil.encrypt(tmp[1]+mui.charAt(i)+mui.charAt(j)+mui.charAt(k)+mui.charAt(l), "SHA-1")+" "+tmp[3]);
							sha=sha+mui.charAt(i)+mui.charAt(j)+mui.charAt(k)+mui.charAt(l);
							k=lm;
							j=lm;
							i=lm;
							break;
						}
					}
		printWriter.println(sha);
		printWriter.flush();
		
		System.out.println("4."+in.readLine()+" "+sha); 
		System.out.println("5."+in.readLine()); 
		while (true)
		{
			System.out.println("6."+in.readLine()); 
			System.out.println("7."+in.readLine()); 
			String st=in.readLine();
			System.out.println("8."+st); //!!!!
			ArrayList<Integer> al=solve(st);
			for (int i=al.size()-2; i>=0; i--)
			{
				printWriter.println(al.get(i).toString());
				printWriter.flush();
				System.out.println("res."+in.readLine());
			}
		}
		//client.close();  
	}
}
class Node
{
	public String data;
	public Node() {
	}
	public Node(String data,int sp,int pa,int ts,int te) {
		this.data=data;
		this.sp=sp;
		this.pa=pa;
		this.ts=ts;
		this.te=te;
		
	}
	public int sp;
	public int pa;
	public int ts;
	public int te;
}
