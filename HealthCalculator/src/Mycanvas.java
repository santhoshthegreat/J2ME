import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
public class Mycanvas extends Canvas
{
    private int width;   // To hold screen width
    private int height;  // To hold screen height
    Graphics g;
    Image btngender,bg,bk,bkup,panel,bmitxt;
    Image men,women;
    Sprite spritemale,spritefemale,spriteback;
    Sprite spritepanel1,spritepanel2;
    Image score,btnup,btndown,slider,ht,wid;
    Sprite spritescore1,spritescore2,spritescore3;
    Sprite spritebtnup,spritebtndown;
    Sprite spritebtnup1,spritebtndown1;
    Sprite spritebtnup2,spritebtndown2;
    Sprite spriteslider;
    Sprite spritelevel;
    Sprite spritemen,spritewomen;
    Image kg,inch,feet;
    int kg1,inch1,feet1;
    double bmi;
    Image lvl;
    int gender;
    double sliderpos;
    FontUtil fu;
    public Mycanvas()
    {
	setFullScreenMode(true);
	height=getHeight();
	width=getWidth();
        gender=0;
        kg1=50;
        inch1=6;
        feet1=5;
        bmi=0;
        sliderpos=15;
        // 0 - male
        // 1 - female
        try
        {
            btngender = Image.createImage("/gender.png");
            bg = Image.createImage("/bg.png");
            bk = Image.createImage("/back.png");
            panel = Image.createImage("/panel.png");
            lvl = Image.createImage("/lvl.png");
            score = Image.createImage("/score.png");
            btnup = Image.createImage("/btnup.png");
            btndown = Image.createImage("/btndown.png");
            kg = Image.createImage("/kg.png");
            inch = Image.createImage("/inch.png");
            feet = Image.createImage("/feet.png");
            slider = Image.createImage("/slider.png");
            bmitxt = Image.createImage("/bmi.png");
            ht = Image.createImage("/height.png");
            wid = Image.createImage("/width.png");
            men= Image.createImage("/men.png");
            women= Image.createImage("/women.png");
            spritemale=new Sprite(btngender,80,80);
            spritefemale=new Sprite(btngender,80,80);
            spriteback=new Sprite(bk,40,40);
            spriteback.setFrame(1);
            spriteback.setPosition(width-40, height-40);
            spritemale.setFrame(3);
            spritemale.setPosition((width/2)-80,height-120);
            spritefemale.setFrame(0);
            spritefemale.setPosition((width/2),height-120);
            spritepanel1=new Sprite(panel);
            spritepanel1.setPosition((width- spritepanel1.getWidth())/2,20+(panel.getHeight()/2));
            spritepanel2=new Sprite(panel);
            spritepanel2.setPosition((width- spritepanel1.getWidth())/2,panel.getHeight()+40+30+(panel.getHeight()/2)+20);

            spritescore1=new Sprite(score);
            spritescore1.setPosition(spritepanel1.getX()+140, spritepanel1.getY()+9);
            spritescore2=new Sprite(score);
            spritescore2.setPosition(spritepanel2.getX()+140, spritepanel2.getY()+9);

            spritescore3=new Sprite(score);
            spritescore3.setPosition(spritepanel2.getX()+10, spritepanel2.getY()+9);

            spritebtnup=new Sprite(btnup,50,40);
            spritebtndown=new Sprite(btndown,50,40);
            spritebtnup.setPosition(spritepanel1.getX()+spritepanel1.getWidth()-spritebtnup.getWidth(),spritepanel1.getY());
            spritebtndown.setPosition(spritepanel1.getX()+spritepanel1.getWidth()-spritebtnup.getWidth(),spritepanel1.getY()+spritepanel1.getHeight()-spritebtndown.getHeight());

            spritebtnup1=new Sprite(btnup,50,40);
            spritebtndown1=new Sprite(btndown,50,40);
            spritebtnup1.setPosition(spritepanel2.getX()+spritepanel2.getWidth()-spritebtnup1.getWidth(),spritepanel2.getY());
            spritebtndown1.setPosition(spritepanel2.getX()+spritepanel2.getWidth()-spritebtnup1.getWidth(),spritepanel2.getY()+spritepanel2.getHeight()-spritebtndown1.getHeight());

            spritebtnup2=new Sprite(btnup,50,40);
            spritebtndown2=new Sprite(btndown,50,40);
            spritebtnup2.setPosition(spritepanel2.getX()+(spritepanel2.getWidth()/2)-spritebtnup1.getWidth(),spritepanel2.getY());
            spritebtndown2.setPosition(spritepanel2.getX()+(spritepanel2.getWidth()/2)-spritebtnup1.getWidth(),spritepanel2.getY()+spritepanel2.getHeight()-spritebtndown1.getHeight());

            spritelevel=new Sprite(lvl);
            spritelevel.setPosition((width-lvl.getWidth())/2,height-160);
            spriteslider=new Sprite(slider);
            spriteslider.setPosition((width-lvl.getWidth())/2,height-170);


            spritemen=new Sprite(men,70,150);
            spritewomen=new Sprite(women,70,150);
            spritemen.setPosition(width/2+25,spritepanel2.getY()+spritepanel2.getHeight()+25);
            spritewomen.setPosition(width/2+25, spritepanel2.getY()+spritepanel2.getHeight()+25);
            
            FontUtil.initialize();
            fu = FontUtil.getInstance();
            bmi=calc();
        }
        catch(Exception ioErr)
        {
        }

    }
    protected void sizeChanged(int newWidth, int newHeight)
    {
        height=newHeight;
        width=newWidth;
    }
    public void start()
    {
        repaint();
    }
    protected void pointerReleased(int x, int y)
    {
        spriteback.setFrame(1);
        spritebtnup.setFrame(0);
        spritebtnup1.setFrame(0);
        spritebtnup2.setFrame(0);
        spritebtndown.setFrame(0);
        spritebtndown1.setFrame(0);
        spritebtndown2.setFrame(0);
        repaint();
        if(x>=spriteback.getX() && x<=(spriteback.getX()+spriteback.getWidth()) && y>=spriteback.getY() && y<=(spriteback.getY()+spriteback.getHeight()))
        {
            HealthCalculator.showmnu();
        }
    }
    protected void pointerPressed(int x, int y)
    {
        if(x>=spriteback.getX() && x<=(spriteback.getX()+spriteback.getWidth()) && y>=spriteback.getY() && y<=(spriteback.getY()+spriteback.getHeight()))
        {
            spriteback.setFrame(0);
        }
        else if(x>=spritemale.getX() && x<=(spritemale.getX()+spritemale.getWidth()) && y>=spritemale.getY() && y<=(spritemale.getY()+spritemale.getHeight()))
        {
            gender=0;
            bmi=calc();
        }
        else if(x>=spritefemale.getX() && x<=(spritefemale.getX()+spritefemale.getWidth()) && y>=spritefemale.getY() && y<=(spritefemale.getY()+spritemale.getHeight()))
        {
            gender=1;
            bmi=calc();
        }
        else if(x>=spritebtnup.getX() && x<=(spritebtnup.getX()+spritebtnup.getWidth()) && y>=spritebtnup.getY() && y<=(spritebtnup.getY()+spritebtnup.getHeight()))
        {
            // btnup
            spritebtnup.setFrame(1);
            if(kg1<200)
            {
                kg1=kg1+1;
            }
            bmi=calc();
        }
        else if(x>=spritebtnup1.getX() && x<=(spritebtnup1.getX()+spritebtnup1.getWidth()) && y>=spritebtnup1.getY() && y<=(spritebtnup1.getY()+spritebtnup1.getHeight()))
        {
            // btnup
            spritebtnup1.setFrame(1);
            if(inch1<12)
            {
                inch1=inch1+1;
            }
            bmi=calc();
        }
        else if(x>=spritebtnup2.getX() && x<=(spritebtnup2.getX()+spritebtnup2.getWidth()) && y>=spritebtnup2.getY() && y<=(spritebtnup2.getY()+spritebtnup2.getHeight()))
        {
            // btnup
            spritebtnup2.setFrame(1);
            if(feet1<12)
            {
                feet1=feet1+1;
            }
            bmi=calc();
        }
        else if(x>=spritebtndown.getX() && x<=(spritebtndown.getX()+spritebtndown.getWidth()) && y>=spritebtndown.getY() && y<=(spritebtndown.getY()+spritebtndown.getHeight()))
        {
            // btnup
            spritebtndown.setFrame(1);
            if(kg1>0)
            {
                kg1=kg1-1;
            }
            bmi=calc();
        }
        else if(x>=spritebtndown1.getX() && x<=(spritebtndown1.getX()+spritebtndown1.getWidth()) && y>=spritebtndown1.getY() && y<=(spritebtndown1.getY()+spritebtndown1.getHeight()))
        {
            // btnup
            spritebtndown1.setFrame(1);
            if(inch1>0)
            {
                inch1=inch1-1;
            }
            bmi=calc();
        }
        else if(x>=spritebtndown2.getX() && x<=(spritebtndown2.getX()+spritebtndown2.getWidth()) && y>=spritebtndown2.getY() && y<=(spritebtndown2.getY()+spritebtndown2.getHeight()))
        {
            // btnup
            spritebtndown2.setFrame(1);
            if(feet1>0)
            {
                feet1=feet1-1;
            }
            bmi=calc();
        }
        repaint();
    }
    private double calc()
    {
        // convert inch to cm
        //bmi= kg / ( ( cm*cm ) / 10000 )
        // 1 inch = 2.54 cm
        int myinch=0;
        myinch=inch1+(feet1*12);
        double mybmi=0;
        try
        {
            double cm = 2.54 * (double)myinch;
            mybmi= kg1 / ((cm*cm)/10000);
            String s=String.valueOf(mybmi);
            if(s.length()>4)
            {
                mybmi=Double.parseDouble(s.substring(0,3));
            }
            if(mybmi<=15)
            {
                sliderpos=spritelevel.getX();
                spriteslider.setPosition(spritelevel.getX()-10,spriteslider.getY());
            }
            else if(mybmi>=40)
            {
                sliderpos=spritelevel.getX()+spritelevel.getWidth();
                spriteslider.setPosition(spritelevel.getX()+spritelevel.getWidth()-10,spriteslider.getY());
            }
            else
            {
                sliderpos=(mybmi-15)*10;
                spriteslider.setPosition(spritelevel.getX()+(int)sliderpos-10,spriteslider.getY());
            }
            return mybmi;
        }
        catch(Exception e)
        {
            spriteslider.setPosition(spritelevel.getX(),spriteslider.getY());
        }
        return 0;
    }

    protected void paint(Graphics g)
    {
        if(gender==0)
        {
            spritemale.setFrame(3);
            spritefemale.setFrame(0);
        }
        else
        {
            spritemale.setFrame(2);
            spritefemale.setFrame(1);
        }
        g.drawImage(bg,width/2,height/2,Graphics.VCENTER|Graphics.HCENTER);
        g.setColor(255,255,255);
//      g.drawImage(panel,width/2,40+(panel.getHeight()/2),Graphics.VCENTER|Graphics.HCENTER);
//      g.drawImage(panel,width/2,panel.getHeight()+40+10+(panel.getHeight()/2),Graphics.VCENTER|Graphics.HCENTER);

        g.drawImage(kg,spritescore1.getX(),spritescore1.getY()-35, 20);
        g.drawImage(inch,spritescore2.getX(),spritescore2.getY()-35, 20);
        g.drawImage(feet,spritescore3.getX(),spritescore3.getY()-35, 20);

//        g.drawImage(lvl,width/2,height-160,Graphics.VCENTER|Graphics.HCENTER);
        spritelevel.paint(g);
        spriteslider.paint(g);

        spritepanel1.paint(g);
        spritepanel2.paint(g);

        g.drawImage(ht,40,spritepanel1.getY()-40,20);
        g.drawImage(wid,40,spritepanel2.getY()-60,20);
        spritemale.paint(g);
        spritefemale.paint(g);
        spriteback.paint(g);
        spritescore1.paint(g);
        spritescore2.paint(g);
        spritebtnup.paint(g);
        spritebtnup1.paint(g);
        spritebtndown.paint(g);
        spritebtndown1.paint(g);

        spritescore3.paint(g);
        spritebtnup2.paint(g);
        spritebtndown2.paint(g);
        try
        {
            fu.showFonts(String.valueOf(kg1),g,spritescore1.getX()+20,spritescore1.getY()+25,width);
            fu.showFonts(String.valueOf(inch1),g,spritescore2.getX()+20,spritescore2.getY()+25,width);
            fu.showFonts(String.valueOf(feet1),g,spritescore3.getX()+20,spritescore3.getY()+25,width);
            g.setClip(0, 0, width, height);
 //           fu.showFonts(String.valueOf(bmi),g,60,height-180,width);
        }
        catch(Exception e)
        {}
        g.setColor(0,0,0);
        g.drawImage(bmitxt, 40, height-310,20);
        fu.showFonts(String.valueOf(bmi),g,60,height-250,width);
        g.setClip(0, 0, width, height);
        String wt="";
        int frameno=0;
        if(bmi<17.5)
        {
            wt="Under Weight";
            frameno=0;
       //   spritemen.setFrame(0);
        }
        else if(bmi<18.5)
        {
            wt="Under Weight";
            frameno=1;
      //    spritemen.setFrame(1);
        }
        else if(bmi<22)
        {
            wt="Normal Weight";
            frameno=2;
      //    spritemen.setFrame(2);
        }
        else if(bmi<25)
        {
            wt="Normal Weight";
            frameno=3;
    //      spritemen.setFrame(3);
        }
        else if(bmi<30)
        {
            wt="Over Weight";
            frameno=4;
  //        spritemen.setFrame(4);
        }
        else if(bmi<40)
        {
            wt="Obesity";
            frameno=5;
//          spritemen.setFrame(5);
        }
        else
        {
            wt="Morbid Obesity";
            frameno=5;
   //       spritemen.setFrame(5);
        }
        if(gender==0)
        {
            spritemen.setFrame(frameno);
            spritemen.paint(g);
        }
        else
        {
            spritewomen.setFrame(frameno);
            spritewomen.paint(g);
        }
        g.drawString(wt, 30, height-220,20);
    }
}