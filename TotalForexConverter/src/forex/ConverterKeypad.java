package forex;
public class ConverterKeypad {
  public int find(int x,int y,int th,int tw,int noh,int now,int h,int w)
  {
      int r=0;
    if(y>h-noh)
    {
        // line 3
        System.out.println("line 3");
         int tx= w/2- (now*3)/2;
         if((x>=tx) && (x<=tx+now))
         {
             System.out.println("val 0");
             r=10;
         }
         else if((x>=tx+(now)) && (x<=tx+(now)+now))
         {
             System.out.println("val -<");
             r=11;
         }
         else if((x>=tx+(now*2)) && (x<=tx+(now*2)+now))
         {
             System.out.println("val .");
             r=12;
         }
    }
    else if(y>h-(2*noh))
    {
// line 2
         System.out.println("line 2");
         int tx= w/2- (now*4)/2;
         if((x>=tx) && (x<=tx+now))
         {
             System.out.println("val 6");
             r=6;
         }
         else if((x>=tx+(now)) && (x<=tx+(now)+now))
         {
             System.out.println("val 7");
             r=7;
         }
         else if((x>=tx+(now*2)) && (x<=tx+(now*2)+now))
         {
             System.out.println("val 8");
             r=8;
         }
         else if((x>=tx+(now*3)) && (x<=tx+(now*3)+now))
         {
             System.out.println("val 9");
             r=9;
         }
    }
    else if(y>h-(3*noh))
    {
       // line 1
         System.out.println("line 1");
         int tx= w/2- (now*5)/2;
         if((x>=tx) && (x<=tx+now))
         {
             System.out.println("val 1");
             r=1;
         }
         else if((x>=tx+(now)) && (x<=tx+(now)+now))
         {
             System.out.println("val 2");
             r=2;
         }
         else if((x>=tx+(now*2)) && (x<=tx+(now*2)+now))
         {
             System.out.println("val 3");
             r=3;
         }
         else if((x>=tx+(now*3)) && (x<=tx+(now*3)+now))
         {
             System.out.println("val 4");
             r=4;
         }
         else if((x>=tx+(now*4)) && (x<=tx+(now*4)+now))
         {
             System.out.println("val 5");
             r=5;
         }
    }
      return r;
  }
}
