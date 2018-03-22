package tettris;
import java.io.IOException;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
class MenuScreen extends Canvas
{
    mytetris hg;
    Image bgimage;
    Image btn1,btn2,btn3;
    Sprite spbtn1,spbtn2,spbtn3;
    Image imginfo,imgclose;
    Sprite spinfo,spclose;
    Image back=null;
    static final Font highFont = Font.getFont (Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
    Image btn,key;
    Sprite sp1,sp2,sp3,sp4,sp5,sp6,sp7,sp8,sp9,sp10,sp11,sp12;
    Image hsimage,aboutimage;
    int width=0;
    int height=0;
    int selectedindex=0;
    Sprite spriteback;
    int currentscene=0;
    Image imgsound;
    Sprite spsound;
    MenuScreen(mytetris hg)
    {
	setFullScreenMode(true);
	width = getWidth();
	height = getHeight();
        this.hg=hg;
        currentscene=0;

        try
        {
            bgimage = Image.createImage("/bgmenu.png");
            hsimage = Image.createImage("/inst.png");
            aboutimage = Image.createImage("/abt.png");
            btn1 = Image.createImage("/btn1.png");
            btn2 = Image.createImage("/btn2.png");
            btn3 = Image.createImage("/btn3.png");
            imgsound=Image.createImage("/sound.png");
            spbtn1=new Sprite(btn1,200,50);
            spbtn2=new Sprite(btn2,200,50);
            spbtn3=new Sprite(btn3,200,50);
            spbtn1.setPosition(10,100);
            spbtn2.setPosition(220,100);
            spbtn3.setPosition(430,100);
            imginfo = Image.createImage("/info.png");
            imgclose = Image.createImage("/close.png");
            spinfo=new Sprite(imginfo,50,50);
            spclose=new Sprite(imgclose,50,50);
            spinfo.setPosition(0, 0);
            spclose.setPosition(width-spclose.getWidth(), 0);
            back = Image.createImage("/back.png");
            spriteback=new Sprite(back,40,40);
            spriteback.setFrame(1);
            spriteback.setPosition(width-spriteback.getWidth(),height-back.getHeight());
            key = Image.createImage("/key.png");
            btn=Image.createImage("/btn.png");
            
            spsound=new Sprite(imgsound,50,50);
            spsound.setPosition(295,50);

            sp1=new Sprite(btn,60,60);
            sp2=new Sprite(btn,60,60);
            sp3=new Sprite(btn,60,60);
            sp4=new Sprite(btn,60,60);
            sp5=new Sprite(btn,60,60);
            sp6=new Sprite(btn,60,60);
            sp7=new Sprite(btn,60,60);
            sp8=new Sprite(btn,60,60);
            sp9=new Sprite(btn,60,60);
            sp10=new Sprite(btn,60,60);
            sp11=new Sprite(btn,60,60);
            sp12=new Sprite(btn,60,60);
            sp1.setPosition(150,80);
            sp2.setPosition(220,80);
            sp3.setPosition(290,80);
            sp4.setPosition(360,80);
            sp5.setPosition(430,80);
            sp6.setPosition(150,150);
            sp7.setPosition(220,150);
            sp8.setPosition(290,150);
            sp9.setPosition(360,150);
            sp10.setPosition(430,150);
            sp11.setPosition(150,220);
            sp12.setPosition(220,220);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
    protected void pointerPressed(int x,int y)
    {
        if(currentscene==0)
        {
            if(x>=spbtn1.getX() && x<=spbtn1.getWidth()+spbtn1.getX() && y>=spbtn1.getY() && y<=spbtn1.getHeight()+spbtn1.getY())
            {
                spbtn1.setFrame(1);
            }
            else if(x>=spbtn2.getX() && x<=spbtn2.getWidth()+spbtn2.getX() && y>=spbtn2.getY() && y<=spbtn2.getHeight()+spbtn2.getY())
            {
                spbtn2.setFrame(1);
            }
            else if(x>=spbtn3.getX() && x<=spbtn3.getWidth()+spbtn3.getX() && y>=spbtn3.getY() && y<=spbtn3.getHeight()+spbtn3.getY())
            {
                spbtn3.setFrame(1);
            }
            else if(x>=spinfo.getX() && x<=spinfo.getWidth()+spinfo.getX() && y>=spinfo.getY() && y<=spinfo.getHeight()+spinfo.getY())
            {
                spinfo.setFrame(1);
            }
            else if(x>=spclose.getX() && x<=spclose.getWidth()+spclose.getX() && y>=spclose.getY() && y<=spclose.getHeight()+spclose.getY())
            {
                spclose.setFrame(1);
            }
        }
        else if(currentscene==1)
        {
            if(x>=spriteback.getX() && x<=spriteback.getWidth()+spriteback.getX() && y>=spriteback.getY() && y<=spriteback.getHeight()+spriteback.getY())
            {
                spriteback.setFrame(0);
            }
        }
        else if(currentscene==2)
        {
            if(x>=spriteback.getX() && x<=spriteback.getWidth()+spriteback.getX() && y>=spriteback.getY() && y<=spriteback.getHeight()+spriteback.getY())
            {
                spriteback.setFrame(0);
            }
            else if(x>=sp1.getX() && x<=sp1.getWidth()+sp1.getX() && y>=sp1.getY() && y<=sp1.getHeight()+sp1.getY())
            {
                sp1.setFrame(1);
            }
            else if(x>=sp2.getX() && x<=sp2.getWidth()+sp2.getX() && y>=sp2.getY() && y<=sp2.getHeight()+sp2.getY())
            {
                if(hg.unlockedlevel>=2)
                {
                    sp2.setFrame(1);
                }
            }
            else if(x>=sp3.getX() && x<=sp3.getWidth()+sp3.getX() && y>=sp3.getY() && y<=sp3.getHeight()+sp3.getY())
            {
                if(hg.unlockedlevel>=3)
                {
                    sp3.setFrame(1);
                }
            }
            else if(x>=sp4.getX() && x<=sp4.getWidth()+sp4.getX() && y>=sp4.getY() && y<=sp4.getHeight()+sp4.getY())
            {
                if(hg.unlockedlevel>=4)
                {
                    sp4.setFrame(1);
                }
            }
            else if(x>=sp5.getX() && x<=sp5.getWidth()+sp5.getX() && y>=sp5.getY() && y<=sp5.getHeight()+sp5.getY())
            {
                if(hg.unlockedlevel>=5)
                {
                    sp5.setFrame(1);
                }
            }
            else if(x>=sp6.getX() && x<=sp6.getWidth()+sp6.getX() && y>=sp6.getY() && y<=sp6.getHeight()+sp6.getY())
            {
                if(hg.unlockedlevel>=6)
                {
                    sp6.setFrame(1);
                }
            }
            else if(x>=sp7.getX() && x<=sp7.getWidth()+sp7.getX() && y>=sp7.getY() && y<=sp7.getHeight()+sp7.getY())
            {
                if(hg.unlockedlevel>=7)
                {
                    sp7.setFrame(1);
                }
            }
            else if(x>=sp8.getX() && x<=sp8.getWidth()+sp8.getX() && y>=sp8.getY() && y<=sp8.getHeight()+sp8.getY())
            {
                if(hg.unlockedlevel>=8)
                {
                    sp8.setFrame(1);
                }
            }
            else if(x>=sp9.getX() && x<=sp9.getWidth()+sp9.getX() && y>=sp9.getY() && y<=sp9.getHeight()+sp9.getY())
            {
                if(hg.unlockedlevel>=9)
                {
                    sp9.setFrame(1);
                }
            }
            else if(x>=sp10.getX() && x<=sp10.getWidth()+sp10.getX() && y>=sp10.getY() && y<=sp10.getHeight()+sp10.getY())
            {
                if(hg.unlockedlevel>=10)
                {
                    sp10.setFrame(1);
                }
            }
            else if(x>=sp11.getX() && x<=sp11.getWidth()+sp11.getX() && y>=sp11.getY() && y<=sp11.getHeight()+sp11.getY())
            {
                if(hg.unlockedlevel>=11)
                {
                    sp11.setFrame(1);
                }
            }
            else if(x>=sp12.getX() && x<=sp12.getWidth()+sp12.getX() && y>=sp12.getY() && y<=sp12.getHeight()+sp12.getY())
            {
                if(hg.unlockedlevel>=12)
                {
                    sp12.setFrame(1);
                }
            }
        }
        else if(currentscene==3)
        {
            if(x>=spriteback.getX() && x<=spriteback.getWidth()+spriteback.getX() && y>=spriteback.getY() && y<=spriteback.getHeight()+spriteback.getY())
            {
                spriteback.setFrame(0);
            }
        }
        else if(currentscene==4)
        {
            if(x>=spriteback.getX() && x<=spriteback.getWidth()+spriteback.getX() && y>=spriteback.getY() && y<=spriteback.getHeight()+spriteback.getY())
            {
                spriteback.setFrame(0);
            }
        }
        repaint();
    }
    protected void pointerReleased(int x,int y)
    {
        spriteback.setFrame(1);
        if(currentscene==0)
        {
            handlemenuevent(x,y);
        }
        else if(currentscene==3)
        {
            handleaboutevent(x,y);
        }
        else if(currentscene==1)
        {
            handleobjectiveevent(x,y);
        }
        else if(currentscene==2)
        {
            handlecategevent(x,y);
        }
        repaint();
    }
    private void handlecategevent(int x,int y)
    {
            sp1.setFrame(0);
            sp2.setFrame(0);
            sp3.setFrame(0);
            sp4.setFrame(0);
            sp5.setFrame(0);
            sp6.setFrame(0);
            sp7.setFrame(0);
            sp8.setFrame(0);
            sp9.setFrame(0);
            sp10.setFrame(0);
            sp11.setFrame(0);
            sp12.setFrame(0);
            if(x>=spriteback.getX() && x<=spriteback.getWidth()+spriteback.getX() && y>=spriteback.getY() && y<=spriteback.getHeight()+spriteback.getY())
            {
                spriteback.setFrame(0);
                currentscene=0;
            }
            else if(x>=sp1.getX() && x<=sp1.getWidth()+sp1.getX() && y>=sp1.getY() && y<=sp1.getHeight()+sp1.getY())
            {
                if(hg.unlockedlevel>=1)
                {
//                    hg.startLevel(1);
                    this.hg.showCanvaslevel(1);                    
                }
            }    
            else if(x>=sp2.getX() && x<=sp2.getWidth()+sp2.getX() && y>=sp2.getY() && y<=sp2.getHeight()+sp2.getY())
            {
                if(hg.unlockedlevel>=2)
                {
//                    hg.startLevel(2);
                    hg.showCanvaslevel(2);                                        
                }
            }
            else if(x>=sp3.getX() && x<=sp3.getWidth()+sp3.getX() && y>=sp3.getY() && y<=sp3.getHeight()+sp3.getY())
            {
                if(hg.unlockedlevel>=3)
                {
  //                  hg.startLevel(3);
                    hg.showCanvaslevel(3);                      
                }
            }
            else if(x>=sp4.getX() && x<=sp4.getWidth()+sp4.getX() && y>=sp4.getY() && y<=sp4.getHeight()+sp4.getY())
            {
                if(hg.unlockedlevel>=4)
                {
      //              hg.startLevel(4);
                    hg.showCanvaslevel(4);                      
                }
            }
            else if(x>=sp5.getX() && x<=sp5.getWidth()+sp5.getX() && y>=sp5.getY() && y<=sp5.getHeight()+sp5.getY())
            {
                if(hg.unlockedlevel>=5)
                {
    //                hg.startLevel(5);
                    hg.showCanvaslevel(5);                      
                }
            }
            else if(x>=sp6.getX() && x<=sp6.getWidth()+sp6.getX() && y>=sp6.getY() && y<=sp6.getHeight()+sp6.getY())
            {
                if(hg.unlockedlevel>=6)
                {
    //                hg.startLevel(6);
                    hg.showCanvaslevel(6);                      
                }
            }
            else if(x>=sp7.getX() && x<=sp7.getWidth()+sp7.getX() && y>=sp7.getY() && y<=sp7.getHeight()+sp7.getY())
            {
                if(hg.unlockedlevel>=7)
                {
  //                  hg.startLevel(7);
                    hg.showCanvaslevel(7);                      
                }
            }
            else if(x>=sp8.getX() && x<=sp8.getWidth()+sp8.getX() && y>=sp8.getY() && y<=sp8.getHeight()+sp8.getY())
            {
                if(hg.unlockedlevel>=8)
                {
        //            hg.startLevel(8);
                    hg.showCanvaslevel(8);                      
                }
            }
            else if(x>=sp9.getX() && x<=sp9.getWidth()+sp9.getX() && y>=sp9.getY() && y<=sp9.getHeight()+sp9.getY())
            {
                if(hg.unlockedlevel>=9)
                {
      //              hg.startLevel(9);
                    hg.showCanvaslevel(9);                      
                }
            }
            else if(x>=sp10.getX() && x<=sp10.getWidth()+sp10.getX() && y>=sp10.getY() && y<=sp10.getHeight()+sp10.getY())
            {
                if(hg.unlockedlevel>=10)
                {
       //             hg.startLevel(10);
                    hg.showCanvaslevel(10);                      
                }
            }
            else if(x>=sp11.getX() && x<=sp11.getWidth()+sp11.getX() && y>=sp11.getY() && y<=sp11.getHeight()+sp11.getY())
            {
                if(hg.unlockedlevel>=11)
                {
     //               hg.startLevel(11);
                    hg.showCanvaslevel(11);                      
                }
            }
            else if(x>=sp12.getX() && x<=sp12.getWidth()+sp12.getX() && y>=sp12.getY() && y<=sp12.getHeight()+sp12.getY())
            {
                if(hg.unlockedlevel>=12)
                {
      //              hg.startLevel(12);
                    hg.showCanvaslevel(12);                      
                }
            }
            repaint();
    }
    private void handleaboutevent(int x,int y)
    {
            if(x>=spriteback.getX() && x<=spriteback.getWidth()+spriteback.getX() && y>=spriteback.getY() && y<=spriteback.getHeight()+spriteback.getY())
            {
                spriteback.setFrame(0);
                currentscene=0;
            }
            repaint();
    }
    private void handleobjectiveevent(int x,int y)
    {
            if(x>=spriteback.getX() && x<=spriteback.getWidth()+spriteback.getX() && y>=spriteback.getY() && y<=spriteback.getHeight()+spriteback.getY())
            {
                spriteback.setFrame(0);
                currentscene=0;
            }
            repaint();
    }
    private void handlemenuevent(int x,int y)
    {
        spbtn1.setFrame(0);
        spbtn2.setFrame(0);
        spbtn3.setFrame(0);
        spinfo.setFrame(0);
        spclose.setFrame(0);
        spsound.setFrame(0);
        if(x>=spbtn1.getX() && x<=spbtn1.getWidth()+spbtn1.getX() && y>=spbtn1.getY() && y<=spbtn1.getHeight()+spbtn1.getY())
        {
                currentscene=0;
                hg.showCanvas();
        }
        else if(x>=spbtn2.getX() && x<=spbtn2.getWidth()+spbtn2.getX() && y>=spbtn2.getY() && y<=spbtn2.getHeight()+spbtn2.getY())
        {
                currentscene=2;
        }
        else if(x>=spbtn3.getX() && x<=spbtn3.getWidth()+spbtn3.getX() && y>=spbtn3.getY() && y<=spbtn3.getHeight()+spbtn3.getY())
        {
                currentscene=1;
        }
        else if(x>=spinfo.getX() && x<=spinfo.getWidth()+spinfo.getX() && y>=spinfo.getY() && y<=spinfo.getHeight()+spinfo.getY())
        {
                currentscene=3;
        }
        else if(x>=spclose.getX() && x<=spclose.getWidth()+spclose.getX() && y>=spclose.getY() && y<=spclose.getHeight()+spclose.getY())
        {
                currentscene=4;
                hg.exitMIDlet(false);
        }
        else if(x>=spsound.getX() && x<=spsound.getWidth()+spsound.getX() && y>=spsound.getY() && y<=spsound.getHeight()+spsound.getY())
        {
                if(hg.soundstate==true)
                {
                    spsound.setFrame(1);
                    hg.soundstate=false;
                }
                else
                {
                    spsound.setFrame(0);
                    hg.soundstate=true;
                }
        }
        repaint();
    }
    protected void paint(Graphics g)
    {
        if(currentscene==0)
        {
            paintmenu(g);
        }
        else if(currentscene==1)
        {
            paintobjective(g);
        }
        else if(currentscene==2)
        {
            paintcategory(g);
        }
        else if(currentscene==3)
        {
            paintabout(g);
        }
    }
    private void paintobjective(Graphics g)
    {
        g.drawImage(bgimage,0,0,Graphics.LEFT|Graphics.TOP);
        g.drawImage(hsimage,width/2,height/2,Graphics.VCENTER|Graphics.HCENTER);
        g.drawString("sisconsoft", 0,0,20);        
        spriteback.paint(g);
    }
    private void paintabout(Graphics g)
    {
        g.drawImage(bgimage,0,0,Graphics.LEFT|Graphics.TOP);
        g.drawImage(aboutimage,width/2,height/2,Graphics.VCENTER|Graphics.HCENTER);
        g.drawString("sisconsoft", 0,0,20);        
        spriteback.paint(g);
    }
    private void paintcategory(Graphics g)
    {
        g.drawImage(bgimage,0,0,Graphics.LEFT|Graphics.TOP);
        cat(g);
        spriteback.paint(g);
    }
    private void paintmenu(Graphics g)
    {
        g.drawImage(bgimage,0,0,Graphics.TOP|Graphics.LEFT);
        spbtn1.paint(g);
        spbtn2.paint(g);
        spbtn3.paint(g);
        spinfo.paint(g);
        spclose.paint(g);
        spsound.paint(g);
    }
    private void cat(Graphics g)
    {
        sp1.paint(g);
        sp2.paint(g);
        sp3.paint(g);
        sp4.paint(g);
        sp5.paint(g);
        sp6.paint(g);
        sp7.paint(g);
        sp8.paint(g);
        sp9.paint(g);
        sp10.paint(g);
        sp11.paint(g);
        sp12.paint(g);

        int w=0,h=0;
        w=sp1.getWidth();
        h=sp1.getHeight();
        int x1,x2,x3,x4,x5;
        int y1,y2,y3;
        x1=sp1.getX();
        x2=sp2.getX();
        x3=sp3.getX();
        x4=sp4.getX();
        x5=sp5.getX();

        y1=sp1.getY();
        y2=sp6.getY();
        y3=sp11.getY();
        if(hg.unlockedlevel==1)
        {
        g.drawString("1",x1+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawImage(key,x2+(w/2),y1+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x3+(w/2),y1+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x4+(w/2),y1+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x5+(w/2),y1+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x1+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x2+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x3+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x4+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x5+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x1+(w/2),y3+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x2+(w/2),y3+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        }
        else if(hg.unlockedlevel==2)
        {
        g.drawString("1",x1+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("2",x2+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawImage(key,x3+(w/2),y1+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x4+(w/2),y1+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x5+(w/2),y1+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x1+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x2+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x3+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x4+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x5+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x1+(w/2),y3+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x2+(w/2),y3+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        }
        else if(hg.unlockedlevel==3)
        {
        g.drawString("1",x1+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("2",x2+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("3",x3+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawImage(key,x4+(w/2),y1+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x5+(w/2),y1+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x1+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x2+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x3+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x4+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x5+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x1+(w/2),y3+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x2+(w/2),y3+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        }
        else if(hg.unlockedlevel==4)
        {
        g.drawString("1",x1+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("2",x2+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("3",x3+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("4",x4+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawImage(key,x5+(w/2),y1+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x1+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x2+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x3+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x4+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x5+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x1+(w/2),y3+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x2+(w/2),y3+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        }
        else if(hg.unlockedlevel==5)
        {
        g.drawString("1",x1+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("2",x2+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("3",x3+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("4",x4+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("5",x5+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawImage(key,x1+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x2+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x3+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x4+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x5+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x1+(w/2),y3+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x2+(w/2),y3+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        }
        else if(hg.unlockedlevel==6)
        {
        g.drawString("1",x1+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("2",x2+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("3",x3+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("4",x4+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("5",x5+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("6",x1+(w/2),y2+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawImage(key,x2+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x3+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x4+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x5+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x1+(w/2),y3+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x2+(w/2),y3+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        }
        else if(hg.unlockedlevel==7)
        {
        g.drawString("1",x1+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("2",x2+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("3",x3+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("4",x4+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("5",x5+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("6",x1+(w/2),y2+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("7",x2+(w/2),y2+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawImage(key,x3+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x4+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x5+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x1+(w/2),y3+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x2+(w/2),y3+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        }
        else if(hg.unlockedlevel==8)
        {
        g.drawString("1",x1+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("2",x2+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("3",x3+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("4",x4+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("5",x5+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("6",x1+(w/2),y2+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("7",x2+(w/2),y2+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("8",x3+(w/2),y2+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawImage(key,x4+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x5+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x1+(w/2),y3+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x2+(w/2),y3+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        }
        else if(hg.unlockedlevel==9)
        {
        g.drawString("1",x1+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("2",x2+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("3",x3+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("4",x4+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("5",x5+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("6",x1+(w/2),y2+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("7",x2+(w/2),y2+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("8",x3+(w/2),y2+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("9",x4+(w/2),y2+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawImage(key,x5+(w/2),y2+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x1+(w/2),y3+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x2+(w/2),y3+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        }
        else if(hg.unlockedlevel==10)
        {
        g.drawString("1",x1+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("2",x2+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("3",x3+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("4",x4+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("5",x5+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("6",x1+(w/2),y2+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("7",x2+(w/2),y2+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("8",x3+(w/2),y2+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("9",x4+(w/2),y2+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("10",x5+(w/2),y2+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawImage(key,x1+(w/2),y3+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        g.drawImage(key,x2+(w/2),y3+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        }
        else if(hg.unlockedlevel==11)
        {
        g.drawString("1",x1+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("2",x2+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("3",x3+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("4",x4+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("5",x5+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("6",x1+(w/2),y2+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("7",x2+(w/2),y2+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("8",x3+(w/2),y2+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("9",x4+(w/2),y2+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("10",x5+(w/2),y2+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("11",x1+(w/2),y3+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawImage(key,x2+(w/2),y3+(h/2),Graphics.HCENTER|Graphics.VCENTER);
        }
        else if(hg.unlockedlevel==12)
        {
        g.drawString("1",x1+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("2",x2+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("3",x3+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("4",x4+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("5",x5+(w/2),y1+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("6",x1+(w/2),y2+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("7",x2+(w/2),y2+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("8",x3+(w/2),y2+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("9",x4+(w/2),y2+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("10",x5+(w/2),y2+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("11",x1+(w/2),y3+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        g.drawString("12",x2+(w/2),y3+(h/2)-(g.getFont().getHeight()/2),Graphics.HCENTER|Graphics.TOP);
        }
    }
}