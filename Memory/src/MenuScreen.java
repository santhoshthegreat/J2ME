import java.io.IOException;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.rms.RecordStore;
class MenuScreen extends Canvas
{
    Memory s;
    Image bgimage,i1,i2,i3,i4,i5,i6,i7,i8;
    Image back=null;
    Image backup=null;
    static final Font highFont = Font.getFont (Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
    Image btnleft,btnright;
    Image btnleft1,btnright1;
    Image categ1,categ2,categ3,categ4,categ5,categ6;
    Sprite sp1,sp2,sp3,sp4,sp5,sp6;
    Image selection;
    Image hsimage;
    int width=0;
    int height=0;
    int selectedindex=0;
    Sprite spr[];
    Sprite btnlt;
    Sprite btnrt;
    Sprite spriteback;
    int sz=0;
    int currentscene=0;
    MenuScreen(Memory s)
    {
	setFullScreenMode(true);
	width = getWidth();
	height = getHeight();
        this.s=s;
        sz=width/3;
        currentscene=0;
        try
        {
            bgimage = Image.createImage("/bg.png");
            hsimage = Image.createImage("/hs.png");
            bgimage=Resizer.resizeImagepng(bgimage,width,height);
            i1 = Image.createImage("/play.png");
            i1=Resizer.resizeImagepng(i1,sz,sz);
            i2 = Image.createImage("/objective.png");
            i2=Resizer.resizeImagepng(i2,sz,sz);
            i3 = Image.createImage("/category.png");
            i3=Resizer.resizeImagepng(i3,sz,sz);
            i4 = Image.createImage("/highscore.png");
            i4=Resizer.resizeImagepng(i4,sz,sz);
            i5 = Image.createImage("/help.png");
            i5=Resizer.resizeImagepng(i5,sz,sz);
            i6 = Image.createImage("/about.png");
            i6=Resizer.resizeImagepng(i6,sz,sz);
            i7 = Image.createImage("/exit.png");
            i7=Resizer.resizeImagepng(i7,sz,sz);
            i8 = Image.createImage("/keyup.png");
            i8=Resizer.resizeImagepng(i8,sz,sz);
            btnleft=Image.createImage("/left.png");
            //btnleft=Resizer.resizeImagepng(btnleft,sz,width/2-20);
            btnright=Image.createImage("/right.png");
            //btnright=Resizer.resizeImagepng(btnright,sz,width/2-20);
            btnleft1=Image.createImage("/left1.png");
            btnright1=Image.createImage("/right1.png");
            back = Image.createImage("/back.png");
            backup = Image.createImage("/backup.png");

            categ1 = Image.createImage("/categ/fruits.png");
            categ2 = Image.createImage("/categ/cars.png");
            categ3 = Image.createImage("/categ/animals.png");
            categ4 = Image.createImage("/categ/objects.png");
            categ5 = Image.createImage("/categ/specials.png");
            categ6 = Image.createImage("/categ/vegetables.png");
            sp1=new Sprite(categ1);
            sp2=new Sprite(categ2);
            sp3=new Sprite(categ3);
            sp4=new Sprite(categ4);
            sp5=new Sprite(categ5);
            sp6=new Sprite(categ6);

            selection=Image.createImage("/categ/selection.png");
            // currentscene
            // 0 - menu
            // 1 - obj
            // 2 - categ
            // 3 - hs
            // 4 - help
            // 5 - abt
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        spr=new Sprite[7];
        spr[0]=new Sprite(i1);
        spr[1]=new Sprite(i2);
        spr[2]=new Sprite(i3);
        spr[3]=new Sprite(i4);
        spr[4]=new Sprite(i5);
        spr[5]=new Sprite(i6);
        spr[6]=new Sprite(i7);
        btnlt=new Sprite(btnleft);
        btnrt=new Sprite(btnright);
        btnlt.setPosition(width/2-btnlt.getWidth()-30,sz+76);
        btnrt.setPosition(width/2+30,sz+76);
        spriteback=new Sprite(back);
        spriteback.setPosition(width-back.getWidth(),height-back.getHeight());
    }
    protected void pointerPressed(int x,int y)
    {
        if(currentscene==0)
        {
            if(x>=spr[0].getX() && x<=spr[0].getWidth()+spr[0].getX() && y>=spr[0].getY() && y<=spr[0].getHeight()+spr[0].getY())
            {
                spr[0].setImage(i8, i8.getWidth(),i8.getHeight());
            }
            if(x>=spr[1].getX() && x<=spr[1].getWidth()+spr[1].getX() && y>=spr[1].getY() && y<=spr[1].getHeight()+spr[1].getY())
            {
                spr[1].setImage(i8, i8.getWidth(),i8.getHeight());
            }
            if(x>=spr[2].getX() && x<=spr[2].getWidth()+spr[2].getX() && y>=spr[2].getY() && y<=spr[2].getHeight()+spr[2].getY())
            {
                spr[2].setImage(i8, i8.getWidth(),i8.getHeight());
            }
            if(x>=spr[3].getX() && x<=spr[3].getWidth()+spr[3].getX() && y>=spr[3].getY() && y<=spr[3].getHeight()+spr[3].getY())
            {
                spr[3].setImage(i8, i8.getWidth(),i8.getHeight());
            }
            if(x>=spr[4].getX() && x<=spr[4].getWidth()+spr[4].getX() && y>=spr[4].getY() && y<=spr[4].getHeight()+spr[4].getY())
            {
                spr[4].setImage(i8, i8.getWidth(),i8.getHeight());
            }
            if(x>=spr[5].getX() && x<=spr[5].getWidth()+spr[5].getX() && y>=spr[5].getY() && y<=spr[5].getHeight()+spr[5].getY())
            {
                spr[5].setImage(i8, i8.getWidth(),i8.getHeight());
            }
            if(x>=spr[6].getX() && x<=spr[6].getWidth()+spr[6].getX() && y>=spr[6].getY() && y<=spr[6].getHeight()+spr[6].getY())
            {
                spr[6].setImage(i8, i8.getWidth(),i8.getHeight());
            }

            if(x>=btnlt.getX() && x<=btnlt.getWidth()+btnlt.getX() && y>=btnlt.getY() && y<=btnlt.getHeight()+btnlt.getY())
            {
                btnlt.setImage(btnleft1,btnleft1.getWidth(),btnleft1.getHeight());
                repaint();
            }
            if(x>=btnrt.getX() && x<=btnrt.getWidth()+btnrt.getX() && y>=btnrt.getY() && y<=btnrt.getHeight()+btnrt.getY())
            {
                btnrt.setImage(btnright1,btnright1.getWidth(),btnright1.getHeight());
                repaint();
            }

        }
        else if(currentscene==1)
        {
            if(x>=spriteback.getX() && x<=spriteback.getWidth()+spriteback.getX() && y>=spriteback.getY() && y<=spriteback.getHeight()+spriteback.getY())
            {
                spriteback.setImage(backup,backup.getWidth(),backup.getHeight());
            }
        }
        else if(currentscene==2)
        {
            if(x>=spriteback.getX() && x<=spriteback.getWidth()+spriteback.getX() && y>=spriteback.getY() && y<=spriteback.getHeight()+spriteback.getY())
            {
                spriteback.setImage(backup,backup.getWidth(),backup.getHeight());
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
            else if(x>=sp4.getX() && x<=sp4.getWidth()+sp4.getX() && y>=sp4.getY() && y<=sp4.getHeight()+sp4.getY())
            {
                sp4.setImage(selection,selection.getWidth(),selection.getHeight());
            }
            else if(x>=sp5.getX() && x<=sp5.getWidth()+sp5.getX() && y>=sp5.getY() && y<=sp5.getHeight()+sp5.getY())
            {
                sp5.setImage(selection,selection.getWidth(),selection.getHeight());
            }
            else if(x>=sp6.getX() && x<=sp6.getWidth()+sp6.getX() && y>=sp6.getY() && y<=sp6.getHeight()+sp6.getY())
            {
                sp6.setImage(selection,selection.getWidth(),selection.getHeight());
            }

        }
        else if(currentscene==3)
        {
            if(x>=spriteback.getX() && x<=spriteback.getWidth()+spriteback.getX() && y>=spriteback.getY() && y<=spriteback.getHeight()+spriteback.getY())
            {
                spriteback.setImage(backup,backup.getWidth(),backup.getHeight());
            }
        }
        else if(currentscene==4)
        {
            if(x>=spriteback.getX() && x<=spriteback.getWidth()+spriteback.getX() && y>=spriteback.getY() && y<=spriteback.getHeight()+spriteback.getY())
            {
                spriteback.setImage(backup,backup.getWidth(),backup.getHeight());
            }
        }
        else if(currentscene==5)
        {
            if(x>=spriteback.getX() && x<=spriteback.getWidth()+spriteback.getX() && y>=spriteback.getY() && y<=spriteback.getHeight()+spriteback.getY())
            {
                spriteback.setImage(backup,backup.getWidth(),backup.getHeight());
            }
        }
        repaint();
    }
    protected void pointerReleased(int x,int y)
    {
        if(currentscene==0)
        {
            handlemenuevent(x,y);
        }
        else if(currentscene==4)
        {
            handlehelpevent(x,y);
        }
        else if(currentscene==5)
        {
            handleaboutevent(x,y);
        }
        else if(currentscene==3)
        {
            handlehighscoreevent(x,y);
        }
        else if(currentscene==1)
        {
            handleobjectiveevent(x,y);
        }
        else if(currentscene==2)
        {
            handlecategevent(x,y);
        }
    }
    private void handlecategevent(int x,int y)
    {
            sp1.setImage(categ1,categ1.getWidth(), categ1.getHeight());
            sp2.setImage(categ2,categ2.getWidth(), categ2.getHeight());
            sp3.setImage(categ3,categ3.getWidth(), categ3.getHeight());
            sp4.setImage(categ4,categ4.getWidth(), categ4.getHeight());
            sp5.setImage(categ5,categ5.getWidth(), categ5.getHeight());
            sp6.setImage(categ6,categ6.getWidth(), categ6.getHeight());
            if(x>=sp1.getX() && x<=sp1.getWidth()+sp1.getX() && y>=sp1.getY() && y<=sp1.getHeight()+sp1.getY())
            {
                s.categ=0;
            }
            else if(x>=sp2.getX() && x<=sp2.getWidth()+sp2.getX() && y>=sp2.getY() && y<=sp2.getHeight()+sp2.getY())
            {
                s.categ=1;
            }
            else if(x>=sp3.getX() && x<=sp3.getWidth()+sp3.getX() && y>=sp3.getY() && y<=sp3.getHeight()+sp3.getY())
            {
                s.categ=2;
            }
            else if(x>=sp4.getX() && x<=sp4.getWidth()+sp4.getX() && y>=sp4.getY() && y<=sp4.getHeight()+sp4.getY())
            {
                s.categ=3;
            }
            else if(x>=sp5.getX() && x<=sp5.getWidth()+sp5.getX() && y>=sp5.getY() && y<=sp5.getHeight()+sp5.getY())
            {
                s.categ=4;
            }
            else if(x>=sp6.getX() && x<=sp6.getWidth()+sp6.getX() && y>=sp6.getY() && y<=sp6.getHeight()+sp6.getY())
            {
                s.categ=5;
            }
            currentscene=0;
            repaint();
    }
    private void handlehighscoreevent(int x,int y)
    {
            currentscene=0;
            repaint();
    }
    private void handleaboutevent(int x,int y)
    {
            currentscene=0;
            repaint();
    }
    private void handleobjectiveevent(int x,int y)
    {
            currentscene=0;
            repaint();
    }
    private void handlehelpevent(int x,int y)
    {
            currentscene=0;
            repaint();
    }
    private void handlemenuevent(int x,int y)
    {
        spr[0].setImage(i1, i1.getWidth(),i1.getHeight());
        spr[1].setImage(i2, i2.getWidth(),i2.getHeight());
        spr[2].setImage(i3, i3.getWidth(),i3.getHeight());
        spr[3].setImage(i4, i4.getWidth(),i4.getHeight());
        spr[4].setImage(i5, i5.getWidth(),i5.getHeight());
        spr[5].setImage(i6, i6.getWidth(),i6.getHeight());
        spr[6].setImage(i7, i7.getWidth(),i7.getHeight());
        spriteback.setImage(back,back.getWidth(),backup.getHeight());
        btnlt.setImage(btnleft,btnleft.getWidth(),btnleft.getHeight());
        btnrt.setImage(btnright,btnrt.getWidth(),btnrt.getHeight());
        repaint();
        if(x>=btnlt.getX() && x<=btnlt.getWidth()+btnlt.getX() && y>=btnlt.getY() && y<=btnlt.getHeight()+btnlt.getY())
        {
                selectedindex=selectedindex-1;
                if(selectedindex<0)
                selectedindex=6;
                repaint();
        }
        if(x>=btnrt.getX() && x<=btnrt.getWidth()+btnrt.getX() && y>=btnrt.getY() && y<=btnrt.getHeight()+btnrt.getY())
        {
                selectedindex=(selectedindex+1)%7;
                repaint();
                serviceRepaints();
        }
        if(x>=spr[0].getX() && x<=spr[0].getWidth()+spr[0].getX() && y>=spr[0].getY() && y<=spr[0].getHeight()+spr[0].getY())
        {
                // menu play
                currentscene=0;
       		s.newgame();
        }
        if(x>=spr[1].getX() && x<=spr[1].getWidth()+spr[1].getX() && y>=spr[1].getY() && y<=spr[1].getHeight()+spr[1].getY())
        {
                // obj
                currentscene=1;
                repaint();
        }
        if(x>=spr[2].getX() && x<=spr[2].getWidth()+spr[2].getX() && y>=spr[2].getY() && y<=spr[2].getHeight()+spr[2].getY())
        {
                // categ
                currentscene=2;
                repaint();
        }
        if(x>=spr[3].getX() && x<=spr[3].getWidth()+spr[3].getX() && y>=spr[3].getY() && y<=spr[3].getHeight()+spr[3].getY())
        {
                // Highscore
                currentscene=3;
                repaint();
        }
        if(x>=spr[4].getX() && x<=spr[4].getWidth()+spr[4].getX() && y>=spr[4].getY() && y<=spr[4].getHeight()+spr[4].getY())
        {
                // Help
                currentscene=4;
                repaint();
        }
        if(x>=spr[5].getX() && x<=spr[5].getWidth()+spr[5].getX() && y>=spr[5].getY() && y<=spr[5].getHeight()+spr[5].getY())
        {
                // About
                currentscene=5;
                repaint();
        }
        if(x>=spr[6].getX() && x<=spr[6].getWidth()+spr[6].getX() && y>=spr[6].getY() && y<=spr[6].getHeight()+spr[6].getY())
        {

                repaint();
                try
                {
                    // Exit
                    currentscene=6;
                    s.exit();
                }
                catch (MIDletStateChangeException ex)
                {
                    ex.printStackTrace();
                }
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
            paintobjective(g);
        }
        else if(currentscene==2)
        {
            paintcategory(g);
        }
        else if(currentscene==3)
        {
            painthighscore(g);
        }
        else if(currentscene==4)
        {
            painthelp(g);
        }
        else if(currentscene==5)
        {
            paintabout(g);
        }
    }
    private void paintobjective(Graphics g)
    {
        Image obj=null;
        try
        {
            obj = Image.createImage("/obj.png");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        g.drawImage(bgimage,0,0,Graphics.LEFT|Graphics.TOP);
        g.drawImage(obj,width/2,height/2,Graphics.VCENTER|Graphics.HCENTER);
        spriteback.paint(g);
    }
    private void painthighscore(Graphics g)
    {
        g.drawImage(bgimage,0,0,Graphics.LEFT|Graphics.TOP);
        g.drawImage(hsimage,width/2,height/2,Graphics.VCENTER|Graphics.HCENTER);
        hs(g);
        spriteback.paint(g);
    }
    private void painthelp(Graphics g)
    {
        Image hlp=null;
        try
        {
            hlp = Image.createImage("/hlp.png");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        g.drawImage(bgimage,0,0,Graphics.LEFT|Graphics.TOP);
        g.drawImage(hlp,width/2,height/2,Graphics.VCENTER|Graphics.HCENTER);
        spriteback.paint(g);
    }
    private void paintabout(Graphics g)
    {
        Image abt=null;
        try
        {
            abt = Image.createImage("/abt.png");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        g.drawImage(bgimage,0,0,Graphics.LEFT|Graphics.TOP);
        g.drawImage(abt,width/2,height/2,Graphics.VCENTER|Graphics.HCENTER);
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
        try
        {
            spr[selectedindex-1].defineReferencePixel(0,0);
            spr[selectedindex-1].setPosition(5,50);
            spr[selectedindex-1].paint(g);
        }
        catch(Exception e)
        {
            spr[6].defineReferencePixel(0,0);
            spr[6].setPosition(5,50);
            spr[6].paint(g);
        }
        try
        {
        spr[selectedindex].defineReferencePixel(0,0);
        spr[selectedindex].setPosition(width/2-(spr[selectedindex].getWidth()/2),75);
        spr[selectedindex].paint(g);
        }
        catch(Exception e)
        {}
        try
        {
        spr[selectedindex+1].defineReferencePixel(0,0);
        spr[selectedindex+1].setPosition(width-5-spr[selectedindex].getWidth(),50);
        spr[selectedindex+1].paint(g);
        }
        catch(Exception e)
        {
        spr[0].defineReferencePixel(0,0);
        spr[0].setPosition(width-5-spr[selectedindex].getWidth(),50);
        spr[0].paint(g);
        }
        btnlt.paint(g);
        btnrt.paint(g);
    }

    private void cat(Graphics g)
    {
//	g.setColor(250,250,250);
//	g.fillRect(0,0,width,height);
//	g.setColor(170,170,200);
//	g.fillRoundRect(10,10,width-20,height-20,10,10);
        sp1.setPosition(width/2-(sp1.getWidth()/2),60);
        sp2.setPosition(width/2-(sp1.getWidth()/2),60+sp1.getHeight()*1);
        sp3.setPosition(width/2-(sp1.getWidth()/2),60+sp2.getHeight()*2);
        sp4.setPosition(width/2-(sp1.getWidth()/2),60+sp3.getHeight()*3);
        sp5.setPosition(width/2-(sp1.getWidth()/2),60+sp4.getHeight()*4);
        sp6.setPosition(width/2-(sp1.getWidth()/2),60+sp5.getHeight()*5);
        sp1.paint(g);
        sp2.paint(g);
        sp3.paint(g);
        sp4.paint(g);
        sp5.paint(g);
        sp6.paint(g);
    }

    private void hs(Graphics g)
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
            else
            {
                samp="";
            }
            rs.closeRecordStore();
	}
	catch(Exception e){}
//	g.setColor(170,170,200);
//	g.fillRoundRect(40,40,width-80,height-80,10,10);
	g.setColor(250,250,250);
	int scoreheight=140;
	g.setFont(highFont);
//	g.drawString("HIGHSCORES",40,scoreheight,20);
//	g.drawLine(0, 35+highFont.getHeight(), width, 35+highFont.getHeight());
//	g.drawLine(0,height-35, width,height-35);
	scoreheight=scoreheight+highFont.getHeight();
	if(samp!="")
	{
            String scr[]=sortclass.getscore(samp);
            for(int i=0;i<scr.length;i++)
            {
                if(scr[i]!=null)
		g.drawString(scr[i],85,(i+1)*40+scoreheight,20);
		else
                g.drawString("PLAYER    --",85,(i+1)*40+scoreheight,20);
            }
	}
	else
	{
            for(int i=1;i<=4;i++)
            {
                g.drawString("PLAYER    --",85,(i+1)*40+scoreheight,20);
            }
	}
    }
}