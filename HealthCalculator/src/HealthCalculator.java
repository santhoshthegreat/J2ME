import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDletStateChangeException;
public class HealthCalculator extends MIDlet implements CommandListener
{
//    public static menu m;
//    public static Mycanvas myc;
    public static Display dis;
    private Command cmdback;
    protected void destroyApp(boolean unconditional)
    {
        notifyDestroyed();
    }
    protected void pauseApp()
    {
    }
    public Display getDisplay()
    {
        return Display.getDisplay(this);
    }
    public static void showbmi()
    {
        Mycanvas myc = new Mycanvas();
    	dis.setCurrent(myc);
        myc.start();
    }
    public static void showmnu()
    {
        menu m=new menu();
        m.show();
    }
    protected void startApp() throws MIDletStateChangeException
    {
        dis=getDisplay();
        com.sun.lwuit.Display.init(this);
        dis.setCurrent(new SplashScreen(this));
        showmnu();
    }
    public void exitapp()
    {
        destroyApp(true);
    }
    public void exit()
    {
        destroyApp(false);
    }
    public void commandAction(Command c, Displayable displayable)
    {
        if(c==cmdback)
	{
            dis.setCurrent(null);
            HealthCalculator.showmnu();
	}
    }
}