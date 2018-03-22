import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;
class ImageCanvas extends Canvas implements Runnable, CommandListener
{
  private static final Command cExitCommand = new Command("Exit", 7, 4);
  private static final Command cPlayCommand = new Command("Play movie", 8, 2);
  private static final Command cPicCommand = new Command("Load picture", 8, 1);
  private static final Command cLoadCommand = new Command("Load movie", 8, 2);
  private static final Command cSaveCommand = new Command("Save movie", 8, 2);
  private static final Command cStopCommand = new Command("Stop movie", 8, 2);
  private final FaceDisasterMIDlet midlet;
  private static final int CHUNK_SIZE = 1024;
  private Image currentImage = null;
  private Image tempImage = null;
  String mImgName = "";
  public Image[] imgMovie = new Image[10];
  int advCount = 0;
  int indexMovie = 0;
  int indexMovieMax = 0;
  boolean indexOver = false;
  int indexMovieLast = 0;
  public int[] rgbData;
  public int[] rgbData1;
  boolean paintFirsttime = true;
  int paintParam = 0;
  int xCursor = 20;
  int yCursor = 20;
  int xStart = 20;
  int yStart = 20;
  boolean modeWarp = false;
  Image resizedImage;
  public Image toolImage;
  public int wTool;
  public int hTool;
  public int w;
  public int h;
  boolean modePlayMovie = false;
  int debug = 0;
  Image tmpImage;
  Display disp;
  Display dispthis;
  long startTime = 0L;
  Random rand = new Random();
  int indexI = 0;
  int indexJ = 0;
  int indexDif = 0;
  boolean nextPaint = true;
  boolean switchUp = true;
  int imageWidth = 0;
  int imageHeight = 0;
  String nameReg = "";
  String cStrTemp = "";
  String inputMovie = "";
  private List implicitList;
  boolean loadMovieMode = false;
  boolean modeToolPaste = false;
  boolean reklamaMode = false;
  boolean flagContinue = false;
  ImageCanvas(FaceDisasterMIDlet paramFaceDisasterMIDlet)
  {
    this.midlet = paramFaceDisasterMIDlet;
    setFullScreenMode(true);
    this.w = getWidth();
    this.h = getHeight();
    this.disp = Display.getDisplay(paramFaceDisasterMIDlet);
    addCommand(cPlayCommand);
    addCommand(cPicCommand);
    addCommand(cExitCommand);
    addCommand(cLoadCommand);
    addCommand(cSaveCommand);
    setCommandListener(this);
  }

  public void displayImage(String paramString)
  {
    try
    {
      if (paramString == "automa")
      {
        int i = 2 + this.rand.nextInt() % 2;
        this.currentImage = Image.createImage("/img" + i + ".jpg");
      }
      else
      {
        FileConnection localFileConnection = (FileConnection)Connector.open(paramString, 1);
        InputStream localInputStream = localFileConnection.openInputStream();
        long l = localFileConnection.fileSize();
        int j = 0;
        byte mybyte[] = new byte[0];
        while (j < l)
        {
          byte[] arrayOfByte1 = new byte[1024];
          int k = localInputStream.read(arrayOfByte1, 0, 1024);
          byte[] arrayOfByte2 = new byte[mybyte.length + 1024];
          System.arraycopy(mybyte, 0, arrayOfByte2, 0, j);
          System.arraycopy(arrayOfByte1, 0, arrayOfByte2, j, k);
          mybyte = arrayOfByte2;
          j += k;
        }
        localInputStream.close();
        localFileConnection.close();
        this.currentImage = Image.createImage(mybyte, 0, j);
      }
      setFullScreenMode(true);
      this.w = getWidth();
      this.h = getHeight();
      this.resizedImage = createThumbnail(this.currentImage, this.w, this.h);
      this.imgMovie[0] = this.resizedImage;
      this.indexMovie = 1;
      this.currentImage = null;
      repaint();
    }
    catch (IOException localIOException)
    {
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  private Image createThumbnail(Image paramImage, int paramInt1, int paramInt2)
  {
    int i = paramImage.getWidth();
    int j = paramImage.getHeight();
    int k;
    if (i < j)
      k = paramInt1;
    else
      k = paramInt2;
    if (i < k)
      k = i;
    this.rgbData = new int[i * j];
    this.rgbData1 = new int[paramInt1 * paramInt2];
    paramImage.getRGB(this.rgbData, 0, i, 0, 0, i, j);
    int l = 100 * i / paramInt1;
    if ((paramInt2 - 2) * l / 100 * i > this.rgbData.length)
      paramInt2 = this.rgbData.length / i * 100 / l;
    for (int i1 = 0; i1 < paramInt2; ++i1)
      for (int i2 = 0; i2 < paramInt1; ++i2)
        this.rgbData1[(i1 * paramInt1 + i2)] = this.rgbData[(i2 * l / 100 + i1 * l / 100 * i)];
    Image localImage = Image.createRGBImage(this.rgbData1, paramInt1, paramInt2, true);
    this.imageWidth = localImage.getWidth();
    this.imageHeight = localImage.getHeight();
    int i2 = this.imageWidth * this.imageHeight;
    this.rgbData = new int[i2];
    this.rgbData1 = new int[i2];
    localImage.getRGB(this.rgbData, 0, this.imageWidth, 0, 0, this.imageWidth, this.imageHeight);
    localImage.getRGB(this.rgbData1, 0, this.imageWidth, 0, 0, this.imageWidth, this.imageHeight);
    int i3 = 0;
    if (this.imageWidth > this.imageHeight)
    {
      for (int i4 = this.imageWidth - 1; i4 > -1; --i4)
        for (int i5 = 0; i5 < this.imageHeight; ++i5)
        {
          this.rgbData1[i3] = this.rgbData[(i4 + i5 * this.imageWidth)];
          ++i3;
        }
      int i4 = this.imageWidth;
      this.imageWidth = this.imageHeight;
      this.imageHeight = i4;
      this.tmpImage = Image.createRGBImage(this.rgbData1, this.imageWidth, this.imageHeight, false);
      localImage = this.tmpImage;
    }
    this.xCursor = (this.imageWidth / 2);
    this.yCursor = (this.imageHeight / 2);
    return localImage;
  }

  public void warpFace(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.imageWidth = this.resizedImage.getWidth();
    int l=0;
    this.imageHeight = this.resizedImage.getHeight();
    int i = this.imageWidth * this.imageHeight;
    int j = paramInt3;
    int k = paramInt4;
    this.resizedImage.getRGB(this.rgbData, 0, this.imageWidth, 0, 0, this.imageWidth, this.imageHeight);
    for (l = 0; l < i; ++l)
    this.rgbData1[l] = this.rgbData[l];
    l = (this.yStart - 20) * this.imageWidth;
    int i1 = (this.yStart + 20) * this.imageWidth;
    int i2 = this.yStart * this.imageWidth;
    int i3;
    int i4;
    int i5;
    int i6;
    int i7;
    int i8;
    if (paramInt3 > paramInt1)
    {
      i3 = 100 * (paramInt3 - paramInt1 + 20) / 20;
      i4 = 25 * (paramInt3 - paramInt1);
      i5 = l;
      while (i5 < i1)
      {
        paramInt3 = j - i4 * (i5 / this.imageWidth - this.yStart) / 100 * (i5 / this.imageWidth - this.yStart) / 100;
        i3 = 100 * (paramInt3 - paramInt1 + 20) / 20;
        for (i7 = 0; i7 < paramInt3 - paramInt1 + 20; ++i7)
          this.rgbData1[(i5 + paramInt1 - 20 + i7)] = this.rgbData[(i5 + paramInt1 - 20 + 100 * i7 / i3)];
        i7 = i3 * (this.imageWidth - paramInt3) / 100;
        for (i8 = 0; i8 < paramInt3 - paramInt1 + 20; ++i8)
          this.rgbData1[(i5 + paramInt3 + 100 * i8 / i3)] = this.rgbData[(i5 + paramInt1 + i8)];
        i5 += this.imageWidth;
      }
    }
    else
    {
      i3 = 100 * (paramInt1 - paramInt3 + 20) / 20;
      i4 = 25 * (paramInt1 - paramInt3);
      i5 = l;
      while (i5 < i1)
      {
        paramInt3 = j + i4 * (i5 / this.imageWidth - this.yStart) / 100 * (i5 / this.imageWidth - this.yStart) / 100;
        i3 = 100 * (paramInt1 - paramInt3 + 20) / 20;
        for (i7 = 0; i7 < paramInt1 - paramInt3 + 20; ++i7)
          this.rgbData1[(i5 + paramInt1 + 20 - i7)] = this.rgbData[(i5 + paramInt1 + 20 - 100 * i7 / i3)];
        for (i7 = 0; i7 < paramInt1 - paramInt3 + 20; ++i7)
          this.rgbData1[(i5 + paramInt3 - 100 * i7 / i3)] = this.rgbData[(i5 + paramInt1 - i7)];
        i5 += this.imageWidth;
      }
    }
    int i9;
    int i10;
    if ((paramInt1 == paramInt3) && (paramInt2 == paramInt4))
    {
      i5 = this.imageWidth / 15;
      i7 = paramInt3;
      if (i7 < 2 * i5)
        i7 = 2 * i5 + 1;
      if (i7 > this.imageWidth - 2 * i5)
        i7 = this.imageWidth - 2 * i5 - 1;
      i8 = paramInt4;
      if (i8 < 2 * i5)
        i8 = 2 * i5 + 1;
      if (i8 > this.imageHeight - 2 * i5)
        i8 = this.imageHeight - 2 * i5 - 1;
      i9 = -i5;
      for (i10 = i8 - i5; i10 < i8 + i5; ++i10)
      {
        int i12 = -1;
        int i13 = i7 - 2 * i5;
        for (int i14 = i7 - i5; i14 < i7 + i5; ++i14)
        {
          if ((i13 - i7) * (i13 - i7) + (i10 + i9 - i8) * (i10 + i9 - i8) < 4 * i5 * i5)
            this.rgbData1[((i10 + i9) * this.imageWidth + i13)] = this.rgbData[(i10 * this.imageWidth + i14)];
          if ((i13 - i7) * (i13 - i7) + (i10 + i9 + 1 - i8) * (i10 + i9 + 1 - i8) < 4 * i5 * i5)
            this.rgbData1[((i10 + i9 + 1) * this.imageWidth + i13)] = this.rgbData[(i10 * this.imageWidth + i14)];
          if ((++i13 - i7) * (i13 - i7) + (i10 + i9 - i8) * (i10 + i9 - i8) < 4 * i5 * i5)
            this.rgbData1[((i10 + i9) * this.imageWidth + i13)] = this.rgbData[(i10 * this.imageWidth + i14)];
          if ((i13 - i7) * (i13 - i7) + (i10 + i9 + 1 - i8) * (i10 + i9 + 1 - i8) < 4 * i5 * i5)
            this.rgbData1[((i10 + i9 + 1) * this.imageWidth + i13)] = this.rgbData[(i10 * this.imageWidth + i14)];
          ++i13;
        }
        ++i9;
      }
    }
    Image localImage = Image.createRGBImage(this.rgbData1, this.imageWidth, this.imageHeight, false);
    this.resizedImage = localImage;
    this.resizedImage.getRGB(this.rgbData, 0, this.imageWidth, 0, 0, this.imageWidth, this.imageHeight);
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
    this.resizedImage = localImage;
    this.imgMovie[this.indexMovie] = localImage;
    this.indexMovie += 1;
    if (this.indexMovie <= 9)
      return;
    this.indexMovie = 0;
    this.indexOver = true;
  }

  protected void paint(Graphics paramGraphics)
  {
    this.w = getWidth();
    this.h = getHeight();
    paramGraphics.setColor(0);
    paramGraphics.fillRect(0, 0, this.w, this.h);
    this.startTime = System.currentTimeMillis();
    if (this.modePlayMovie)
    {
      this.startTime = System.currentTimeMillis();
      paramGraphics.drawImage(this.imgMovie[this.indexMovie], 0, 0, 20);
      if (this.nextPaint)
      {
        do
        this.indexDif = (1 + this.rand.nextInt() % 3);
        while (this.indexDif < 1);
        this.nextPaint = false;
        this.indexI = 0;
        this.indexJ = 0;
        this.switchUp = true;
      }
      if (this.indexI < 5 - this.indexDif)
        if (this.switchUp)
        {
          if (this.indexJ < this.indexDif)
          {
            this.indexMovie += 1;
            if (this.indexMovie > this.indexMovieMax)
              this.indexMovie = 0;
            if (this.indexMovie < 0)
              this.indexMovie = this.indexMovieMax;
            this.indexJ += 1;
          }
          else
          {
            this.switchUp = false;
            this.indexI += 1;
          }
        }
        else if (this.indexJ > 0)
        {
          this.indexMovie -= 1;
          if (this.indexMovie > this.indexMovieMax)
            this.indexMovie = 0;
          if (this.indexMovie < 0)
            this.indexMovie = this.indexMovieMax;
          this.indexJ -= 1;
        }
        else
        {
          this.switchUp = true;
        }
      else
        this.nextPaint = true;
      this.disp.callSerially(this);
    }
    else
    {
      if (this.resizedImage != null)
      {
        paramGraphics.drawImage(this.resizedImage, 0, 0, 20);
      }
      else
      {
        paramGraphics.setColor(16777215);
        paramGraphics.drawString("Please wait.", this.w / 2, this.h / 2 - 40, 65);
        paramGraphics.drawString("Image will be resized ", this.w / 2, this.h / 2 - 20, 65);
        paramGraphics.drawString("for current display.", this.w / 2, this.h / 2, 65);
        paramGraphics.drawString("It may take 1 minute.", this.w / 2, this.h / 2 + 20, 65);
      }
      if (this.loadMovieMode)
        return;
      if (this.reklamaMode)
      {
        paramGraphics.setColor(0, 0, 0);
        paramGraphics.fillRect(0, 0, this.w, this.h);
        paramGraphics.drawImage(this.resizedImage, this.w / 2, this.h / 2, 3);
        return;
      }
      if (this.modeToolPaste)
      {
        paramGraphics.drawImage(this.toolImage, this.xCursor, this.yCursor, 20);
      }
      else
      {
        paramGraphics.setColor(0, 255, 100);
        paramGraphics.fillRect(this.xCursor - 2, this.yCursor - 2, 5, 5);
        if (!this.modeWarp)
          return;
        paramGraphics.drawLine(this.xStart, this.yStart, this.xCursor, this.yCursor);
      }
    }
  }
  public void pointerReleased(int x,int y)
  {
  }
  public void pointerDragged(int x,int y)
  {
      xCursor=x;
      yCursor=y;
      warpFace(this.xStart, this.yStart, this.xCursor, this.yCursor);
      repaint();
      this.xStart = this.xCursor;
      this.yStart = this.yCursor;
  }
public void pointerPressed(int x,int y)
{
    xStart=x;
    yStart=y;
    repaint();
}
  public void keyPressed(int paramInt)
  {
    int i = getGameAction(paramInt);
    if (this.loadMovieMode)
      return;
    if (this.reklamaMode)
    {
      if (i == 8)
      {
        this.reklamaMode = false;
        this.resizedImage = this.imgMovie[this.indexMovieLast];
        this.flagContinue = false;
        repaint();
      }
      return;
    }
    if (this.modeToolPaste)
      switch (i)
      {
      case 2:
        this.xCursor -= 5;
        if (this.xCursor < 0)
          this.xCursor = 0;
        repaint();
        break;
      case 5:
        this.xCursor += 5;
        if (this.xCursor > this.imageWidth - this.wTool)
          this.xCursor = (this.imageWidth - this.wTool);
        repaint();
        break;
      case 1:
        this.yCursor -= 5;
        if (this.yCursor < 0)
          this.yCursor = 0;
        repaint();
        break;
      case 6:
        this.yCursor += 5;
        if (this.yCursor > this.imageHeight - this.hTool)
          this.yCursor = (this.imageHeight - this.hTool);
        repaint();
        break;
      case 8:
        int j = this.wTool * this.hTool;
        int[] arrayOfInt = new int[j];
        this.toolImage.getRGB(arrayOfInt, 0, this.wTool, 0, 0, this.wTool, this.hTool);
        for (int k = this.xCursor; k < this.xCursor + this.wTool; ++k)
          for (int l = this.yCursor; l < this.yCursor + this.hTool; ++l)
          {
            if (Integer.toHexString(arrayOfInt[((l - this.yCursor) * this.wTool + (k - this.xCursor))]).length() <= 6)
              continue;
            this.rgbData1[(l * this.imageWidth + k)] = arrayOfInt[((l - this.yCursor) * this.wTool + (k - this.xCursor))];
          }
        this.resizedImage = Image.createRGBImage(this.rgbData1, this.imageWidth, this.imageHeight, false);
        this.imgMovie[(this.indexMovie - 1)] = this.resizedImage;
        this.modeToolPaste = false;
        repaint();
      case 3:
      case 4:
      case 7:
      }
    else
      switch (i)
      {
      case 2:
        this.xCursor -= 5;
        if (this.xCursor < 20)
          this.xCursor = 20;
        repaint();
        break;
      case 5:
        this.xCursor += 5;
        if (this.xCursor > this.imageWidth - 21)
          this.xCursor = (this.imageWidth - 21);
        repaint();
        break;
      case 1:
        this.yCursor -= 5;
        if (this.yCursor < 20)
          this.yCursor = 20;
        repaint();
        break;
      case 6:
        this.yCursor += 5;
        if (this.yCursor > this.imageHeight - 21)
          this.yCursor = (this.imageHeight - 21);
        repaint();
        break;
      case 8:
        if (this.modeWarp)
        {
          warpFace(this.xStart, this.yStart, this.xCursor, this.yCursor);
          this.modeWarp = false;
        }
        else
        {
          this.modeWarp = true;
          this.xStart = this.xCursor;
          this.yStart = this.yCursor;
        }
        repaint();
      case 3:
      case 4:
      case 7:
      }
  }

  protected void keyReleased(int paramInt)
  {
  }
  public void run()
  {
    int i;
    do
      i = (int)(System.currentTimeMillis() - this.startTime);
    while (i < 100);
    repaint();
  }
  public void cleanUp()
  {
    this.currentImage = null;
    this.tempImage = null;
    String str = "";
    this.indexMovie = 0;
    this.indexMovieMax = 0;
    this.indexOver = false;
    this.indexMovieLast = 0;
    this.paintFirsttime = true;
    this.paintParam = 0;
    this.xCursor = 20;
    this.yCursor = 20;
    this.xStart = 20;
    this.yStart = 20;
    this.modeWarp = false;
    this.resizedImage = null;
    this.modePlayMovie = false;
    this.debug = 0;
    this.tmpImage = null;
    this.startTime = 0L;
    this.indexI = 0;
    this.indexJ = 0;
    this.indexDif = 0;
    this.nextPaint = true;
    this.switchUp = true;
    this.loadMovieMode = false;
  }

  public void saveMovie()
  {
    if ((this.indexMovie < 2) && (!this.indexOver))
      return;
    Object localObject = new InputForm(this);
    this.disp.setCurrent((Displayable)localObject);
  }
  public boolean backInput()
  {
    this.disp.setCurrent(this);
    if (this.inputMovie.length() < 1)
      return false;
    String str = "lanemcila";
    int[] arrayOfInt = new int[this.imageWidth * this.imageHeight];
    int i = 0;
    if ((this.indexMovie == 1) && (!this.indexOver))
      return false;
    if (this.indexOver)
      i = 9;
    else
      i = this.indexMovie - 1;
    for (int j = 0; j <= i; ++j)
    {
      this.imgMovie[j].getRGB(arrayOfInt, 0, this.imageWidth, 0, 0, this.imageWidth, this.imageHeight);
      try
      {
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream localDataOutputStream = new DataOutputStream(localByteArrayOutputStream);
        localDataOutputStream.writeInt(this.imageWidth);
        localDataOutputStream.writeInt(this.imageHeight);
        for (int k = 0; k < arrayOfInt.length; ++k)
          localDataOutputStream.writeInt(arrayOfInt[k]);
      }
      catch (IOException localIOException)
      {
        return false;
      }
    }
    return true;
  }

  public void openMovie(int paramInt)
  {
    this.disp.setCurrent(this);
    byte[] arrayOfByte = null;
    int i = 0;
    int[] arrayOfInt = null;
    this.currentImage = null;
    this.tempImage = null;
    String str = "";
    this.indexMovie = (i - 1);
    this.indexMovieMax = 0;
    this.indexOver = false;
    this.indexMovieLast = 0;
    this.rgbData = arrayOfInt;
    this.rgbData1 = arrayOfInt;
    this.paintFirsttime = false;
    this.paintParam = 0;
    this.xCursor = 20;
    this.yCursor = 20;
    this.xStart = 20;
    this.yStart = 20;
    this.modeWarp = false;
    this.resizedImage = this.imgMovie[(i - 1)];
    this.modePlayMovie = false;
    this.debug = 0;
    this.tmpImage = this.resizedImage;
    this.startTime = 0L;
    this.indexI = 0;
    this.indexJ = 0;
    this.indexDif = 0;
    this.nextPaint = true;
    this.switchUp = true;
    playMovie();
  }

  public void playMovie()
  {
    if ((this.indexMovie == 1) && (!this.indexOver))
      return;
    this.modePlayMovie = true;
    this.indexMovieLast = (this.indexMovie - 1);
    if (this.indexOver)
      this.indexMovieMax = 9;
    else
      this.indexMovieMax = (this.indexMovie - 1);
    this.indexMovie = 0;
    removeCommand(cPlayCommand);
    addCommand(cStopCommand);
    removeCommand(cPicCommand);
    removeCommand(cLoadCommand);
    removeCommand(cSaveCommand);
    repaint();
  }

  public void loadPic()
  {
    cleanUp();
    this.midlet.displayFileBrowser();
  }

  public void commandAction(Command paramCommand, Displayable paramDisplayable)
  {
    Object localObject;
    if ((this.modeToolPaste) || (this.reklamaMode))
    {
      localObject = new Alert("Notice");
      ((Alert)localObject).setString("Press FIRE to finish current action, please. After that you can use Menu commands again.");
      ((Alert)localObject).setTimeout(-2);
      this.disp.setCurrent((Displayable)localObject);
      return;
    }
    if ((paramDisplayable.equals(this.implicitList)) && (paramCommand == List.SELECT_COMMAND))
      openMovie(((List)paramDisplayable).getSelectedIndex());
    if (paramCommand == cPlayCommand)
      playMovie();
    if (paramCommand == cStopCommand)
    {
      this.modePlayMovie = false;
      this.modeWarp = false;
      addCommand(cPicCommand);
      addCommand(cPlayCommand);
      addCommand(cLoadCommand);
      if (!this.loadMovieMode)
      addCommand(cSaveCommand);
      removeCommand(cStopCommand);
      this.resizedImage = this.imgMovie[this.indexMovieLast];
      this.indexMovie = (this.indexMovieLast + 1);
    }
    if (paramCommand == cPicCommand)
      loadPic();
    if (paramCommand == cSaveCommand)
      saveMovie();
    if (paramCommand != cExitCommand)
      return;
    this.midlet.canvasExit();
  }
}
