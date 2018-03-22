import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

class InputForm extends Form
  implements CommandListener
{
  TextField TxtName = new TextField("Movie", "", 32, 0);
  Command cancelCommand = new Command("Cancel", 3, 1);
  Command okCommand = new Command("OK", 4, 0);
  ImageCanvas imgCan;

  public InputForm(ImageCanvas paramImageCanvas)
  {
    super("Input");
    this.imgCan = paramImageCanvas;
    append("Enter the name of movie and press OK. Please wait, it can take 1 minute. ");
    append(" ");
    append(this.TxtName);
    addCommand(this.cancelCommand);
    addCommand(this.okCommand);
    setCommandListener(this);
  }

  public void commandAction(Command paramCommand, Displayable paramDisplayable)
  {
    if (paramCommand == this.cancelCommand)
      this.imgCan.backInput();
    if (paramCommand != this.okCommand)
      return;
    this.imgCan.inputMovie = this.TxtName.getString();
    this.imgCan.backInput();
  }
}

/* Location:           L:\FaceDisaster.jar
 * Qualified Name:     InputForm
 * JD-Core Version:    0.5.4
 */