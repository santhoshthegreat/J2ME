import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.io.file.FileSystemListener;
import javax.microedition.io.file.FileSystemRegistry;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;

class FileSelector extends List
  implements CommandListener, FileSystemListener
{
  private static final Image ROOT_IMAGE = FaceDisasterMIDlet.makeImage("/root.png");
  private static final Image FOLDER_IMAGE = FaceDisasterMIDlet.makeImage("/folder.png");
  private static final Image FILE_IMAGE = FaceDisasterMIDlet.makeImage("/file.png");
  public final OperationsQueue queue = new OperationsQueue();
  private static final String FILE_SEPARATOR = (System.getProperty("file.separator") != null) ? System.getProperty("file.separator") : "/";
  private static final String UPPER_DIR = "..";
  private final FaceDisasterMIDlet midlet;
  private final Command openCommand = new Command("Open", 8, 1);
  private final Command createDirCommand = new Command("Create new directory", 8, 2);
  private final Command deleteCommand = new Command("Delete", 8, 3);
  private final Command renameCommand = new Command("Rename", 8, 4);
  private final Command exitCommand = new Command("Exit", 7, 1);
  private static final int RENAME_OP = 0;
  private static final int MKDIR_OP = 1;
  private static final int INIT_OP = 2;
  private static final int OPEN_OP = 3;
  private static final int DELETE_OP = 4;
  private Vector rootsList = new Vector();
  private FileConnection currentRoot = null;

  FileSelector(FaceDisasterMIDlet paramFaceDisasterMIDlet)
  {
    super("FaceDisaster", 3);
    this.midlet = paramFaceDisasterMIDlet;
    addCommand(this.openCommand);
    addCommand(this.exitCommand);
    setSelectCommand(this.openCommand);
    setCommandListener(this);
    this.queue.enqueueOperation(new FileSelector.FaceDisasterOperations(2));
  }

  void stop()
  {
    this.queue.abort();
    FileSystemRegistry.removeFileSystemListener(this);
  }

  void inputReceived(String paramString, int paramInt)
  {
    switch (paramInt)
    {
    case 0:
      this.queue.enqueueOperation(new FileSelector.FaceDisasterOperations(paramString, 0));
      break;
    case 1:
      this.queue.enqueueOperation(new FileSelector.FaceDisasterOperations(paramString, 1));
    }
  }

  public void commandAction(Command paramCommand, Displayable paramDisplayable)
  {
    if (paramCommand == this.openCommand)
    {
      this.queue.enqueueOperation(new FileSelector.FaceDisasterOperations(3));
    }
    else if (paramCommand == this.renameCommand)
    {
      this.queue.enqueueOperation(new FileSelector.FaceDisasterOperations(0));
    }
    else if (paramCommand == this.deleteCommand)
    {
      this.queue.enqueueOperation(new FileSelector.FaceDisasterOperations(4));
    }
    else if (paramCommand == this.createDirCommand)
    {
      this.queue.enqueueOperation(new FileSelector.FaceDisasterOperations(1));
    }
    else
    {
      if (paramCommand != this.exitCommand)
        return;
      this.midlet.fileSelectorExit();
    }
  }

  public void rootChanged(int paramInt, String paramString)
  {
    this.queue.enqueueOperation(new FileSelector.FaceDisasterOperations(2));
  }

  private void displayAllRoots()
  {
    setTitle("Face Disaster - [Roots]");
    deleteAll();
    Enumeration localEnumeration = this.rootsList.elements();
    while (localEnumeration.hasMoreElements())
    {
      String str = (String)localEnumeration.nextElement();
      append(str.substring(1), ROOT_IMAGE);
    }
    this.currentRoot = null;
  }

  private void createNewDir()
  {
    if (this.currentRoot == null)
      this.midlet.showMsg("Is not possible to create a new root");
    else
      this.midlet.requestInput("New dir name", "", 1);
  }

  private void createNewDir(String paramString)
  {
    if (this.currentRoot == null)
      return;
    try
    {
      FileConnection localFileConnection = (FileConnection)Connector.open(this.currentRoot.getURL() + paramString, 2);
      localFileConnection.mkdir();
    }
    catch (IOException localIOException)
    {

    }
    displayCurrentRoot();
  }

  private void loadRoots()
  {
    if (!this.rootsList.isEmpty())
      this.rootsList.removeAllElements();
    try
    {
      Enumeration localEnumeration = FileSystemRegistry.listRoots();
      while (localEnumeration.hasMoreElements())
        this.rootsList.addElement(FILE_SEPARATOR + (String)localEnumeration.nextElement());
    }
    catch (Throwable localThrowable)
    {
      this.midlet.showMsg(localThrowable.getMessage());
    }
  }

  private void deleteCurrent()
  {
    if (this.currentRoot == null)
    {
      this.midlet.showMsg("Is not possible to delete a root");
    }
    else
    {
      int i = getSelectedIndex();
      if (i < 0)
        return;
      String str = getString(i);
      if (str.equals(".."))
      {
        this.midlet.showMsg("Is not possible to delete an upper dir");
      }
      else
      {
        try
        {
          FileConnection localFileConnection = (FileConnection)Connector.open(this.currentRoot.getURL() + str, 2);
          if (localFileConnection.exists())
            localFileConnection.delete();
          else
            this.midlet.showMsg("File " + localFileConnection.getName() + " does not exists");
        }
        catch (IOException localIOException)
        {

        }
        catch (SecurityException localSecurityException)
        {

        }
        displayCurrentRoot();
      }
    }
  }

  private void renameCurrent()
  {
    if (this.currentRoot == null)
    {
      this.midlet.showMsg("Is not possible to rename a root");
    }
    else
    {
      int i = getSelectedIndex();
      if (i < 0)
        return;
      String str = getString(i);
      if (str.equals(".."))
        this.midlet.showMsg("Is not possible to rename the upper dir");
      else
        this.midlet.requestInput("New name", str, 0);
    }
  }

  private void renameCurrent(String paramString)
  {
    if (this.currentRoot == null)
    {
      this.midlet.showMsg("Is not possible to rename a root");
    }
    else
    {
      int i = getSelectedIndex();
      if (i < 0)
        return;
      String str = getString(i);
      if (str.equals(".."))
      {
        this.midlet.showMsg("Is not possible to rename the upper dir");
      }
      else
      {
        try
        {
          FileConnection localFileConnection = (FileConnection)Connector.open(this.currentRoot.getURL() + str, 2);
          if (localFileConnection.exists())
            localFileConnection.rename(paramString);
          else
            this.midlet.showMsg("File " + localFileConnection.getName() + " does not exists");
        }
        catch (IOException localIOException)
        {
        }
        catch (SecurityException localSecurityException)
        {
        }
        displayCurrentRoot();
      }
    }
  }

  private void openSelected()
  {
    if (this.midlet.firstTimeFS)
    {
      FileSystemRegistry.addFileSystemListener(this);
      this.midlet.firstTimeFS = false;
    }
    int i = getSelectedIndex();
    if (i < 0)
      return;
    String str1 = getString(i);
    if (str1.endsWith(FILE_SEPARATOR))
    {
      try
      {
        if (this.currentRoot == null)
          this.currentRoot = ((FileConnection)Connector.open("file:///" + str1, 1));
        else
          this.currentRoot.setFileConnection(str1);
        displayCurrentRoot();
      }
      catch (IOException localIOException1)
      {
      }
      catch (SecurityException localSecurityException)
      {
      }
    }
    else if (str1.equals(".."))
    {
      if (this.rootsList.contains(this.currentRoot.getPath() + this.currentRoot.getName()))
        displayAllRoots();
      else
        try
        {
          this.currentRoot.setFileConnection("..");
          displayCurrentRoot();
        }
        catch (IOException localIOException2)
        {
        }
    }
    else
    {
      String str2 = this.currentRoot.getURL() + str1;
      this.midlet.path = this.currentRoot.getURL();
      if ((!str1.endsWith(".jpg")) && (!str1.endsWith(".jpeg")) && (!str1.endsWith(".png")) && (!str1.endsWith(".gif")) && (!str1.endsWith(".JPG")) && (!str1.endsWith(".JPEG")) && (!str1.endsWith(".PNG")) && (!str1.endsWith(".GIF")))
      {
        Alert localAlert = new Alert("Notice");
        localAlert.setString("Please choose only JPG, PNG or GIF file format and try again.");
        localAlert.setTimeout(-2);
        Display.getDisplay(this.midlet).setCurrent(localAlert);
        return;
      }
      this.midlet.displayImage(str2);
    }
  }

  private void displayCurrentRoot()
  {
    try
    {
      setTitle("Face Disaster - [" + this.currentRoot.getURL() + "]");
      deleteAll();
      append("..", FOLDER_IMAGE);
      Enumeration localEnumeration = this.currentRoot.list("*", false);
      while (localEnumeration.hasMoreElements())
      {
        String s = (String)localEnumeration.nextElement();
        if (s.endsWith(FILE_SEPARATOR))
          append(s, FOLDER_IMAGE);
      }
      Object localObject = this.currentRoot.list("*.png", false);
      String str;
      while (((Enumeration)localObject).hasMoreElements())
      {
        str = (String)((Enumeration)localObject).nextElement();
        if (str.endsWith(FILE_SEPARATOR))
          append(str, FOLDER_IMAGE);
        else
          append(str, FILE_IMAGE);
      }
      localObject = this.currentRoot.list();
      while (((Enumeration)localObject).hasMoreElements())
      {
        str = (String)((Enumeration)localObject).nextElement();
        if (str.endsWith(FILE_SEPARATOR))
          append(str, FOLDER_IMAGE);
        else
          append(str, FILE_IMAGE);
      }
    }
    catch (IOException localIOException)
    {
    }
    catch (SecurityException localSecurityException)
    {
    }
  }

  private class FaceDisasterOperations
    implements Operation
  {
    private final String parameter;
    private final int operationCode;

    FaceDisasterOperations(int arg2)
    {
      this.parameter = null;
      int i=arg2;
      this.operationCode = i;
    }

    FaceDisasterOperations(String paramInt, int arg3)
    {
      this.parameter = paramInt;
      int i=arg3;
      this.operationCode = i;
    }

    public void execute()
    {
      switch (this.operationCode)
      {
      case 2:
        String str = "";
        if (!FileSelector.this.midlet.path.equals("0"))
          str = FileSelector.this.midlet.path;
        else
          str = System.getProperty("fileconn.dir.photos");
        FileSelector.this.loadRoots();
        if (str != null)
        {
          try
          {
//            FileSelector.access(FileSelector.this, (FileConnection)Connector.open(str, 1));
            FileSelector.this.displayCurrentRoot();
          }
          catch (Exception localException)
          {
            FileSelector.this.displayAllRoots();
          }
          return;
        }
        FileSelector.this.displayAllRoots();
        break;
      case 3:
        FileSelector.this.openSelected();
        break;
      case 4:
        FileSelector.this.deleteCurrent();
        break;
      case 0:
        if (this.parameter != null)
        {
          FileSelector.this.renameCurrent(this.parameter);
          return;
        }
        FileSelector.this.renameCurrent();
        break;
      case 1:
        if (this.parameter != null)
        {
          FileSelector.this.createNewDir(this.parameter);
          return;
        }
        FileSelector.this.createNewDir();
      }
    }
  }
}