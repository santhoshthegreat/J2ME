package tettris;
import java.io.IOException;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;

class Sound extends Thread
{
    int soundid;
    public void startSound(int i)
    {
        soundid=i;
        this.start();
    }
    public void run()
    {
        Player mPlayer;
        try
        {
            mPlayer = Manager.createPlayer(super.getClass().getResourceAsStream("/" + String.valueOf(soundid) + ".wav"), "audio/x-wav");
            mPlayer.prefetch();
            mPlayer.start();
        }
        catch (IOException ex)
        {
        }
        catch (MediaException ex)
        {
        }
    }
}