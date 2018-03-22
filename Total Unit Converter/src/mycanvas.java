import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.Sprite;
public class mycanvas extends Canvas
{
        public Graphics g;
      	static final Font highFont = Font.getFont (Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
      	static final Font lowFont = Font.getFont (Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_SMALL);
        private TotalUnitConverter obj;

        Image bg=null;
        Image valbg=null;
        Image valbgleft=null;
        Image valbgright=null;
        Sprite spritevalbglefttop=null;
        Sprite spritevalbgleftbottom=null;
        Sprite spritevalbgrighttop=null;
        Sprite spritevalbgrightbottom=null;
        Image top=null;
        Image valbgover=null;
        Image bottom=null;
        Image i1=null;
        Image i2=null;
        Image i3=null;
        Image i4=null;
        Image i5=null;
        Image i6=null;
        Image i7=null;
        Image i8=null;
        Image i9=null;
        Image i10=null;
        Image i11=null;
        Image i12=null;
        Image i13=null;
        Image i14=null;

        Image ii1=null;
        Image ii2=null;
        Image ii3=null;
        Image ii4=null;
        Image ii5=null;
        Image ii6=null;
        Image ii7=null;
        Image ii8=null;
        Image ii9=null;
        Image ii10=null;
        Image ii11=null;
        Image ii12=null;
        Image ii13=null;
        Image ii14=null;

   //     Image flag=null;

        Sprite s1=null;
        Sprite s2=null;
        Sprite s3=null;
        Sprite s4=null;
        Sprite s5=null;
        Sprite s6=null;
        Sprite s7=null;
        Sprite s8=null;
        Sprite s9=null;
        Sprite s10=null;
        Sprite s11=null;
        Sprite s12=null;
        Sprite s13=null;
        Sprite s14=null;

        int h=0,w=0;

        FontUtil fu;
	public mycanvas(TotalUnitConverter obj,String a,String b)
	{
            this.obj=obj;
            setFullScreenMode(true);
            TotalUnitConverter.a=a;
            TotalUnitConverter.b=b;
            h=getHeight();
            w=getWidth();


            FontUtil.initialize();
            fu = FontUtil.getInstance();


            int keypadwidth=w/4-2;
            try
            {

                top=Image.createImage("/top.png");
                if(w!=top.getWidth())
                {
                    top=Resizer.resizeImagepng(top,w,top.getHeight());
                }
                bottom=Image.createImage("/bottom.png");
                if(w!=top.getWidth())
                {
                    bottom=Resizer.resizeImagepng(bottom,w,bottom.getHeight());
                }

                bg=Image.createImage("/bg.png");
                if(w!=bg.getWidth() && h!=bg.getHeight())
                {
                    bg=Resizer.resizeImagepng(bg,w,h);
                }
                valbg=Image.createImage("/valbg.png");
                valbgover=Image.createImage("/valbg.1.png");

     //         valbgleft=Resizer.resizeImagepng(valbg,((w*40)/100)-3,lowFont.getHeight()*2);
                valbgleft=Resizer.resizeImagepng(valbg,((w*40)/100)-3,60);

   //           valbgover=Resizer.resizeImagepng(valbgover,((w*40)/100)-3,lowFont.getHeight()*2);
                valbgover=Resizer.resizeImagepng(valbgover,((w*40)/100)-3,60);

                // reduce 10 for left space and right space of 5 each

        //        valbgright=Resizer.resizeImagepng(valbg,((w*60)/100)-3,lowFont.getHeight()*2);
                valbgright=Resizer.resizeImagepng(valbg,((w*60)/100)-3,60);
 
                i1=Image.createImage("/1.png");
                i1=Resizer.resizeImagepng(i1,keypadwidth,keypadwidth);
                i2=Image.createImage("/2.png");
                i2=Resizer.resizeImagepng(i2,keypadwidth,keypadwidth);
                i3=Image.createImage("/3.png");
                i3=Resizer.resizeImagepng(i3,keypadwidth,keypadwidth);
                i4=Image.createImage("/4.png");
                i4=Resizer.resizeImagepng(i4,keypadwidth,keypadwidth);
                i5=Image.createImage("/5.png");
                i5=Resizer.resizeImagepng(i5,keypadwidth,keypadwidth);
                i6=Image.createImage("/6.png");
                i6=Resizer.resizeImagepng(i6,keypadwidth,keypadwidth);
                i7=Image.createImage("/7.png");
                i7=Resizer.resizeImagepng(i7,keypadwidth,keypadwidth);
                i8=Image.createImage("/8.png");
                i8=Resizer.resizeImagepng(i8,keypadwidth,keypadwidth);
                i9=Image.createImage("/9.png");
                i9=Resizer.resizeImagepng(i9,keypadwidth,keypadwidth);
                i10=Image.createImage("/0.png");
                i10=Resizer.resizeImagepng(i10,keypadwidth,keypadwidth);
                i11=Image.createImage("/dot.png");
                i11=Resizer.resizeImagepng(i11,keypadwidth,keypadwidth);
                i12=Image.createImage("/del.png");
                i12=Resizer.resizeImagepng(i12,keypadwidth,(keypadwidth)*2+2);
                i13=Image.createImage("/home.png");
                i13=Resizer.resizeImagepng(i13,keypadwidth*2+2,(keypadwidth));
                i14=Image.createImage("/clear.png");
                i14=Resizer.resizeImagepng(i14,keypadwidth,(keypadwidth));

                ii1=Image.createImage("/1.1.png");
                ii1=Resizer.resizeImagepng(ii1,keypadwidth,keypadwidth);
                ii2=Image.createImage("/2.1.png");
                ii2=Resizer.resizeImagepng(ii2,keypadwidth,keypadwidth);
                ii3=Image.createImage("/3.1.png");
                ii3=Resizer.resizeImagepng(ii3,keypadwidth,keypadwidth);
                ii4=Image.createImage("/4.1.png");
                ii4=Resizer.resizeImagepng(ii4,keypadwidth,keypadwidth);
                ii5=Image.createImage("/5.1.png");
                ii5=Resizer.resizeImagepng(ii5,keypadwidth,keypadwidth);
                ii6=Image.createImage("/6.1.png");
                ii6=Resizer.resizeImagepng(ii6,keypadwidth,keypadwidth);
                ii7=Image.createImage("/7.1.png");
                ii7=Resizer.resizeImagepng(ii7,keypadwidth,keypadwidth);
                ii8=Image.createImage("/8.1.png");
                ii8=Resizer.resizeImagepng(ii8,keypadwidth,keypadwidth);
                ii9=Image.createImage("/9.1.png");
                ii9=Resizer.resizeImagepng(ii9,keypadwidth,keypadwidth);
                ii10=Image.createImage("/0.1.png");
                ii10=Resizer.resizeImagepng(ii10,keypadwidth,keypadwidth);
                ii11=Image.createImage("/dot.1.png");
                ii11=Resizer.resizeImagepng(ii11,keypadwidth,keypadwidth);
                ii12=Image.createImage("/del.1.png");
                ii12=Resizer.resizeImagepng(ii12,keypadwidth,(keypadwidth)*2+2);
                ii13=Image.createImage("/home.1.png");
                ii13=Resizer.resizeImagepng(ii13,keypadwidth*2+2,(keypadwidth));
                ii14=Image.createImage("/clear.1.png");
                ii14=Resizer.resizeImagepng(ii14,keypadwidth,(keypadwidth));

 //               flag=Image.createImage("/flag.png");

            }
            catch(Exception e)
            {
            }

            spritevalbglefttop=new Sprite(valbgleft);
            spritevalbglefttop.setPosition(2,40+top.getHeight());
            spritevalbgleftbottom=new Sprite(valbgleft);
            spritevalbgleftbottom.setPosition(2,spritevalbglefttop.getY()+spritevalbglefttop.getHeight()+4);

            spritevalbgrighttop=new Sprite(valbgright);
            spritevalbgrighttop.setPosition(spritevalbglefttop.getX()+spritevalbglefttop.getWidth()+2,40+top.getHeight());
            spritevalbgrightbottom=new Sprite(valbgright);
            spritevalbgrightbottom.setPosition(spritevalbgleftbottom.getX()+spritevalbgleftbottom.getWidth()+2,spritevalbgrighttop.getY()+spritevalbgrighttop.getHeight()+4);

//            int hs=spritevalbgleftbottom.getY()+spritevalbgleftbottom.getHeight() + top.getHeight()+10;
            int hs=spritevalbgleftbottom.getY()+spritevalbgleftbottom.getHeight()+3;
            s1=new Sprite(i1);
            s1.setPosition(1+(keypadwidth*0),hs+10);
            s2=new Sprite(i2);
            s2.setPosition(3+(keypadwidth*1),hs+10);
            s3=new Sprite(i3);
            s3.setPosition(5+(keypadwidth*2),hs+10);
            s11=new Sprite(i11);
            s11.setPosition(7+(keypadwidth*3),hs+10);

            hs=hs+keypadwidth+2;

            s4=new Sprite(i4);
            s4.setPosition(1+(keypadwidth*0),hs+10);
            s5=new Sprite(i5);
            s5.setPosition(3+(keypadwidth*1),hs+10);
            s6=new Sprite(i6);
            s6.setPosition(5+(keypadwidth*2),hs+10);
            s12=new Sprite(i12);
            s12.setPosition(7+(keypadwidth*3),hs+10);


            hs=hs+keypadwidth+2;


            s7=new Sprite(i7);
            s7.setPosition(1+(keypadwidth*0),hs+10);
            s8=new Sprite(i8);
            s8.setPosition(3+(keypadwidth*1),hs+10);
            s9=new Sprite(i9);
            s9.setPosition(5+(keypadwidth*2),hs+10);

            hs=hs+keypadwidth+3;

            s14=new Sprite(i14);
            s14.setPosition(1+(keypadwidth*0),hs+10);
            s10=new Sprite(i10);
            s10.setPosition(3+(keypadwidth*1),hs+10);
            s13=new Sprite(i13);
            s13.setPosition(5+(keypadwidth*2),hs+10);
            hs=hs+keypadwidth+3;
        }
 	protected void sizeChanged(int w, int h)
        {
            h=getHeight();
            w=getWidth();
        }
	public void paint(Graphics g)
	{
            this.g=g;
         
            g.drawImage(bg, 0, 0,20);
//            g.drawImage(top, 0,0,20);
            spritevalbglefttop.paint(g);
            spritevalbgleftbottom.paint(g);
            spritevalbgrighttop.paint(g);
            spritevalbgrightbottom.paint(g);
        //    g.drawImage(top,0,spritevalbgrightbottom.getY()+spritevalbgrightbottom.getHeight()+10,20);

            s1.paint(g);
            s2.paint(g);
            s3.paint(g);
            s4.paint(g);
            s5.paint(g);
            s6.paint(g);
            s7.paint(g);
            s8.paint(g);
            s9.paint(g);
            s10.paint(g);
            s11.paint(g);
            s12.paint(g);
            s13.paint(g);
            s14.paint(g);

            g.setColor(255,255,255);
            g.setFont(highFont);
            g.setClip(spritevalbglefttop.getX(),spritevalbglefttop.getY(),spritevalbglefttop.getX()+spritevalbglefttop.getWidth()-8,spritevalbglefttop.getY()+spritevalbglefttop.getHeight());
            g.drawString(TotalUnitConverter.a,spritevalbglefttop.getX()+10,spritevalbglefttop.getY()+20,20);
            g.setClip(spritevalbgleftbottom.getX(),spritevalbgleftbottom.getY(),spritevalbgleftbottom.getX()+spritevalbgleftbottom.getWidth()-8,spritevalbgleftbottom.getY()+spritevalbgleftbottom.getHeight());
            g.drawString(TotalUnitConverter.b,spritevalbgleftbottom.getX()+10,spritevalbgleftbottom.getY()+20,20);
            g.setClip(spritevalbgrighttop.getX(),spritevalbgrighttop.getY(),spritevalbgrighttop.getWidth(),spritevalbgrighttop.getHeight());
    //        g.drawString(TotalUnitConverter.aval,spritevalbgrighttop.getX()+10,spritevalbgrighttop.getY()+20,20);

           fu.showFonts(TotalUnitConverter.aval, g,spritevalbgrighttop.getX()+10,spritevalbgrighttop.getY()+20,w);

            g.setClip(spritevalbgrightbottom.getX(),spritevalbgrightbottom.getY(),spritevalbgrightbottom.getWidth(),spritevalbgrightbottom.getHeight());
        //    g.drawString(TotalUnitConverter.bval,spritevalbgrightbottom.getX()+8,spritevalbgrightbottom.getY()+20,20);

            fu.showFonts(TotalUnitConverter.bval,g,spritevalbgrightbottom.getX()+10,spritevalbgrightbottom.getY()+20,w);
            g.setClip(0,0,w,h);
            g.drawImage(bottom,0,h-bottom.getHeight(),20);
   //         g.drawImage(flag,0,h-flag.getHeight(),20);
        }
        public void setvalues(String a,String b)
        {
            TotalUnitConverter.a=a;
            TotalUnitConverter.b=b;
        }
        protected void pointerPressed(int x, int y)
	{
            if(x>=s1.getX() && x<=(s1.getX()+s1.getWidth()) && y>=s1.getY() && y<=(s1.getY()+s1.getHeight()))
            {
                s1.setImage(ii1, i1.getWidth(), i1.getHeight());
            }
            else if(x>=s2.getX() && x<=(s2.getX()+s2.getWidth()) && y>=s2.getY() && y<=(s2.getY()+s2.getHeight()))
            {
                s2.setImage(ii2, i2.getWidth(), i2.getHeight());
            }
            else if(x>=s3.getX() && x<=(s3.getX()+s3.getWidth()) && y>=s3.getY() && y<=(s3.getY()+s3.getHeight()))
            {
                s3.setImage(ii3, i3.getWidth(), i3.getHeight());
            }
            else if(x>=s4.getX() && x<=(s4.getX()+s4.getWidth()) && y>=s4.getY() && y<=(s4.getY()+s4.getHeight()))
            {
                s4.setImage(ii4, i4.getWidth(), i4.getHeight());
            }
            else if(x>=s5.getX() && x<=(s5.getX()+s5.getWidth()) && y>=s5.getY() && y<=(s5.getY()+s5.getHeight()))
            {
                s5.setImage(ii5, i5.getWidth(), i5.getHeight());
            }
            else if(x>=s6.getX() && x<=(s6.getX()+s6.getWidth()) && y>=s6.getY() && y<=(s6.getY()+s6.getHeight()))
            {
                s6.setImage(ii6, i6.getWidth(), i6.getHeight());
            }
            else if(x>=s7.getX() && x<=(s7.getX()+s7.getWidth()) && y>=s7.getY() && y<=(s7.getY()+s7.getHeight()))
            {
                s7.setImage(ii7, i7.getWidth(), i7.getHeight());
            }
            else if(x>=s8.getX() && x<=(s8.getX()+s8.getWidth()) && y>=s8.getY() && y<=(s8.getY()+s8.getHeight()))
            {
                s8.setImage(ii8, i8.getWidth(), i8.getHeight());
            }
            else if(x>=s9.getX() && x<=(s9.getX()+s9.getWidth()) && y>=s9.getY() && y<=(s9.getY()+s9.getHeight()))
            {
                s9.setImage(ii9, i9.getWidth(), i9.getHeight());
            }
            else if(x>=s10.getX() && x<=(s10.getX()+s10.getWidth()) && y>=s10.getY() && y<=(s10.getY()+s10.getHeight()))
            {
                s10.setImage(ii10, i10.getWidth(), i10.getHeight());
            }
            else if(x>=s11.getX() && x<=(s11.getX()+s11.getWidth()) && y>=s11.getY() && y<=(s11.getY()+s11.getHeight()))
            {
                s11.setImage(ii11, i11.getWidth(), i11.getHeight());
            }
            else if(x>=s12.getX() && x<=(s12.getX()+s12.getWidth()) && y>=s12.getY() && y<=(s12.getY()+s12.getHeight()))
            {
                s12.setImage(ii12, i12.getWidth(), i12.getHeight());
            }
            else if(x>=s13.getX() && x<=(s13.getX()+s13.getWidth()) && y>=s13.getY() && y<=(s13.getY()+s13.getHeight()))
            {
                s13.setImage(ii13, i13.getWidth(), i13.getHeight());
            }
            else if(x>=s14.getX() && x<=(s14.getX()+s14.getWidth()) && y>=s14.getY() && y<=(s14.getY()+s14.getHeight()))
            {
                s14.setImage(ii14, i14.getWidth(), i14.getHeight());
            }
            else if(x>=spritevalbglefttop.getX() && x<=(spritevalbglefttop.getX()+spritevalbglefttop.getWidth()) && y>=spritevalbglefttop.getY() && y<=(spritevalbglefttop.getY()+spritevalbglefttop.getHeight()))
            {
                spritevalbglefttop.setImage(valbgover,spritevalbglefttop.getWidth(),spritevalbglefttop.getHeight());
            }
            else if(x>=spritevalbgleftbottom.getX() && x<=(spritevalbgleftbottom.getX()+spritevalbgleftbottom.getWidth()) && y>=spritevalbgleftbottom.getY() && y<=(spritevalbgleftbottom.getY()+spritevalbgleftbottom.getHeight()))
            {
                spritevalbgleftbottom.setImage(valbgover,spritevalbgleftbottom.getWidth(),spritevalbgleftbottom.getHeight());
            }
            repaint();

        }
        public void cnv()
        {
            try
            {
                if(TotalUnitConverter.type==4)
                {
                    if(TotalUnitConverter.a.equals(TotalUnitConverter.b))
                    {
                        TotalUnitConverter.bval=TotalUnitConverter.aval;
                    }
                    else if(TotalUnitConverter.a.equals("Kelvin") && TotalUnitConverter.b.equals("Celcius"))
                    {
                        TotalUnitConverter.bval=Double.toString(Double.parseDouble(TotalUnitConverter.aval)-273.15);
                    }
                    else if(TotalUnitConverter.a.equals("Kelvin") && TotalUnitConverter.b.equals("Reaumur"))
                    {
                        TotalUnitConverter.bval=Double.toString((Double.parseDouble(TotalUnitConverter.aval)-273.15)*((double)4/(double)5));
                    }
                    else if(TotalUnitConverter.a.equals("Kelvin") && TotalUnitConverter.b.equals("Fahrenheit"))
                    {
                        TotalUnitConverter.bval=Double.toString((Double.parseDouble(TotalUnitConverter.aval)*((double)9/(double)5))-459.67);
                    }
                    else if(TotalUnitConverter.a.equals("Kelvin") && TotalUnitConverter.b.equals("Rankine"))
                    {
                        TotalUnitConverter.bval=Double.toString(Double.parseDouble(TotalUnitConverter.aval)*((double)9/(double)5));
                    }
                    else if(TotalUnitConverter.a.equals("Celcius") && TotalUnitConverter.b.equals("Kelvin"))
                    {
                        TotalUnitConverter.bval=Double.toString((Double.parseDouble(TotalUnitConverter.aval)+273.15)*((double)9/(double)5));
                    }
                    else if(TotalUnitConverter.a.equals("Celcius") && TotalUnitConverter.b.equals("Reaumur"))
                    {
                        TotalUnitConverter.bval=Double.toString(Double.parseDouble(TotalUnitConverter.aval)*((double)4/(double)5));
                    }
                    else if(TotalUnitConverter.a.equals("Celcius") && TotalUnitConverter.b.equals("Fahrenheit"))
                    {
                        TotalUnitConverter.bval=Double.toString(Double.parseDouble(TotalUnitConverter.aval)+273.15);
                    }
                    else if(TotalUnitConverter.a.equals("Celcius") && TotalUnitConverter.b.equals("Rankine"))
                    {
                        TotalUnitConverter.bval=Double.toString( ( Double.parseDouble(TotalUnitConverter.aval) ) * ((double)9/(double)5) + 32);
                    }
                    else if(TotalUnitConverter.a.equals("Reaumur") && TotalUnitConverter.b.equals("Kelvin"))
                    {
                        TotalUnitConverter.bval=Double.toString(Double.parseDouble(TotalUnitConverter.aval)*((double)5/(double)4)+273.15);
                    }
                    else if(TotalUnitConverter.a.equals("Reaumur") && TotalUnitConverter.b.equals("Celcius"))
                    {
                        TotalUnitConverter.bval=Double.toString( ( Double.parseDouble(TotalUnitConverter.aval) ) * ((double)5/(double)4));
                    }
                    else if(TotalUnitConverter.a.equals("Reaumur") && TotalUnitConverter.b.equals("Fahrenheit"))
                    {
                        TotalUnitConverter.bval=Double.toString((Double.parseDouble(TotalUnitConverter.aval)*((double)9/(double)4)) + 32);
                    }
                    else if(TotalUnitConverter.a.equals("Reaumur") && TotalUnitConverter.b.equals("Rankine"))
                    {
                        TotalUnitConverter.bval=Double.toString(Double.parseDouble(TotalUnitConverter.aval)*((double)9/(double)4)+491.67);
                    }
                    else if(TotalUnitConverter.a.equals("Fahrenheit") && TotalUnitConverter.b.equals("Kelvin"))
                    {
                        TotalUnitConverter.bval=Double.toString( (Double.parseDouble(TotalUnitConverter.aval)+459.67) *((double)5/(double)9));
                    }
                    else if(TotalUnitConverter.a.equals("Fahrenheit") && TotalUnitConverter.b.equals("Celcius"))
                    {
                        try
                        {
                            TotalUnitConverter.bval=Double.toString( (Double.parseDouble(TotalUnitConverter.aval)-32)*((double)5/(double)9));
                        }
                        catch(Exception e)
                        {
                            TotalUnitConverter.bval="0";
                        }
                    }
                    else if(TotalUnitConverter.a.equals("Fahrenheit") && TotalUnitConverter.b.equals("Reaumur"))
                    {
                        TotalUnitConverter.bval=Double.toString((Double.parseDouble(TotalUnitConverter.aval)-32)*((double)4/(double)9));
                    }
                    else if(TotalUnitConverter.a.equals("Fahrenheit") && TotalUnitConverter.b.equals("Rankine"))
                    {
                        TotalUnitConverter.bval=Double.toString(Double.parseDouble(TotalUnitConverter.aval)+459.67);
                    }
                    else if(TotalUnitConverter.a.equals("Rankine") && TotalUnitConverter.b.equals("Kelvin"))
                    {
                        TotalUnitConverter.bval=Double.toString( (Double.parseDouble(TotalUnitConverter.aval)) *((double)5/(double)9));
                    }
                    else if(TotalUnitConverter.a.equals("Rankine") && TotalUnitConverter.b.equals("Celcius"))
                    {
                        TotalUnitConverter.bval=Double.toString( ( Double.parseDouble(TotalUnitConverter.aval)-491.67)*((double)5/(double)9));
                    }
                    else if(TotalUnitConverter.a.equals("Rankine") && TotalUnitConverter.b.equals("Reaumur"))
                    {
                        TotalUnitConverter.bval=Double.toString((Double.parseDouble(TotalUnitConverter.aval)-491.67)*((double)4/(double)9));
                    }
                    else if(TotalUnitConverter.a.equals("Rankine") && TotalUnitConverter.b.equals("Fahrenheit"))
                    {
                        TotalUnitConverter.bval=Double.toString(Double.parseDouble(TotalUnitConverter.aval)-459.67);
                    }
                    repaint();
                }
                else
                {
                    TotalUnitConverter.convert();
                }
            }
            catch (IOException ex)
            {
                repaint();
                ex.printStackTrace();
            }
        }
	protected void pointerReleased(int x, int y)
	{
            s1.setImage(i1, i1.getWidth(), i1.getHeight());
            s2.setImage(i2, i2.getWidth(), i2.getHeight());
            s3.setImage(i3, i3.getWidth(), i3.getHeight());
            s4.setImage(i4, i4.getWidth(), i4.getHeight());
            s5.setImage(i5, i5.getWidth(), i5.getHeight());
            s6.setImage(i6, i6.getWidth(), i6.getHeight());
            s7.setImage(i7, i7.getWidth(), i7.getHeight());
            s8.setImage(i8, i8.getWidth(), i8.getHeight());
            s9.setImage(i9, i9.getWidth(), i9.getHeight());
            s10.setImage(i10, i10.getWidth(), i10.getHeight());
            s11.setImage(i11, i11.getWidth(), i11.getHeight());
            s12.setImage(i12, i12.getWidth(), i12.getHeight());
            s13.setImage(i13, i13.getWidth(), i13.getHeight());
            s14.setImage(i14, i14.getWidth(), i14.getHeight());
            spritevalbglefttop.setImage(valbgleft,spritevalbglefttop.getWidth(),spritevalbglefttop.getHeight());
            spritevalbgleftbottom.setImage(valbgleft,spritevalbgleftbottom.getWidth(),spritevalbgleftbottom.getHeight());
            if(x>=spritevalbglefttop.getX() && x<=(spritevalbglefttop.getX()+spritevalbglefttop.getWidth()) && y>=spritevalbglefttop.getY() && y<=(spritevalbglefttop.getY()+spritevalbglefttop.getHeight()))
            {
                // process event for spritevalbgrighttop
                TotalUnitConverter.sel=1;
                obj.showform1();
                cnv();
            }
            else if(x>=spritevalbgleftbottom.getX() && x<=(spritevalbgleftbottom.getX()+spritevalbgleftbottom.getWidth()) && y>=spritevalbgleftbottom.getY() && y<=(spritevalbgleftbottom.getY()+spritevalbgleftbottom.getHeight()))
            {
                TotalUnitConverter.sel=2;
                obj.showform2();
                cnv();
            }
            else if(x>=s1.getX() && x<=(s1.getX()+s1.getWidth()) && y>=s1.getY() && y<=(s1.getY()+s1.getHeight()))
            {
                StringBuffer s=new StringBuffer("");
                if(TotalUnitConverter.aval.equals("0"))
                {
                    s=new StringBuffer("1");
                }
                else
                {
                    s.append(TotalUnitConverter.aval);
                    s.append("1");
                }
                TotalUnitConverter.aval=s.toString();
                cnv();
            }
            else if(x>=s2.getX() && x<=(s2.getX()+s2.getWidth()) && y>=s2.getY() && y<=(s2.getY()+s2.getHeight()))
            {
                StringBuffer s=new StringBuffer("");
                if(TotalUnitConverter.aval.equals("0"))
                {
                    s=new StringBuffer("2");
                }
                else
                {
                    s.append(TotalUnitConverter.aval);
                    s.append("2");
                }
                TotalUnitConverter.aval=s.toString();
                cnv();
            }
            else if(x>=s3.getX() && x<=(s3.getX()+s3.getWidth()) && y>=s3.getY() && y<=(s3.getY()+s3.getHeight()))
            {
                StringBuffer s=new StringBuffer("");
                if(TotalUnitConverter.aval.equals("0"))
                {
                    s=new StringBuffer("3");
                }
                else
                {
                    s.append(TotalUnitConverter.aval);
                    s.append("3");
                }
                TotalUnitConverter.aval=s.toString();
                cnv();
            }
            else if(x>=s4.getX() && x<=(s4.getX()+s4.getWidth()) && y>=s4.getY() && y<=(s4.getY()+s4.getHeight()))
            {
                StringBuffer s=new StringBuffer("");
                if(TotalUnitConverter.aval.equals("0"))
                {
                    s=new StringBuffer("4");
                }
                else
                {
                    s.append(TotalUnitConverter.aval);
                    s.append("4");
                }
                TotalUnitConverter.aval=s.toString();
                cnv();
            }
            else if(x>=s5.getX() && x<=(s5.getX()+s5.getWidth()) && y>=s5.getY() && y<=(s5.getY()+s5.getHeight()))
            {
                StringBuffer s=new StringBuffer("");
                if(TotalUnitConverter.aval.equals("0"))
                {
                    s=new StringBuffer("5");
                }
                else
                {
                    s.append(TotalUnitConverter.aval);
                    s.append("5");
                }
                TotalUnitConverter.aval=s.toString();
                cnv();
            }
            else if(x>=s6.getX() && x<=(s6.getX()+s6.getWidth()) && y>=s6.getY() && y<=(s6.getY()+s6.getHeight()))
            {
                StringBuffer s=new StringBuffer("");
                if(TotalUnitConverter.aval.equals("0"))
                {
                    s=new StringBuffer("6");
                }
                else
                {
                    s.append(TotalUnitConverter.aval);
                    s.append("6");
                }
                TotalUnitConverter.aval=s.toString();
                cnv();
            }
            else if(x>=s7.getX() && x<=(s7.getX()+s7.getWidth()) && y>=s7.getY() && y<=(s7.getY()+s7.getHeight()))
            {
                StringBuffer s=new StringBuffer("");
                if(TotalUnitConverter.aval.equals("0"))
                {
                    s=new StringBuffer("7");
                }
                else
                {
                    s.append(TotalUnitConverter.aval);
                    s.append("7");
                }
                TotalUnitConverter.aval=s.toString();
                cnv();
            }
            else if(x>=s8.getX() && x<=(s8.getX()+s8.getWidth()) && y>=s8.getY() && y<=(s8.getY()+s8.getHeight()))
            {
                StringBuffer s=new StringBuffer("");
                if(TotalUnitConverter.aval.equals("0"))
                {
                    s=new StringBuffer("8");
                }
                else
                {
                    s.append(TotalUnitConverter.aval);
                    s.append("8");
                }
                TotalUnitConverter.aval=s.toString();
                cnv();
            }
            else if(x>=s9.getX() && x<=(s9.getX()+s9.getWidth()) && y>=s9.getY() && y<=(s9.getY()+s9.getHeight()))
            {
                StringBuffer s=new StringBuffer("");
                if(TotalUnitConverter.aval.equals("0"))
                {
                    s=new StringBuffer("9");
                }
                else
                {
                    s.append(TotalUnitConverter.aval);
                    s.append("9");
                }
                TotalUnitConverter.aval=s.toString();
                cnv();
            }          
            else if(x>=s10.getX() && x<=(s10.getX()+s10.getWidth()) && y>=s10.getY() && y<=(s10.getY()+s10.getHeight()))
            {
                StringBuffer s=new StringBuffer("");
                if(TotalUnitConverter.aval.equals("0"))
                {
                    s=new StringBuffer("0");
                }
                else
                {
                    s.append(TotalUnitConverter.aval);
                    s.append("0");
                }
                TotalUnitConverter.aval=s.toString();
                cnv();
            }
            else if(x>=s11.getX() && x<=(s11.getX()+s11.getWidth()) && y>=s11.getY() && y<=(s11.getY()+s11.getHeight()))
            {
                StringBuffer s=new StringBuffer("");
                if(TotalUnitConverter.aval.length()<=0)
                {
                    s=new StringBuffer("0.");
                }
                else
                {
                    if(TotalUnitConverter.aval.indexOf('.')==-1)
                    {
                        s.append(TotalUnitConverter.aval + ".");
                    }
                    else
                    {
                        s.append(TotalUnitConverter.aval);
                    }
                }
                TotalUnitConverter.aval=s.toString();
                cnv();
            }
            else if(x>=s12.getX() && x<=(s12.getX()+s12.getWidth()) && y>=s12.getY() && y<=(s12.getY()+s12.getHeight()))
            {
                StringBuffer s=new StringBuffer("");
                if(TotalUnitConverter.aval.length()<=1)
                {
                   s=new StringBuffer("0");
                }
                else
                {
                    s.append(TotalUnitConverter.aval.substring(0, TotalUnitConverter.aval.length()-1));
                }
                TotalUnitConverter.aval=s.toString();
                cnv();
            }
            else if(x>=s13.getX() && x<=(s13.getX()+s13.getWidth()) && y>=s13.getY() && y<=(s13.getY()+s13.getHeight()))
            {
                TotalUnitConverter.m.show();
            }
            else if(x>=s14.getX() && x<=(s14.getX()+s14.getWidth()) && y>=s14.getY() && y<=(s14.getY()+s14.getHeight()))
            {
                TotalUnitConverter.aval="0";
                TotalUnitConverter.bval="0";
                cnv();
            }
	}
}