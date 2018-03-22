package tettris;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.rms.RecordStore;
public class mytetris extends MIDlet implements CommandListener
{
    private Display display;
    private Command exitCommand;
    private Command pauseCommand;
    private Command goonCommand;
    private MenuScreen mnu;
    maingame tetris;
    boolean started;
    public int unlockedlevel;
    public boolean soundstate;
    public int curscr=0;
    public void old_sa()
    {
        if (!this.started) 
        {
            this.started = true;
            this.tetris.start();
            this.display = Display.getDisplay(this);
            this.display.setCurrent(this.tetris);
            this.exitCommand = new Command("Exit", 7, 0);
            this.pauseCommand = new Command("Pause", 6, 0);
            this.goonCommand = new Command("Continue", 4, 0);
            this.tetris.setCommandListener(this);
            this.tetris.addCommand(this.exitCommand);
            this.tetris.addCommand(this.pauseCommand);
            this.tetris.addCommand(this.goonCommand);
        }
        else 
        {
            this.tetris.wakemeup();
            this.display.setCurrent(this.tetris);
        }
    }
    public mytetris()
    {
        unlockedlevel=1;        
        soundstate=true;
        curscr=0;      
        mnu=new MenuScreen(this);                                
        display=Display.getDisplay(this);
    }
    private int getunlockedlevel()
    {
        RecordStore rs = null;
        String samp="";
        try
        {
            rs = RecordStore.openRecordStore("unlocklevel",true);
            if(rs.getNumRecords()>0)
            {
                byte b[] = rs.getRecord(1);
                samp = new String(b,0,b.length);
            }
            else
            {
                samp="1";
            }
            rs.closeRecordStore();
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        return Integer.parseInt(samp);
    }
    public Display getDisplay()
    {
        return Display.getDisplay(this);
    }
    public void pauseApp()
    {
        this.tetris.gosleep();
    } 
    public void old_da(boolean unconditional)
    {
    } 
    public void commandAction(Command c, Displayable d) 
    {
        if (c == this.exitCommand) { mytetris localmytetris = this; ((mytetris)localmytetris).notifyDestroyed2(); }
        if (c == this.pauseCommand) this.tetris.gosleep();
        if (c == this.goonCommand)
        this.tetris.wakemeup();
    }
    public void old_con()
    {
        this.tetris = new maingame();
        this.started = false;
    }
    protected final void startApp()
    {
        if(curscr==0)
        {
            display.setCurrent(new SplashScreen(this));                                
        }
        else if(curscr==1)
        {
            showCanvas();
        }
    }
    public void showCanvas()
    {
        curscr=1;
        int i = 0;
        if (i == 0)
        {
            old_con();
            old_sa();
        }
        else
        {
            if (i != 2)
            return;
            old_sa();
        }
    }
    public void showCanvaslevel(int level)
    {
        curscr=1;
        int i = 0;
        if (i == 0)
        {
            old_con();
            old_sa();
        }
        else
        {
            if (i != 2)
            return;
            old_sa();
        }
        tetris.level=level;                
    }    
    protected final void destroyApp(boolean paramBoolean)
    {
        old_da(paramBoolean);
    }
    public final void destroyApp2(boolean paramBoolean)
    {
    }
    public void exitMIDlet(boolean param)
    {
        notifyDestroyed();
    }
    public final void notifyDestroyed2()
    {
        super.notifyDestroyed();
    }
    public void showmenu()
    {
        curscr=0;
        display.setCurrent(mnu);
    }
}