import javax.microedition.lcdui.*;
import java.io.*;
import java.util.*;
import javax.microedition.lcdui.game.Sprite;
import net.mypapit.java.StringTokenizer;
public class MyCanvas extends Canvas
{
//	public Graphics g;
        static final Font highFont = Font.getFont (Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
	int screenWidth;
	int screenHeight;
	FontUtil fu;
	public boolean locked=false;
	Thirukkural t;
	InputStream tis,tts;
//	StringBuffer k=new StringBuffer();
	int currentkural;
	String title,ttl;
	Image im;
        Image bgimage;
        Image imgmenu;
        Image imgprev,imgnext;
        Sprite spriteprev,spritenext;
        Sprite spritemenu;
	public MyCanvas(Thirukkural t)
	{
		setFullScreenMode(true);
		this.t=t;
		this.screenWidth = getWidth();
		this.screenHeight = getHeight();
		im=null;
                try
		{
                im=Image.createImage("/ok.png");
                im=Imagemanip.resizeImage(im,screenWidth,50);
                imgprev = Image.createImage("/prev.png");
                imgnext = Image.createImage("/next.png");
                imgmenu = Image.createImage("/menu.png");

                bgimage = Image.createImage("/bg.jpg");
                if(screenWidth!=640 || screenHeight!=640)
                {
                    bgimage=Imagemanip.resizeImage(bgimage,screenWidth,screenHeight);
                }
		FontUtil.initialize(screenWidth);
		fu = FontUtil.getInstance();
                spriteprev=new Sprite(imgprev,70,50);
                spritenext=new Sprite(imgnext,70,50);
                spritemenu=new Sprite(imgmenu,70,60);
		}
		catch (Exception e)
		{
		}
	}
	public void start(int kural)
	{
		fu.x=0;
		fu.y=0;
		currentkural=kural;
	}
	protected void paint(Graphics g)
	{
//                g.setColor(140,210,100);
//		g.fillRect(0,0,screenWidth,screenHeight);
                g.drawImage(bgimage,0,0,Graphics.TOP|Graphics.LEFT);
		g.drawImage(im,0,0,Graphics.TOP|Graphics.LEFT);

                spriteprev.setPosition(0,1);
                spriteprev.paint(g);
                spritenext.setPosition(screenWidth-spritenext.getWidth(),1);
                spritenext.paint(g);
                spritemenu.setPosition(screenWidth-spritemenu.getWidth(),screenHeight-spritemenu.getHeight());
                spritemenu.paint(g);
		try
		{
			StringBuffer s=new StringBuffer("");
			int bytesread=1;
			int comein=1;
			byte[] buf;
			String k="";
			String out=".";
			String th="";
			if(currentkural<381)
			{
				title="/Arathupal";
			}
			else if(currentkural<1081) 
 			{
				title="/porutpal";
			}
			else 
 			{
				title="/kamathupal";
			}
			if(currentkural<11) 
 			{
				th="1.bin";
			}
			else if(currentkural<21) 
 			{
				th= "2.bin";
			}
			else if(currentkural<31) 
 			{
				th= "3.bin";
			}
			else if(currentkural<41) 
 			{
				th= "4.bin";
			}
			else if(currentkural<51) 
 			{
				th="5.bin";
			}
			else if(currentkural<61) 
 			{
				th="6.bin";
			}
			else if(currentkural<71) 
 			{
				th="7.bin";
			}
			else if(currentkural<81) 
 			{
				th="8.bin";
			}
			else if(currentkural<91) 
 			{
				th="9.bin";
			}
			else if(currentkural<101) 
 			{
				th="10.bin";
			}
			else if(currentkural<111) 
 			{
				th="11.bin";
			}
			else if(currentkural<121) 
 			{
				th="12.bin";
			}
			else if(currentkural<131) 
 			{
				th="13.bin";
			}
			else if(currentkural<141) 
 			{
				th="14.bin";
			}
			else if(currentkural<151) 
 			{
				th="15.bin";
			}
			else if(currentkural<161) 
 			{
				th="16.bin";
			}
			else if(currentkural<171) 
 			{
				th="17.bin";
			}
			else if(currentkural<181) 
 			{
				th="18.bin";
			}
			else if(currentkural<191) 
 			{
				th="19.bin";
			}
			else if(currentkural<201) 
 			{
				th="20.bin";
			}
			else if(currentkural<211) 
 			{
				th="21.bin";
			}
			else if(currentkural<221) 
 			{
				th="22.bin";
			}
			else if(currentkural<231) 
 			{
				th="23.bin";
			}
			else if(currentkural<241) 
 			{
				th="24.bin";
			}
			else if(currentkural<251) 
 			{
				th="25.bin";
			}
			else if(currentkural<261) 
 			{
				th="26.bin";
			}
			else if(currentkural<271) 
 			{
				th="27.bin";
			}
			else if(currentkural<281) 
 			{
				th="28.bin";
			}
			else if(currentkural<291) 
 			{
				th="29.bin";
			}
			else if(currentkural<301) 
 			{
				th="30.bin";
			}
			else if(currentkural<311) 
 			{
				th="31.bin";
			}
			else if(currentkural<321) 
 			{
				th= "32.bin";
			}
			else if(currentkural<331) 
 			{
				th= "33.bin";
			}
			else if(currentkural<341) 
 			{
				th= "34.bin";
			}
			else if(currentkural<351) 
 			{
				th= "35.bin";
			}
			else if(currentkural<361) 
 			{
				th= "36.bin";
			}
			else if(currentkural<371) 
 			{
				th= "37.bin";
			}
			else if(currentkural<381) 
 			{
				th= "38.bin";
			}
			else if(currentkural<391) 
 			{
				th= "39.bin";
			}
			else if(currentkural<401) 
 			{
				th= "40.bin";
			}
			else if(currentkural<411) 
 			{
				th= "41.bin";
			}
			else if(currentkural<421) 
 			{
				th= "42.bin";
			}
			else if(currentkural<431) 
 			{
				th= "43.bin";
			}
			else if(currentkural<441) 
 			{
				th= "44.bin";
			}
			else if(currentkural<451) 
 			{
				th= "45.bin";
			}
			else if(currentkural<461) 
 			{
				th= "46.bin";
			}
			else if(currentkural<471) 
 			{
				th= "47.bin";
			}
			else if(currentkural<481) 
 			{
				th= "48.bin";
			}
			else if(currentkural<491) 
 			{
				th= "49.bin";
			}
			else if(currentkural<501) 
 			{
				th= "50.bin";
			}
			else if(currentkural<511) 
 			{
				th= "51.bin";
			}
			else if(currentkural<521) 
 			{
				th= "52.bin";
			}
			else if(currentkural<531) 
 			{
				th= "53.bin";
			}
			else if(currentkural<541) 
 			{
				th= "54.bin";
			}
			else if(currentkural<551) 
 			{
				th= "55.bin";
			}
			else if(currentkural<561) 
 			{
				th= "56.bin";
			}
			else if(currentkural<571) 
 			{
				th= "57.bin";
			}
			else if(currentkural<581) 
 			{
				th= "58.bin";
			}
			else if(currentkural<591) 
 			{
				th= "59.bin";
			}
			else if(currentkural<601) 
 			{
				th= "60.bin";
			}
			else if(currentkural<611) 
 			{
				th= "61.bin";
			}
			else if(currentkural<621) 
 			{
				th= "62.bin";
			}
			else if(currentkural<631) 
 			{
				th= "63.bin";
			}
			else if(currentkural<641) 
 			{
				th= "64.bin";
			}
			else if(currentkural<651) 
 			{
				th= "65.bin";
			}
			else if(currentkural<661) 
 			{
				th= "66.bin";
			}
			else if(currentkural<671) 
 			{
				th= "67.bin";
			}
			else if(currentkural<681) 
 			{
				th= "68.bin";
			}
			else if(currentkural<691) 
 			{
				th= "69.bin";
			}
			else if(currentkural<701) 
 			{
				th= "70.bin";
			}
			else if(currentkural<711) 
 			{
				th= "71.bin";
			}
			else if(currentkural<721) 
 			{
				th= "72.bin";
			}
			else if(currentkural<731) 
 			{
				th= "73.bin";
			}
			else if(currentkural<741) 
 			{
				th= "74.bin";
			}
			else if(currentkural<751) 
 			{
				th= "75.bin";
			}
			else if(currentkural<761) 
 			{
				th= "76.bin";
			}
			else if(currentkural<771) 
 			{
				th= "77.bin";
			}
			else if(currentkural<781) 
 			{
				th= "78.bin";
			}
			else if(currentkural<791) 
 			{
				th= "79.bin";
			}
			else if(currentkural<801) 
 			{
				th= "80.bin";
			}
			else if(currentkural<811) 
 			{
				th= "81.bin";
			}
			else if(currentkural<821) 
 			{
				th= "82.bin";
			}
			else if(currentkural<831) 
 			{
				th= "83.bin";
			}
			else if(currentkural<841) 
 			{
				th= "84.bin";
			}
			else if(currentkural<851) 
 			{
				th= "85.bin";
			}
			else if(currentkural<861) 
 			{
				th= "86.bin";
			}
			else if(currentkural<871) 
 			{
				th= "87.bin";
			}
			else if(currentkural<881) 
 			{
				th= "88.bin";
			}
			else if(currentkural<891) 
 			{
				th= "89.bin";
			}
			else if(currentkural<901) 
 			{
				th= "90.bin";
			}
			else if(currentkural<911) 
 			{
				th= "91.bin";
			}
			else if(currentkural<921) 
 			{
				th= "92.bin";
			}
			else if(currentkural<931) 
 			{
				th= "93.bin";
			}
			else if(currentkural<941) 
 			{
				th= "94.bin";
			}
			else if(currentkural<951) 
 			{
				th= "95.bin";
			}
			else if(currentkural<961) 
 			{
				th= "96.bin";
			}
			else if(currentkural<971) 
 			{
				th= "97.bin";
			}
			else if(currentkural<981) 
 			{
				th= "98.bin";
			}
			else if(currentkural<991) 
 			{
				th= "99.bin";
			}
			else if(currentkural<1001) 
 			{
				th= "100.bin";
			}
			else if(currentkural<1011) 
 			{
				th= "101.bin";
			}
			else if(currentkural<1021) 
 			{
				th= "102.bin";
			}
			else if(currentkural<1031) 
 			{
				th= "103.bin";
			}
			else if(currentkural<1041) 
 			{
				th= "104.bin";
			}
			else if(currentkural<1051) 
 			{
				th= "105.bin";
			}
			else if(currentkural<1061) 
 			{
				th= "106.bin";
			}
			else if(currentkural<1071) 
 			{
				th= "107.bin";
			}
			else if(currentkural<1081) 
 			{
				th= "108.bin";
			}
			else if(currentkural<1091) 
 			{
				th= "109.bin";
			}
			else if(currentkural<1101) 
 			{
				th= "110.bin";
			}
			else if(currentkural<1111) 
 			{
				th= "111.bin";
			}
			else if(currentkural<1121) 
 			{
				th= "112.bin";
			}
			else if(currentkural<1131) 
 			{
				th= "113.bin";
			}
			else if(currentkural<1141) 
 			{
				th= "114.bin";
			}
			else if(currentkural<1151) 
 			{
				th= "115.bin";
			}
			else if(currentkural<1161) 
 			{
				th= "116.bin";
			}
			else if(currentkural<1171) 
 			{
				th= "117.bin";
			}
			else if(currentkural<1181) 
 			{
				th= "118.bin";
			}
			else if(currentkural<1191) 
 			{
				th= "119.bin";
			}
			else if(currentkural<1201) 
 			{
				th= "120.bin";
			}
			else if(currentkural<1211) 
 			{
				th= "121.bin";
			}
			else if(currentkural<1221) 
 			{
				th= "122.bin";
			}
			else if(currentkural<1231) 
 			{
				th= "123.bin";
			}
			else if(currentkural<1241) 
 			{
				th= "124.bin";
			}
			else if(currentkural<1251) 
 			{
				th= "125.bin";
			}
			else if(currentkural<1261) 
 			{
				th= "126.bin";
			}
			else if(currentkural<1271) 
 			{
				th= "127.bin";
			}
			else if(currentkural<1281) 
 			{
				th= "128.bin";
			}
			else if(currentkural<1291) 
 			{
				th= "129.bin";
			}
			else if(currentkural<1301) 
 			{
				th= "130.bin";
			}
			else if(currentkural<1311) 
 			{
				th= "131.bin";
			}
			else if(currentkural<1321) 
 			{
				th= "132.bin";
			}
			else
			{
				th= "133.bin";
			}
                        ttl=title + "e" + th;
			title=title + th;
			tis=getClass().getResourceAsStream(title);
			while(bytesread>0 && comein==1)
			{
				buf=new byte[40];
				bytesread=tis.read(buf);
				if(bytesread<=0)
				{
					comein=0;
				}
				else
				{
					k=new String(buf,0,bytesread);
				}
				buf=null;
				s.append(k);
				k=null;
				int j=0,m=0;
				if(currentkural<=9)
				{
				j=2;
				}
				else if(currentkural<=99)
				{
				j=3;
				}
				else if(currentkural<=999)
				{
				j=4;
				}
				else
				{
				j=5;
				}
				if(currentkural+1<10 )
				{
				 m=3;
				}
				else if(currentkural+1<=99)
				{
				m=2;
				}
				else if(currentkural+1<=999 )
				{
					m=1;
				}
				else 
				{
					m=0;
				}
				if(s.toString().indexOf(Integer.toString(currentkural)+".")!=-1 || comein==1)
				{
					if(s.toString().indexOf(Integer.toString(currentkural+1)+".")!=-1)
					{
                                            out=s.toString().substring( (s.toString().indexOf(Integer.toString(currentkural)+".") +j ),s.toString().indexOf(Integer.toString(currentkural+1)+".")-m);
                                            comein=0;
					}
				}
			} 
			StringTokenizer st=new StringTokenizer(out," ");
			int counter=0;
			fu.x=50;
			fu.y=90;
			while(st.hasMoreTokens())
			{
                            counter=counter+1;
                            if(counter==5 || !st.hasMoreTokens())
                            {
                                fu.y=fu.y+25;
				counter=0;
                                // fnhh
				fu.x=50;
                            }
                            fu.showFonts(st.nextToken(),g);
                            fu.x=fu.x+6;
                            }
                            fu.y=fu.y+25;
                            fu.x=50;
                            tis=null;
                        // eng font
			s=new StringBuffer("");
			bytesread=1;
			comein=1;
			k="";
			out=".";
			tts=getClass().getResourceAsStream(ttl);
			while(bytesread>0 && comein==1)
			{
				buf=new byte[40];
				bytesread=tts.read(buf);
				if(bytesread<=0)
				{
					comein=0;
				}
				else
				{
					k=new String(buf,0,bytesread);
				}
				buf=null;
				s.append(k);
				k=null;
				int j=0,m=0;
				if(currentkural<=9)
				{
				j=2;
				}
				else if(currentkural<=99)
				{
				j=3;
				}
				else if(currentkural<=999)
				{
				j=4;
				}
				else
				{
				j=5;
				}
				if(currentkural+1<10 )
				{
				 m=3;
				}
				else if(currentkural+1<=99)
				{
				m=2;
				}
				else if(currentkural+1<=999 )
				{
					m=1;
				}
				else 
				{
					m=0;
				}
				if(s.toString().indexOf(Integer.toString(currentkural)+".")!=-1 || comein==1)
				{
					if(s.toString().indexOf(Integer.toString(currentkural+1)+".")!=-1)
					{
//					out=s.toString().substring( (s.toString().indexOf(Integer.toString(currentkural)+".") +j ),s.toString().indexOf(Integer.toString(currentkural+1)+".")-m);
					out=s.toString().substring( (s.toString().indexOf(Integer.toString(currentkural)+".") +j ),s.toString().indexOf(Integer.toString(currentkural+1)+".")-m-1);
					comein=0;
					}
					else
					{
					}
				}
			}
			g.setColor(0,0,0);
			g.setClip(0,0,screenWidth,screenHeight);
			g.setFont(highFont); 
			st=new StringTokenizer(out," ");
			String[] imagefontsarr=st.toArray();
			fu.x=50;
			fu.y=fu.y+20;
			for (int i=0;i<imagefontsarr.length;++i)
			{
				if(fu.x+highFont.stringWidth(imagefontsarr[i]) >=screenWidth )
				{
				fu.y=fu.y+25;
				fu.x=50;
				}	
			g.drawString(imagefontsarr[i],fu.x,fu.y,20);
			fu.x=3+fu.x+highFont.stringWidth(imagefontsarr[i]);
			}
			} 
			catch(Exception e)
			{
				System.out.println(e);
			}
			g.setColor(0,0,0);
			g.fillRoundRect(screenWidth/2-30,5,60,40,20,20);
			g.setColor(250,250,250);
                        g.drawString(Integer.toString(currentkural),screenWidth/2-25,12,20);
	}
	protected void keyPressed(int code)
	{
		if(code==-7)
		{
			t.begin();
		}
		if(code==KEY_NUM6|(getGameAction(code)==Canvas.RIGHT))
		{
			currentkural=Math.min(currentkural+1,1330);
			repaint();
		}
		if(code==KEY_NUM4|(getGameAction(code)==Canvas.LEFT))
		{
			currentkural=Math.max(currentkural-1,1);
			repaint();
		}
		if(code==KEY_NUM5|(getGameAction(code)==Canvas.FIRE))
		{
			Random randomizer = new Random();
			int r = Math.abs(randomizer.nextInt());
			currentkural=(r % (1 - 1330)) + 1;
			repaint();
		}
	}
        protected void pointerPressed(int x,int y)
        {
            if(x>=spriteprev.getX() && x<=spriteprev.getWidth()+spriteprev.getX() && y>=spriteprev.getY() && y<=spriteprev.getHeight()+spriteprev.getY())
            {
                spriteprev.setFrame(1);
                repaint();
            }
            if(x>=spritenext.getX() && x<=spritenext.getWidth()+spritenext.getX() && y>=spritenext.getY() && y<=spritenext.getHeight()+spritenext.getY())
            {
                spritenext.setFrame(1);
                repaint();
            }
            if(x>=spritemenu.getX() && x<=spritemenu.getWidth()+spritemenu.getX() && y>=spritemenu.getY() && y<=spritemenu.getHeight()+spritemenu.getY())
            {
                spritemenu.setFrame(1);
                repaint();
            }
        }
        protected void pointerReleased(int x,int y)
        {
            spriteprev.setFrame(0);
            spritenext.setFrame(0);
            spritemenu.setFrame(0);
            if(x>=spriteprev.getX() && x<=spriteprev.getWidth()+spriteprev.getX() && y>=spriteprev.getY() && y<=spriteprev.getHeight()+spriteprev.getY())
            {
		currentkural=Math.max(currentkural-1,1);
            }
            if(x>=spritenext.getX() && x<=spritenext.getWidth()+spritenext.getX() && y>=spritenext.getY() && y<=spritenext.getHeight()+spritenext.getY())
            {
		currentkural=Math.min(currentkural+1,1330);
            }
            if(x>=spritemenu.getX() && x<=spritemenu.getWidth()+spritemenu.getX() && y>=spritemenu.getY() && y<=spritemenu.getHeight()+spritemenu.getY())
            {
//		t.begin();
                
                t.showmenu();
            }
            repaint();
        }
        protected void sizeChanged(int w, int h)
        {
            screenWidth=w;
            screenHeight=h;
            try
            {
                im=Imagemanip.resizeImage(im,screenWidth,40);
            }
            catch(Exception e)
            {
            }
        }

}