import java.io.IOException;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
public class SplashScreen extends Canvas implements Runnable
{
	private Image mImage;
	Thirukkural tk;
	public SplashScreen(Thirukkural tk)
	{
            this.tk = tk;
            setFullScreenMode(true);
            try
            {
                mImage = Image.createImage("/sisconsoft.jpg");
                Thread t = new Thread(this);
                t.start();
            }
            catch(IOException e)
            {
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
            {
//                MenuScreen ms=new MenuScreen(tk);
//	        Display.getDisplay(tk).setCurrent(ms);
                tk.showmenu();
            }
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

    	/**
     	* A key release event triggers the dismiss()
     	* method to be called.
     	*/

    	public void keyReleased(int keyCode)
	{
      	dismiss();
    	}

    	/**
     	* A pointer release event triggers the dismiss()
     	* method to be called.
     	*/

    	public void pointerReleased(int x, int y)
	{
      	dismiss();
    	}
}