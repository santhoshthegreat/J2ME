import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
public class SplashScreen extends Canvas implements Runnable
{
	private Image mImage;
        HealthCalculator bmimidlet;
	public SplashScreen(HealthCalculator bmimidlet)
        {
                this.bmimidlet=bmimidlet;
		setFullScreenMode(true);
	    try
		{
	    	mImage = Image.createImage("/siscon.png");
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
        	//set background color to overdraw what ever was previously displayed
        	g.setColor(0xFFFFFF);
        	g.fillRect(0,0, width, height);
        	g.drawImage(mImage,width/2,height/2,Graphics.HCENTER | Graphics.VCENTER);
    	}
    	/**
     	* Dismisses the splash screen with a key press or a pointer press
     	*/
    	public void dismiss()
    	{
            if (isShown())
            {
                bmimidlet.showbmi();
            }
        }
    	/**
     	* Default timeout with thread
     	*/
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