import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.Sprite;
public class Mycanvas extends GameCanvas implements Runnable
{
    private int width;   // To hold screen width
    private int height;  // To hold screen height
    Graphics g;
    HealthCalculator bmimidlet;
    int aboutusscreen=0;
//    Image btngender,bg,bkup,panel;
    Image btngender,bg,panel;
    Image men,women;
    Image info,close;
    Image aboutscreen;
    Sprite spritemale,spritefemale;
    Image score,btnup,btndown;
            //,slider;
    Sprite spritescore1,spritescore2,spritescore3;
    Sprite spritebtnup,spritebtndown;
    Sprite spritebtnup1,spritebtndown1;
    Sprite spritebtnup2,spritebtndown2;
//    Sprite spriteslider;
//    Sprite spritelevel;
    Sprite spritemen,spritewomen;
    Sprite spriteinfo,spriteclose;
    int kg,cm;
    int age;
    double bodyfatp,bmi,bmr,calorieneed,lbm;
    String bmitext;
    int selectedactivitylevel; //1-5

    Image imgactivity;
    Sprite spriteactivity1,spriteactivity2,spriteactivity3,spriteactivity4,spriteactivity5;
    Image lvl;
    int gender;
    double sliderpos;
    FontUtil fu;

    private static Thread runner;
    private boolean isplay;


    public boolean isscrollable;
    // mode 1-start 0-stop
    public int scrollablebtn;
    // btntoscroll 1-htup,2-htdown,3-wtup,4-wt-down,ag-up,ag-down
    
    public Mycanvas(HealthCalculator bmimidlet)
    {
        super(true);
        this.bmimidlet=bmimidlet;
	setFullScreenMode(true);
	height=getHeight();
	width=getWidth();
        isplay=false;
        g=getGraphics();
        gender=0;
        kg=60;
        cm=160;
        age=26;
        bodyfatp=0;
        bmi=0;
        bmr=0;
        calorieneed=0;
        lbm=0;
        selectedactivitylevel=1;
        bmitext="";
        sliderpos=0;
        scrollablebtn=0;
        isscrollable=false;
        try
        {
            btngender = Image.createImage("/gender.png");
            bg = Image.createImage("/bg.png");
            btnup = Image.createImage("/btnup.png");
            btndown = Image.createImage("/btndown.png");
//            slider = Image.createImage("/slider.png");
            panel = Image.createImage("/panel.png");
            lvl = Image.createImage("/lvl.jpg");
            men= Image.createImage("/men.png");
            women= Image.createImage("/women.png");
            info= Image.createImage("/info.png");
            close= Image.createImage("/close.png");
            aboutscreen=Image.createImage("/about.png");
            imgactivity=Image.createImage("/activity.png");

 
            spritemale=new Sprite(btngender,100,100);
            spritefemale=new Sprite(btngender,100,100);
            spriteinfo=new Sprite(info,50,50);
            spriteinfo.setPosition(5,5);
            spriteclose=new Sprite(close,50,50);
            spriteclose.setPosition(width-45,5);


            spriteactivity1=new Sprite(imgactivity,120,20);
            spriteactivity2=new Sprite(imgactivity,120,20);
            spriteactivity3=new Sprite(imgactivity,120,20);
            spriteactivity4=new Sprite(imgactivity,120,20);
            spriteactivity5=new Sprite(imgactivity,120,20);
            spriteactivity1.setPosition(10,260);
            spriteactivity2.setPosition(10,280);
            spriteactivity3.setPosition(10,300);
            spriteactivity4.setPosition(10,320);
            spriteactivity5.setPosition(10,340);



            spriteactivity1.setFrame(0);
            spriteactivity2.setFrame(1);
            spriteactivity3.setFrame(2);
            spriteactivity4.setFrame(3);
            spriteactivity5.setFrame(4);
            score = Image.createImage("/panel.png");

            spritescore1=new Sprite(score);
            spritescore1.setPosition(20,140);

            spritescore2=new Sprite(score);
            spritescore2.setPosition(130, 140);

            spritescore3=new Sprite(score);
            spritescore3.setPosition(240,140);


            spritemale.setFrame(3);
            spritemale.setPosition(spritescore2.getX(),260);

            spritefemale.setFrame(0);
            spritefemale.setPosition(spritescore3.getX(),260);

            
            spritebtnup=new Sprite(btnup,90,40);
            spritebtndown=new Sprite(btndown,90,40);
            spritebtnup.setPosition(spritescore2.getX(),spritescore2.getY()-spritebtnup.getHeight()+6);
            spritebtndown.setPosition(spritescore2.getX(),spritescore2.getY()+spritescore2.getHeight()-10);

            spritebtnup1=new Sprite(btnup,90,40);
            spritebtndown1=new Sprite(btndown,90,40);
            spritebtnup1.setPosition(spritescore1.getX(),spritescore1.getY()-spritebtnup.getHeight()+6);
            spritebtndown1.setPosition(spritescore1.getX(),spritescore1.getY()+spritescore1.getHeight()-10);

            spritebtnup2=new Sprite(btnup,90,40);
            spritebtndown2=new Sprite(btndown,90,40);
            spritebtnup2.setPosition(spritescore3.getX(),spritescore3.getY()-spritebtnup.getHeight()+6);
            spritebtndown2.setPosition(spritescore3.getX(),spritescore3.getY()+spritescore3.getHeight()-10);

 //           spritelevel=new Sprite(lvl);
 //           spritelevel.setPosition((width-lvl.getWidth())/2,height-40);
 //           spriteslider=new Sprite(slider);
 //           spriteslider.setPosition((width-lvl.getWidth())/2,height-30);


            spritemen=new Sprite(men,70,150);
            spritewomen=new Sprite(women,70,150);
            spritemen.setPosition(280,400);
            spritewomen.setPosition(280,400);

            FontUtil.initialize();
            fu = FontUtil.getInstance();


System.out.println("4");

            bmicalc();
            fatcalc();
            bmrcalc();
            lbmcalc();
//            System.out.println(bmi);
//            System.out.println(bmr);
//            System.out.println(bodyfatp);
//            System.out.println(calorieneed);
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
        runner=new Thread(this);
        isplay=true;
        runner.start();
    }
    protected void pointerReleased(int x, int y)
    {
        isscrollable=false;
        if(aboutusscreen==1)
        {
            aboutusscreen=0;
        }
        else
        {
            spritebtnup.setFrame(0);
            spritebtnup1.setFrame(0);
            spritebtnup2.setFrame(0);
            spritebtndown.setFrame(0);
            spritebtndown1.setFrame(0);
            spritebtndown2.setFrame(0);
            spriteclose.setFrame(0);
            spriteinfo.setFrame(0);
            if(x>=spriteclose.getX() && x<=(spriteclose.getX()+spriteclose.getWidth()) && y>=spriteclose.getY() && y<=(spriteclose.getY()+spriteclose.getHeight()))
            {
                bmimidlet.exitapp();
            }
            else if(x>=spriteinfo.getX() && x<=(spriteinfo.getX()+spriteinfo.getWidth()) && y>=spriteinfo.getY() && y<=(spriteinfo.getY()+spriteinfo.getHeight()))
            {
                  aboutusscreen=1;
            }
        }
        bmicalc();
        fatcalc();
        bmrcalc();
        lbmcalc();
    }
    protected void pointerPressed(int x, int y)
    {
        if(x>=spriteclose.getX() && x<=(spriteclose.getX()+spriteclose.getWidth()) && y>=spriteclose.getY() && y<=(spriteclose.getY()+spriteclose.getHeight()))
        {
            spriteclose.setFrame(1);
        }
        else if(x>=spriteinfo.getX() && x<=(spriteinfo.getX()+spriteinfo.getWidth()) && y>=spriteinfo.getY() && y<=(spriteinfo.getY()+spriteinfo.getHeight()))
        {
            spriteinfo.setFrame(1);
        }
        else if(x>=spritemale.getX() && x<=(spritemale.getX()+spritemale.getWidth()) && y>=spritemale.getY() && y<=(spritemale.getY()+spritemale.getHeight()))
        {
            gender=0;
        }
        else if(x>=spritefemale.getX() && x<=(spritefemale.getX()+spritefemale.getWidth()) && y>=spritefemale.getY() && y<=(spritefemale.getY()+spritemale.getHeight()))
        {
            gender=1;

        }
        else if(x>=spritebtnup.getX() && x<=(spritebtnup.getX()+spritebtnup.getWidth()) && y>=spritebtnup.getY() && y<=(spritebtnup.getY()+spritebtnup.getHeight()))
        {
            // btnup
            spritebtnup.setFrame(1);
            scrollablebtn=1;
            isscrollable=true;
        }
        else if(x>=spritebtnup1.getX() && x<=(spritebtnup1.getX()+spritebtnup1.getWidth()) && y>=spritebtnup1.getY() && y<=(spritebtnup1.getY()+spritebtnup1.getHeight()))
        {
            // btnupcm
            spritebtnup1.setFrame(1);
            scrollablebtn=3;
            isscrollable=true;
        }
        else if(x>=spritebtnup2.getX() && x<=(spritebtnup2.getX()+spritebtnup2.getWidth()) && y>=spritebtnup2.getY() && y<=(spritebtnup2.getY()+spritebtnup2.getHeight()))
        {
            // btnup
            spritebtnup2.setFrame(1);
            scrollablebtn=5;
            isscrollable=true;
        }
        else if(x>=spritebtndown.getX() && x<=(spritebtndown.getX()+spritebtndown.getWidth()) && y>=spritebtndown.getY() && y<=(spritebtndown.getY()+spritebtndown.getHeight()))
        {
            // btnup
            spritebtndown.setFrame(1);
            scrollablebtn=2;
            isscrollable=true;
        }
        else if(x>=spritebtndown1.getX() && x<=(spritebtndown1.getX()+spritebtndown1.getWidth()) && y>=spritebtndown1.getY() && y<=(spritebtndown1.getY()+spritebtndown1.getHeight()))
        {
            // btnup
            spritebtndown1.setFrame(1);
            scrollablebtn=4;
            isscrollable=true;
        }
        else if(x>=spritebtndown2.getX() && x<=(spritebtndown2.getX()+spritebtndown2.getWidth()) && y>=spritebtndown2.getY() && y<=(spritebtndown2.getY()+spritebtndown2.getHeight()))
        {
            // btnup
            spritebtndown2.setFrame(1);
            scrollablebtn=6;
            isscrollable=true;
        }
        else if(x>=spriteactivity1.getX() && x<=(spriteactivity1.getX()+spriteactivity1.getWidth()) && y>=spriteactivity1.getY() && y<=(spriteactivity1.getY()+spriteactivity1.getHeight()))
        {
            selectedactivitylevel=1;
        }
        else if(x>=spriteactivity2.getX() && x<=(spriteactivity2.getX()+spriteactivity2.getWidth()) && y>=spriteactivity2.getY() && y<=(spriteactivity2.getY()+spriteactivity2.getHeight()))
        {
            selectedactivitylevel=2;
        }
        else if(x>=spriteactivity3.getX() && x<=(spriteactivity3.getX()+spriteactivity3.getWidth()) && y>=spriteactivity3.getY() && y<=(spriteactivity3.getY()+spriteactivity3.getHeight()))
        {
            selectedactivitylevel=3;
        }
        else if(x>=spriteactivity4.getX() && x<=(spriteactivity4.getX()+spriteactivity4.getWidth()) && y>=spriteactivity4.getY() && y<=(spriteactivity4.getY()+spriteactivity4.getHeight()))
        {
            selectedactivitylevel=4;
        }
        else if(x>=spriteactivity5.getX() && x<=(spriteactivity5.getX()+spriteactivity5.getWidth()) && y>=spriteactivity5.getY() && y<=(spriteactivity5.getY()+spriteactivity5.getHeight()))
        {
            selectedactivitylevel=5;
        }
        bmicalc();
        fatcalc();
        bmrcalc();
        lbmcalc();
    }
    private void bmicalc()
    {
        bmi=0;
        try
        {
            bmi=(double)kg/((cm*cm)/(double)10000);
            System.out.println("BMI : " + bmi);
        }
        catch(Exception e)
        {
            bmi=0;
        }
        int frameno=0;
        if(bmi<17.5)
        {
            bmitext="Under Weight";
            frameno=0;
        }
        else if(bmi<18.5)
        {
            bmitext="Under Weight";
            frameno=1;
        }
        else if(bmi<22)
        {
            bmitext="Normal Weight";
            frameno=2;
        }
        else if(bmi<25)
        {
            bmitext="Normal Weight";
            frameno=3;
        }
        else if(bmi<30)
        {
            bmitext="Over Weight";
            frameno=4;
        }
        else if(bmi<40)
        {
            bmitext="Obesity";
            frameno=5;
        }
        else
        {
            bmitext="Morbid Obesity";
            frameno=5;
        }
        spritemen.setFrame(frameno);
        spritewomen.setFrame(frameno);

    }
    private void bmrcalc()
    {
        try
        {
            if(gender==1)
            {
                bmr=(double)655+(9.6*(double)kg)+(1.8*(double)cm)-(4.7*(double)age);
            }
            else
            {
                bmr=(double)66+(13.7*(double)kg)+((double)5*(double)cm)-(6.8*(double)age);
                //66+(13.7*B53)+(5*B54)-(6.8*B55)
            }
            System.out.println("bmr : " +  bmr);
            if(selectedactivitylevel==1)
            calorieneed=bmr*1.2;
            else if(selectedactivitylevel==2)
            calorieneed=bmr*1.375;
            else if(selectedactivitylevel==3)
            calorieneed=bmr*1.55;
            else if(selectedactivitylevel==4)
            calorieneed=bmr*1.725;
            else if(selectedactivitylevel==5)
            calorieneed=bmr*1.9;
        }
        catch(Exception e)
        {
            bmr=0;
            calorieneed=0;
        }
    }
    private void fatcalc()
    {
        bodyfatp=0;
        try
        {
            if(age<=15)
            {
                bodyfatp=(1.51*bmi)-(0.70*(double)age)-(3.6*(double)gender)+1.4;
            }
            else
            {
                bodyfatp=(1.20*bmi)-(0.23*(double)age)-(10.8*(double)gender)-5.4;
            }
        }
        catch(Exception e)
        {
     //       spriteslider.setPosition(spritelevel.getX(),spriteslider.getY());
            bodyfatp=0;
        }
    }
    private void lbmcalc()
    {
        lbm=0;
        try
        {
            if(gender==0)
            {
                //(1.1)W[1 - (.011636)BMI]
                lbm=(1.1*(double)kg)*((double)1-0.11636)*bmi;
            }
            else
            {
                //(1.07)W[1 - (.013832)BMI]
                lbm=(1.07*(double)kg)*((double)1-.013832)*bmi;
            }
        }
        catch(Exception e)
        {
        }
    }
    void paintscreen(Graphics g)
    {
        if(aboutusscreen==1)
        {
//            g.setColor(255,255,255);
//            g.fillRect(0, 0, width, height);
            g.drawImage(aboutscreen, width/2, height/2,Graphics.VCENTER|Graphics.HCENTER);
        }
        else
        {
            g.drawImage(bg,width/2,height/2,Graphics.VCENTER|Graphics.HCENTER);
            g.setColor(255,255,255);

            if(gender==0)
            {
                spritemale.setFrame(3);
                spritefemale.setFrame(0);
                spritemen.paint(g);
            }
            else
            {
                spritemale.setFrame(2);
                spritefemale.setFrame(1);
                spritewomen.paint(g);
            }
            
//            spritelevel.paint(g);
//            spriteslider.paint(g);

            spritemale.paint(g);
            spritefemale.paint(g);
            spriteinfo.paint(g);
            spriteclose.paint(g);
            spritebtnup.paint(g);
            spritebtnup1.paint(g);
            spritebtnup2.paint(g);
            spritebtndown.paint(g);
            spritebtndown1.paint(g);
            spritebtndown2.paint(g);
            spritescore1.paint(g);
            spritescore2.paint(g);
            spritescore3.paint(g);
            try
            {
                fu.showFonts(String.valueOf(cm),g,spritescore1.getX()+22,spritescore1.getY()+10,width);
                fu.showFonts(String.valueOf(kg),g,spritescore2.getX()+24,spritescore2.getY()+10,width);
                fu.showFonts(String.valueOf(age),g,spritescore3.getX()+25,spritescore3.getY()+10,width);
                g.setClip(0, 0, width, height);
            }
            catch(Exception e)
            {}
            String weight="";
            if(gender==0)
            {
                if(bodyfatp<5)
                {
                    weight="Essential Fat";
                }
                else if(bodyfatp>=6&& bodyfatp<13)
                {
                    weight="Athletes";
                }
                else if(bodyfatp>=14&& bodyfatp<18)
                {
                    weight="Fitness";
                }
                else if(bodyfatp>=18&& bodyfatp<24)
                {
                    weight="Average";
                }
                else
                {
                    weight="Obese";
                }
            }
            else
            {
                if(bodyfatp<13)
                {
                    weight="Essential Fat";
                }
                else if(bodyfatp>=14 && bodyfatp<20)
                {
                    weight="Athletes";
                }
                else if(bodyfatp>=21 && bodyfatp<24)
                {
                    weight="Fitness";
                }
                else if(bodyfatp>=25 && bodyfatp<31)
                {
                    weight="Average";
                }
                else
                {
                    weight="Obese";
                }
            }
            g.setColor(0,0,0);
//          fu.showFonts(String.valueOf(bodyfatp),g,60,height-90,width);
//          g.setClip(0, 0, width, height);
            if(bmi==Double.NaN)
            {
                bmi=0;
            }
            if(bodyfatp==Double.NaN)
            {
                bodyfatp=0;
            }
            if(bmr==Double.NaN)
            {
                bmr=0;
            }
            if(calorieneed==Double.NaN)
            {
                calorieneed=0;
            }
            g.drawString("BMI",70,height-200,Graphics.TOP|Graphics.RIGHT);
            g.drawString(String.valueOf(Math.ceil(bmi)).substring(0,String.valueOf(Math.ceil(bmi)).length()-2),80,height-200,20);
            g.drawString(String.valueOf(bmitext),150,height-200,20);

            g.drawString("BMR",70,height-170,Graphics.TOP|Graphics.RIGHT);
            g.drawString(String.valueOf(Math.ceil(bmr)).substring(0, String.valueOf(Math.ceil(bmr)).length()-2),80,height-170,20);
            g.drawString("Metabolic Rate",150,height-170,20);

            g.drawString("FAT %",70,height-140,Graphics.TOP|Graphics.RIGHT);
            g.drawString(String.valueOf(Math.ceil(bodyfatp)).substring(0, String.valueOf(Math.ceil(bodyfatp)).length()-2),80,height-140,20);
            g.drawString(String.valueOf(weight),150,height-140,20);
//          fu.showFonts(String.valueOf(bmi),g,60,height-120,width);
//          g.setClip(0, 0, width, height);

            g.drawString("Calorie",70,height-110,Graphics.TOP|Graphics.RIGHT);
            g.drawString(String.valueOf(Math.ceil(calorieneed)).substring(0, String.valueOf(Math.ceil(calorieneed)).length()-2),80,height-110,20);
            g.drawString("Daily Need",150,height-110,20);

            g.drawString("LBM",70,height-80,Graphics.TOP|Graphics.RIGHT);
            g.drawString(String.valueOf(Math.ceil(lbm)).substring(0, String.valueOf(Math.ceil(lbm)).length()-2),80,height-80,20);
            g.drawString("Lean Body Mass",150,height-80,20);

            if(selectedactivitylevel==1)
            {
                spriteactivity1.setFrame(5);
                spriteactivity2.setFrame(1);
                spriteactivity3.setFrame(2);
                spriteactivity4.setFrame(3);
                spriteactivity5.setFrame(4);
            }
            else if(selectedactivitylevel==2)
            {
                spriteactivity1.setFrame(0);
                spriteactivity2.setFrame(6);
                spriteactivity3.setFrame(2);
                spriteactivity4.setFrame(3);
                spriteactivity5.setFrame(4);
            }
            else if(selectedactivitylevel==3)
            {
                spriteactivity1.setFrame(0);
                spriteactivity2.setFrame(1);
                spriteactivity3.setFrame(7);
                spriteactivity4.setFrame(3);
                spriteactivity5.setFrame(4);
            }
            else if(selectedactivitylevel==4)
            {
                spriteactivity1.setFrame(0);
                spriteactivity2.setFrame(1);
                spriteactivity3.setFrame(2);
                spriteactivity4.setFrame(8);
                spriteactivity5.setFrame(4);
            }
            else if(selectedactivitylevel==5)
            {
                spriteactivity1.setFrame(0);
                spriteactivity2.setFrame(1);
                spriteactivity3.setFrame(2);
                spriteactivity4.setFrame(3);
                spriteactivity5.setFrame(9);
            }
            spriteactivity1.paint(g);
            spriteactivity2.paint(g);
            spriteactivity3.paint(g);
            spriteactivity4.paint(g);
            spriteactivity5.paint(g);
        }
        g.setColor(250,250,250);
        g.drawString("+",spritebtnup.getX()+(spritebtnup.getWidth()/2)-6,spritebtnup.getY()+(spritebtnup.getHeight()/2)-10,20);
        g.drawString("-",spritebtndown.getX()+(spritebtndown.getWidth()/2)-6,spritebtndown.getY()+(spritebtndown.getHeight()/2)-10,20);
        g.drawString("+",spritebtnup1.getX()+(spritebtnup1.getWidth()/2)-6,spritebtnup1.getY()+(spritebtnup1.getHeight()/2)-10,20);
        g.drawString("-",spritebtndown1.getX()+(spritebtndown1.getWidth()/2)-6,spritebtndown1.getY()+(spritebtndown1.getHeight()/2)-10,20);
        g.drawString("+",spritebtnup2.getX()+(spritebtnup2.getWidth()/2)-6,spritebtnup2.getY()+(spritebtnup2.getHeight()/2)-10,20);
        g.drawString("-",spritebtndown2.getX()+(spritebtndown2.getWidth()/2)-6,spritebtndown2.getY()+(spritebtndown2.getHeight()/2)-10,20);
    }
    public void run()
    {
        while(isplay)
        {
            paintscreen(g);
            btnscroller();
            flushGraphics();
            try
            {
                Thread.sleep(50);
            }
            catch(Exception e){}
        }
    }
    private void btnscroller()
    {
        if(isscrollable)
        {
            if(scrollablebtn==1)
            {
                if(kg<=350)
                kg=kg+1;
            }
            if(scrollablebtn==2)
            {
                if(kg>0)
                kg=kg-1;
            }
            if(scrollablebtn==3)
            {
                if(cm<=350)
                cm=cm+1;
            }
            if(scrollablebtn==4)
            {
                if(cm>0)
                cm=cm-1;
            }
            if(scrollablebtn==5)
            {
                if(age<=150)
                age=age+1;
            }
            if(scrollablebtn==6)
            {
                if(age>0)
                age=age-1;
            }
            bmicalc();
            fatcalc();
            bmrcalc();
            lbmcalc();
        }
    }
}