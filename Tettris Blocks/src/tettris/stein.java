package tettris;
import java.util.Random;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
public class stein
{
    Graphics imageGraphics;
    Image stein1;
    Image stein2;
    Image stein3;
    Image stein4;
    Image stein5;
    Image stein6;
    Image stein7;
    private int xpos;
    private int ypos;
    private int typ;
    private int rotation;
    private int oldxpos;
    private int oldypos;
    private int oldrotation;
    private int farbe;
    Random rand;
    public stein(int scaler)
    {
        this.rand = new Random();
        this.stein1 = Image.createImage(scaler + 1, scaler + 1);
        this.stein2 = Image.createImage(scaler + 1, scaler + 1);
        this.stein3 = Image.createImage(scaler + 1, scaler + 1);
        this.stein4 = Image.createImage(scaler + 1, scaler + 1);
        this.stein5 = Image.createImage(scaler + 1, scaler + 1);
        this.stein6 = Image.createImage(scaler + 1, scaler + 1);
        this.stein7 = Image.createImage(scaler + 1, scaler + 1);
        createpics(scaler);
    }
    private void createpics(int scaler)
    {
        for (int i = 1; i <= 7; ++i) 
        {
            switch (i)
            {
                case 1:
                    this.imageGraphics = this.stein1.getGraphics();
                    break;
                case 2:
                    this.imageGraphics = this.stein2.getGraphics();
                    break;
                case 3:
                    this.imageGraphics = this.stein3.getGraphics();
                    break;
                case 4:
                    this.imageGraphics = this.stein4.getGraphics();
                    break;
                case 5:
                    this.imageGraphics = this.stein5.getGraphics();
                    break;
                case 6:
                    this.imageGraphics = this.stein6.getGraphics();
                    break;
                case 7:
                    this.imageGraphics = this.stein7.getGraphics();
            }
            this.imageGraphics.setColor(privategetcolor(i));
            this.imageGraphics.fillRect(0, 0, scaler, scaler);
            this.imageGraphics.setColor(0);
            this.imageGraphics.drawRect(0, 0, scaler, scaler);
        }
    }
    public Image getImage(int pattern)
    {
        if ((pattern == 21) || (pattern == 11)) return this.stein1;
        if ((pattern == 22) || (pattern == 12)) return this.stein2;
        if ((pattern == 23) || (pattern == 13)) return this.stein3;
        if ((pattern == 24) || (pattern == 14)) return this.stein4;
        if ((pattern == 25) || (pattern == 15)) return this.stein5;
        if ((pattern == 26) || (pattern == 16)) return this.stein6;
        if ((pattern == 27) || (pattern == 17)) return this.stein7;
        return this.stein4;
    }
    public void neuerstein(int posx, int posy)
    {
        this.typ = (this.rand.nextInt() % 7 + 1);
        this.xpos = posx;
        this.ypos = posy;
        this.oldypos = posy;
        this.oldxpos = posx;
        this.rotation = 0;
        this.oldrotation = 0;
    }
    public void setbackxyr()
    {
        this.xpos = this.oldxpos;
        this.ypos = this.oldypos;
        this.rotation = this.oldrotation;
    }
    public void backupxyr() 
    {
        this.oldxpos = this.xpos;
        this.oldypos = this.ypos;
        this.oldrotation = this.rotation;
    }
    public void movelinks(int steps)
    {
        this.xpos -= steps;
    }
    public void moverechts(int steps)
    {
        this.xpos += steps;
    }
    public void movedown(int steps) 
    {
        this.ypos += steps;
    }
    public void rotate(int steps) 
    {
        switch (this.typ)
        {
        case 1:
            this.rotation = ((this.rotation + steps) % 1); break;
        case 2:
            this.rotation = ((this.rotation + steps) % 2); break;
        case 3:
            this.rotation = ((this.rotation + steps) % 4); break;
        case 4:
            this.rotation = ((this.rotation + steps) % 2); break;
        case 5:
            this.rotation = ((this.rotation + steps) % 2); break;
        case 6:
            this.rotation = ((this.rotation + steps) % 4); break;
        case 7:
            this.rotation = ((this.rotation + steps) % 4);
        }
    }
    public int getcolor(int pattern)
    {
        if ((pattern == 21) || (pattern == 11)) this.farbe = 16777113;
        else if ((pattern == 22) || (pattern == 12)) this.farbe = 6724044;
        else if ((pattern == 23) || (pattern == 13)) this.farbe = 13408767;
        else if ((pattern == 24) || (pattern == 14)) this.farbe = 16737894;
        else if ((pattern == 25) || (pattern == 15)) this.farbe = 10079487;
        else if ((pattern == 26) || (pattern == 16)) this.farbe = 3394713;
        else if ((pattern == 27) || (pattern == 17)) this.farbe = 6710886;
        return this.farbe;
    }
    private int privategetcolor(int type)
    {
        if (type == 1) this.farbe = 16777113;
        else if (type == 2) this.farbe = 6724044;
        else if (type == 3) this.farbe = 13408767;
        else if (type == 4) this.farbe = 16737894;
        else if (type == 5) this.farbe = 10079487;
        else if (type == 6) this.farbe = 3394713;
        else if (type == 7) this.farbe = 6710886;
        return this.farbe;  
    }
    public int getx()
    {
        return this.xpos;
    }
    public int gety() 
    {
        return this.ypos;
    }
    public int getrot() 
    {
        return this.rotation;
    }
    public int gettyp() 
    {
        return this.typ;
    }
    public int getoldx() 
    {
        return this.oldxpos;
    }
    public int getoldy() 
    {
        return this.oldypos;
    }
    public int getoldrot() 
    {
        return this.oldrotation;
    }
}