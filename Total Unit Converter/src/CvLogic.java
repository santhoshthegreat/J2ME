import java.io.IOException;
import java.io.InputStream;
import net.mypapit.java.StringTokenizer;
public class CvLogic
{
    public CvLogic()
    {
    }
    public void convert() throws IOException
    {
        String a=TotalUnitConverter.a;
        try
        {
            a=a.replace('/',' ');
        }
        catch(Exception e)
        {}
//      String b=TotalUnitConverter.b;
        String type;
        type=TotalUnitConverter.typetext;
//      int convertfrom=TotalUnitConverter.a1;
        int convertto=TotalUnitConverter.b1;
        double aval=Double.parseDouble(TotalUnitConverter.aval);
        double bval=Double.parseDouble(TotalUnitConverter.bval);
        InputStream tis;
   	StringBuffer s=new StringBuffer();
        byte[] buf;
        int bytesread=1;
        String k="";
        String[] arr=new String[0];
        try
        {
            tis=getClass().getResourceAsStream("/" + type + a + ".dat");
            while(bytesread>0)
            {
                buf=new byte[40];
                bytesread=tis.read(buf);
                if(bytesread>0==true)
                {
                   k=new String(buf,0,bytesread);
                   s.append(k);
                   StringTokenizer st=new StringTokenizer(s.toString(),"-");
                   arr=new String[st.countTokens()];
                   arr=st.toArray();
                }
            }
                   bval=Double.parseDouble(arr[convertto])*aval;
        }
        catch(Exception e)
        {
            bval=0;
        }
        TotalUnitConverter.bval=Double.toString(bval);
    }
}
