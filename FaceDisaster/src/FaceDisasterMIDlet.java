import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Image;
import javax.microedition.midlet.MIDlet;

public class FaceDisasterMIDlet extends MIDlet
{
//  private final Image logo = makeImage("/logo.png");
  private final ImageCanvas imageCanvas;
  private FileSelector fileSelector;
  private final InputScreen inputScreen;
  private int operationCode = -1;
  public boolean unr = true;
  public String userStr = "";
  public boolean firstTimeFS = true;
  public boolean switchFSR = false;
  String path = "0";
  int datum = 0;
  int minute = 0;
  int loadNr = 0;

  public FaceDisasterMIDlet()
  {
//    ErrorScreen.init(this.logo, Display.getDisplay(this));
    this.imageCanvas = new ImageCanvas(this);
    this.inputScreen = new InputScreen(this);
  }

 
  public void startApp()
  {
    Displayable localDisplayable = Display.getDisplay(this).getCurrent();
    displayImage("automa");
  }

  public void pauseApp()
  {
  }

  public void destroyApp(boolean paramBoolean)
  {
    if (!this.firstTimeFS)
      this.fileSelector.stop();
    notifyDestroyed();
  }

  void canvasExit()
  {
    destroyApp(false);
  }

  void fileSelectorExit()
  {
    destroyApp(false);
  }

  void cancelInput()
  {
    Display.getDisplay(this).setCurrent(this.fileSelector);
  }

  void input(String paramString)
  {
    this.fileSelector.inputReceived(paramString, this.operationCode);
    Display.getDisplay(this).setCurrent(this.fileSelector);
  }

  void displayImage(String paramString)
  {
    this.imageCanvas.setFullScreenMode(true);
    this.imageCanvas.w = this.imageCanvas.getWidth();
    this.imageCanvas.h = this.imageCanvas.getHeight();
    Display.getDisplay(this).setCurrent(this.imageCanvas);
    this.imageCanvas.displayImage(paramString);
  }

  void displayFileBrowser()
  {
    if (this.firstTimeFS)
    {
      this.fileSelector = new FileSelector(this);
      Displayable localDisplayable = Display.getDisplay(this).getCurrent();
      if (localDisplayable == null)
      {
        int i = (System.getProperty("microedition.io.file.FileConnection.version") != null) ? 1 : 0;
        String str = "";
        if (i == 0)
          str = str + "\nFile Connection API is not available";
        Alert localAlert = new Alert(null, str, null, AlertType.INFO);
        if (i != 0)
        {
          localAlert.setTimeout(30);
          Display.getDisplay(this).setCurrent(localAlert, this.fileSelector);
        }
        else
        {
          Display.getDisplay(this).setCurrent(localAlert);
        }
      }
      else
      {
        Display.getDisplay(this).setCurrent(localDisplayable);
      }
    }
    Display.getDisplay(this).setCurrent(this.fileSelector);
  }


  void showMsg(String paramString)
  {
    Alert localAlert = new Alert(null, paramString, null, AlertType.INFO);
    localAlert.setTimeout(3000);
    Display.getDisplay(this).setCurrent(localAlert, this.fileSelector);
  }

  void requestInput(String paramString1, String paramString2, int paramInt)
  {
    this.inputScreen.setQuestion(paramString1, paramString2);
    this.operationCode = paramInt;
    Display.getDisplay(this).setCurrent(this.inputScreen);
  }

  static Image makeImage(String paramString)
  {
    Image localImage = null;
    try
    {
      localImage = Image.createImage(paramString);
    }
    catch (Exception localException)
    {
    }
    return localImage;
  }
}
