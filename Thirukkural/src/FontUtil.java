import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import net.mypapit.java.StringTokenizer;
public class FontUtil {
			public static int x;
			public static int y;
			private static final int[] fontWidth={12,8,11,12,13,11,12,11,13,12,19,24,18,13,19,25,12,13,17,16,16,15,14,14,15,15,14,14,14,14,16,14,14,14,16,15,16,16,16,16,13,13,16,15,14,14,18,12,16,19,13,18,14,18,16,24,18,24,19,17,26,14,15,20,20,15,26,14,15,20,21,19,28,16,16,23,19,17,27,14,15,22,20,17,29,18,20,22,25,22,34,25,20,27,18,11,15,17,15,19,21,12,16,19,17,25,17,10,14,17,15,19,18,11,16,17,15,21,18,17,20,17,21,24,23,18,25,22,20,27,14,9,19,13,19,23,17,18,19,18,20,20,22,21,24,24,26,23,21,21,21,26,28,28,30,27};
		private static final int[] fontStartPos = {0,13,22,34,46,59,71,83,95,108,120,139,164,182,195,214,239,254,270,287,303,319,334,348,362,378,392,406,420,434,448,464,478,492,506,522,537,553,569,585,602,616,629,645,660,674,689,708,720,737,756,770,788,813,831,847,872,890,914,933,950,976,990,1005,1025,1045,1061,1087,1101,1116,1136,1157,1176,1204,1220,1236,1259,1278,1295,1322,1336,1351,1373,1394,1411,1440,1458,1478,1501,1526,1548,1582,1607,1627,1654,1672,1683,1698,1715,1730,1749,1770,1782,1798,1817,1833,1856,1874,1884,1899,1916,1931,1951,1969,1980,1996,2013,2028,2049,2067,2084,2104,2121,2142,2166,2189,2207,2232,2254,2274,2301,2315,2324,2343,2356,2376,2399,2416,2434,2453,2471,2491,2511,2433,2554,2578,2602,2628,2651,2672,2693,2714,2740,2768,2796,2826};
private static final String[] imageChars = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59","60","61","62","63","64","65","66","67","68","69","70","71","72","73","74","75","76","77","78","79","80","81","82","83","84","85","86","87","88","89","90","91","92","93","94","95","96","97","98","99","100","101","102","103","104","105","106","107","108","109","110","111","112","113","114","115","116","117","118","119","120","121","122","123","124","125","126","127","128","129","130","131","132","133","134","135","136","137","138","139","140","141","142","143","144","145","146","147","148","149","150","151","152","153","154","155"}; 
	private static FontUtil instance = null;
	int fontImageHeight;
	private Image fontImage;
	static int wid;
	private FontUtil()
	{		
		try
		{
			this.fontImage = Image.createImage("/font.png");
		}
		catch (Exception exe)
		{
			exe.printStackTrace();
		}
		this.fontImageHeight = this.fontImage.getHeight();
	}
	public static void initialize(int w)
	{
		instance = new FontUtil();
		wid=w;
	}
	public static FontUtil getInstance()
	{		
		return instance;
	}

public void showFonts(String fonts,Graphics g)
{
	StringTokenizer st=new StringTokenizer(fonts,",");
	String[] imagefontsarr=st.toArray();
	for (int i = 0; i < imagefontsarr.length; ++i)
	{
		String imageFonts = imagefontsarr[i];
		for (int j = 0; j < imageChars.length; ++j)
		{
			if (imageFonts.compareTo(imageChars[j])==0)
			{
				if(x>(wid-15))
				{
					y=y+25;
					x=50;
				}
				g.setClip(x,y,this.fontWidth[j],this.fontImageHeight);
				g.drawImage(this.fontImage,x - this.fontStartPos[j],y,20);
				x += this.fontWidth[j];
				break;
			}
		}
	}
}

}






