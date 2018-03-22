package tettris;
import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
public class SplashScreen extends Canvas implements Runnable
{
	private Image mImage;
	private Display d;
	private mytetris hg;
	public SplashScreen(mytetris hg)
	{
		setFullScreenMode(true);
	  	this.hg = hg;
	      try
		{
                    mImage = Image.createImage("/sisconsoft.png");
                    Thread t = new Thread(this);
                    t.start();
                }
                catch(IOException e)
		{
                    System.out.println("Error in splash");
                    e.printStackTrace();
        	}
    	}
    	public void paint(Graphics g)
	{
                int width = getWidth();
        	int height = getHeight();
        	g.setColor(0xFFFFFF);
        	g.fillRect(0,0, width, height);
        	g.drawImage(mImage,width/2,height/2,Graphics.HCENTER | Graphics.VCENTER);
    	}
    	public void dismiss()
	{
      	if (isShown())
	hg.showmenu();
	}
    	public void run()
	{
      	try
		{
            	Thread.sleep(3000);//set for 3 seconds
        	}
        	catch (InterruptedException e)
		{
            	System.out.println("InterruptedException");
            	e.printStackTrace();
        	}
        	dismiss();
    	}
    	public void keyReleased(int keyCode)
	{
      	dismiss();
    	}
	public void pointerReleased(int x, int y)
	{
      	dismiss();
    	}
}