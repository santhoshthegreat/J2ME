import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Image;
import javax.microedition.midlet.MIDlet;
public class FunnyFace extends MIDlet
{
    int xCursor = 20;
    int yCursor = 20;
    int xStart = 20;
    int yStart = 20;
    private final Display display;
    private static ImageCanvas imgc;
    private static FunnyFace funnyface;
    public Image currentImage;
    public int imageWidth = 0;
    public int imageHeight = 0;    
    private static FileSelector fileSelector;
    public boolean firstTimeFS = true;
    public String path = "0";
    public String pathandfile="0";
    public boolean waitingforimageload;
    public String fileselectormode;

    public int[] rgbData;
    public int[] rgbData1;
    public FunnyFace()
    {
        currentImage=null;
        firstTimeFS=true;
        funnyface=this;
        fileselectormode="";
        waitingforimageload=false;
        display = Display.getDisplay(funnyface);
        fileSelector=new FileSelector(funnyface);
        imgc=new ImageCanvas(funnyface);
     }
    public void startApp()
    {
        display.setCurrent(imgc);
    }
    public void pauseApp()
    {
    }
    public void destroyApp(boolean paramBoolean)
    {
        notifyDestroyed();
    }
    void canvasExit()
    {
        destroyApp(false);
    }
    void displayFileBrowser()
    {
        if(fileselectormode.equals("open"))
        {
            fileSelector.addCommand(fileSelector.openCommand);
            fileSelector.removeCommand(fileSelector.saveCommand);
            fileSelector.setSelectCommand(fileSelector.openCommand);
        }
        else if(fileselectormode.equals("save"))
        {
            fileSelector.addCommand(fileSelector.saveCommand);
        }
        if (firstTimeFS)
        {
            firstTimeFS=false;
            Displayable localDisplayable = display.getCurrent();
            if (localDisplayable == null)
            {
                int i = (System.getProperty("microedition.io.file.FileConnection.version") != null) ? 1 : 0;
                String str = "";
                if (i == 0)
                str = str + "\nFile Connection API is not available";
                Alert localAlert = new Alert(null, str, null, AlertType.INFO);
                if (i != 0)
                {
                    localAlert.setTimeout(50);
                    display.setCurrent(localAlert,fileSelector);
                }
                else
                {
                    display.setCurrent(localAlert);
                }
            }
            else
            {
                display.setCurrent(localDisplayable);
            }
        }
        display.setCurrent(fileSelector);
    }
  public void displayImage(String paramString)
  {
            warpmenu();
            imgc.selectedmenuitem=0;
            openimage opimg=new openimage(paramString,imgc.w,imgc.h);
        //    opimg.run();
            waitingforimageload=true;
            System.out.println("Loading");
            imgc.repaint();
  }
  public void warpFace(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
       if (paramInt3 > paramInt1)
       {
            if(paramInt3-paramInt1>4)
            {
                paramInt3=paramInt1+4;
            }
       }
       else  if (paramInt1 > paramInt3)
       {
            if(paramInt1-paramInt3>4)
            {
                paramInt3=paramInt1-4;
            }
       }
    if (paramInt4 > paramInt2)
    {
        if(paramInt4-paramInt2>4)
        {
            paramInt4=paramInt2+4;
        }
    }
    else if (paramInt2 > paramInt4)
    {
        if(paramInt2-paramInt4>4)
        {
            paramInt4=paramInt2-4;
        }
    }
    int l=0;
    imageWidth = currentImage.getWidth();
    imageHeight = currentImage.getHeight();
    int i = imageWidth * imageHeight;
    int j = paramInt3;
    int k = paramInt4;
    currentImage.getRGB(rgbData,0,imageWidth,0,0,imageWidth,imageHeight);
    for (l = 0; l < i; ++l)
    rgbData1[l] = rgbData[l];
    l = (yStart - 20) * imageWidth;
    int i1 = (yStart + 20) * imageWidth;
    int i2 = yStart * imageWidth;
    int i3;
    int i4;
    int i5;
    int i6;
    int i7;
    int i8;
    if (paramInt3 > paramInt1)
    {
      i3 = 100 * (paramInt3 - paramInt1 + 20) / 10;
      i4 = 25 * (paramInt3 - paramInt1);
      i5 = l;
      while (i5 < i1)
      {
        paramInt3 = j - i4 * (i5 / imageWidth - yStart) / 100 * (i5 / imageWidth - yStart) / 100;
        i3 = 100 * (paramInt3 - paramInt1 + 20) / 20;
        for (i7 = 0; i7 < paramInt3 - paramInt1 + 20; ++i7)
          rgbData1[(i5 + paramInt1 - 20 + i7)] = rgbData[(i5 + paramInt1 - 20 + 100 * i7 / i3)];
        i7 = i3 * (imageWidth - paramInt3) / 100;
        for (i8 = 0; i8 < paramInt3 - paramInt1 + 20; ++i8)
          rgbData1[(i5 + paramInt3 + 100 * i8 / i3)] = rgbData[(i5 + paramInt1 + i8)];
        i5 += imageWidth;
      }
    }
    else
    {
      i3 = 100 * (paramInt1 - paramInt3 + 20) / 10;
      i4 = 25 * (paramInt1 - paramInt3);
      i5 = l;
      while (i5 < i1)
      {
        paramInt3 = j + i4 * (i5 / imageWidth - yStart) / 100 * (i5 / imageWidth - yStart) / 100;
        i3 = 100 * (paramInt1 - paramInt3 + 20) / 20;
        for (i7 = 0; i7 < paramInt1 - paramInt3 + 20; ++i7)
          rgbData1[(i5 + paramInt1 + 20 - i7)] = rgbData[(i5 + paramInt1 + 20 - 100 * i7 / i3)];
       for (i7 = 0; i7 < paramInt1 - paramInt3 + 20; ++i7)
          rgbData1[(i5 + paramInt3 - 100 * i7 / i3)] = rgbData[(i5 + paramInt1 - i7)];
        i5 += imageWidth;
      }
    }
    int i9;
    int i10;
/*
 spot marker
    if ((paramInt1 == paramInt3) && (paramInt2 == paramInt4))
    {
//      i5 = imageWidth / 15;
      i5 = imageWidth / 40;
      i7 = paramInt3;
      if (i7 < 2 * i5)
        i7 = 2 * i5 + 1;
      if (i7 > imageWidth - 2 * i5)
        i7 = imageWidth - 2 * i5 - 1;
      i8 = paramInt4;
      if (i8 < 2 * i5)
        i8 = 2 * i5 + 1;
      if (i8 > imageHeight - 2 * i5)
        i8 = imageHeight - 2 * i5 - 1;
      i9 = -i5;
      for (i10 = i8 - i5; i10 < i8 + i5; ++i10)
      {
        int i12 = -1;
        int i13 = i7 - 2 * i5;
        for (int i14 = i7 - i5; i14 < i7 + i5; ++i14)
        {
          if ((i13 - i7) * (i13 - i7) + (i10 + i9 - i8) * (i10 + i9 - i8) < 4 * i5 * i5)
            rgbData1[((i10 + i9) * imageWidth + i13)] = rgbData[(i10 * imageWidth + i14)];
          if ((i13 - i7) * (i13 - i7) + (i10 + i9 + 1 - i8) * (i10 + i9 + 1 - i8) < 4 * i5 * i5)
            rgbData1[((i10 + i9 + 1) * imageWidth + i13)] = rgbData[(i10 * imageWidth + i14)];
          if ((++i13 - i7) * (i13 - i7) + (i10 + i9 - i8) * (i10 + i9 - i8) < 4 * i5 * i5)
            rgbData1[((i10 + i9) * imageWidth + i13)] = rgbData[(i10 * imageWidth + i14)];
          if ((i13 - i7) * (i13 - i7) + (i10 + i9 + 1 - i8) * (i10 + i9 + 1 - i8) < 4 * i5 * i5)
            rgbData1[((i10 + i9 + 1) * imageWidth + i13)] = rgbData[(i10 * imageWidth + i14)];
          ++i13;
        }
        ++i9;
      }
    }
    */
    Image localImage = Image.createRGBImage(this.rgbData1, this.imageWidth, this.imageHeight, false);
    currentImage = localImage;
    currentImage.getRGB(this.rgbData, 0, this.imageWidth, 0, 0, this.imageWidth, this.imageHeight);
    for (i6 = 0; i6 < i; ++i6)
      this.rgbData1[i6] = this.rgbData[i6];
    i6 = j;
    i7 = i6 - 20;
    i8 = i6 + 20;
    if (paramInt4 > paramInt2)
    {
      i3 = 100 * (paramInt4 - paramInt2 + 20) / 20;
      i4 = 25 * (paramInt4 - paramInt2);
      for (i9 = i7; i9 < i8 + 1; ++i9)
      {
        paramInt4 = k - i4 * (i9 - i6) / 100 * (i9 - i6) / 100;
        i3 = 100 * (paramInt4 - paramInt2 + 20) / 20;
        for (i10 = 0; i10 < paramInt4 - paramInt2 + 20; ++i10)
          this.rgbData1[(i9 + (paramInt2 - 20 + i10) * this.imageWidth)] = this.rgbData[(i9 + (paramInt2 - 20 + 100 * i10 / i3) * this.imageWidth)];
        for (i10 = 0; i10 < paramInt4 - paramInt2 + 20; ++i10)
          this.rgbData1[(i9 + (paramInt4 + 100 * i10 / i3) * this.imageWidth)] = this.rgbData[(i9 + (paramInt2 + i10) * this.imageWidth)];
      }
    }
    else
    {
      i3 = 100 * (paramInt2 - paramInt4 + 20) / 20;
      i4 = 25 * (paramInt2 - paramInt4);
      for (i9 = i7; i9 < i8 + 1; ++i9)
      {
        paramInt4 = k + i4 * (i9 - i6) / 100 * (i9 - i6) / 100;
        i3 = 100 * (paramInt2 - paramInt4 + 20) / 20;
        for (int i11 = 0; i11 < paramInt2 - paramInt4 + 20; ++i11)
          this.rgbData1[(i9 + (paramInt2 + 20 - i11) * this.imageWidth)] = this.rgbData[(i9 + (paramInt2 + 20 - 100 * i11 / i3) * this.imageWidth)];
        for ( int i11 = 0; i11 < paramInt2 - paramInt4 + 20; ++i11)
          this.rgbData1[(i9 + (paramInt4 - 100 * i11 / i3) * this.imageWidth)] = this.rgbData[(i9 + (paramInt2 - i11) * this.imageWidth)];
      }
    }
    localImage = Image.createRGBImage(this.rgbData1, this.imageWidth, this.imageHeight, false);
    currentImage = localImage;
  }
  public Image resize(Image paramImage, int paramInt1, int paramInt2)
  {
    System.out.println("0");
    int i = paramImage.getWidth();
    int j = paramImage.getHeight();
    System.out.println("1");
    int k;
    if (i < j)
      k = paramInt1;
    else
      k = paramInt2;
    if (i < k)
      k = i;
    rgbData = new int[i * j];
    rgbData1 = new int[paramInt1 * paramInt2];
    System.out.println("2");
    paramImage.getRGB(rgbData, 0, i, 0, 0, i, j);
    int l = 100 * i / paramInt1;
    if ((paramInt2 - 2) * l / 100 * i > rgbData.length)
    paramInt2 = rgbData.length / i * 100 / l;
    for (int i1 = 0; i1 < paramInt2; ++i1)
    for (int i2 = 0; i2 < paramInt1; ++i2)
    rgbData1[(i1 * paramInt1 + i2)] = rgbData[(i2 * l / 100 + i1 * l / 100 * i)];
    Image localImage = Image.createRGBImage(rgbData1, paramInt1, paramInt2, true);
    imageWidth = localImage.getWidth();
    imageHeight = localImage.getHeight();
    int i2 = imageWidth * imageHeight;
    rgbData = new int[i2];
    rgbData1 = new int[i2];
    System.out.println("3");
    localImage.getRGB(rgbData, 0, imageWidth, 0, 0, imageWidth, imageHeight);
    localImage.getRGB(rgbData1, 0,imageWidth, 0, 0, imageWidth, imageHeight);
    int i3 = 0;
    System.out.println("4");
    if (this.imageWidth > imageHeight)
    {
      for (int i4 = imageWidth - 1; i4 > -1; --i4)
        for (int i5 = 0; i5 < imageHeight; ++i5)
        {
          rgbData1[i3] = rgbData[(i4 + i5 * imageWidth)];
          ++i3;
        }
      int i4 = imageWidth;
      imageWidth = imageHeight;
      imageHeight = i4;
    System.out.println("5");
      Image tmpImage = Image.createRGBImage(rgbData1, imageWidth, imageHeight, false);
      localImage = tmpImage;
    }
    xCursor = (imageWidth / 2);
    yCursor = (imageHeight / 2);
    return localImage;
  }
    void showMsg(String paramString)
    {
        Alert localAlert = new Alert(null, paramString, null, AlertType.INFO);
        localAlert.setTimeout(3000);
        Display.getDisplay(this).setCurrent(localAlert, fileSelector);
    }

class openimage implements Runnable
{
    String paramfile;
    Image img;
    int w,h;
    public openimage(String s,int width,int height)
    {
        paramfile=s;
        img=null;
        w=width;
        h=height;
        Thread t=new Thread(this);
        t.start();
    }
    public void run()
    {
        try
        {
            System.out.println("Loading from thread image : " + paramfile);
            System.out.println("Loading from thread image : " + funnyface.path);
            FileConnection fc = (FileConnection)Connector.open(paramfile, 1);
            InputStream in = fc.openInputStream();
            img=Image.createImage(in);
            in.close();
            System.out.println("okok");
            funnyface.imageHeight=img.getHeight();
            funnyface.imageWidth=img.getWidth();
            img = resize(img,w,h);
            System.out.println("Thread image Loaded");
            funnyface.currentImage=img;
            System.out.println("Repainting thread image");
        }
        catch (Exception e)
        {
            System.out.println("Exception thrown : " + e.toString());
            System.out.println("Exception thrown : " + e.getMessage());
        }
        finally
        {
            waitingforimageload=false;
            imgc.repaint();
        }
    }
}
    public void warpmenu()
    {
        display.setCurrent(imgc);
    }
  public void refresh(int w,int h)
  {
    warpmenu();
    if(path.equals("0"))
    {
        try
        {
            currentImage = Image.createImage("/img2.jpg");
            imageWidth = currentImage.getWidth();
            imageHeight = currentImage.getHeight();
            currentImage = resize(currentImage,w,h);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    else
    {
        displayImage(pathandfile);
    }
  }

}
