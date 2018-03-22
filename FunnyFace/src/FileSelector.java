import java.io.IOException;
import java.io.OutputStream;
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
import java.util.Random;
class FileSelector extends List implements CommandListener, FileSystemListener
{
    private Image ROOT_IMAGE;
    private Image FOLDER_IMAGE;
    private Image FILE_IMAGE;
    private Random rand;
    private static String FILE_SEPARATOR = (System.getProperty("file.separator") != null) ? System.getProperty("file.separator") : "/";
    private final FunnyFace midlet;
    public final Command openCommand = new Command("Open", 6, 1);
    public final Command saveCommand = new Command("Save", 6, 1);
    private final Command backCommand = new Command("Back", 6, 1);
    private Vector rootsList = new Vector();
    private FileConnection currentRoot = null;
    public boolean issaving=false;
    FileSelector(FunnyFace paramFaceDisasterMIDlet)
    {
        super("Open File",3);
        midlet = paramFaceDisasterMIDlet;
        FILE_SEPARATOR="/";
        addCommand(openCommand);
        setSelectCommand(openCommand);
        addCommand(saveCommand);
        addCommand(backCommand);
        setCommandListener(this);
        try
        {
            ROOT_IMAGE = Image.createImage("/root.png");
            FOLDER_IMAGE = Image.createImage("/folder.png");
            FILE_IMAGE = Image.createImage("/file.png");
            faceop();
        }
        catch (Exception ex)
        {
            midlet.showMsg(ex.toString());
        }
        FileSystemRegistry.addFileSystemListener(this);
    }
    void stop()
    {
        FileSystemRegistry.removeFileSystemListener(this);
    }
    public void commandAction(Command paramCommand, Displayable paramDisplayable)
    {
        if (paramCommand == openCommand)
        {
            openSelected();
        }
        if (paramCommand == saveCommand)
        {
            saveSelected();
        }
        else if (paramCommand == backCommand)
        {
            midlet.warpmenu();
        }
    }
    public void rootChanged(int paramInt, String paramString)
    {
        faceop();
    }
    private void displayAllRoots()
    {
        setTitle("FaceWarp (Roots)");
        deleteAll();
        Enumeration localEnumeration = rootsList.elements();
        while (localEnumeration.hasMoreElements())
        {
            String str = (String)localEnumeration.nextElement();
            append(str.substring(1), ROOT_IMAGE);
        }
        this.currentRoot = null;
    }
    private void loadRoots()
    {
        if (!rootsList.isEmpty())
        rootsList.removeAllElements();
        try
        {
            Enumeration localEnumeration = FileSystemRegistry.listRoots();
            while (localEnumeration.hasMoreElements())
            rootsList.addElement(FILE_SEPARATOR + (String)localEnumeration.nextElement());
        }
        catch (Exception e)
        {
            midlet.showMsg(e.toString());
        }
    }
    private void openSelected()
    {
        FileSystemRegistry.addFileSystemListener(this);
        int i = getSelectedIndex();
        if (i < 0)
        return;
        String str1 = getString(i);
        if (str1.endsWith(FILE_SEPARATOR))
        {
            try
            {
                if (currentRoot == null)
                currentRoot = ((FileConnection)Connector.open("file:///" + str1, 1));
                else
                currentRoot.setFileConnection(str1);
                displayCurrentRoot();
            }
            catch (IOException e)
            {
            }
            catch (SecurityException e1)
            {
            }
        }
        else if (str1.equals(".."))
        {
            if (rootsList.contains(currentRoot.getPath() + currentRoot.getName()))
            displayAllRoots();
            else
            try
            {
                currentRoot.setFileConnection("..");
                displayCurrentRoot();
            }
            catch (IOException e)
            {
                midlet.showMsg(e.toString());
            }
        }
        else
        {
            midlet.pathandfile=currentRoot.getURL() + str1;
            midlet.path = currentRoot.getURL();
            if ((!str1.endsWith(".jpg")) && (!str1.endsWith(".jpeg")) && (!str1.endsWith(".png")) && (!str1.endsWith(".gif")) && (!str1.endsWith(".JPG")) && (!str1.endsWith(".JPEG")) && (!str1.endsWith(".PNG")) && (!str1.endsWith(".GIF")))
            {
                Alert localAlert = new Alert("Notice");
                localAlert.setString("Please choose only JPG, PNG or GIF file format and try again.");
                localAlert.setTimeout(-2);
                Display.getDisplay(midlet).setCurrent(localAlert);
                return;
            }
            midlet.displayImage(midlet.pathandfile);
        }
    }
    private void displayCurrentRoot()
    {
        try
        {
            setTitle("Face Disaster - [" + this.currentRoot.getURL() + "]");
            deleteAll();
            append("..", FOLDER_IMAGE);
            Enumeration localEnumeration = this.currentRoot.list("*",false);
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
        catch (IOException e)
        {
        }
        catch (SecurityException e1)
        {
        }
    }
    private void saveSelected()
    {

        FileSystemRegistry.addFileSystemListener(this);
        int i = getSelectedIndex();
        if (i < 0)
        return;
        String str1 = getString(i);
        if (str1.endsWith(FILE_SEPARATOR))
        {
            midlet.path = currentRoot.getURL();
            saveimages si=new saveimages(midlet.path,midlet.currentImage,false);
      //      saveimages.savemyimage1();
        }
        else if (str1.equals(".."))
        {
            midlet.path = currentRoot.getURL();
            saveimages si=new saveimages(midlet.path,midlet.currentImage,false);
      //      saveimages.savemyimage1();
        }
        else
        {
            midlet.pathandfile=currentRoot.getURL() + str1;
            midlet.path = currentRoot.getURL();
            saveimages si=new saveimages(midlet.pathandfile,midlet.currentImage,true);
     //       saveimages.savemyimage();
            
        }
    }
    private void faceop()
    {
        String str = "";
        if (!FileSelector.this.midlet.path.equals("0"))
        str = FileSelector.this.midlet.path;
        else
        str = "";
        System.out.println("String : " + str);
        FileSelector.this.loadRoots();
        if (str != null || !str.equals(""))
        {
            try
            {
                FileSelector.this.displayCurrentRoot();
            }
            catch (Exception e)
            {
                FileSelector.this.displayAllRoots();
            }
            return;
        }
        FileSelector.this.displayAllRoots();
    }
}