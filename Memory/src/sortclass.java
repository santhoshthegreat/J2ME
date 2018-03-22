import java.io.*;
public class sortclass
{
	private static int highscorecount=4;
	public static String getstr(String str)
	{
		String[] tmp={};
		String[] names={};
		String[] scores={};
		String s="";	
		StringTokenizer st=new StringTokenizer(str,"-");
		while(st.hasMoreTokens())
		{
			tmp=st.toArray();
		}	
		// tmp.length is of 2 segments
		StringTokenizer st1=new StringTokenizer(tmp[1],".");
		names=st1.toArray();		
		StringTokenizer st2=new StringTokenizer(tmp[0],".");
		scores=st2.toArray();
		tmp=null;
		st1=null;
		st2=null;
//		int[] a=new int[scores.length];
		int[] a;
		if(highscorecount<scores.length)
		{
			a=new int[highscorecount];
		}
		else
		{
			a=new int[scores.length];
		}
		for(int i=0;i<scores.length && i<highscorecount;i++)			{
			a[i]=Integer.parseInt(scores[i]);
		}
        	for (int i=a.length; --i>= 0;)
		{
			for (int j=0;j<i;j++)
			{
                		if(a[j]>a[j+1])
				{
                    		int temp=a[j];
					String stemp=names[j];
                    		a[j]=a[j+1];
					names[j]=names[j+1];
                    		a[j+1]=temp;
					names[j+1]=stemp;
                		}
            	}
        	}
		for(int i=0;i<a.length;i++)
		{
			s=s+Integer.toString(a[i]);
			if(i!=(a.length-1))
			{
				s=s+".";	
			}
		}
		s = s + "-";
		for(int i=0;i<names.length;i++)
		{
			s=s+names[i];
			if(i!=(names.length-1))
			{
				s=s+".";
			}
		}
		return(s);
	}
	public static String[] getscore(String str)
	{
		String[] tmp={};
		String[] names={};
		String[] scores={};
		StringTokenizer st=new StringTokenizer(str,"-");
		while(st.hasMoreTokens())
		{
			tmp=st.toArray();
		}	
		// tmp.length is of 2 segments
		StringTokenizer st2=new StringTokenizer(tmp[0],".");
		scores=st2.toArray();
		StringTokenizer st1=new StringTokenizer(tmp[1],".");
		names=st1.toArray();
		tmp=null;
		st1=null;
		st2=null;
		int[] a;
		if(highscorecount<scores.length)
		{
			a=new int[highscorecount];
		}
		else
		{
			a=new int[scores.length];
		}
		for(int i=0;i<scores.length && i<highscorecount;i++)	
		{
			a[i]=Integer.parseInt(scores[i]);
		}
        	for (int i=a.length; --i>= 0;)
		{
			for (int j = 0; j < i; j++)
			{
                		if (a[j] > a[j+1])
				{
                    		int temp = a[j];
					String stemp=names[j];
                    		a[j] = a[j+1];
					names[j]=names[j+1];
                    		a[j+1] = temp;
					names[j+1]=stemp;
                		}
            		}
        	}
		String s[]=new String[a.length];
		for(int i=0;i<a.length;i++)
		{
			s[i]=names[i] + " - " + Integer.toString(a[i]);
		}
		return(s);
	}
}