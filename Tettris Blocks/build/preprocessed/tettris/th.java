package tettris;
import javax.microedition.rms.RecordStore;

class th extends Thread
{
    int recordnumber;
    public th(int i)
    {
        recordnumber=i;
    }
    public void run()
    {
        RecordStore rs = null;
	try
	{
            rs = RecordStore.openRecordStore("unlocklevel",true);
            if(rs.getNumRecords()==0)
            {
                rs.addRecord(String.valueOf(recordnumber).getBytes(), 0, String.valueOf(recordnumber).getBytes().length);
            }
            else
            {
                rs.setRecord(1,String.valueOf(recordnumber).getBytes(), 0, String.valueOf(recordnumber).getBytes().length);
            }
            rs.closeRecordStore();
	}
	catch(Exception e)
        {
        }
    }
}