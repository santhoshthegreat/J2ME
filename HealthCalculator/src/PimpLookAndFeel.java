import com.sun.lwuit.Component;
import com.sun.lwuit.plaf.DefaultLookAndFeel;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
public class PimpLookAndFeel extends DefaultLookAndFeel
{
    private static final Image SCROLL_DOWN;
    private static final Image SCROLL_UP;
    static
    {
        Image sd = null;
        Image su = null;
        try
        {
            sd = Image.createImage("/down.png");
            su = Image.createImage("/up.png");
        }
        catch(Exception ioErr)
        {
            ioErr.printStackTrace();
        }
        SCROLL_DOWN = sd;
        SCROLL_UP = su;
}
private void drawScrollImpl(Graphics g, Component c, float offsetRatio, int blockSize, boolean vertical)
{
    int x = c.getX();
    int y = c.getY();
    int width, height, aX, aY, bX, bY;
    width = SCROLL_UP.getWidth();
    int margin = 0;
    aX = x + c.getWidth() - width - margin;
    x = aX;
    bX = aX;
    aY = y + margin;
    bY = y + c.getHeight() - margin - SCROLL_UP.getHeight();
    y = aY + SCROLL_UP.getHeight();
    height = c.getHeight() - SCROLL_UP.getHeight() * 2;
    g.setColor(0xffffff);
    g.fillRect(x, y, width, height);
    g.drawImage(SCROLL_UP, aX, aY,10);
    g.drawImage(SCROLL_DOWN, bX, bY,10);
    g.setColor(0xcccccc);
    g.fillRoundRect(x + 2, y + 2, width - 4, height - 4, 10, 10);
    g.setColor(0x333333);
    int offset = (int)(height * offsetRatio);
    g.fillRoundRect(x + 2, y + 2 + offset, width - 4, height / 3, 10, 10);
}
public void drawVerticalScroll(Graphics g, Component c, float offsetRatio, float blockSizeRatio)
{
     int blockSize = (int)(c.getHeight() * blockSizeRatio);
     drawScrollImpl(g, c, offsetRatio, blockSize, true);
}
public int getVerticalScrollWidth()
{
    return SCROLL_UP.getWidth();
}
public int getHorizontalScrollHeight()
{
    return 0;
}
}