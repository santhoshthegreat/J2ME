package forex;
import java.io.IOException;
import javax.microedition.rms.*;

import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
class Mycanvas extends GameCanvas implements Runnable
{
	String[] units_to_db={"AUD","USD","ZWD","EUR","GBP","SGD","JPY","CNY","IDR"};
	String[] rates_to_db={"0","0","0","0","0","0","0","0","0"};
	public volatile String txt="0";
	public volatile int unit=0;
	private boolean iscompleted=false;
	TotalForexConverter tfc;
	private int width;        // To hold screen width
	private int height;       // To hold screen height
	private boolean isplay;
	private int tmp=20;
	Graphics g;
	private static Process process;
	private Sprite loadSprite;
	Image ok=null;
	Image ok1=null;
	public Mycanvas(TotalForexConverter tfc)
	{
		super(true);
		this.tfc=tfc;
		isplay=true;
		height=getHeight();
		width=getWidth();
		iscompleted=false;
		Image loadImage=null;
		try {
			loadImage = Image.createImage("/load.png");
			ok = Image.createImage("/ok.png");
			ok1 = Image.createImage("/ok1.png");
			loadSprite = new Sprite(loadImage,36,36);
			loadSprite.setFrame(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	protected void pointerPressed(int x,int y)
	{
		if(iscompleted==true)
		{
			g.drawImage(ok1,(getWidth()/2)-(ok.getWidth()/2),(getHeight()/2)-(ok.getHeight()/2),Graphics.LEFT|Graphics.TOP);
			flushGraphics();
			try
			{
				Thread.sleep(100);
			}
			catch(Exception e)
			{		
			}	
		}
	}
	protected void pointerReleased(int x,int y)
	{
		if(iscompleted==true)
		{
			isplay=false;
			TotalForexConverter.m.show();
			System.gc();
		}
	}
	protected void sizeChanged(int newWidth, int newHeight)
	{
		height=newHeight;
		width=newWidth;		
	}
	public void start()
	{
		Thread t = new Thread(this);
		t.start();
		process = new Process(this);
		process.start();
	}
	public synchronized void update()
	{
		synchronized(this)
		{

			g.setColor(0xFFFFFF);
			g.fillRect(0, 0, width, height);
			g.setColor(0x000000);
	//		g.drawString(unit + "-" + txt,30,100 + tmp,Graphics.LEFT|Graphics.TOP);
			tmp=tmp+20;
			flushGraphics();
			rates_to_db[unit]=txt;
			if(unit==rates_to_db.length-1)
			{
								
				String s="";
				for(int i=0;i<rates_to_db.length;i++)
				{
					s=s + units_to_db[i]+"-"+rates_to_db[i];
					if (i==rates_to_db.length-1)
					{}
					else
					{
						s=s+",";
					}
				}
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH)+1;
				int date = calendar.get(Calendar.DATE);
				String index="";
				
				// if null create 31 records else leave alone
				
				try
				{
				RecordStore rs = null;
				rs = RecordStore.openRecordStore("i"+month+"."+year,true);
				if(rs.getNumRecords()==0)
				{
					for(int i=0;i<=31;i++)
					{
						byte[] byteOutputData = String.valueOf("0").getBytes();
						rs.addRecord(byteOutputData,0,0);	
					}
				}
				rs.closeRecordStore();
				rs=null;
				}
				catch (Exception e)
				{
					System.out.println(e.toString());
				}
				

				
				// identify recordnum null or not
				try
				{
				RecordStore rs = null;
				rs = RecordStore.openRecordStore("i"+month+"."+year,true);
				if(rs.getRecord(date)==null)
				{
					//System.out.println("Null");
					// add new forex data in month.year recordstore passing data string s
					index=forexaddnew(date,month,year,s);
					byte[] byteOutputData = String.valueOf(index).getBytes();	
					rs.setRecord(date,byteOutputData,0,byteOutputData.length);
				}
				rs.closeRecordStore();
				rs=null;
				}
				catch (Exception e)
				{
					System.out.println(e.toString());
				}
				
				
				int recordidval;
				
				// get index for record values
				try
				{
				RecordStore rs = null;
				rs = RecordStore.openRecordStore("i" + month+"."+year,false);
				byte[] recData = rs.getRecord(date);
				String recordid=new String(recData);
				recordid=recordid.toString().substring(recordid.indexOf('.')+1);
				recordidval=Integer.parseInt(recordid);	
	//			System.out.println("#RecID: " + recordid );
				rs.closeRecordStore();
				rs=null;
				}
				catch (Exception e)
				{
					recordidval=0;	
					System.out.println(e.toString());
				}
				
	
	
				try
				{
				RecordStore rs = null;
				rs = RecordStore.openRecordStore(month+"."+year,false);
				byte[] recData = new byte[rs.getRecordSize(recordidval)];
				recData=rs.getRecord(recordidval);
			//	System.out.println("#RecID: 1 " + new String(recData));
				rs.closeRecordStore();
				rs=null;
				}
				catch (Exception e)
				{
					System.out.println(e.toString());
				}
				
				iscompleted=true;
				try
				{
					Thread.sleep(100);
				}
				catch(Exception e)
				{
				}
				try
				{
					Thread.yield();
				}
				catch(Exception e)
				{}
				
				
				// the end
				
				g.setColor(0xFFFFFF);
				g.fillRect(0, 0, width, height);
				g.setColor(0x000000);
				g.drawImage(ok,(getWidth()/2)-(ok.getWidth()/2),(getHeight()/2)-(ok.getHeight()/2),Graphics.LEFT|Graphics.TOP);
				String str="Update Successful";
				g.drawString(str,(getWidth()/2)-(g.getFont().stringWidth(str)/2),(getHeight()/2)-(g.getFont().getHeight()/2)+ok.getHeight(),Graphics.LEFT|Graphics.TOP);
				flushGraphics();
				
				isplay=false;
		//		System.out.println("update over");
				
			}
		}
	}
	private String forexaddnew(int date,int month,int year,String s)
	{
		String index=null;
		try
		{
			RecordStore rs = null;
			rs = RecordStore.openRecordStore(month+"."+year,true);
			byte[] byteOutputData = String.valueOf(s).toString().getBytes();			
			int recordnum=0;
			recordnum=rs.addRecord(byteOutputData,0,byteOutputData.length);
			rs.closeRecordStore();
			rs=null;
			index=date + "." + Integer.toString(recordnum);
			// 	save the values date.recordid[index] in index datastore
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
		return index;
	}
	public void run() {
		// TODO Auto-generated method stub
		g = getGraphics();
		while (isplay == true)
		{
			while(iscompleted==false)
			{
		//		System.out.println("loop");
				g.setColor(0xFFFFFF);
				g.fillRect(0, 0, width, height);
				g.setColor(0x000000);
				g.drawString("Loading...",(getWidth()/2)-(loadSprite.getWidth()/2),getHeight()/2+(loadSprite.getHeight()/2),Graphics.LEFT|Graphics.TOP);
				loadSprite.setFrame((loadSprite.getFrame()+1)%loadSprite.getFrameSequenceLength());
				loadSprite.setPosition((getWidth()/2)-(loadSprite.getWidth()/2),getHeight()/2-(loadSprite.getHeight()/2));
				loadSprite.paint(g);
		      	flushGraphics();	
				try
				{
					Thread.sleep(100);
				}
				catch(Exception e)
				{
				}
				if(iscompleted==false)
				{
					g.setColor(0xFFFFFF);
					g.fillRect(0, 0, width, height);
					g.setColor(0x000000);
					g.drawString("Loading.",(getWidth()/2)-(loadSprite.getWidth()/2),getHeight()/2+(loadSprite.getHeight()/2),Graphics.LEFT|Graphics.TOP);
					loadSprite.setFrame((loadSprite.getFrame()+1)%loadSprite.getFrameSequenceLength());
					loadSprite.setPosition((getWidth()/2)-(loadSprite.getWidth()/2),getHeight()/2-(loadSprite.getHeight()/2));
					loadSprite.paint(g);
					flushGraphics();
				}
			}
		//	g.setColor(250);
		//	g.fillRect(0, 100, width, height);
		//	g.setColor(250);
		//	g.drawString(txt,30,100,Graphics.LEFT|Graphics.TOP);
		//	flushGraphics();
	//		System.out.println("isplay loop");
	    }
	}
}

class Process implements Runnable
{
	final String[] rates={"AUD","USD","ZWD","EUR","GBP","SGD","JPY","CNY","IDR"};
	final private Mycanvas mc;
	public Process(Mycanvas mc)
	{
		this.mc = mc;
	}
	public void run()
	{
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try
		{
			// send url parameter to transmit and push the values to db
			for(int i=0;i<rates.length;i++)
			{
				transmit("INR",rates[i],i);	
			}
		}
		catch (Exception error)
		{
			System.err.println(error.toString());
		}
	}
	public void start()
	{
		Thread thread = new Thread(this);
		try
		{
			thread.start();
		}
		catch (Exception error)
		{
		}
	}
	private void transmit(String from,String to,int i) throws IOException, InterruptedException
	{
		String url = "http://www.trailets.com/forex-new.php?from=" + from + "&to=" + to;
		//	Place code here to receive or send transmission.
//		Thread.sleep(100);
		synchronized(mc)
		{
		//	System.out.println(fc.txt);
			StreamConnection sc = null;
			InputStream is = null;
			StringBuffer buffer = new StringBuffer();
			try
			{
				sc = (StreamConnection)Connector.open(url);
				is = sc.openInputStream();
				int chars;
				while((chars = is.read()) != -1)
				{
					buffer.append((char) chars);
				}
				// System.out.println(buffer.toString());
			}
                        catch(Exception e)
                        {
                           buffer.append("0");
                        }
			finally
			{
				if(is != null)
				{
					is.close();
				}
				if(sc != null)
				{
					sc.close();
				}
			}
			synchronized(mc)
			{
				mc.txt=buffer.toString();
				mc.unit=i;
			}
			mc.update();
		}
	} 
}
