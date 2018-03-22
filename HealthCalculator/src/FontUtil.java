import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
public class FontUtil
{
//  public static int x;
//  public static int y;
    private static final int[] fontWidth={4,17,16,16,17,17,17,17,17,17,4,17,11,11};
    private static final int[] fontStartPos = {0,6,27,47,67,87,107,127,147,167,187,194,214,229};
    private static final char[] imageChars = {'1','2','3','4','5','6','7','8','9','0','.','E','-','+'};
    private static FontUtil instance = null;
    int fontImageHeight;
    private Image fontImage;
    private FontUtil()
    {
        try
        {
            this.fontImage = Image.createImage("/font.png");
        }
        catch (Exception exe)
        {
        }
        this.fontImageHeight = this.fontImage.getHeight();
    }
    public static void initialize()
    {
        instance = new FontUtil();
    }
    public static FontUtil getInstance()
    {
        return instance;
    }
    public void showFonts(String fonts,Graphics g,int x,int y,int w)
    {
        // add displacement
        x=x+3;
        char[] imagefontsarr=fonts.toCharArray();
        for (int i = 0; i < imagefontsarr.length; ++i)
        {
            char imageFonts = imagefontsarr[i];
            for (int j = 0; j < imageChars.length; ++j)
            {
                if (imageFonts==imageChars[j])
                {
                    if(x>=w-20)
                    {
                        break;
                    }
                    g.setClip(x,y,fontWidth[j],fontImageHeight);
                    g.drawImage(fontImage,x - fontStartPos[j],y,20);
                    x += fontWidth[j]+2;
                    break;
                }
            }
        }
    }
}