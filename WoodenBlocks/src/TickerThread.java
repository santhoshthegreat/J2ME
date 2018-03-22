
public class TickerThread extends Thread 
{
    private WoodenBlocks game;	
    private boolean running;		
    private boolean skipNextTick;	
	public TickerThread(WoodenBlocks game) 
        {
		this.game = game;
		this.running = true;
		this.skipNextTick = true;	// skip the first drop, because ticking is at the start of the loop,

	}

	public void stopThread() 
        {
		this.running = false;
	}

	public void skipNextTick() 
        {
		this.skipNextTick = true;
	}
	public void run() 
        {
	    while(this.running) 
            {			
			if(this.skipNextTick) 
                        {
				this.skipNextTick = false;
			}
                        else 
                        {
				this.game.tick();
			}
			try 
                        {
				Thread.sleep(this.game.getTickSpeed());	// sleep between tick
			}
                        catch(InterruptedException ie) 
                        {
			}
		}
	}
}
