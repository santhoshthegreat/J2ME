import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
public class TotalUnitConverter extends MIDlet implements CommandListener
{
    private List lst;
    private Command cmdback,cmdok,cmdgo;
    public static mycanvas myc;
    public static menu m;
    public static String a="";
    public static String b="";
    public static String aval="0";
    public static String bval="0";
    public static int a1=1;
    public static int b1=1;
    public static int sel=0;
    public static int type=0;
    public static String typetext="";
    static public Display dis;
    public void startApp()
    {
        dis=getDisplay();
        com.sun.lwuit.Display.init(this);
        typetext="area";
        myc=new mycanvas(this,a,b);
        m=new menu(this);
        dis.setCurrent(new SplashScreen(this));
        type=1;
    }
    public static void shw()
    {
        m.show();
    }
    public void exitapp()
    {
        destroyApp(true);
    }
    protected void pauseApp()
    {}
    protected void destroyApp(boolean unconditional)
    {
        notifyDestroyed();
    }
    public void exit()
    {
        destroyApp(false);
    }
    public Display getDisplay()
    {
        return Display.getDisplay(this);
    }
    public void switchDisplayable(Displayable nextDisplayable)
    {
        dis = Display.getDisplay(this);
    }

    public void showform1()
    {
            if(type==1)
            {
            lst = new List("Convert From", List.IMPLICIT);
            lst.append("Acre",null);
            lst.append("Hectare",null);
            lst.append("Township",null);
            lst.append("Barn",null);
            lst.append("Homestead",null);
            lst.append("Square Mile",null);
            lst.append("Square Cm",null);
            lst.append("Square Meter",null);
            lst.append("Square Inch",null);
            lst.append("Square Yard",null);
            lst.append("Square Foot",null);
            lst.append("Square Kilometer",null);
            }
            else if(type==2)
            {
            lst = new List("Convert From", List.IMPLICIT);
            lst.append("Bale",null);
            lst.append("Quintal",null);
            lst.append("Ton Metric",null);
            lst.append("Stone",null);
            lst.append("Kilogram",null);
            lst.append("Pound",null);
            lst.append("Ounce",null);
            lst.append("Gram",null);
            lst.append("Decigram",null);
            lst.append("Centigram",null);
            lst.append("Milligram",null);
            lst.append("Microgram",null);
            }
            else if(type==3)
            {
            lst = new List("Convert From", List.IMPLICIT);
            lst.append("Second",null);
            lst.append("Minute",null);
            lst.append("Hour",null);
            lst.append("Day",null);
            lst.append("Week",null);
            lst.append("Month",null);
            lst.append("Year",null);
            lst.append("Decade",null);
            lst.append("Century",null);
            lst.append("Millenium",null);
            lst.append("Quarter",null);
            }
            else if(type==4)
            {
            lst = new List("Convert From", List.IMPLICIT);
            lst.append("Kelvin",null);
            lst.append("Celcius",null);
            lst.append("Reaumur",null);
            lst.append("Fahrenheit",null);
            lst.append("Rankine",null);
            }
            else if(type==5)
            {
            lst = new List("Convert From", List.IMPLICIT);
            lst.append("Centimeter",null);
            lst.append("Decimeter",null);
            lst.append("Furlong",null);
            lst.append("Fathom",null);
            lst.append("Feet",null);
            lst.append("Finger",null);
            lst.append("Fist",null);
            lst.append("Gigameter",null);
            lst.append("Hectometer",null);
            lst.append("Kilometer",null);
            lst.append("Meter",null);
            lst.append("MicroInch",null);
            lst.append("Micrometer",null);
            lst.append("Micron",null);
            lst.append("Mile",null);
            lst.append("Nauticalmile",null);
            lst.append("Millimeter",null);
            lst.append("Nanometer",null);
            lst.append("Quadrant",null);
            lst.append("Yard",null);
            lst.append("Inch",null);
            }
            else if(type==6)
            {
            lst = new List("Convert From", List.IMPLICIT);
            lst.append("Atmosphere",null);
            lst.append("Attobar",null);
            lst.append("Bar",null);
            lst.append("CentiBar",null);
            lst.append("DeciBar",null);
            lst.append("KiloBar",null);
            lst.append("MicroBar",null);
            lst.append("NanoBar",null);
            lst.append("YottaBar",null);
            lst.append("ZetaBar",null);
            }
            else if(type==7)
            {
            lst = new List("Convert From", List.IMPLICIT);
            lst.append("Foot/hr",null);
            lst.append("Inch/hr",null);
            lst.append("Km/hr",null);
            lst.append("Mtr/hr",null);
            lst.append("Mile/hr",null);
            lst.append("Yard/hr",null);
            lst.append("Speed of Light [Water]",null);
            lst.append("Speed of Light [Air]",null);
            lst.append("Speed of Sound [Water]",null);
            lst.append("Speed of Sound [Air]",null);
            }
            else if(type==8)
            {
            lst = new List("Convert From", List.IMPLICIT);
            lst.append("Acre foot/day",null);
            lst.append("Acre inch/day",null);
            lst.append("Barrel/day",null);
            lst.append("Barrel/hr",null);
            lst.append("Billion cubic foot/day",null);
            lst.append("Cubic foot/day",null);
            lst.append("Cubic foot/hr",null);
            lst.append("Gallon/day",null);
            lst.append("Liter/day",null);
            lst.append("Ounce/day",null);
            lst.append("Milliliter/day",null);
            }
            else if(type==9)
            {
            lst = new List("Convert From", List.IMPLICIT);
            lst.append("Micro Liter",null);
            lst.append("Milli Liter",null);
            lst.append("Teaspoon",null);
            lst.append("Tablespoon",null);
            lst.append("Bushnel",null);
            lst.append("Ounce",null);
            lst.append("Deci Liter",null);
            lst.append("Cup",null);
            lst.append("Pint",null);
            lst.append("Liter",null);
            lst.append("Gallon",null);
            lst.append("Peck",null);
            lst.append("Centi Liter",null);
            lst.append("Bucket",null);
            lst.append("Barrel",null);
            lst.append("Kilo Liter",null);
            }
            else if(type==10)
            {
            lst = new List("Convert From", List.IMPLICIT);
            lst.append("Aluminium",null);
            lst.append("Copper",null);
            lst.append("Gold",null);
            lst.append("Iron",null);
            lst.append("Lead",null);
            lst.append("Silver",null);
            lst.append("Water",null);
            }
            cmdgo=new Command("Select", Command.ITEM, 1);
            cmdback=new Command("Back", Command.BACK,0);
            lst.addCommand(cmdgo);
            lst.addCommand(cmdback);
            lst.setCommandListener(this);
            dis.setCurrent(lst);
        }
        public void showform2()
        {
            if(type==1)
            {
            lst = new List("Convert to", List.IMPLICIT);
            lst.append("Acre",null);
            lst.append("Hectare",null);
            lst.append("Township",null);
            lst.append("Barn",null);
            lst.append("Homestead",null);
            lst.append("Square Mile",null);
            lst.append("Square Cm",null);
            lst.append("Square Meter",null);
            lst.append("Square Inch",null);
            lst.append("Square Yard",null);
            lst.append("Square Foot",null);
            lst.append("Square Kilometer",null);
            }
            else if(type==2)
            {
            lst = new List("Convert to", List.IMPLICIT);
            lst.append("Bale",null);
            lst.append("Quintal",null);
            lst.append("Ton Metric",null);
            lst.append("Stone",null);
            lst.append("Kilogram",null);
            lst.append("Pound",null);
            lst.append("Ounce",null);
            lst.append("Gram",null);
            lst.append("Deci Gram",null);
            lst.append("Centi Gram",null);
            lst.append("Milli Gram",null);
            lst.append("Micro Gram",null);
            }
            else if(type==3)
            {
            lst = new List("Convert to", List.IMPLICIT);
            lst.append("Second",null);
            lst.append("Minute",null);
            lst.append("Hour",null);
            lst.append("Day",null);
            lst.append("Week",null);
            lst.append("Month",null);
            lst.append("Year",null);
            lst.append("Decade",null);
            lst.append("Century",null);
            lst.append("Millenuium",null);
            lst.append("Quarter",null);
            }
            else if(type==4)
            {
            lst = new List("Convert to", List.IMPLICIT);
            lst.append("Kelvin",null);
            lst.append("Celcius",null);
            lst.append("Reaumur",null);
            lst.append("Fahrenheit",null);
            lst.append("Rankine",null);
            }
            else if(type==5)
            {
            lst = new List("Convert to", List.IMPLICIT);
            lst.append("Centimeter",null);
            lst.append("Decimeter",null);
            lst.append("Furlong",null);
            lst.append("Fathom",null);
            lst.append("Feet",null);
            lst.append("Finger",null);
            lst.append("Fist",null);
            lst.append("GigaMeter",null);
            lst.append("HectoMeter",null);
            lst.append("KiloMeter",null);
            lst.append("Meter",null);
            lst.append("MicroInch",null);
            lst.append("MicroMeter",null);
            lst.append("Micron",null);
            lst.append("Mile",null);
            lst.append("Nautical Mile",null);
            lst.append("MilliMeter",null);
            lst.append("NanoMeter",null);
            lst.append("Quadrant",null);
            lst.append("Yard",null);
            lst.append("Inch",null);
            }
            else if(type==6)
            {
            lst = new List("Convert to", List.IMPLICIT);
            lst.append("Atmosphere",null);
            lst.append("Attobar",null);
            lst.append("Bar",null);
            lst.append("CentiBar",null);
            lst.append("DeciBar",null);
            lst.append("KiloBar",null);
            lst.append("MicroBar",null);
            lst.append("NanoBar",null);
            lst.append("YottaBar",null);
            lst.append("ZetaBar",null);
            }
            else if(type==7)
            {
            lst = new List("Convert to", List.IMPLICIT);
            lst.append("Foot/hr",null);
            lst.append("Inch/hr",null);
            lst.append("Km/hr",null);
            lst.append("Mtr/hr",null);
            lst.append("Mile/hr",null);
            lst.append("Yard/hr",null);
            lst.append("Speed of Light [Water]",null);
            lst.append("Speed of Light [Air]",null);
            lst.append("Speed of Sound [Water]",null);
            lst.append("Speed of Sound [Air]",null);
            }
            else if(type==8)
            {
            lst = new List("Convert to", List.IMPLICIT);
            lst.append("Acre foot/day",null);
            lst.append("Acre inch/day",null);
            lst.append("Barrel/day",null);
            lst.append("Barrel/hr",null);
            lst.append("Billion cubic foot/day",null);
            lst.append("Cubic foot/day",null);
            lst.append("Cubic foot/hr",null);
            lst.append("Gallon/day",null);
            lst.append("Liter/day",null);
            lst.append("Ounce/day",null);
            lst.append("Milliliter/day",null);
            }
            else if(type==9)
            {
            lst = new List("Convert to", List.IMPLICIT);
            lst.append("MicroLiter",null);
            lst.append("MilliLiter",null);
            lst.append("Teaspoon",null);
            lst.append("Tablespoon",null);
            lst.append("Bushnel",null);
            lst.append("Ounce",null);
            lst.append("DeciLiter",null);
            lst.append("Cup",null);
            lst.append("Pint",null);
            lst.append("Litre",null);
            lst.append("Gallon",null);
            lst.append("Peck",null);
            lst.append("CentiLiter",null);
            lst.append("Bucket",null);
            lst.append("Barrel",null);
            lst.append("KiloLitre",null);
            }
            else if(type==10)
            {
            lst = new List("Convert to", List.IMPLICIT);
            lst.append("Aluminium",null);
            lst.append("Copper",null);
            lst.append("Gold",null);
            lst.append("Iron",null);
            lst.append("Lead",null);
            lst.append("Silver",null);
            lst.append("Water",null);
            }
            cmdgo=new Command("Select", Command.ITEM, 1);
            cmdback=new Command("Back", Command.BACK,0);
            lst.addCommand(cmdgo);
            lst.addCommand(cmdback);
            lst.setCommandListener(this);
            dis.setCurrent(lst);
        }
        public void setareacanvas()
        {
            a="Acre";
            b="Hectare";
            typetext="area";
            type=1;
            myc.setvalues(a, b);
            dis.setCurrent(myc);
        }
        public void setmasscanvas()
        {
            a="Bale";
            b="Quintal";
            typetext="mass";
            type=2;
            myc.setvalues(a, b);
            dis.setCurrent(myc);
        }
        public void settimecanvas()
        {
            a="Second";
            b="Minute";
            typetext="time";
            type=3;
            myc.setvalues(a, b);
            dis.setCurrent(myc);
        }
        public void settemperaturecanvas()
        {
            a="Kelvin";
            b="Celcius";
            typetext="temperature";
            type=4;
            myc.setvalues(a, b);
            dis.setCurrent(myc);
        }
        public void setdistancecanvas()
        {
            a="Centimeter";
            b="Decimeter";
            typetext="distance";
            type=5;
            myc.setvalues(a, b);
            dis.setCurrent(myc);
        }
        public void setpressurecanvas()
        {
            a="Atmosphere";
            b="Attobar";
            typetext="pressure";
            type=6;
            myc.setvalues(a, b);
            dis.setCurrent(myc);
        }
        public void setspeedcanvas()
        {
            a="Foot/hr";
            b="Inch/hr";
            typetext="speed";
            type=7;
            myc.setvalues(a, b);
            dis.setCurrent(myc);
        }
        public void setflowratecanvas()
        {
            a="Acre foot/day";
            b="Acre Inch/day";
            typetext="flowrate";
            type=8;
            myc.setvalues(a, b);
            dis.setCurrent(myc);
        }
        public void setvolumecanvas()
        {
            a="MicroLiter";
            b="MilliLiter";
            typetext="volume";
            type=9;
            myc.setvalues(a, b);
            dis.setCurrent(myc);
        }
        public void setdensitycanvas()
        {
            a="Aluminium";
            b="Copper";
            typetext="density";
            type=10;
            myc.setvalues(a, b);
            dis.setCurrent(myc);
        }
	public void commandAction(Command c,Displayable s)
	{
 		if (c == List.SELECT_COMMAND)
		{
                    if(sel==1)
                    {
                        a=lst.getString(lst.getSelectedIndex());
                        a1=lst.getSelectedIndex();

                    }
                    else
                    {
                        b=lst.getString(lst.getSelectedIndex());
                        b1=lst.getSelectedIndex();
                    }
                    myc.setvalues(a, b);
                    myc.cnv();
                    dis.setCurrent(myc);
		}
		if(c==cmdback)
		{
//                    myc.setvalues(a, b);
                    dis.setCurrent(myc);
		}
		if(c==cmdok)
		{
                    myc.setvalues(a, b);
                    dis.setCurrent(myc);
		}
		else if(c==cmdgo)
		{
                    if(sel==1)
                    {
                        a=lst.getString(lst.getSelectedIndex());
                        a1=lst.getSelectedIndex();

                    }
                    else
                    {
                        b=lst.getString(lst.getSelectedIndex());
                        b1=lst.getSelectedIndex();
                    }
                    myc.setvalues(a, b);
                    myc.cnv();
                    dis.setCurrent(myc);
		}
	}
        public static void convert() throws IOException
        {
            CvLogic cvl=new CvLogic();
            cvl.convert();
            myc.repaint();
            System.out.println(typetext);
            System.out.println(a);
            System.out.println(b);
 
        }
}
