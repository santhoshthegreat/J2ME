package forex;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import javax.microedition.rms.RecordStore;

import net.mypapit.java.StringTokenizer;
public class CvLogic
{
    public CvLogic()
    {
    }
    public void convert() throws IOException
    {
        String a=TotalForexConverter.a;
        int convertto=TotalForexConverter.b1;
        double aval=Double.parseDouble(TotalForexConverter.aval);
        double bval=Double.parseDouble(TotalForexConverter.bval);
        
        
        
        
        
        
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int date = calendar.get(Calendar.DATE);
        
        
        
		int recordidval=0;
		// get index for record values
		try
		{
		RecordStore rs = null;
		rs = RecordStore.openRecordStore("i" + month+"."+year,false);
		byte[] recData = rs.getRecord(date);
		String recordid=new String(recData);
		recordid=recordid.toString().substring(recordid.indexOf('.')+1);
		recordidval=Integer.parseInt(recordid);	
		System.out.println("#RecID: " + recordid );
		rs.closeRecordStore();
		rs=null;
		}
		catch (Exception e)
		{
			recordidval=0;	
			System.out.println(e.toString());
		}
		
		
		String str="-";
		if(recordidval==0)
		{
			System.out.println("Record Null");
			
			//----- Null Records ------			
			// str="AUD-48.0,USD-0.02,ZWD-8.46,EUR-65.2,GBP-73.3,SGD-0.02,JPY-1,CNY-6.9,IDR-0.0";
			str="";
			String[] units_to_db={"AUD","USD","ZWD","EUR","GBP","SGD","JPY","CNY","IDR"};
			for(int i=0;i<units_to_db.length;i++)
			{
				str+=units_to_db[i] + "-0";
				if(i!=units_to_db.length-1)
				{	
					str+=",";
				}
			}
	//		System.out.println("#Rec: " + str);
			//-----
			
		
			// get last updated value
			try
			{
				RecordStore rs = null;
				rs = RecordStore.openRecordStore(month+"."+year,false);
				int recsize=0;
				recsize=rs.getSize();
				if(recsize==0)
				{
				}
				else
				{
					recordidval=recsize;
					byte[] recData = new byte[rs.getRecordSize(recordidval)];
					recData=rs.getRecord(recordidval);
					str=new String(recData);
					rs.closeRecordStore();
					rs=null;	
				}
			}
			catch (Exception e)
			{
//				System.out.println("#Rec: " + str);
				System.out.println(e.toString());
			}
			System.out.println("#: " + str);
			
			
			
			
			
			
			
			
			
		}
		else
		{
		
			try
			{
				RecordStore rs = null;
				rs = RecordStore.openRecordStore(month+"."+year,false);
				byte[] recData = new byte[rs.getRecordSize(recordidval)];
				recData=rs.getRecord(recordidval);
				str=new String(recData);
				System.out.println("#RecID: 1 " + str);
				rs.closeRecordStore();
				rs=null;
			}
			catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
		
        bval=aval;
        TotalForexConverter.bval=Double.toString(bval);
		System.out.println("----------------------");
        System.out.println(TotalForexConverter.a);
		System.out.println(TotalForexConverter.b);
		System.out.println(TotalForexConverter.a1);
		System.out.println(TotalForexConverter.b1);
		System.out.println(TotalForexConverter.aval);
		System.out.println(TotalForexConverter.bval);
		System.out.println(str);
		System.out.println("----------------------");
    }
}
