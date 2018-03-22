package tettris;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
public class maingame extends Canvas
{
	private int width;
	private int height;
	private int scaler;
  	private int yoffset;
  	private int xoffset;
  	private int[][] tetrismat;
	public int level =1;
	private int reihen = 10;
  	private int points;
	private int allreihen = 0;
	private int x = 0;
	private int y = 0;
  	private int clipwidth;
  	private int clipheight;
  	private int ystart;
  	private int yende;
  	private int xstart;
  	private int xende;
  	private int fontxoffset;
  	private stein stein;
	private TimerTask timertask = new timertask();
	private Timer timer = new Timer();
	private Random random = new Random();
	private static final int XLENGHT = 11;
	private static final int YLENGHT = 21;
	private static final int WAND = 88;
	private static final int BODEN = 77;
	private static final int XSTARTPOS = 6;
	private static final int YSTARTPOS = 1;
	private static final int STARTREIHEN = 10;
	private static final int STARTLEVEL = 1;
	private static final int anker = 20;
	private boolean paintall = true;
	private boolean firstpaint = true;
	private boolean steindown = false;
	private boolean steindown2 = false;
	private boolean asleep = false;
	private boolean gostatus = false;
	private static final Font font = Font.getFont(64, 0, 8);
  	private Image dbuffer;
  	private Graphics myg;
	public void start()
  	{
		setFullScreenMode(true);
		this.width = getWidth();
		this.height = getHeight();
		this.scaler = (this.height / 21);
		this.dbuffer = Image.createImage(this.width, this.height);
		this.myg = this.dbuffer.getGraphics();
		this.scaler = (this.height / 21);
		this.fontxoffset = (this.xoffset + this.scaler * 11);
		this.xoffset = 2;
		this.yoffset = 0;
		this.stein = new stein(this.scaler);
		this.clipwidth = (6 * this.scaler + 3);
		this.clipheight = (6 * this.scaler + 3);
		fillmatrix();
		this.stein.neuerstein(6, 1);
		changematrix(this.stein);
		zeichnen();
                
                switch(level)
                {
                    case 1:
                        this.timer.schedule(this.timertask, 0L, 460L);
                        break;                    
                    case 2:
                        this.timer.schedule(this.timertask, 0L, 450L);
                        break;
                    case 3:
                        this.timer.schedule(this.timertask, 0L, 400L);
                        break;
                    case 4:
                        this.timer.schedule(this.timertask, 0L, 370L);
                        break;
                    case 5:
                        this.timer.schedule(this.timertask, 0L, 350L);
                        break;
                    case 6:
                        this.timer.schedule(this.timertask, 0L, 330L);
                        break;
                    case 7:
                        this.timer.schedule(this.timertask, 0L, 320L);
                        break;
                    case 8:
                        this.timer.schedule(this.timertask, 0L, 310L);
                        break;
                    case 9:
                        this.timer.schedule(this.timertask, 0L, 300L);
                        break;
                    case 10:
                        this.timer.schedule(this.timertask, 0L, 270L);                                
                        break;
                    case 11:
                        this.timer.schedule(this.timertask, 0L, 240L);                                
                        break;
                    case 12:
                        this.timer.schedule(this.timertask, 0L, 200L);                                
                        break;                         
                }
  	}
	protected void keyPressed(int keyCode)
	{
		if (this.asleep)
      		return;
		switch (getGameAction(keyCode))
    		{
    		case 1:
			this.stein.rotate(1);
			changematrix(this.stein);
			zeichnen();
			break;
    		case 2:
			this.stein.movelinks(1);
			changematrix(this.stein);
			zeichnen();
			break;
    		case 6:
			this.stein.movedown(1);
			while (changematrix(this.stein))
			this.stein.movedown(1);
			zeichnen();
			break;
    		case 5:
			this.stein.moverechts(1);
			changematrix(this.stein);
			zeichnen();
    		case 3:
    		case 4:
    		}
  	}
	private synchronized boolean changematrix(stein stein)
    	{
        if (steincheck(stein.gety(), stein.getx(), stein.getrot(), stein.gettyp() + 10, 0, stein.gettyp()))
        {
            if (!steincheck(stein.getoldy() + 1, stein.getoldx(), stein.getoldrot(), stein.gettyp() + 10, 0, stein.gettyp()))
            {
                this.paintall = false;
                steincheck(stein.getoldy(), stein.getoldx(), stein.getoldrot(), stein.gettyp() + 10, 1, stein.gettyp());
                stein.setbackxyr();
            }
            else if (stein.getrot() == stein.getoldrot()) 
            {
                this.paintall = true;
                steincheck(stein.getoldy(), stein.getoldx(), stein.getoldrot(), stein.gettyp() + 20, 1, stein.gettyp());
                for (int i = 0; i < 11; ++i) 
                {
                    if ((this.tetrismat[2][i] <= 20) || (this.tetrismat[2][i] == 88)) continue; levelstate(true);
                }
                droprow();
                stein.neuerstein(6, 1);
                this.steindown = true;
                if (changematrix(stein))
                {
                    this.points += 1;
                }
            }
            else
            {
                this.paintall = false;
                steincheck(stein.getoldy(), stein.getoldx(), stein.getoldrot(), stein.gettyp() + 10, 1, stein.gettyp());
                stein.setbackxyr();
            }
            return false;
        } 
        this.paintall = false;
        steincheck(stein.getoldy(), stein.getoldx(), stein.getoldrot(), 0, 1, stein.gettyp());
        steincheck(stein.gety(), stein.getx(), stein.getrot(), stein.gettyp() + 10, 1, stein.gettyp());
        stein.backupxyr(); 
        return true;
    }
    private boolean steincheck(int y, int x, int drehung, int pattern, int doit, int typ)
    {
        switch (typ)
        {
            case 1:
                switch (drehung)
                {
                    case 0:
                    if ((this.tetrismat[y][x] > 20) || (this.tetrismat[y][(x - 1)] > 20) || (this.tetrismat[(y + 1)][x] > 20) || (this.tetrismat[(y + 1)][(x - 1)] > 20))
                    {
                        return true;
                    }
                    if (doit == 1)
                    {
                        this.tetrismat[y][x] = pattern;
                        this.tetrismat[y][(x - 1)] = pattern;
                        this.tetrismat[(y + 1)][x] = pattern;
                        this.tetrismat[(y + 1)][(x - 1)] = pattern;
                    }
                    return false;
                }
                break;
            case 2:
                switch (drehung)
                {
                    case 0:
                    if ((this.tetrismat[y][x] > 20) || (this.tetrismat[y][(x - 1)] > 20) || (this.tetrismat[(y + 1)][x] > 20) || (this.tetrismat[(y + 1)][(x + 1)] > 20))
                    {
                        return true;
                    }
                    if (doit == 1)
                    {
                        this.tetrismat[y][x] = pattern;
                        this.tetrismat[y][(x - 1)] = pattern;
                        this.tetrismat[(y + 1)][x] = pattern;
                        this.tetrismat[(y + 1)][(x + 1)] = pattern;
                    }
                    return false;
                    case 1:
                    if ((this.tetrismat[y][x] > 20) || (this.tetrismat[(y + 1)][x] > 20) || (this.tetrismat[(y - 1)][(x + 1)] > 20) || (this.tetrismat[y][(x + 1)] > 20))
                    {
                        return true;
                    }
                    if (doit == 1)
                    {
                        this.tetrismat[y][x] = pattern;
                        this.tetrismat[(y + 1)][x] = pattern;
                        this.tetrismat[(y - 1)][(x + 1)] = pattern;
                        this.tetrismat[y][(x + 1)] = pattern;
                    }
                    return false;
                }
                break;
            case 3:
                switch (drehung)
                {
                    case 0:
                        if ((this.tetrismat[y][x] > 20) || (this.tetrismat[y][(x - 1)] > 20) || (this.tetrismat[y][(x + 1)] > 20) || (this.tetrismat[(y + 1)][(x - 1)] > 20))
                        {
                            return true;
                        }
                        if (doit == 1)
                        {
                            this.tetrismat[y][x] = pattern;
                            this.tetrismat[y][(x - 1)] = pattern;
                            this.tetrismat[y][(x + 1)] = pattern;
                            this.tetrismat[(y + 1)][(x - 1)] = pattern;
                        }
                        return false;
                    case 1:
                        if ((this.tetrismat[y][x] > 20) || (this.tetrismat[(y - 1)][x] > 20) || (this.tetrismat[(y + 1)][x] > 20) || (this.tetrismat[(y + 1)][(x + 1)] > 20))
                        {
                            return true;
                        }
                        if (doit == 1)
                        {
                            this.tetrismat[y][x] = pattern;
                            this.tetrismat[(y - 1)][x] = pattern;
                            this.tetrismat[(y + 1)][x] = pattern;
                            this.tetrismat[(y + 1)][(x + 1)] = pattern;
                        }
                        return false;
                     case 2:
                        if ((this.tetrismat[y][x] > 20) || (this.tetrismat[y][(x - 1)] > 20) || (this.tetrismat[y][(x + 1)] > 20) || (this.tetrismat[(y - 1)][(x + 1)] > 20))
                        {
                            return true;
                        }
                        if (doit == 1)
                        {
                            this.tetrismat[y][x] = pattern;
                            this.tetrismat[y][(x - 1)] = pattern;
                            this.tetrismat[y][(x + 1)] = pattern;
                            this.tetrismat[(y - 1)][(x + 1)] = pattern;
                        }
                        return false;
                     case 3:
                        if ((this.tetrismat[y][x] > 20) || (this.tetrismat[(y - 1)][x] > 20) || (this.tetrismat[(y + 1)][x] > 20) || (this.tetrismat[(y - 1)][(x - 1)] > 20))
                        {
                            return true;
                        }
                        if (doit == 1)
                        {
                            this.tetrismat[y][x] = pattern;
                            this.tetrismat[(y - 1)][x] = pattern;
                            this.tetrismat[(y + 1)][x] = pattern;
                            this.tetrismat[(y - 1)][(x - 1)] = pattern;
                        }
                        return false;
                    }
                    break;
                case 4:
                    switch (drehung)
                    {
                        case 0:
                        if ((this.tetrismat[y][x] > 20) || (this.tetrismat[y][(x + 1)] > 20) || (this.tetrismat[(y + 1)][x] > 20) || (this.tetrismat[(y + 1)][(x - 1)] > 20))
                        {
                            return true;
                        }
                        if (doit == 1)
                        {
                            this.tetrismat[y][x] = pattern;
                            this.tetrismat[y][(x + 1)] = pattern;
                            this.tetrismat[(y + 1)][x] = pattern;
                            this.tetrismat[(y + 1)][(x - 1)] = pattern;
                        }
                        return false;
                        case 1:
                        if ((this.tetrismat[y][x] > 20) || (this.tetrismat[(y + 1)][(x + 1)] > 20) || (this.tetrismat[y][(x + 1)] > 20) || (this.tetrismat[(y - 1)][x] > 20))
                        {
                            return true;
                        }
                        if (doit == 1)
                        {
                            this.tetrismat[y][x] = pattern;
                            this.tetrismat[(y + 1)][(x + 1)] = pattern;
                            this.tetrismat[y][(x + 1)] = pattern;
                            this.tetrismat[(y - 1)][x] = pattern;
                        }
                        return false;
                    }
                    break;
                case 5:
                    switch (drehung)
                    {
                        case 0:
                            if ((this.tetrismat[y][x] > 20) || (this.tetrismat[y][(x - 1)] > 20) || (this.tetrismat[y][(x - 2)] > 20) || (this.tetrismat[y][(x + 1)] > 20))
                            {
                                return true;
                            }
                            if (doit == 1)
                            {
                                this.tetrismat[y][x] = pattern;
                                this.tetrismat[y][(x - 1)] = pattern;
                                this.tetrismat[y][(x - 2)] = pattern;
                                this.tetrismat[y][(x + 1)] = pattern;
                            }
                            return false;
                        case 1:
                            if ((this.tetrismat[y][x] > 20) || (this.tetrismat[(y + 1)][x] > 20) || (this.tetrismat[(y + 2)][x] > 20) || (this.tetrismat[(y - 1)][x] > 20))
                            {
                                return true;
                            }
                            if (doit == 1)
                            {
                                this.tetrismat[y][x] = pattern;
                                this.tetrismat[(y + 1)][x] = pattern;
                                this.tetrismat[(y + 2)][x] = pattern;
                                this.tetrismat[(y - 1)][x] = pattern;
                            }
                            return false;
                        }
                        break;
                    case 6:
                        switch (drehung)
                        {
                            case 0:
                                if ((this.tetrismat[y][x] > 20) || (this.tetrismat[y][(x - 1)] > 20) || (this.tetrismat[y][(x + 1)] > 20) || (this.tetrismat[(y + 1)][(x + 1)] > 20))
                                {
                                    return true;
                                }
                                if (doit == 1)
                                {
                                    this.tetrismat[y][x] = pattern;
                                    this.tetrismat[y][(x - 1)] = pattern;
                                    this.tetrismat[y][(x + 1)] = pattern;
                                    this.tetrismat[(y + 1)][(x + 1)] = pattern;
                                }
                                return false;
                            case 1:
                                if ((this.tetrismat[y][x] > 20) || (this.tetrismat[(y - 1)][x] > 20) || (this.tetrismat[(y + 1)][x] > 20) || (this.tetrismat[(y - 1)][(x + 1)] > 20))
                                {
                                    return true;
                                }
                                if (doit == 1)
                                {
                                    this.tetrismat[y][x] = pattern;
                                    this.tetrismat[(y - 1)][x] = pattern;
                                    this.tetrismat[(y + 1)][x] = pattern;
                                    this.tetrismat[(y - 1)][(x + 1)] = pattern;
                                }       
                                return false;
                            case 2:
                                if ((this.tetrismat[y][x] > 20) || (this.tetrismat[y][(x - 1)] > 20) || (this.tetrismat[y][(x + 1)] > 20) || (this.tetrismat[(y - 1)][(x - 1)] > 20))
                                {
                                    return true;
                                }
                                if (doit == 1)
                                {
                                    this.tetrismat[y][x] = pattern;
                                    this.tetrismat[y][(x - 1)] = pattern;
                                    this.tetrismat[y][(x + 1)] = pattern;
                                    this.tetrismat[(y - 1)][(x - 1)] = pattern;
                                }
                                return false;
                            case 3:
                                if ((this.tetrismat[y][x] > 20) || (this.tetrismat[(y - 1)][x] > 20) || (this.tetrismat[(y + 1)][x] > 20) || (this.tetrismat[(y + 1)][(x - 1)] > 20))
                                {
                                    return true;
                                }
                                if (doit == 1)
                                {
                                    this.tetrismat[y][x] = pattern;
                                    this.tetrismat[(y - 1)][x] = pattern;
                                    this.tetrismat[(y + 1)][x] = pattern;
                                    this.tetrismat[(y + 1)][(x - 1)] = pattern;
                                }
                                return false;
                        }
                        break;
                    case 7:
                        switch (drehung)
                        {
                            case 0:
                            if ((this.tetrismat[y][x] > 20) || (this.tetrismat[y][(x - 1)] > 20) || (this.tetrismat[y][(x + 1)] > 20) || (this.tetrismat[(y + 1)][x] > 20))
                            {
                               return true;
                            }
                            if (doit == 1)
                            {
                                this.tetrismat[y][x] = pattern;
                                this.tetrismat[y][(x - 1)] = pattern;
                                this.tetrismat[y][(x + 1)] = pattern;
                                this.tetrismat[(y + 1)][x] = pattern;
                            }
                            return false;
                            case 1:
                                if ((this.tetrismat[y][x] > 20) || (this.tetrismat[(y - 1)][x] > 20) || (this.tetrismat[(y + 1)][x] > 20) || (this.tetrismat[y][(x + 1)] > 20))
                                {
                                    return true;
                                }
                                if (doit == 1)
                                {
                                    this.tetrismat[y][x] = pattern;
                                    this.tetrismat[(y - 1)][x] = pattern;
                                    this.tetrismat[(y + 1)][x] = pattern;
                                    this.tetrismat[y][(x + 1)] = pattern;
                                }
                                return false;
                                case 2:
                                if ((this.tetrismat[y][x] > 20) || (this.tetrismat[y][(x - 1)] > 20) || (this.tetrismat[y][(x + 1)] > 20) || (this.tetrismat[(y - 1)][x] > 20))
                                {
                                    return true;
                                }
                                if (doit == 1)
                                {
                                    this.tetrismat[y][x] = pattern;
                                    this.tetrismat[y][(x - 1)] = pattern;
                                    this.tetrismat[y][(x + 1)] = pattern;
                                    this.tetrismat[(y - 1)][x] = pattern;
                                }
                                return false;
                                case 3:
                                if ((this.tetrismat[y][x] > 20) || (this.tetrismat[(y - 1)][x] > 20) || (this.tetrismat[(y + 1)][x] > 20) || (this.tetrismat[y][(x - 1)] > 20))
                                {
                                    return true;
                                }
                                if (doit == 1)
                                {
                                    this.tetrismat[y][x] = pattern;
                                    this.tetrismat[(y - 1)][x] = pattern;
                                    this.tetrismat[(y + 1)][x] = pattern;
                                    this.tetrismat[y][(x - 1)] = pattern;
                                }
                                return false;
                            }
                        }
            return true;
        }
        private void droprow()
        {
            for (int y = 1; y < 21; ++y)
            {
                boolean leerstelle = false;
                for (int x = 1; x < 11; ++x)
                {
                    if (this.tetrismat[y][x] != 0)
                    continue;
                    leerstelle = true;
                }
                if (!leerstelle) 
                {
                    for (; y > 1; --y) 
                    {
                        leerstelle = false;
                        for (x = 0; x <= 11; ++x)
                            this.tetrismat[y][x] = this.tetrismat[(y - 1)][x];
                    }
                    levelstate(false);
                    for (x = 0; x <= 11; ++x) this.tetrismat[y][x] = 0;
                    for (y = 0; y <= 21; ++y)
                    {
                        this.tetrismat[y][0] = 88; this.tetrismat[y][11] = 88; 
                    }
                    droprow();
                }
            }
        }
        public void levelstate(boolean gameover)
        {
            if (!gameover) 
            {
                if (this.reihen > 1) 
                {
                    this.reihen -= 1;
                    this.allreihen += 1;
                }
                else 
                {
                    this.reihen = 10;
                    if (this.level < 12) 
                    {
                        this.level += 1;
                        this.timertask.cancel();
                        this.timertask = new timertask();
                        switch (this.level)
                        {
                            case 2:
                                this.timer.schedule(this.timertask, 0L, 450L);
                                break;
                            case 3:
                                this.timer.schedule(this.timertask, 0L, 400L);
                                break;
                            case 4:
                                this.timer.schedule(this.timertask, 0L, 370L);
                                break;
                            case 5:
                                this.timer.schedule(this.timertask, 0L, 350L);
                                break;
                            case 6:
                                this.timer.schedule(this.timertask, 0L, 330L);
                                break;
                            case 7:
                                this.timer.schedule(this.timertask, 0L, 320L);
                                break;
                            case 8:
                                this.timer.schedule(this.timertask, 0L, 310L);
                                break;
                            case 9:
                                this.timer.schedule(this.timertask, 0L, 300L);
                                break;
                            case 10:
                                this.timer.schedule(this.timertask, 0L, 60L);                                
                                break;
                            case 11:
                                this.timer.schedule(this.timertask, 0L, 280L);                                
                                break;
                            case 12:
                                this.timer.schedule(this.timertask, 0L, 270L);                                
                                break;                                
                        }
                    }
                }
            }
            else
            {
                fillmatrix();
                this.gostatus = true;
                this.level = 1;
                this.reihen = 10;
                this.allreihen = 0;
                this.points = 0;
                gosleep();
            }
        }
        private void fillmatrix()
        {
            this.tetrismat = new int[22][12];
            for (int i = 0; i <= 21; ++i) 
            {
                for (int j = 0; j <= 11; ++j) 
                {
                    this.tetrismat[21][11] = 0;
                }
            }
            for (int i = 0; i <= 11; ++i)
            {
                this.tetrismat[21][i] = 77;
            }
            for (int i = 0; i <= 21; ++i)
            {
                this.tetrismat[i][0] = 88;
                this.tetrismat[i][11] = 88;
            }
        }
        protected synchronized void paint(Graphics g)
        {
            if ((this.paintall) || (this.steindown))
            {
                g.setClip(this.xoffset + this.scaler, this.yoffset + this.scaler, this.width, this.height);
                this.steindown = false;
            }
            else if (this.firstpaint)
            {
                g.setClip(0, 0, this.width, this.height);
                this.firstpaint = false;
            }
            else
            {
                this.x = (this.xoffset + (this.stein.getx() - 3) * this.scaler);
                this.y = (this.yoffset + (this.stein.gety() - 3) * this.scaler);
                g.setClip(this.x, this.y, this.clipwidth, this.clipheight);
            }
            g.drawImage(this.dbuffer, 0, 0, 20);
        }
        private synchronized void zeichnen()
        {
            this.myg.setColor(16777215);
            this.myg.fillRect(0, 0, this.width, this.height);
            if ((this.asleep) && (!this.gostatus)) 
            {
                try 
                {
                    Image picpause = Image.createImage("paused.png");
                    this.myg.drawImage(picpause, (this.width - picpause.getWidth()) / 2, (this.height - picpause.getHeight()) / 2, 20);
                }
                catch (Exception e)
                {
                    this.myg.drawString("- Pause -", this.width / 2, this.height / 2, 20);
                }
            }
            else if (this.gostatus)
            {
                try 
                {
                    Image picgameover = Image.createImage("goscreen.png");
                    this.myg.drawImage(picgameover, (this.width - picgameover.getWidth()) / 2, (this.height - picgameover.getHeight()) / 2, 20);
                }
                catch (Exception e)
                {
                    this.myg.drawString("- Game over -", this.width / 2, this.height / 2, 20);
                }
            }
            else
            {
                this.myg.setColor(14869218);
                this.myg.fillRect(this.xoffset + this.scaler + 1, this.yoffset + this.scaler + 1, 11 * this.scaler - this.scaler - 1, 21 * this.scaler - this.scaler - 1);
                this.myg.setColor(0);
                this.myg.drawRect(this.xoffset + this.scaler, this.yoffset + this.scaler, 11 * this.scaler - this.scaler, 21 * this.scaler - this.scaler);
                this.myg.setFont(font);
                this.myg.drawString("  Rows", this.fontxoffset, this.yoffset + this.scaler, 20);
                this.myg.drawString("    " + this.allreihen, this.fontxoffset, this.yoffset + this.scaler + font.getHeight(), 20);
                this.myg.drawString("  Level", this.fontxoffset, this.yoffset + this.scaler + font.getHeight() * 2, 20);
                this.myg.drawString("    " + this.level, this.fontxoffset, this.yoffset + this.scaler + font.getHeight() * 3, 20);
                this.myg.drawString("  Points", this.fontxoffset, this.yoffset + this.scaler + font.getHeight() * 4, 20);
                this.myg.drawString("    " + this.points, this.fontxoffset, this.yoffset + this.scaler + font.getHeight() * 5, 20);
                if ((this.paintall) || (this.steindown) || (this.firstpaint)) 
                {
                    this.ystart = 0;
                    this.xstart = 0;
                    this.yende = 21;
                    this.xende = 11;
                }
                else 
                {
                    this.ystart = (this.stein.gety() - 3);
                    this.xstart = (this.stein.getx() - 3);
                    this.yende = (this.stein.gety() + 3);
                    this.xende = (this.stein.getx() + 3);
                    if (this.ystart < 0)
                        this.ystart = 0;
                    if (this.xstart < 0)
                        this.xstart = 0;
                    if (this.yende > 21)
                        this.yende = 21;
                    if (this.xende > 11)
                        this.xende = 11;
                }
                for (this.y = this.ystart; this.y <= this.yende; this.y += 1)
                {
                    for (this.x = this.xstart; this.x <= this.xende; this.x += 1)
                    {
                        if ((this.tetrismat[this.y][this.x] <= 10) || (this.tetrismat[this.y][this.x] >= 30))
                        continue;
                        this.myg.drawImage(this.stein.getImage(this.tetrismat[this.y][this.x]), this.xoffset + this.x * this.scaler, this.yoffset + this.y * this.scaler, 20);
                    }
                }
        }
        repaint();
    }
    public void wakemeup()
    {
        this.gostatus = false;
        this.asleep = false;
        this.firstpaint = true;
        zeichnen();
        this.timertask.cancel();
        this.timertask = new timertask();
        switch (this.level)
        {
            case 2:
                this.timer.schedule(this.timertask, 0L, 450L); 
                break;
            case 3:
                this.timer.schedule(this.timertask, 0L, 400L);
                break;
            case 4:
                this.timer.schedule(this.timertask, 0L, 370L); 
                break;
            case 5:
                this.timer.schedule(this.timertask, 0L, 350L); 
                break;
            case 6:
                this.timer.schedule(this.timertask, 0L, 330L); 
                break;
            case 7:
                this.timer.schedule(this.timertask, 0L, 320L); 
                break;
            case 8:
                this.timer.schedule(this.timertask, 0L, 310L); 
                break;
            case 9:
                this.timer.schedule(this.timertask, 0L, 300L);
        }
    }
    public void gosleep()
    {
        this.timertask.cancel();
        this.asleep = true;
        this.firstpaint = true;
        zeichnen();
    }
    class timertask extends TimerTask
    {
        timertask()
        {
        }
        public void run()
        {
            maingame.this.stein.movedown(1);
            maingame.this.changematrix(maingame.this.stein);
            maingame.this.zeichnen();
        }
    }
}