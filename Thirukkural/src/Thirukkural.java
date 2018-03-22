import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
public class Thirukkural extends MIDlet implements CommandListener
{
	private List lst;
	private Command cmdback,cmdok,cmdgo;
	private TextField t;
	private static MyCanvas myc;
        private Display disp;
        private MenuScreen ms;
	protected void startApp()
	{
                disp=Display.getDisplay(this);
                ms=new MenuScreen(this);
                begin();
		myc=new MyCanvas(this);
 	}
	protected void pauseApp(){}
	protected void destroyApp(boolean status)
	{
		notifyDestroyed();
	}
	public void showkural(int i)
	{
		disp.setCurrent(myc);
		myc.start(i);
	}
	public void enterkural()
	{
		Form f;
		f=new Form("Jump to Kural");
		t=new TextField("Enter kural Number to jump","",4,TextField.NUMERIC);
		f.append(t);
		cmdok=new Command("Ok", Command.ITEM, 1);
		cmdback=new Command("Back", Command.BACK,0); 
		f.addCommand(cmdback);
		f.addCommand(cmdok);
		f.setCommandListener(this);
                ms.currentscene=0;
		disp.setCurrent(f);
	}
	public void chapter()
	{
		lst = new List("Kural by category", List.IMPLICIT);
		// 1
		lst.append("The Praise of God",null);
// 2		
lst.append("The Blessing of Rain",null);
//3	
	lst.append("The Merit of Ascetics",null);
//4	
	lst.append("The Power of Virtue",null);
//5	
	lst.append("Married Life",null);
//6	
	lst.append("The Worth of a wife",null);
//7	
	lst.append("The Wealth of Children",null);
//8	
	lst.append("Loving - Kindness",null);
//9	
	lst.append("Hospitality",null);
//10	
	lst.append("Sweet Words",null);
//11	
	lst.append("Gratitude",null);
//12	
	lst.append("Equity",null);
//13	
	lst.append("Self Control",null);
//14	
	lst.append("Good Decorum",null);
//15	
	lst.append("Against Coveting Another's wife",null);
//16	
	lst.append("Forgiveness",null);
//17	
	lst.append("Avoid envy",null);
//18	
	lst.append("Against Covetousness",null);
//19	
	lst.append("Against Slander",null);
//20	
	lst.append("Against Vain Speaking",null);
//21	
	lst.append("Fear of sin",null);
//22	
	lst.append("Duty to society",null);
//23	
	lst.append("Charity",null);
//24	
	lst.append("Renown",null);
//25	
	lst.append("Compassion",null);
//26	
	lst.append("Abstinence from Flesh",null);
//27	
	lst.append("Penance",null);
//28	
	lst.append("Imposture",null);
//29	
	lst.append("Absense of Fraud",null);
//30	
	lst.append("Veracity",null);
//31	
	lst.append("Restraining Anger",null);
//32
lst.append("Non Violence",null);
//33
lst.append("Non-Killing",null);
//34
lst.append("Instability",null);
//35
lst.append("Renunciation",null);
//36
lst.append("Truth - Consciousness",null);
//37
lst.append("Curbing of Desire",null);
//38
lst.append("Destiny",null);
//39
lst.append("The Grandeur of Monarchy",null);
//40
lst.append("Education",null);
//41
lst.append("Non=Learning",null);
//42
lst.append("Listening",null);
//43
lst.append("Possession of Wisdom",null);
//44
lst.append("Avoiding Faults",null);
//45
lst.append("Gaining Great Men's help",null);
//46
lst.append("Avoiding Mean Company",null);
//47
lst.append("Deliberation before action",null);
//48
lst.append("Judging Strength",null);
//49
lst.append("Knowing the proper time",null);
//50
lst.append("Judging the place",null);
//51
lst.append("Testing of men for confidence",null);
//52
lst.append("Testing and Entrusting",null);
//53
lst.append("Cherishing kinsmen",null);
//54
lst.append("Unforgetfullness",null);
//55
lst.append("Just Government",null);
//56
lst.append("Cruel Tyranny",null);
//57
lst.append("Avoiding Terrorism",null);
//58
lst.append("Beign Looks",null);
//59
lst.append("Espionage",null);
//60
lst.append("Energy",null);
//61
lst.append("Freedom from sloth",null);
//62
lst.append("Manly Effort",null);
//63
lst.append("Hope in Mishap",null);
//64
lst.append("Ministers",null);
//65
lst.append("Power of Speech",null);
//66
lst.append("Purity of Action",null);
//67
lst.append("Powerful Acts",null);
//68
lst.append("Modes of Action",null);
//69
lst.append("Embassy",null);
//70
lst.append("Walk with kings",null);
//71
lst.append("Divining the mind",null);
//72
lst.append("Judging the audience",null);
//73
lst.append("Rage before counsils",null);
//74
lst.append("The Country",null);
//75
lst.append("Fortress",null);
//76
lst.append("Ways of Making Wealth",null);
//77
lst.append("The Glory of the Army",null);
//78
lst.append("Military Pride",null);
//79
lst.append("Friendship",null);
//80
lst.append("Testing Friendship",null);
//81
lst.append("Intimacy",null);
//82
lst.append("Bad Friendship",null);
//83
lst.append("False Friendship",null);
//84
lst.append("Folly",null);
//85
lst.append("Petty Conceit",null);
//86
lst.append("Hatred",null);
//87
lst.append("Noble Hostility",null);
//88
lst.append("Appraising Enemies",null);
//89
lst.append("Secret Foe",null);
//90
lst.append("Offend not the great",null);
//91
lst.append("Being led by woman",null);
//92
lst.append("Wanton Women",null);
//93
lst.append("Not Drinking Liquor",null);
//94
lst.append("Gambling",null);
//95
lst.append("Medicine",null);
//96
lst.append("Nobility",null);
//97
lst.append("Honour",null);
//98
lst.append("Greatness",null);
//99
lst.append("Sublimity",null);
//100
lst.append("Courtesy",null);
//101
lst.append("Futile Wealth",null);
//102
lst.append("Sensitiveness to shame",null);
//103
lst.append("Promoting Family Welfare",null);
//104
lst.append("Farming",null);
//105
lst.append("Devoid",null);
//106
lst.append("Asking",null);
//107
lst.append("Dread of Beggary",null);
//108
lst.append("Meanness",null);
//109
lst.append("Beauty's Dart",null);
//110
lst.append("Signs speak the heart",null);
//111
lst.append("Embrace Bliss",null);
//112
lst.append("Beauty Extolled",null);
//113
lst.append("Love's Excellence",null);
//114
lst.append("Decorum Defied",null);
//115
lst.append("Public clamour",null);
//116
lst.append("Pangs of seperation",null);
//117
lst.append("Wailing of pining love",null);
//118
lst.append("Wasteful look of wistful life",null);
//119
lst.append("Wailing over pallon",null);
//120
lst.append("pining alone",null);
//121
lst.append("sad memories",null);
//122
lst.append("Dream visions",null);
//123
lst.append("Eventide shy",null);
//124
lst.append("Limbs languish",null);
//125
lst.append("soliloquy",null);
//126
lst.append("Reserve Lost",null);
//127
lst.append("Mutual yearning",null);
//128
lst.append("Feeling surmised",null);
//129
lst.append("Longing for reunion",null);
//130
lst.append("chiding the heart",null);
//131
lst.append("Bouderie",null);
//132
lst.append("Feigned Anger",null);
//133
lst.append("sulking charm",null);
		cmdgo=new Command("gotochapter", Command.ITEM, 1);
		cmdback=new Command("Back", Command.BACK,0);
		lst.addCommand(cmdgo);
		lst.addCommand(cmdback);
		lst.setCommandListener(this); 
                ms.currentscene=0;
                disp.setCurrent(lst);
	}
	public void exit()
	{
            notifyDestroyed();
	}
        public void showmenu()
        {
            ms.currentscene=0;
            disp.setCurrent(ms);
        }
	public void begin()
	{
            ms.currentscene=0;
            disp.setCurrent(new SplashScreen(this));
	}
	public void commandAction(Command c,Displayable s)
	{
		if (c == List.SELECT_COMMAND)
		{
			showkural((lst.getSelectedIndex()*10)+1);
		}
		if(c==cmdback)
		{
			disp.setCurrent(ms);
		}	
		if(c==cmdok)
		{
			int i;
			i=Integer.parseInt(t.getString());
			showkural(i);
		}
		else if(c==cmdgo)
		{
			showkural((lst.getSelectedIndex()*10)+1);
		}
	}
}
