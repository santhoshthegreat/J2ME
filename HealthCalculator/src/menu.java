import com.sun.lwuit.*;
import com.sun.lwuit.animations.CommonTransitions;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.GridLayout;
public class menu extends Form
{
    int height,width;
//    HealthCalculator tfc;
//  PimpLookAndFeel p=new PimpLookAndFeel();
//    public menu(final HealthCalculator tfc)
    public menu()
    {
//        this.tfc=tfc;
        Display.init(this);
        Resources res=null;
        try
        {
            res = Resources.open("/thm.res");
            UIManager.getInstance().setThemeProps(res.getTheme(res.getThemeResourceNames()[0]));
//          UIManager.getInstance().setLookAndFeel(p);
        }
        catch (java.io.IOException e){e.printStackTrace();}
        GridLayout layout = new GridLayout(3,3);
        setLayout(layout);
        setTitle("Total Health Calculator");
        try
        {
            Image i1=res.getImage("bmr");
            Image i2=res.getImage("bmi");
            Image i3=res.getImage("rmr");
            Image i4=res.getImage("bodyfat");
            Image i5=res.getImage("calperday");
            Image i6=res.getImage("creatine");
            Image i7=res.getImage("healthtips");
            Image i8=res.getImage("macronutrient");
            Image i9=res.getImage("proteinneeds");
            Image i10=res.getImage("about");
            Image i11=res.getImage("help");
            Image i12=res.getImage("exit");
            Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12;
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
            b11=new Button(i11);
            b11.setAlignment(Label.CENTER);
            b12=new Button(i12);
            b12.setAlignment(Label.CENTER);
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
            setLayout(layout);
            //f.show();
            b1.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    HealthCalculator.showbmi();
                }
            });
            b2.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {

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
                }
            });
            b10.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    try
                    {
            		final Dialog d=new Dialog("Total Health Calculator");
                	d.setTitle("Total Health Calculator");
                        d.setTransitionOutAnimator(CommonTransitions.createFade(100));
         //               d.setTransitionInAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL,true,200));
                	TextArea lbl=new TextArea("Developed by Sisconsoft,Coimbatore. Design & Programming - Santhosh Kumar,Contact +91 99521 66070 / support@sisconsoft.com");
                        lbl.setAlignment(CENTER);
                        lbl.setEditable(false);
                	lbl.setSingleLineTextArea(false);
                	lbl.setGrowByContent(true);
                 	d.addComponent(lbl);
                	Button button = new Button("      OK      ");
                	d.addComponent(button);
                	button.addActionListener(new ActionListener()
                        {
                            public void actionPerformed(ActionEvent ae)
                            {
                                d.dispose();
                            }
                	});
                	d.show(0,0,0,0,true);
            	}
            	catch(Exception e)
            	{
            		System.out.println(e.getMessage());
            	}
            }
        });
        b11.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	try
            	{
            		final Dialog d=new Dialog("Help");
                	d.setTitle("Total Health Calculator");
                        d.setTransitionOutAnimator(CommonTransitions.createFade(100));
           //             d.setTransitionInAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL,true,200));
                	TextArea lbl=new TextArea("Total Health Calculator enables you to calculate health units such as BMI, BMR, RMR, Body Fat, Calories Per Day, Creatine, Nutrient, Protein Needs to lead a healthy life. The application is provided as is without any warranty and can be used for reference only.It is advised to have proper consultation with a professional for its accuracy.");
                        lbl.setAlignment(CENTER);
                        lbl.setEditable(false);
                	lbl.setSingleLineTextArea(false);
                	lbl.setGrowByContent(true);
                   	d.addComponent(lbl);
                	Button button = new Button("      OK      ");
                	d.addComponent(button);
                	button.addActionListener(new ActionListener()
                        {
                            public void actionPerformed(ActionEvent ae)
                            {
                                d.dispose();
                            }
                	});
                	d.show(0,0,0,0,true);
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
            	HealthCalculator.dis=null;
            }
        });
        }
        catch (Exception e)
        {
        }
     }
}