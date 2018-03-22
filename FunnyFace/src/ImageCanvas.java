import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
class ImageCanvas extends Canvas
{
  boolean modeWarp = false;
  public int w;
  public int h;
  Display disp;
  FunnyFace ff;
  public int selectedmenuitem;
  // 1-aboutus 0-menu and morph
  private Image menubg=null;
  private Image imgaboutus=null;
  Image btn1,btn2,btn3,btn4,btn5;
  Sprite sb1,sb2,sb3,sb4,sb5;
  ImageCanvas(FunnyFace ff)
  {
    this.ff=ff;
    setFullScreenMode(true);
    w = getWidth();
    h = getHeight()-20;
    selectedmenuitem=0;
    disp = Display.getDisplay(ff);
    try
    {
      menubg=Image.createImage("/bg.png");
      Resizer.resizeImage(menubg,360,100);
      btn1 = Image.createImage("/button1.png");
      btn2 = Image.createImage("/button2.png");
      btn3 = Image.createImage("/button3.png");
      btn4 = Image.createImage("/button4.png");
      btn5 = Image.createImage("/button5.png");
      imgaboutus = Image.createImage("/about.png");
      sb1=new Sprite(btn1,70,60);
      sb2=new Sprite(btn2,70,60);
      sb3=new Sprite(btn3,70,60);
      sb4=new Sprite(btn4,70,60);
      sb5=new Sprite(btn5,70,60);
      sb1.setPosition(5,h-90);
      sb2.setPosition(75,h-90);
      sb3.setPosition(145,h-90);
      sb4.setPosition(215,h-90);
      sb5.setPosition(285,h-90);
      ff.refresh(w,h);
      repaint();
    }
    catch (Exception Exception)
    {
      Exception.printStackTrace();
    }
  }
  protected void paint(Graphics g)
  {
    if(selectedmenuitem==0)
    {
        // showpics
        g.setColor(0,0,0);
        g.fillRect(0, 0,w,h);
//      paramGraphics.fillRect(xCursor - 2, yCursor - 2, 5, 5);
        if(ff.waitingforimageload==true)
        {
            System.out.println("Loading paint");
            g.setColor(0,0,250);
            g.drawString("Loading..",w-50,h-20,20);
        }
        else
        {
            if (ff.currentImage != null)
            {
                System.out.println("Loaded paint");
                g.drawImage(ff.currentImage, 0, 0, 20);
            }
        }
        g.drawLine(ff.xCursor - 2, ff.yCursor,ff.xCursor+2,ff.yCursor);
        g.drawLine(ff.xCursor , ff.yCursor - 2,ff.xCursor,ff.yCursor+2);
        g.drawRoundRect(ff.xCursor-3,ff.yCursor-3,6,6,50,50);
//        paramGraphics.drawLine(xStart, yStart, xCursor,yCursor);
        g.drawImage(menubg,0,h-menubg.getHeight(),20);
        sb1.paint(g);
        sb2.paint(g);
        sb3.paint(g);
        sb4.paint(g);
        sb5.paint(g);
    }
    else if(selectedmenuitem==1)
    {
        g.drawImage(imgaboutus, w/2,h/2,Graphics.HCENTER|Graphics.VCENTER);
    }
  }
  public void pointerReleased(int x,int y)
  {
    sb1.setFrame(0);
    sb2.setFrame(0);
    sb3.setFrame(0);
    sb4.setFrame(0);
    sb5.setFrame(0);
    if(selectedmenuitem==0)
    {
        if(y<h-menubg.getHeight())
        {
            try
            {
                ff.warpFace(ff.xStart, ff.yStart, ff.xCursor, ff.yCursor);
                ff.xStart = ff.xCursor;
                ff.yStart = ff.yCursor;
                modeWarp=false;
            }
            catch(Exception e)
            {
            }
        }
        if(x>sb1.getX() && x<(sb1.getX()+sb1.getWidth()) && y>sb1.getY() && y<(sb1.getY()+sb1.getHeight()))
        {
            // load
            selectedmenuitem=0;
            ff.fileselectormode="open";
            ff.waitingforimageload=false;            
            ff.displayFileBrowser();
        }
        else if(x>sb2.getX() && x<(sb2.getX()+sb2.getWidth()) && y>sb2.getY() && y<(sb2.getY()+sb2.getHeight()))
        {
            // refresh
            selectedmenuitem=0;
            ff.waitingforimageload=false;
            ff.refresh(w,h);
        }
        else if(x>sb3.getX() && x<(sb3.getX()+sb3.getWidth()) && y>sb3.getY() && y<(sb3.getY()+sb3.getHeight()))
        {
            //save
            selectedmenuitem=0;
            ff.fileselectormode="save";
            ff.waitingforimageload=false;            
            ff.displayFileBrowser();
        }
        else if(x>sb4.getX() && x<(sb4.getX()+sb4.getWidth()) && y>sb4.getY() && y<(sb4.getY()+sb4.getHeight()))
        {
            // about
            selectedmenuitem=1;
        }
        else if(x>sb5.getX() && x<(sb5.getX()+sb5.getWidth()) && y>sb5.getY() && y<(sb5.getY()+sb5.getHeight()))
        {
            selectedmenuitem=0;            
            ff.canvasExit();
        }
    }
    else if(selectedmenuitem==1)
    {
        selectedmenuitem=0;
    }
    repaint();
  }
  public void pointerDragged(int x,int y)
  {
    if(selectedmenuitem==0)
    {
        if(y<h-menubg.getHeight())
        {
            ff.xCursor=x;
            ff.yCursor=y;
            repaint();
        }
    }
  }
public void pointerPressed(int x,int y)
{
    if(selectedmenuitem==0)
    {
        if(x>sb1.getX() && x<(sb1.getX()+sb1.getWidth()) && y>sb1.getY() && y<(sb1.getY()+sb1.getHeight()))
        {
            sb1.setFrame(1);
        }
        else if(x>sb2.getX() && x<(sb2.getX()+sb2.getWidth()) && y>sb2.getY() && y<(sb2.getY()+sb2.getHeight()))
        {
            sb2.setFrame(1);
        }
        else if(x>sb3.getX() && x<(sb3.getX()+sb3.getWidth()) && y>sb3.getY() && y<(sb3.getY()+sb3.getHeight()))
        {
            sb3.setFrame(1);
        }
        else if(x>sb4.getX() && x<(sb4.getX()+sb4.getWidth()) && y>sb4.getY() && y<(sb4.getY()+sb4.getHeight()))
        {
            sb4.setFrame(1);
        }
        else if(x>sb5.getX() && x<(sb5.getX()+sb5.getWidth()) && y>sb5.getY() && y<(sb5.getY()+sb5.getHeight()))
        {
            sb5.setFrame(1);
        }
        else if(y<h-menubg.getHeight())
        {
            modeWarp=true;
            ff.xCursor=x;
            ff.yCursor=y;
            ff.xStart=x;
            ff.yStart=y;
        }
    }
    repaint();
}
  public void keyPressed(int paramInt)
  {
    int i = getGameAction(paramInt);
//    if (modeToolPaste)
      switch (i)
      {
      case 2:
        ff.xCursor -= 5;
        if (ff.xCursor < 0)
          ff.xCursor = 0;
        repaint();
        break;
      case 5:
        ff.xCursor += 5;
        repaint();
        break;
      case 1:
        ff.yCursor -= 5;
        if (ff.yCursor < 0)
          ff.yCursor = 0;
        repaint();
        break;
      case 6:
        if(ff.yCursor<h-menubg.getHeight())
        {
            ff.yCursor += 5;
            repaint();
            break;
        }

      case 8:
        ff.currentImage = Image.createRGBImage(ff.rgbData1,ff.imageWidth, ff.imageHeight, false);
        repaint();
      }
//    else
      switch (i)
      {
      case 2:
        ff.xCursor -= 5;
        if (ff.xCursor < 20)
          ff.xCursor = 20;
        repaint();
        break;
      case 5:
        ff.xCursor += 5;
        if (ff.xCursor > ff.imageWidth - 21)
          ff.xCursor = (ff.imageWidth - 21);
        repaint();
        break;
      case 1:
        ff.yCursor -= 5;
        if (ff.yCursor < 20)
          ff.yCursor = 20;
        repaint();
        break;
      case 6:
        if(ff.yCursor<h-menubg.getHeight())
        {
            ff.yCursor += 5;
            if (ff.yCursor > ff.imageHeight - 21)
            ff.yCursor = (ff.imageHeight - 21);
            repaint();
            break;
        }
      case 8:
        if (modeWarp)
        {
          ff.warpFace(ff.xStart,ff.yStart,ff.xCursor,ff.yCursor);
          modeWarp = false;
        }
        else
        {
          modeWarp = true;
          ff.xStart = ff.xCursor;
          ff.yStart = ff.yCursor;
        }
        repaint();
      }
  }
  protected void keyReleased(int paramInt)
  {
  }
}
