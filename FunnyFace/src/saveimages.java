
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.lcdui.Image;

class saveimages implements Runnable
{
    public String str;
    public Image currentImage;
    public boolean savemyimageisrunning;
    public String progress;
    Thread t;
    public saveimages(String str1,Image img,boolean isrunning)
    {
        str=str1;
        currentImage=img;
        savemyimageisrunning=isrunning;
        progress="waiting";
        t=new Thread(this);
        t.start();
    }
    public String savemyimage()
    {
            String status="";
            FileConnection fc=null;
            OutputStream os=null;
            String path=str;
            try
            {
                String ext=path.substring(path.length()-4);
                path=path.substring(0,path.length()-4).concat("-warp").concat(".png");
                fc=(FileConnection)Connector.open(path,Connector.READ_WRITE);
                if(!fc.exists())
                fc.create();
                int iw=currentImage.getWidth();
                int ih=currentImage.getHeight();
                int rawInt[]=new int[iw*ih];
                currentImage.getRGB(rawInt,0,iw,0,0,iw,ih);
                byte byteData[]=PNGEncoder.toPNG(currentImage,false);
                os= fc.openOutputStream();
                os.write(byteData);
                os.flush();
                status="Successful";
            }
            catch(Exception e)
            {
                status=e.toString();
//                midlet.showMsg(e.toString());
            }
            finally
            {
                try
                {
                    os.close();
                    fc.close();
                }
                catch(Exception e)
                {
                }
            }
            return status;
    }

    public String savemyimage1()
    {
            String status="";
            FileConnection fc=null;
            OutputStream os=null;
            String path=str;
            try
            {
                path=path.concat("warp").concat(".png");
                fc=(FileConnection)Connector.open(path,Connector.READ_WRITE);
                if(!fc.exists())
                fc.create();
                int iw=currentImage.getWidth();
                int ih=currentImage.getHeight();
                int rawInt[]=new int[iw*ih];
                currentImage.getRGB(rawInt,0,iw,0,0,iw,ih);
                byte byteData[]=PNGEncoder.toPNG(currentImage,false);
                os= fc.openOutputStream();
                os.write(byteData);
                os.flush();
                status="Successful";
            }
            catch(Exception e)
            {
                status=e.toString(); 
//                midlet.showMsg(e.toString());
            }
            finally
            {
                try
                {
                    os.close();
                    fc.close();
                }
                catch(Exception e)
                {
                }
            }
            return status;
    }

    public void run()
    {
        if(savemyimageisrunning==false)
        {
            progress=savemyimage1();
        }
        else
        {
            progress=savemyimage1();
        }
    }
}