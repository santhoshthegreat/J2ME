import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
public class tb extends DisplayBox 
{
	private int hiScore; // the current hi score
	private static final String TITLE_STRING = "Wooden Blocks";
	private static final String AUTHOR_STRING = "Santhosh";
	private static final String HI_SCORE_PREFIX_STRING = "HighScore: ";
	private static final String LEVEL_STRING = "Go to Menu";
	public tb(int x, int y, int width, int height,
					int fgColor, int bgColor,
					Font font) 
        {
		super(0, 0, width, height, fgColor, bgColor);
	}
	protected void paintBoxContents(Graphics g) 
        {
		int y = 100;	// get initial y, initial at bottom of the box
		g.drawString(LEVEL_STRING, width/2, y, Graphics.BOTTOM | Graphics.HCENTER);
		y += 50;
		g.drawString(HI_SCORE_PREFIX_STRING + this.hiScore, width/2, y, Graphics.BOTTOM | Graphics.HCENTER);
		y += 50;
		g.drawString(AUTHOR_STRING,width/2, y, Graphics.BOTTOM | Graphics.HCENTER);
		y += 50;
		g.drawString(TITLE_STRING, width/2, y, Graphics.BOTTOM | Graphics.HCENTER);
	}
	public boolean setHiScore(int hiScore) 
        {
            if(this.hiScore != hiScore) 
            {
                this.hiScore = hiScore;
		return true;
            }
            return false;
	}
}
