import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

class InputScreen extends Form
  implements CommandListener
{
  private final FaceDisasterMIDlet midlet;
  private final TextField inputField = new TextField("Input", "", 32, 0);
  private final Command okCommand = new Command("OK", 4, 1);
  private final Command cancelCommand = new Command("Cancel", 4, 1);

  InputScreen(FaceDisasterMIDlet paramFaceDisasterMIDlet)
  {
    super("Input");
    this.midlet = paramFaceDisasterMIDlet;
    append(this.inputField);
    addCommand(this.okCommand);
    addCommand(this.cancelCommand);
    setCommandListener(this);
  }

  public void setQuestion(String paramString1, String paramString2)
  {
    this.inputField.setLabel(paramString1);
    this.inputField.setString(paramString2);
  }

  public String getInputText()
  {
    return this.inputField.getString();
  }

  public void commandAction(Command paramCommand, Displayable paramDisplayable)
  {
    if (paramCommand == this.okCommand)
    {
      this.midlet.input(this.inputField.getString());
    }
    else
    {
      if (paramCommand != this.cancelCommand)
        return;
      this.midlet.cancelInput();
    }
  }
}