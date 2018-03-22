package forex;
import java.io.IOException;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDletStateChangeException;
public class TotalForexConverter extends MIDlet implements CommandListener
{
    public static menu m;
    static public Display dis;
    
    
    private List lst;
    public static String a="";
    public static String b="";
    public static String aval="0";
    public static String bval="0";
    public static convertercanvas cc;
    public static int a1=1;
    public static int b1=1;
    public static int sel=0;
    private Command cmdback,cmdok,cmdgo;
//	private Form form;
//	private Command exit;
//	private Command start;
	protected void destroyApp(boolean unconditional)
	{
		// TODO Auto-generated method stub
		notifyDestroyed();
	}
	protected void pauseApp() 
	{
		// TODO Auto-generated method stub
	}
    public Display getDisplay()
    {
        return Display.getDisplay(this);
    }
	public void showupdate()
	{
			Mycanvas myc = new Mycanvas(this);
    		dis.setCurrent(myc);
    		myc.start();
	}
	protected void startApp() throws MIDletStateChangeException 
	{
		  dis=getDisplay();
		  dis.setCurrent(new SplashScreen(this));
	      com.sun.lwuit.Display.init(this);
	      m=new menu(this);
	      cc=new convertercanvas(this,a,b);
	}
    protected void setcanvas()
    {
        a="INR";
        b="USD";
        cc.setvalues(a, b);
        dis.setCurrent(cc);
    }
    public void exitapp()
    {
        destroyApp(true);
    }
	public final void exit() 
	{
		destroyApp(false);
	}
	public void commandAction(Command c, Displayable displayable)
	{
		
		if (c == List.SELECT_COMMAND)
		{
                    if(sel==1)
                    {
                        a=lst.getString(lst.getSelectedIndex());
                        a1=lst.getSelectedIndex();

                    }
                    else
                    {
                        b=lst.getString(lst.getSelectedIndex());
                        b1=lst.getSelectedIndex();
                    }
                    cc.setvalues(a, b);
                    dis.setCurrent(cc);
		}
		if(c==cmdback)
		{
//                    myc.setvalues(a, b);
                    dis.setCurrent(cc);
		}
		if(c==cmdok)
		{
                    cc.setvalues(a, b);
                    dis.setCurrent(cc);
		}
		else if(c==cmdgo)
		{
                    cc.setvalues(a, b);
                    dis.setCurrent(cc);
		}
	}
    public static void convert() throws IOException
    {
     CvLogic cvl=new CvLogic();
     cvl.convert();
     cc.repaint();
     System.out.println(a);
     System.out.println(b);
    }
    public void showform1()
    {
            lst = new List("Convert From", List.IMPLICIT);
            lst.append("INR",null);
            lst.append("AUD",null);
            lst.append("USD",null);
            lst.append("ZWD",null);
            lst.append("EUR",null);
            lst.append("GBP",null);
            lst.append("SGD",null);
            lst.append("JPY",null);
            lst.append("CNY",null);
            lst.append("IDR",null);
            cmdgo=new Command("Select", Command.ITEM, 1);
            cmdback=new Command("Back", Command.BACK,0);
            lst.addCommand(cmdgo);
            lst.addCommand(cmdback);
            lst.setCommandListener(this);
            dis.setCurrent(lst);
        }
        public void showform2()
        {
            lst = new List("Convert to", List.IMPLICIT);
            lst.append("INR",null);
            lst.append("AUD",null);
            lst.append("USD",null);
            lst.append("ZWD",null);
            lst.append("EUR",null);
            lst.append("GBP",null);
            lst.append("SGD",null);
            lst.append("JPY",null);
            lst.append("CNY",null);
            lst.append("IDR",null);
            cmdgo=new Command("Select", Command.ITEM, 1);
            cmdback=new Command("Back", Command.BACK,0);
            lst.addCommand(cmdgo);
            lst.addCommand(cmdback);
            lst.setCommandListener(this);
            dis.setCurrent(lst);
        }
}
