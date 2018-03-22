package forex;
import com.sun.lwuit.*;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.GridLayout;

public class menu extends Form{
    int height,width;
    TotalForexConverter tfc;
    public menu(final TotalForexConverter tfc)
    {
        this.tfc=tfc;
        Display.init(this);
        Resources res=null;
        try
        {
            res = Resources.open("/thm.res");
            UIManager.getInstance().setThemeProps(res.getTheme(
            res.getThemeResourceNames()[0]) );
        }
        catch (java.io.IOException e){e.printStackTrace();}
        final Command cmdexit=new Command("Exit");
        addCommand(cmdexit);
        setScrollableY(true);
        setFocusable(true);
    //    FlowLayout layout = new FlowLayout();
             GridLayout layout = new GridLayout(3,2);
       // setTransitionOutAnimator(CommonTransitions.createFade(400));
        setLayout(layout);
        Image i1=res.getImage("update");
        Image i2=res.getImage("converter");
        Image i3=res.getImage("history");
        Image i4=res.getImage("chart");
        Image i5=res.getImage("live");
        Image i6=res.getImage("about");
        Image i7=res.getImage("help");
        Image i8=res.getImage("exit");
        Button b1,b2,b3,b4,b5,b6,b7,b8;
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
        addComponent(b1);
        addComponent(b2);
        addComponent(b3);
        addComponent(b4);
        addComponent(b5);
        addComponent(b6);
        addComponent(b7);
        addComponent(b8);
        b1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	tfc.showupdate();
            }
        });
        b2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                tfc.setcanvas();
            }
        });
        b3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {

            }
        });
        b4.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {

            }
        });
        b5.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
           //     TotalUnitConverter.aval="0";
          //      TotalUnitConverter.bval="0";
         //       tuc.setdistancecanvas();
            }
        });
        b6.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	try
            	{
            		final Dialog d=new Dialog("Total Forex Converter");
                	d.setTitle("Total Forex Converter");
                	TextArea lbl=new TextArea("Developed by Siscon,Coimbatore. Design & Programming - Santhosh Kumar, For queries contact +91 99521 66070 / support@sisconsoft.com");
                	lbl.setEditable(false);
                	lbl.setSingleLineTextArea(false);
                	lbl.setGrowByContent(true);
                 	d.addComponent(lbl);
                	Button button = new Button("Back");
                	d.addComponent(button);
                	button.addActionListener(new ActionListener() {
                		  public void actionPerformed(ActionEvent ae) {
                		    d.dispose();
                		  }
                	});
                	d.show(0,0,0,0,false);
            	}
            	catch(Exception e)
            	{
            		System.out.println(e.getMessage());
            	}
            }
        });
        b7.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	try
            	{
            		final Dialog d=new Dialog("Help");
                	d.setTitle("Total Forex Converter");
                	TextArea lbl=new TextArea("Total Forex Converter is a Utility for converting multiple Forex units updated with live rates.Update Live currency Rate,Choose a source and destination unit, tap the Numeric keys for values and tap the convert button to convert the values");
                	lbl.setEditable(false);
                	lbl.setSingleLineTextArea(false);
                	lbl.setGrowByContent(true);
                	d.addComponent(lbl);
                	Button button = new Button("Back");
                	d.addComponent(button);
                	button.addActionListener(new ActionListener() {
                		  public void actionPerformed(ActionEvent ae) {
                		    d.dispose();
                		  }
                	});
                	d.show(0,0,0,0,false);
                    
            	}
            	catch(Exception e)
            	{
            		System.out.println(e.getMessage());
            	}
            }
        });
        b8.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	tfc.exit();
            }
        });
     }
}
