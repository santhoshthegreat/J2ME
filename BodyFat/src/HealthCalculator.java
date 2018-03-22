import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDletStateChangeException;
public class HealthCalculator extends MIDlet implements CommandListener
{
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
    public void showbmi()
    {
        Mycanvas myc = new Mycanvas(this);
    	dis.setCurrent(myc);
        myc.start();
    }
    protected void startApp() throws MIDletStateChangeException
    {
        dis=getDisplay();
        dis.setCurrent(new SplashScreen(this));
    }
    public void exitapp()
    {
        this.destroyApp(true);
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

	}
    }
}