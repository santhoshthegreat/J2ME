import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
public class mybox extends DisplayBox {

	private String caption;	
	private int value;	
	private Font font;	
	public mybox(int x, int y, int width, int height,
				   int fgColor, int bgColor,
				   Font font, String caption,  int initialValue) {
		super(x, y, width, height, fgColor, bgColor);
		
		this.caption = caption;
		this.font = font;
		this.value = initialValue;
	}
	
	public boolean updateValue(int value) {
		if(this.value != value) {
			this.value = value;
			return true;
		}
		
		return false;
	}
	
	protected void paintBoxContents(Graphics g) {
		int xCenter = this.x + (this.width / 2);
		g.setFont(this.font);             
                g.setColor(0x00ffffff);
		g.fillRect(x, y+20,width, height-30);            
                g.setColor(0x00555555);               
		g.drawString(this.caption, xCenter, this.y, Graphics.TOP | Graphics.HCENTER);
		g.setFont(this.font);
		g.drawString(Integer.toString(this.value), xCenter, this.y + this.height-10, Graphics.HCENTER | Graphics.BOTTOM);
	}
}
