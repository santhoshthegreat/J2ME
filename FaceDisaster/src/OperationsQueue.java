import java.util.Vector;

class OperationsQueue
  implements Runnable
{
  private volatile boolean running = true;
  private final Vector operations = new Vector();

  OperationsQueue()
  {
    new Thread(this).start();
  }

  void enqueueOperation(Operation paramOperation)
  {
    this.operations.addElement(paramOperation);
    synchronized (this)
    {
      super.notify();
    }
  }

  void abort()
  {
    this.running = false;
    synchronized (this)
    {
      super.notify();
    }
  }

  public void run()
  {
    while (this.running)
    {
      while (this.operations.size() > 0)
      {
        try
        {
          ((Operation)this.operations.firstElement()).execute();
        }
        catch (Exception localException)
        {
        }
        this.operations.removeElementAt(0);
      }
      synchronized (this)
      {
        try
        {
          super.wait();
        }
        catch (InterruptedException localInterruptedException)
        {
        }
      }
    }
  }
}

/* Location:           L:\FaceDisaster.jar
 * Qualified Name:     OperationsQueue
 * JD-Core Version:    0.5.4
 */