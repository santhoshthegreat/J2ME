import java.util.Random.*;
import java.util.*;
public class rndclass
{
	private Random rd = null;
	private int[] Arand;
	private int[] arr;
	private int[] random(int[] src)
 	{
	    	if(src == null)
		{
	   		return null; 
	  	}
	 	rd = new Random();
	   	int[] tmp = new int[src.length];
	   	int num = src.length;
	 	int index;
		for(int i = 0;i < src.length;i++)
	  	{
	     		index = Math.abs(rd.nextInt()) % num;
	    		tmp[i] = src[index];
	      	src[index] = src[num - 1];
	      	num--;
	  	}
	  	return tmp;
	}
	public rndclass(int row,int col)
	{
		Arand=new int[row*col];
		int pair= (col * row) / 2;
		int[] a=new int[(col*row)/2];
		int[] b=new int[(col*row)/2];
		for(int i=0;i<pair;i++)
		{
			a[i]=i+1;
		}
		int a1[] = random(a);
		for(int i=0;i<pair;i++)
		{
			b[i]=i+1;
		}
		int b1[] = random(b);
		for(int i=0;i<a1.length;i++)			
		{
			Arand[i]=a1[i];
		}
		int j=0;
		for(int i=((row*col)/2);i<(row*col);i++)			
		{
			Arand[i]=b1[j];
			j=j+1;
		}
		arr=new int[row*col];
		arr = random(Arand);
	}
	public int[] getrandarr()
	{
		return(arr);
	}
}