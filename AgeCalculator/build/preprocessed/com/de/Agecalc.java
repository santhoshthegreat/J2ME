package com.de;
import java.io.IOException;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.util.Date;
public class Agecalc extends MIDlet implements CommandListener 
{
private boolean midletPaused = false;
//<editor-fold defaultstate="collapsed" desc=" Generated Fields ">
private Command exitCommand;
private Command exitCommand1;
private Command itemCommand;
private Form form;
private DateField dateField;
private StringItem stringItem;
private Image image;
//</editor-fold>

    /**
     * The AgeCalc constructor.
     */
    public Agecalc() {
    }

//<editor-fold defaultstate="collapsed" desc=" Generated Methods ">
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">
/**
 * Initializes the application.
 * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
 */
private void initialize () {
        // write pre-initialize user code here
 
        // write post-initialize user code here
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">
/**
 * Performs an action assigned to the Mobile Device - MIDlet Started point.
 */
public void startMIDlet () 
{
        // write pre-action user code here
    if (form == null) 
    {
        form = new Form ("Welcome", new Item[] { getDateField (), getStringItem () });
        form.addCommand (getExitCommand ());
        form.addCommand (getItemCommand ());
        form.setCommandListener (this);
        Display.getDisplay(this).setCurrent(form);
    }
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">
/**
 * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
 */
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">
/**
 * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
 * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
 * @param nextDisplayable the Displayable to be set
 */
        // write post-switch user code here
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">
/**
 * Called by a system to indicated that a command has been invoked on a particular displayable.
 * @param command the Command that was invoked
 * @param displayable the Displayable where the command was invoked
 */
public void commandAction (Command command, Displayable displayable) {
        // write pre-action user code here
if (displayable == form) {
if (command == exitCommand) {
                // write pre-action user code here
exitMIDlet ();
                // write post-action user code here
} else if (command == itemCommand) {
 // write pre-action user code here

//Calendar cal = Calendar.getInstance();
//cal.setTime(dateField.getDate());
long agetime=dateField.getDate().getTime();
final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000L;
long currenttime=new Date().getTime();
long diff=Math.abs(currenttime-agetime);
long days=diff/MILLIS_PER_DAY;
System.out.println(days);
int years=(int)(days/365);
days=days%365;
int months=(int)(days/30);
days=days%12;
stringItem.setText("You are " + years + " years, " + months + " months and " + days + " days old");

 
 // write post-action user code here
}
}       // write post-action user code here
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">
/**
 * Returns an initiliazed instance of exitCommand component.
 * @return the initialized component instance
 */
public Command getExitCommand () {
if (exitCommand == null) {
            // write pre-init user code here
exitCommand = new Command ("Exit", Command.EXIT, 0);
            // write post-init user code here
}
return exitCommand;
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: form ">
/**
 * Returns an initiliazed instance of form component.
 * @return the initialized component instance
 */
//</editor-fold>



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: splashScreen ">
/**
 * Returns an initiliazed instance of splashScreen component.
 * @return the initialized component instance
 */

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image ">
/**
 * Returns an initiliazed instance of image component.
 * @return the initialized component instance
 */
public Image getImage () {
if (image == null) {
            try {
                // write pre-init user code here
                   image=Image.createImage("/siscon.png");
            }
            catch(Exception e)
            {
                
            }
 /*   
image = Image.createImage (1, 1);
 // write post-init user code here
         */
}
return image;
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand1 ">
/**
 * Returns an initiliazed instance of exitCommand1 component.
 * @return the initialized component instance
 */
public Command getExitCommand1 () {
if (exitCommand1 == null) {
 // write pre-init user code here
exitCommand1 = new Command ("Exit", Command.EXIT, 0);
 // write post-init user code here
}
return exitCommand1;
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: itemCommand ">
/**
 * Returns an initiliazed instance of itemCommand component.
 * @return the initialized component instance
 */
public Command getItemCommand () {
if (itemCommand == null) {
 // write pre-init user code here
itemCommand = new Command ("Calculate Age", Command.ITEM, 0);
 // write post-init user code here
}
return itemCommand;
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: dateField ">
/**
 * Returns an initiliazed instance of dateField component.
 * @return the initialized component instance
 */
public DateField getDateField () {
if (dateField == null) {
 // write pre-init user code here
dateField = new DateField ("BirthDate", DateField.DATE);
dateField.setDate (new java.util.Date (System.currentTimeMillis ()));
 // write post-init user code here
}
return dateField;
}
//</editor-fold>



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem ">
/**
 * Returns an initiliazed instance of stringItem component.
 * @return the initialized component instance
 */
public StringItem getStringItem () {
if (stringItem == null) {
 // write pre-init user code here
stringItem = new StringItem ("", null);
 // write post-init user code here



}
return stringItem;
}
//</editor-fold>

    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay() {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
  //          resumeMIDlet();
        } else {
            initialize();
            startMIDlet();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
    }
}