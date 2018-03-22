import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import javax.microedition.rms.*;
import java.util.Random.*;
public class memblock extends GameCanvas implements Runnable
{
	int moves;
	private boolean init=false;
  	static final Font highFont = Font.getFont (Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
	public boolean isplay;
	Memory mem;
	private int width;
	int matched;
	private boolean touchlock; 
	private int height;
	private int Arand[];
	Sprite sp[];
	private Image img=null;
	private Image img1=null;
        private Image hsimg=null;
	private Image i1=null;
	private Image i2=null;
	private Image backup=null;
	private Image bk=null;
        Sprite spriteback=null;
	private static Thread runner;
//	Sprite selsprite;
	private int col,row;
	private int cursor;
	private int cellwidth,cellheight;
	private int selected1,selected2;
	public memblock(Memory mem)
	{
		//installation code
		super(true);
		setFullScreenMode(true);
		this.mem=mem;
		isplay=true;
	}
 	protected void sizeChanged(int w, int h)  
    	{  
        	width = w;  
                height = h;
    	}  
	public void starter(Graphics g)
	{
		int x=0,y=0,i=0;
		while(y<(height-(height%row)))
		{
			while(x<(width-(width%col)) && i<(row*col))
			{
				sp[i].setPosition(x,y);
				sp[i].paint(g);
				i=i+1;
				x=x+cellwidth;	
			}
			x=0;
			y=y+cellheight;
		}
		if(init==false)
		{
			init=true;
			init(g);
		}
	}
	public void painter(Graphics g)
	{
		serviceRepaints();
	}
	public void start()
	{
		matched=0;
		moves=0;
		col=5;
		row=6;
		touchlock=false;
		try
		{
			img=Image.createImage("/tile.png");
                        img1=Image.createImage("/blocks/fruits.png");
			switch(mem.categ)
			{
				case 0:img1=Image.createImage("/blocks/fruits.png");break;
				case 1:img1=Image.createImage("/blocks/cars.png");break;
				case 2:img1=Image.createImage("/blocks/animals.png");break;
				case 3:img1=Image.createImage("/blocks/products.png");break;
				case 4:img1=Image.createImage("/blocks/crystals.png");break;
				case 5:img1=Image.createImage("/blocks/vegetables.png");break;
			}
			bk=Image.createImage("/back.png");
			backup=Image.createImage("/backup.png");
			i1=Image.createImage("/correct.png");
			i2=Image.createImage("/wrong.png");
                        hsimg = Image.createImage("/hs.png");
		}
		catch(Exception e)
		{
			System.out.println("Error creating Background Image : " + e);	
		}
		width=getWidth();
		height=getHeight()-50;
                spriteback=new Sprite(bk);
                spriteback.setPosition(getWidth()-spriteback.getWidth(),getHeight()-spriteback.getHeight());
		rndclass rnd=new rndclass(row,col);
		Arand=rnd.getrandarr();
		cursor=0;
		selected1=-1;
		selected2=-1;
		cellwidth=width/col;
		cellheight=height/row;
		img=Resizer.resizeImagepng(img,cellwidth,cellheight);
		img1=Resizer.resizeImage(img1,cellwidth*(((row*col)/2)+2),cellheight);
		i1=Resizer.resizeImage(i1,cellwidth,cellheight);
		i2=Resizer.resizeImage(i2,cellwidth,cellheight);
		sp=new Sprite[row*col];
		for(int i=0;i<(row*col);i++)
		{
			sp[i]=new Sprite(img1,cellwidth,cellheight);
			sp[i].setFrame(0);
		}
		runner=new Thread(this);
		runner.start();
	}
	private void handlefire(Graphics g)
	{
		if(selected2==-1 && selected1!=-1 && selected1!=cursor)
		{
			selected2=cursor;
			moves=moves+1;
			if(sp[cursor].getFrame()<(row*col)/2)
			{
				sp[selected2].setFrame(Arand[selected2]);
				// added start
				sp[selected2].paint(g);
				flushGraphics();
				try
				{
					Thread.sleep(1500);
				}
				catch(Exception e){}
				// added end
				if(Arand[selected1]==Arand[selected2])
				{
					//Match
					// Animation begin
					matched=matched+2;
					for(int l=0;l<row;l++)
					{
						sp[selected1].setFrame(l);
						sp[selected1].paint(g);
						sp[selected2].setFrame(l);
						sp[selected2].paint(g);
						flushGraphics();
						try
						{
							Thread.sleep(170);
						}
						catch(Exception e){}
					}
					Sprite s=new Sprite(i1);
					int x,y;
					x=sp[selected1].getRefPixelX();
					y=sp[selected1].getRefPixelY();
					s.setPosition(x,y);
					s.paint(g);
					Sprite s1=new Sprite(i1);
					x=sp[selected2].getRefPixelX();
					y=sp[selected2].getRefPixelY();
					s1.setPosition(x,y);
					s1.paint(g);
					flushGraphics();
					try
					{
						Thread.sleep(200);
					}
					catch(Exception e){}
					//Animation end
					sp[selected1].setFrame((row*col)/2+1);
					sp[selected2].setFrame((row*col)/2+1);
					//make selected1,selected2 selected and not to be selected again
					selected1=-1;
					selected2=-1;
					if(matched==(row*col))
					{
						isplay=false;
						for(int i=1;i<height-5;i=i+5)
						{
							g.fillRect(0,0,width,i);
							flushGraphics();
							try
							{
								Thread.sleep(10);
							}
							catch(Exception e){}
						}
						saveplayer(g);
					}
				}
				else
				{
					//No Match
					// Animation begin
					for(int l=0;l<row;l++)
					{
						sp[selected1].setFrame(l);
						sp[selected1].paint(g);
						sp[selected2].setFrame(l);
						sp[selected2].paint(g);
						flushGraphics();
						try
						{
							Thread.sleep(200);
						}
						catch(Exception e){}
					}
					Sprite s=new Sprite(i2);
					int x,y;
					x=sp[selected1].getRefPixelX();
					y=sp[selected1].getRefPixelY();
					s.setPosition(x,y);
					s.paint(g);
					Sprite s1=new Sprite(i2);
					x=sp[selected2].getRefPixelX();
					y=sp[selected2].getRefPixelY();
					s1.setPosition(x,y);
					s1.paint(g);
					flushGraphics();
					try
					{
						Thread.sleep(300);
					}
					catch(Exception e){}
					// Animation end
					sp[selected1].setFrame(0);
					sp[selected2].setFrame(0);
					selected1=-1;
					selected2=-1;
				}
			}
			else
			{
				selected2=-1;
			}
		}
		else if(selected1==-1 && selected2==-1)
		{
			moves=moves+1;
			if(sp[cursor].getFrame()<(row*col)/2)
			{
				selected1=cursor;
				sp[selected1].setFrame(Arand[selected1]);
			}
		}
	}
        protected void pointerPressed(int x, int y)
        {
            if(x>=spriteback.getX() && x<=spriteback.getWidth()+spriteback.getX() && y>=spriteback.getY() && y<=spriteback.getHeight()+spriteback.getY())
            {
                Graphics g=getGraphics();
                spriteback.setImage(backup,backup.getWidth(),backup.getHeight());
                spriteback.paint(g);
                flushGraphics();
            }
        }
	protected void pointerReleased(int x, int y)
	{
            Graphics g=getGraphics();
            spriteback.setImage(bk,bk.getWidth(),bk.getHeight());
            spriteback.paint(g);
            flushGraphics();
            if(x>=spriteback.getX() && x<=(spriteback.getX()+spriteback.getWidth()) && y>=spriteback.getY() && y<=(spriteback.getY()+spriteback.getHeight()))
            {
                isplay=false;
                runner=null;
                mem.showmenu();
            }
            else
            {
		if(!touchlock)
		{
                	touchlock=true;
			int tx,ty;
			tx=(int)Math.ceil((double)x/(double)cellwidth);
			ty=(int)Math.ceil((double)y/(double)cellheight);
			cursor = tx+((ty-1)*col)-1;
			flushGraphics();
			handlefire(g);
		}
                touchlock=false;
            }
	}
	private void init(Graphics g)
	{
		for(int i=0;i<(row*col);i++)
		{
			sp[i].setFrame(Arand[i]);
			sp[i].paint(g);
			try
			{
				flushGraphics();
				Thread.sleep(200);
			}
			catch(Exception e){}
		}
		for(int i=0;i<(row*col);i++)
		{
			sp[i].setFrame(0);
			sp[i].paint(g);
			try
			{
				flushGraphics();
				Thread.sleep(200);
			}
			catch(Exception e){}
		}
	}
	public void run()
	{
            Graphics g = getGraphics();
            while(isplay)
            {
                verifygamestate(g);
                checkuserinput(g);
                updategamescreen(getGraphics());
                try
                {
                    Thread.sleep(90);
                }
                catch(Exception e){}
            }
	}
	private void verifygamestate(Graphics g)
	{
		starter(g);
		painter(g);
	}
	private void checkuserinput(Graphics g)
	{
		int keystate=getKeyStates();
		calculatecoupleX(keystate,g);
	}
	private void updategamescreen(Graphics g)
	{
		g.setColor(0,0,0);
		g.fillRect(0,getHeight()-spriteback.getHeight(),width,spriteback.getHeight());
		g.setFont(highFont);
		g.setColor(250,250,250);
	     	g.drawString("Moves : " + Integer.toString(moves),5,getHeight()-highFont.getHeight()-10,20);
                spriteback.paint(g);
		flushGraphics();
	}
    	public void keyPressed(int kc)
    	{
		if(kc==-7)
		{
                    isplay=false;
                    runner=null;
                    mem.showmenu();
		}
    	}
	private void calculatecoupleX(int keystate,Graphics g)
	{
		if((keystate & LEFT_PRESSED)!=0)
		{
			int a=cursor/col;
			cursor=(Math.max((cursor-1),0)%col)+(a*col);
			repaint();
		}
		else if((keystate & RIGHT_PRESSED)!=0)
		{
			int a=cursor/col;
			cursor=((cursor+1)%col)+(a*col);
			repaint();
		}
		else if((keystate & UP_PRESSED)!=0)
		{
			cursor=cursor-row;
			if(cursor<0)
			{
				cursor=(row*col)+cursor;
			}
			repaint();
		}
		else if((keystate & DOWN_PRESSED)!=0)
		{
			cursor=cursor+col;
			if(cursor>(row*col-1))
			{
				cursor=cursor%(row*col);
			}
			repaint();
		}
		else if((keystate & FIRE_PRESSED)!=0)
		{
			handlefire(g);
		}
	}
	private void saveplayer(Graphics g)
	{
		RecordStore rs = null;
		String samp="";
		try
		{
			rs = RecordStore.openRecordStore("memblock",true);
			if(rs.getNumRecords()>0)	
			{
				byte b[] = rs.getRecord(1);
				samp = new String(b,0,b.length);
			}
			rs.closeRecordStore();
			// save
			String name="";
			try
			{
				RecordStore.deleteRecordStore("memblock");
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			} 
			String s="";
			if(samp.equals(""))
			{
				name= moves + "-PLAYER";
				s=name;
			}
			else
			{
				name= moves + "." + samp + "." + "PLAYER";
				s=sortclass.getstr(name);
			}
			rs = RecordStore.openRecordStore("memblock",true);
			byte[] byteOutputData = String.valueOf(s).toString().getBytes();			
			rs.addRecord(byteOutputData,0,byteOutputData.length);
                        rs.closeRecordStore();
			highscorebackmenu();
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
		flushGraphics(); 
	}
	private void highscorebackmenu()
	{
		Graphics g = getGraphics();
		RecordStore rs = null;
		String samp="";
		try
		{
			rs = RecordStore.openRecordStore("memblock",true);
			if(rs.getNumRecords()>0)	
			{
				byte b[] = rs.getRecord(1);
				samp = new String(b,0,b.length);
			}
			else
			{
				samp="";
			}
			rs.closeRecordStore();
		}
		catch(Exception e){} 
                g.drawImage(hsimg,width/2,height/2,Graphics.VCENTER|Graphics.HCENTER);
                spriteback.paint(g);
                g.setColor(250,250,250);
                int scoreheight=140;
                g.setFont(highFont);
                scoreheight=scoreheight+highFont.getHeight();
		if(samp!="")
		{
			// sortclass so=new sortclass();
			String s[]=sortclass.getscore(samp);
		//	so=null;
			for(int i=0;i<s.length;i++)
			{
				if(s[i]!=null)
				{
				//	g.drawString(s[i],25,(i+1)*25+25,20);
                                    	g.drawString(s[i],85,(i+1)*40+scoreheight,20);
				}
				else
				{
					g.drawString("PLAYER	--",85,(i+1)*40+scoreheight,20);
				}
			}
		}
		else
		{
			for(int i=1;i<=4;i++)
			{
				g.drawString("PLAYER	--",85,(i+1)*40+scoreheight,20);
			}
		}
		flushGraphics();
	}	
}