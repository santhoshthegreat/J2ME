package tettris;
import javax.microedition.lcdui.*;
public class Resizer
{
	public static Image resizeImage(Image src, int screenWidth, int screenHeight)
	{
	int srcWidth = src.getWidth();
	int srcHeight = src.getHeight();
	Image tmp = Image.createImage(screenWidth, srcHeight);
	Graphics g = tmp.getGraphics();
	int ratio = (srcWidth << 16) / screenWidth;
	int pos = ratio / 2;
	for (int x = 0; x < screenWidth; x++)
	{
		g.setClip(x, 0, 1, srcHeight);
		g.drawImage(src, x - (pos >> 16), 0, Graphics.LEFT | Graphics.TOP);
		pos += ratio;
	}
	Image resizedImage = Image.createImage(screenWidth, screenHeight);
	g = resizedImage.getGraphics();
	ratio = (srcHeight << 16) / screenHeight;
	pos = ratio / 2;
	for (int y = 0; y < screenHeight; y++)
	{
		g.setClip(0, y, screenWidth, 1);
		g.drawImage(tmp, 0, y - (pos >> 16), Graphics.LEFT | Graphics.TOP);
		pos += ratio;
	}
	return resizedImage;
	}
	public static Image resizeImagepng(Image src,int destW,int destH) 
	{
		int srcW = src.getWidth();
  		int srcH = src.getHeight();
		int div=1;
		// Correction of width and height to be divideable by div-parameter
  		if ((div != 0) && (destW % div != 0))
		{
    			for (int i=1; i<=div; i++)
			{
      			if ((destW - i) % div == 0)
				{
        				destW = destW - div;
        				if (destH % (destW / div) != 0)
					{
          					for (int j=1; j<=div; j++)
						{
            					if ((destH - j) % (destW / div) == 0)
							{
              						destH = destH - j;
             						break;
            					}
          					}
        				}
      			}
    			}
	  	}
		// Prepare arrays for line-by-line-processing
  		final int[] lineRGB = new int[srcW];
  		final int[] srcPos = new int[destW]; 
  		int n = 0;
  		int eps = -(srcW >> 1);
	  	for( int x = 0; x < srcW; x++ )
		{
    			eps += destW;
    			if ( (eps << 1) >= srcW )
			{
      			if( ++n == destW )
				{
      	  				break;
      			}
      			srcPos[n] = x;
      			eps -= srcW;
    			}
  		}
		final int[] dest = new int[destW*destH];
  		for( int y = 0; y < destH; y++ ) 
		{
    			src.getRGB( lineRGB, 0, srcW, 0, y * srcH / destH, srcW, 1 );
	    		for( int x = 0; x < destW; x++ )
			{
				dest[y*destW+x] = lineRGB[srcPos[x]];
    			}
		}
    		System.gc();
		Image destImg = Image.createRGBImage(dest, destW, destH, true);
  		return destImg;
	}
	public static Image transparentResizeImage(Image temp,int wd,int ht)
	{
	//Need an array (for RGB, with the size of original image)  
	int rgb[] = new int[temp.getWidth()*temp.getHeight()];  
	//Get the RGB array of image into "rgb"  
	temp.getRGB(rgb,0,temp.getWidth(),0,0,temp.getWidth(),temp.getHeight());  
	//Call to our function and obtain RGB2  
	int rgb2[] = reescalaArray(rgb,temp.getWidth(),temp.getHeight(),wd,ht);  
	//Create an image with that RGB array  
	Image  temp2 = Image.createRGBImage(rgb2,wd,ht,true);  
	return temp2;
	}
	private static int[] reescalaArray(int[] ini, int x, int y, int x2, int y2)
	{
		int out[] = new int[x2*y2];  
		for (int yy = 0; yy < y2; yy++) 
		{  
			int dy = yy * y / y2;  
			for (int xx = 0; xx < x2; xx++) 
			{
				int dx = xx * x / x2;  
		  		out[(x2*yy)+xx]=ini[(x*dy)+dx];  
		 	}
		}  
		return out;
	}
}