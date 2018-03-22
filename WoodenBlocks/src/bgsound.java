package tettris;
import java.io.IOException;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
class bgsound extends Thread
{
    private Player mPlayer;
    public void run()
    {
        try
        {
                mPlayer = Manager.createPlayer(super.getClass().getResourceAsStream("/bg.mid"), "audio/midi");
                mPlayer.setLoopCount(-1);
                mPlayer.prefetch();
                try
                {
                    Thread.sleep(10);
                }
                catch (InterruptedException ex)
                {
                    ex.printStackTrace();
                }
                mPlayer.start();
        }
        catch (IOException ex)
        {
        }
        catch (MediaException ex)
        {
        }
    }
    public void startSound()
    {
        this.start();
    }
    public void stopSound()
    {
        try
        {
            if (this.mPlayer != null)
            {
                mPlayer.stop();
                mPlayer.deallocate();
                mPlayer.close();
                mPlayer = null;
            }
        }
        catch (Exception e)
        {
        }
    }
}