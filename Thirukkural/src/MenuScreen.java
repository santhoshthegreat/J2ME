import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
public class MenuScreen extends Canvas
{ 	
    Thirukkural t;
    public int mycategory=0;
    private int width;   // screen width
    private int height;  // screen height
    Image bgimage,i1,i2,i3,i4,i5,i6,i7,i8;
    Image categ1,categ2,categ3;
    Image imgmenu;
    Sprite sp1,sp2,sp3;
    Image selection;
    Sprite spr[];
    Sprite spritemenu;
    Image imgvalluvar,imgolai;
    int currentscene=0;
    Image abt,hlp;
    public MenuScreen(Thirukkural t)
    {
        setFullScreenMode(true);
	this.t=t;
	width = getWidth();
	height = getHeight();
	mycategory=0;
        currentscene=0;
        try
        {
            bgimage = Image.createImage("/bg.jpg");
            if(width!=640 || height!=640)
            {
                bgimage=Imagemanip.resizeImage(bgimage,width,height);
            }
            i1 = Image.createImage("/buttons/btn1.png");
            i2 = Image.createImage("/buttons/btn2.png");
            i3 = Image.createImage("/buttons/btn3.png");
            i4 = Image.createImage("/buttons/btn4.png");
            i5 = Image.createImage("/buttons/btn5.png");
            i6 = Image.createImage("/buttons/btn6.png");
            i7 = Image.createImage("/buttons/btn7.png");
            categ1 = Image.createImage("/categ/arathupal.png");
            categ2 = Image.createImage("/categ/kamathupal.png");
            categ3 = Image.createImage("/categ/porutpal.png");
            selection=Image.createImage("/categ/selection.png");
            imgvalluvar = Image.createImage("/valluvar.png");
            imgolai = Image.createImage("/olai.png");
            hlp = Image.createImage("/hlp.png");
            abt = Image.createImage("/abt.png");
            imgmenu = Image.createImage("/menu.png");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        sp1=new Sprite(categ1);
        sp2=new Sprite(categ2);
        sp3=new Sprite(categ3);
        spr=new Sprite[7];
        spr[0]=new Sprite(i1,75,75);
        spr[1]=new Sprite(i2,75,75);
        spr[2]=new Sprite(i3,75,75);
        spr[3]=new Sprite(i4,75,75);
        spr[4]=new Sprite(i5,75,75);
        spr[5]=new Sprite(i6,75,75);
        spr[6]=new Sprite(i7,75,75);
        spritemenu=new Sprite(imgmenu,70,60);
        spritemenu.setPosition(getWidth()-70,getHeight()-60);
    }
    public void start()
    {
        if(mycategory==0)
	{
            t.showkural(1);
	}
	else if(mycategory==1)
	{
            t.showkural(381);
	}
	else 
	{
            t.showkural(1081);
	}
    }
    protected void sizeChanged(int w, int h)
    {  
        width = w;
        height = h;
        try
        {
            bgimage=Resizer.resizeImagepng(bgimage,width,height);
        }
        catch(Exception e)
        {
        }
    }
    protected void pointerPressed(int x,int y)
    {
        if(currentscene==0)
        {
            if(x>=spr[0].getX() && x<=spr[0].getWidth()+spr[0].getX() && y>=spr[0].getY() && y<=spr[0].getHeight()+spr[0].getY())
            {
                spr[0].setFrame(1);
            }
            if(x>=spr[1].getX() && x<=spr[1].getWidth()+spr[1].getX() && y>=spr[1].getY() && y<=spr[1].getHeight()+spr[1].getY())
            {
                spr[1].setFrame(1);
            }
            if(x>=spr[2].getX() && x<=spr[2].getWidth()+spr[2].getX() && y>=spr[2].getY() && y<=spr[2].getHeight()+spr[2].getY())
            {
                spr[2].setFrame(1);
            }
            if(x>=spr[3].getX() && x<=spr[3].getWidth()+spr[3].getX() && y>=spr[3].getY() && y<=spr[3].getHeight()+spr[3].getY())
            {
                spr[3].setFrame(1);
            }
            if(x>=spr[4].getX() && x<=spr[4].getWidth()+spr[4].getX() && y>=spr[4].getY() && y<=spr[4].getHeight()+spr[4].getY())
            {
                spr[4].setFrame(1);
            }
            if(x>=spr[5].getX() && x<=spr[5].getWidth()+spr[5].getX() && y>=spr[5].getY() && y<=spr[5].getHeight()+spr[5].getY())
            {
                spr[5].setFrame(1);
            }
            if(x>=spr[6].getX() && x<=spr[6].getWidth()+spr[6].getX() && y>=spr[6].getY() && y<=spr[6].getHeight()+spr[6].getY())
            {
                spr[6].setFrame(1);
            }
        }
        else if(currentscene==1)
        {
            if(x>=spritemenu.getX() && x<=spritemenu.getWidth()+spritemenu.getX() && y>=spritemenu.getY() && y<=spritemenu.getHeight()+spritemenu.getY())
            {
                spritemenu.setFrame(1);
            }
            else if(x>=sp1.getX() && x<=sp1.getWidth()+sp1.getX() && y>=sp1.getY() && y<=sp1.getHeight()+sp1.getY())
            {
                sp1.setImage(selection,selection.getWidth(),selection.getHeight());
            }
            else if(x>=sp2.getX() && x<=sp2.getWidth()+sp2.getX() && y>=sp2.getY() && y<=sp2.getHeight()+sp2.getY())
            {
                sp2.setImage(selection,selection.getWidth(),selection.getHeight());
            }
            else if(x>=sp3.getX() && x<=sp3.getWidth()+sp3.getX() && y>=sp3.getY() && y<=sp3.getHeight()+sp3.getY())
            {
                sp3.setImage(selection,selection.getWidth(),selection.getHeight());
            }
       }
       else
       {
           if(x>=spritemenu.getX() && x<=spritemenu.getWidth()+spritemenu.getX() && y>=spritemenu.getY() && y<=spritemenu.getHeight()+spritemenu.getY())
            {
                spritemenu.setFrame(1);
            }
       }
       repaint();
    }
    protected void pointerReleased(int x,int y)
    {
        spritemenu.setFrame(0);
        if(currentscene==0)
        {
            handlemenuevent(x,y);
        }
        else if(currentscene==1)
        {
            handlecategevent(x,y);
        }
        else
        {
            currentscene=0;
        }
        repaint();
    }
    private void handlecategevent(int x,int y)
    {
            sp1.setImage(categ1,categ1.getWidth(), categ1.getHeight());
            sp2.setImage(categ2,categ2.getWidth(), categ2.getHeight());
            sp3.setImage(categ3,categ3.getWidth(), categ3.getHeight());
            if(x>=sp1.getX() && x<=sp1.getWidth()+sp1.getX() && y>=sp1.getY() && y<=sp1.getHeight()+sp1.getY())
            {
                mycategory=0;
            }
            else if(x>=sp2.getX() && x<=sp2.getWidth()+sp2.getX() && y>=sp2.getY() && y<=sp2.getHeight()+sp2.getY())
            {
                mycategory=1;
            }
            else if(x>=sp3.getX() && x<=sp3.getWidth()+sp3.getX() && y>=sp3.getY() && y<=sp3.getHeight()+sp3.getY())
            {
                mycategory=2;
            }
            currentscene=0;
    }
    private void handlemenuevent(int x,int y)
    {
        spr[0].setFrame(0);
        spr[1].setFrame(0);
        spr[2].setFrame(0);
        spr[3].setFrame(0);
        spr[4].setFrame(0);
        spr[5].setFrame(0);
        spr[6].setFrame(0);
        spritemenu.setFrame(0);
        if(x>=spr[0].getX() && x<=spr[0].getWidth()+spr[0].getX() && y>=spr[0].getY() && y<=spr[0].getHeight()+spr[0].getY())
        {
                // menu play
                currentscene=0;
                if(mycategory==0)
                {
                    t.showkural(1);
                }
                else if(mycategory==1)
                {
                    t.showkural(381);
                }
                else if(mycategory==2)
                {
                    t.showkural(1081);
                }
        }
        if(x>=spr[1].getX() && x<=spr[1].getWidth()+spr[1].getX() && y>=spr[1].getY() && y<=spr[1].getHeight()+spr[1].getY())
        {
                currentscene=1;
        }
        if(x>=spr[2].getX() && x<=spr[2].getWidth()+spr[2].getX() && y>=spr[2].getY() && y<=spr[2].getHeight()+spr[2].getY())
        {
                currentscene=2;
        }
        if(x>=spr[3].getX() && x<=spr[3].getWidth()+spr[3].getX() && y>=spr[3].getY() && y<=spr[3].getHeight()+spr[3].getY())
        {
                currentscene=3;
        }
        if(x>=spr[4].getX() && x<=spr[4].getWidth()+spr[4].getX() && y>=spr[4].getY() && y<=spr[4].getHeight()+spr[4].getY())
        {
                currentscene=4;
        }
        if(x>=spr[5].getX() && x<=spr[5].getWidth()+spr[5].getX() && y>=spr[5].getY() && y<=spr[5].getHeight()+spr[5].getY())
        {
                currentscene=5;
        }
        if(x>=spr[6].getX() && x<=spr[6].getWidth()+spr[6].getX() && y>=spr[6].getY() && y<=spr[6].getHeight()+spr[6].getY())
        {
                currentscene=6;
        }
    }
    protected void paint(Graphics g)
    {
        if(currentscene==0)
        {
            paintmenu(g);
        }
        else if(currentscene==1)
        {
            paintcategory(g);
        }
        else if(currentscene==2)
        {
              t.chapter();
        }
        else if(currentscene==3)
        {
              t.enterkural();
        }
        else if(currentscene==4)
        {
            painthelp(g);
        }
        else if(currentscene==5)
        {
            paintabout(g);
        }
        else if(currentscene==6)
        {
            t.exit();
        }
    }
    private void painthelp(Graphics g)
    {
        g.drawImage(bgimage,0,0,Graphics.LEFT|Graphics.TOP);
        g.drawImage(hlp,width/2,height/2,Graphics.VCENTER|Graphics.HCENTER);
        spritemenu.paint(g);
    }
    private void paintabout(Graphics g)
    {
        g.drawImage(bgimage,0,0,Graphics.LEFT|Graphics.TOP);
        g.drawImage(abt,width/2,height/2,Graphics.VCENTER|Graphics.HCENTER);
        spritemenu.paint(g);
    }
    private void paintcategory(Graphics g)
    {
        g.drawImage(bgimage,0,0,Graphics.LEFT|Graphics.TOP);
        sp1.setPosition(width/2-(sp1.getWidth()/2),60);
        sp2.setPosition(width/2-(sp1.getWidth()/2),60+sp1.getHeight()*1);
        sp3.setPosition(width/2-(sp1.getWidth()/2),60+sp2.getHeight()*2);
        sp1.paint(g);
        sp2.paint(g);
        sp3.paint(g);
        spritemenu.paint(g);
    }
    private void paintmenu(Graphics g)
    {
        g.drawImage(bgimage,0,0,Graphics.TOP|Graphics.LEFT);
        g.drawImage(imgvalluvar,width/2+50,80,Graphics.TOP|Graphics.LEFT);
        g.drawImage(imgolai,50,80,Graphics.TOP|Graphics.LEFT);
        spr[0].setPosition(40,275);
        spr[1].setPosition(120,275);
        spr[2].setPosition(200,275);
        spr[3].setPosition(280,275);
        spr[4].setPosition(360,275);
        spr[5].setPosition(440,275);
        spr[6].setPosition(520,275);
        spr[0].paint(g);
        spr[1].paint(g);
        spr[2].paint(g);
        spr[3].paint(g);
        spr[4].paint(g);
        spr[5].paint(g);
        spr[6].paint(g);
    }
 }