import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
public class Memory extends MIDlet
{
    public Display d;
    public int categ;
    private static MenuScreen mnu;
    private static memblock memory;
    public Memory()
    {
        categ=0;
        mnu=new MenuScreen(this);
	d=Display.getDisplay(this);
    }
    protected void destroyApp(boolean unconditional) throws MIDletStateChangeException
    {
        notifyDestroyed();
    }
    public void showmenu()
    {
        mnu=new MenuScreen(this);
        d.setCurrent(mnu);
    }
    protected void pauseApp()
    {
    }
    protected void newgame()
    {
        memory=new memblock(this);
	startgame();
        setgame();
    }
    public void setgame()
    {
	d.setCurrent(memory);
    }
    public void startgame()
    {
        memory.start();
    }
    protected void startApp() throws MIDletStateChangeException
    {
       	d.setCurrent(new SplashScreen(this));
//        d.setCurrent(mnu);
    }
    public void exit() throws MIDletStateChangeException
    {
        destroyApp(false);
    }
}
