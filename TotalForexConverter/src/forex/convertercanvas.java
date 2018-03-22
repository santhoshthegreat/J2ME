package forex;
import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.Sprite;
public class convertercanvas extends Canvas
{
        private boolean touchlock=false;
        public Graphics g;
      	static final Font highFont = Font.getFont (Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        private TotalForexConverter obj;
        Image img=null;
        Image img1=null;
        Image img2=null;
        private boolean viewtypeportrait=false;
        int h=0,w=0;
        Sprite no;
        Image btnimage1,btnimage2;
	public convertercanvas(TotalForexConverter obj,String a,String b)
	{
            this.obj=obj;
            setFullScreenMode(true);
            TotalForexConverter.a=a;
            TotalForexConverter.b=b;
            viewtypeportrait=true;
            h=getHeight();
            w=getWidth();
            no=null;
            System.out.println("test 1");
            try
            {
                img=Image.createImage("/1.png");
                img1=Image.createImage("/up.png");
                img2=Image.createImage("/convert.png");
                btnimage1=Image.createImage("/no.png");
                img=Resizer.resizeImagepng(img,w-10,h/8);
//              btnimage1=Resizer.resizeImagepng(btnimage1,getWidth()-10,40);
                no=new Sprite(btnimage1,60,50);
//                  no=new Sprite(btnimage1,30,30);
            }
            catch(Exception e){}
            System.out.println("test 1");
	}
 	protected void sizeChanged(int w, int h)
        {
            h=getHeight();
            w=getWidth();
            try
            {
                btnimage1=Image.createImage("/no.png");
                no=new Sprite(btnimage1,60,50);
            }
            catch(Exception e){}
            if(w>h)
            {
                viewtypeportrait=false;
            }
            else
            {
                viewtypeportrait=true;
            }
        }
	public void paint(Graphics g)
	{
            this.g=g;
            int y=0;
            h=getHeight();
            w=getWidth();
            g.setColor(0xffffff);
            g.fillRect(0,0,w,h);
            g.setColor(0xffffff);
            g.drawImage(img,0,0,20);
            g.setFont(highFont);
            y=y+img.getHeight();
            g.drawString(TotalForexConverter.a,25,y-(img.getHeight()/2)-(highFont.getHeight()/2),20);
            g.drawImage(img,0,y,20);
            y=y+img.getHeight();
            g.drawString(TotalForexConverter.b,25,y-(img.getHeight()/2)-(highFont.getHeight()/2),20);
            y=y+5;
            g.setColor(0x000000);
            if(viewtypeportrait==true)
            {
                g.drawString(TotalForexConverter.aval,10,y,20);
                g.drawString(TotalForexConverter.a,10,y+highFont.getHeight()+2,20);
                g.drawString("=" ,getWidth()/2,y+(highFont.getHeight()*2)+4,20);
                g.drawString(TotalForexConverter.bval ,10,y+(highFont.getHeight()*3)+6,20);
                g.drawString(TotalForexConverter.b,10,y+(highFont.getHeight()*4)+8,20);
            }
            else
            {
                g.drawString(TotalForexConverter.aval+"",img.getWidth()+2,2,20);
                g.drawString(TotalForexConverter.a,img.getWidth()+2,2+highFont.getHeight(),20);
                g.drawString("=" ,img.getWidth()+2,2+(highFont.getHeight()*2),20);
                g.drawString(TotalForexConverter.bval+"",img.getWidth()+2,2+(highFont.getHeight()*3),20);
                g.drawString(TotalForexConverter.b,img.getWidth()+2,2+(highFont.getHeight()*4),20);
            }
            g.drawImage(img1,w-img1.getWidth(),h-img1.getHeight(),20);
            g.drawImage(img2,0,h-img2.getHeight(),20);
            int firsttolastrow=5;
            int th=0;
            int tw=0;
            int i=0,j=0,k=0;
            th=h-(no.getHeight()*3);
            tw=w/2 - ( ( firsttolastrow * no.getWidth() ) / 2 ) ;
            for (i=1;i<=3;i++)
            {
                for(j=1;j<=firsttolastrow;j++)
                {
                    no.setFrame(k);
                    no.setPosition(tw,th);
                    no.paint(g);
                    tw=tw+60;
             //        tw=tw+30;
                    k=k+1;
                }
                firsttolastrow=firsttolastrow-1;
                tw=w/2 - ( ( firsttolastrow * no.getWidth() ) / 2 ) ;
              th=th+50;
            //    th=th+30;
            }
            no.paint(g);
	}
        public void setvalues(String a,String b)
        {
        	TotalForexConverter.a=a;
        	TotalForexConverter.b=b;
        }
	protected void pointerReleased(int x, int y)
	{
		if(!touchlock)
		{
                    touchlock=true;
                    if((x>=0)&&(x<=w)&&(y<=h)&&(y>=0))
                    {
                        if((y>=0)&&(y<=img.getHeight()))
                        {
                        	TotalForexConverter.sel=1;
                            obj.showform1();
                        }
                        else if((y>img.getHeight()+10)&&(y<=((img.getHeight()*2))))
                        {
                        	TotalForexConverter.sel=2;
                           obj.showform2();
                        }
                        else if( ( ( y>h-img1.getHeight() ) && y<h ) && (x>0 && x<=img2.getWidth()) )
                        {
                            try
                            {
                                	TotalForexConverter.convert();

                            }
                            catch (IOException ex)
                            {
                                ex.printStackTrace();
                            }
                        }
                        else if( ( ( y>h-img1.getHeight() ) && y<h ) && (x>(w-img2.getWidth())&&x<=w) )
                        {
                                TotalForexConverter.m.show();
                        }
                        else
                        {
                            ConverterKeypad keypad=new ConverterKeypad();
                            int th=h-(no.getHeight()*3);
                            int tw=w/2 - ( ( 5 * no.getWidth() ) / 2 ) ;
                            int iii= keypad.find(x,y,th,tw,no.getHeight(),no.getWidth(),h,w);
                     //       System.out.println(iii);
                            if(iii==1)
                            {
                                StringBuffer s=new StringBuffer("");
                                if(TotalForexConverter.aval.equals("0"))
                                {
                                    s=new StringBuffer("1");
                                }
                                else
                                {
                                    s.append(TotalForexConverter.aval);
                                    s.append("1");
                                }
//                              System.out.println("value: " +  TotalUnitConverter.aval);
                                TotalForexConverter.aval=s.toString();
                            }
                            else if(iii==2)
                            {
                                StringBuffer s=new StringBuffer("");
                                if(TotalForexConverter.aval.equals("0"))
                                {
                                    s=new StringBuffer("2");
                                }
                                else
                                {
                                    s.append(TotalForexConverter.aval);
                                    s.append("2");
                                }
//                              System.out.println("value: " +  TotalUnitConverter.aval);
                                TotalForexConverter.aval=s.toString();
                            }
                            else if(iii==3)
                            {
                                StringBuffer s=new StringBuffer("");
                                if(TotalForexConverter.aval.equals("0"))
                                {
                                    s=new StringBuffer("3");
                                }
                                else
                                {
                                    s.append(TotalForexConverter.aval);
                                    s.append("3");
                                }
//                              System.out.println("value: " +  TotalUnitConverter.aval);
                                TotalForexConverter.aval=s.toString();
                            }
                            else if(iii==4)
                            {
                                StringBuffer s=new StringBuffer("");
                                if(TotalForexConverter.aval.equals("0"))
                                {
                                    s=new StringBuffer("4");
                                }
                                else
                                {
                                    s.append(TotalForexConverter.aval);
                                    s.append("4");
                                }
                                TotalForexConverter.aval=s.toString();
                            }
                            else if(iii==5)
                            {
                                StringBuffer s=new StringBuffer("");
                                if(TotalForexConverter.aval.equals("0"))
                                {
                                    s=new StringBuffer("5");
                                }
                                else
                                {
                                    s.append(TotalForexConverter.aval);
                                    s.append("5");
                                }
                                TotalForexConverter.aval=s.toString();
                            }
                            else if(iii==6)
                            {
                                StringBuffer s=new StringBuffer("");
                                if(TotalForexConverter.aval.equals("0"))
                                {
                                    s=new StringBuffer("6");
                                }
                                else
                                {
                                    s.append(TotalForexConverter.aval);
                                    s.append("6");
                                }
                                TotalForexConverter.aval=s.toString();
                            }
                            else if(iii==7)
                            {
                                StringBuffer s=new StringBuffer("");
                                if(TotalForexConverter.aval.equals("0"))
                                {
                                    s=new StringBuffer("7");
                                }
                                else
                                {
                                    s.append(TotalForexConverter.aval);
                                    s.append("7");
                                }
                                TotalForexConverter.aval=s.toString();
                            }
                            else if(iii==8)
                            {
                                StringBuffer s=new StringBuffer("");
                                if(TotalForexConverter.aval.equals("0"))
                                {
                                    s=new StringBuffer("8");
                                }
                                else
                                {
                                    s.append(TotalForexConverter.aval);
                                    s.append("8");
                                }
                                TotalForexConverter.aval=s.toString();
                            }
                            else if(iii==9)
                            {
                                StringBuffer s=new StringBuffer("");
                                if(TotalForexConverter.aval.equals("0"))
                                {
                                    s=new StringBuffer("9");
                                }
                                else
                                {
                                    s.append(TotalForexConverter.aval);
                                    s.append("9");
                                }
                                TotalForexConverter.aval=s.toString();
                            }
                            else if(iii==10)
                            {
                                StringBuffer s=new StringBuffer("");
                                if(TotalForexConverter.aval.equals("0"))
                                {
                                    s=new StringBuffer("0");
                                }
                                else
                                {
                                    s.append(TotalForexConverter.aval);
                                    s.append("0");
                                }
                                TotalForexConverter.aval=s.toString();
                            }
                            else if(iii==11)
                            {
                                StringBuffer s=new StringBuffer("");
                                if(TotalForexConverter.aval.length()==1)
                                {
                                    s=new StringBuffer("");
                                }
                                else
                                {
                                    s.append(TotalForexConverter.aval.substring(0, TotalForexConverter.aval.length()-1));
                                }
                                TotalForexConverter.aval=s.toString();
                            }
                            else if(iii==12)
                            {
                                StringBuffer s=new StringBuffer("");
                                if(TotalForexConverter.aval.length()<1)
                                {
                                    s=new StringBuffer(".");
                                }
                                else
                                {
                                    s.append(TotalForexConverter.aval);
                                    s.append(".");
                                }
                                TotalForexConverter.aval=s.toString();
                            }
                            repaint();
                        }
                    }
		}
		touchlock=false;
	}
}