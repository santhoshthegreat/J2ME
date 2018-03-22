import javax.microedition.lcdui.Graphics;
public class DisplayBox {

	protected int x;	
	protected int y;	
	protected int width;	
	protected int height;	

	protected int fgColor;	
	protected int bgColor;	
	public DisplayBox(int x, int y, int width, int height,
					  int fgColor, int bgColor) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.fgColor = fgColor;
		this.bgColor = bgColor;
	}
	public final void paint(Graphics g) {
		g.setColor(0x00ffffff);
		g.fillRect(30, 80, width-60, height-120);	
		g.setColor(0x00000000);
		g.drawRect(30, 80, width-60, height-120);	
		this.paintBoxContents(g);
	}
	protected void paintBoxContents(Graphics g) {}
}
