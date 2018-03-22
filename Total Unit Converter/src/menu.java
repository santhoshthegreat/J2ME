import com.sun.lwuit.*;
import com.sun.lwuit.Form;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;
import com.sun.lwuit.Command.*;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.FlowLayout;
public class menu extends Form{
    int height,width;
    TotalUnitConverter tuc;
    public menu(TotalUnitConverter uc)
    {
        tuc=uc;
        Display.init(this);
        Resources res=null;
        try
        {
            res = Resources.open("/myresources.res");
            UIManager.getInstance().setThemeProps(res.getTheme(
            res.getThemeResourceNames()[0]) );
        }
        catch (java.io.IOException e){e.printStackTrace();}
        final Command cmdexit=new Command("Exit");
        addCommand(cmdexit);
        setScrollableY(true);
        setFocusable(true);
        FlowLayout layout = new FlowLayout();
        setLayout(layout);
        Image i1 = res.getImage("area");
        Image i2 = res.getImage("mass");
        Image i3 = res.getImage("time");
        Image i4 = res.getImage("temperature");
        Image i5 = res.getImage("distance");
        Image i6 = res.getImage("pressure");
        Image i7 = res.getImage("speed");
        Image i8 = res.getImage("flowrate");
        Image i9 = res.getImage("volume");
        Image i10 = res.getImage("density");
        Image i11 = res.getImage("exit");
        Image i12=res.getImage("help");
        Image i13=res.getImage("about");
        Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13;
        b1=new Button(i1);
        b1.setAlignment(Label.CENTER);
        b2=new Button(i2);
        b2.setAlignment(Label.CENTER);
        b3=new Button(i3);
        b3.setAlignment(Label.CENTER);
        b4=new Button(i4);
        b4.setAlignment(Label.CENTER);
        b5=new Button(i5);
        b5.setAlignment(Label.CENTER);
        b6=new Button(i6);
        b6.setAlignment(Label.CENTER);
        b7=new Button(i7);
        b7.setAlignment(Label.CENTER);
        b8=new Button(i8);
        b8.setAlignment(Label.CENTER);
        b9=new Button(i9);
        b9.setAlignment(Label.CENTER);
        b10=new Button(i10);
        b10.setAlignment(Label.CENTER);
        b11=new Button(i12);
        b11.setAlignment(Label.CENTER);
        b12=new Button(i13);
        b12.setAlignment(Label.CENTER);
        b13=new Button(i11);
        b13.setAlignment(Label.CENTER);
        addComponent(b1);
        addComponent(b2);
        addComponent(b3);
        addComponent(b4);
        addComponent(b5);
        addComponent(b6);
        addComponent(b7);
        addComponent(b8);
        addComponent(b9);
        addComponent(b10);
        addComponent(b11);
        addComponent(b12);
        addComponent(b13);
        b1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                TotalUnitConverter.aval="0";
                TotalUnitConverter.bval="0";
                tuc.setareacanvas();
            }
        });
        b2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                TotalUnitConverter.aval="0";
                TotalUnitConverter.bval="0";
                tuc.setmasscanvas();
            }
        });
        b3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                TotalUnitConverter.aval="0";
                TotalUnitConverter.bval="0";
                tuc.settimecanvas();
            }
        });
        b4.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                TotalUnitConverter.aval="0";
                TotalUnitConverter.bval="0";
                tuc.settemperaturecanvas();
            }
        });
        b5.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                TotalUnitConverter.aval="0";
                TotalUnitConverter.bval="0";
                tuc.setdistancecanvas();
            }
        });
        b6.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                TotalUnitConverter.aval="0";
                TotalUnitConverter.bval="0";
                tuc.setpressurecanvas();
            }
        });
        b7.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                TotalUnitConverter.aval="0";
                TotalUnitConverter.bval="0";
                tuc.setspeedcanvas();
            }
        });
        b8.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                TotalUnitConverter.aval="0";
                TotalUnitConverter.bval="0";
                tuc.setflowratecanvas();
            }
        });
        b9.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                TotalUnitConverter.aval="0";
                TotalUnitConverter.bval="0";
                tuc.setvolumecanvas();
            }
        });
        b10.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                TotalUnitConverter.aval="0";
                TotalUnitConverter.bval="0";
                tuc.setdensitycanvas();
            }
        });
        b13.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                tuc.exit();
            }
        });
        b11.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            //    Dialog d=new Dialog("Help");
            //    d.show("Help","Total Unit Converter is a Utility for converting multiple units.Choose a source and destination unit, tap the Numeric keys for values and tap the convert button to convert the values","Ok","Back");
                try
            	{
                    final Dialog d=new Dialog("Help");
                    TextArea lbl=new TextArea("Total Unit Converter is a Utility for converting multiple units.Choose a source and destination unit, tap the Numeric keys for Converted values");
                    lbl.setEditable(false);
                    lbl.setSingleLineTextArea(false);
                    lbl.setGrowByContent(true);
                    d.addComponent(lbl);
                    Button button = new Button("    OK    ");
                    d.addComponent(button);
                    button.addActionListener(new ActionListener()
                    {
                    	  public void actionPerformed(ActionEvent ae)
                          {
                    	    d.dispose();
                    	  }
                    }
                    );
                    d.show(0,0,0,0,false);
            	}
            	catch(Exception e)
            	{
            		System.out.println(e.getMessage());
            	}
                
            }
        });
        b12.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            //    Dialog d=new Dialog("About Us");
            //    d.show("Total Unit Converter 1.0","Developed by Siscon Software Solutions,Coimbatore. Design & Programming - Santhosh Kumar, For queries contact +91 99521 66070 / santhosh@sisconsoft.com. Thanks for purchasing Total Unit Converter","Ok","Back");

                try
            	{
                    final Dialog d=new Dialog("Total Unit Converter 2.0");
                    TextArea lbl=new TextArea("Developed by Siscon Software Solutions. Design & Programming - Santhosh Kumar, For queries contact +91 99521 66070 / santhosh@sisconsoft.com. Thanks for purchasing Total Unit Converter");
                    lbl.setEditable(false);
                    lbl.setSingleLineTextArea(false);
                    lbl.setGrowByContent(true);
                    d.addComponent(lbl);
                    Button button = new Button("    OK    ");
                    d.addComponent(button);
                    button.addActionListener(new ActionListener()
                    {
                    	  public void actionPerformed(ActionEvent ae)
                          {
                    	    d.dispose();
                    	  }
                    }
                    );
                    d.show(0,0,0,0,false);
            	}
            	catch(Exception e)
            	{
            		System.out.println(e.getMessage());
            	}

            }
        });
        setCommandListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                if(ae.getCommand() == cmdexit)
                {
                    tuc.exit();
                }
            }
        });
    }
}
