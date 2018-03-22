//#condition polish.usePolishGui
/*
 * Created on 05-Jan-2004 at 20:41:52.
 *
 * Copyright (c) 2004-2005 Robert Virkus / Enough Software
 *
 * This file is part of J2ME Polish.
 *
 * J2ME Polish is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * J2ME Polish is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with J2ME Polish; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 * 
 * Commercial licenses are also available, please
 * refer to the accompanying LICENSE.txt or visit
 * http://www.j2mepolish.org for details.
 */
package de.enough.polish.ui;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Timer;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.midlet.MIDlet;

import de.enough.polish.ui.tasks.ImageTask;
import de.enough.polish.util.DeviceInfo;
import de.enough.polish.util.Locale;
import de.enough.polish.util.TextUtil;

/**
 * <p>Manages all defined styles of a specific project.</p>
 * <p>This class is actually pre-processed to get the styles specific for the project and the device.</p>
 *
 * @author Robert Virkus, robert@enough.de
 * <pre>
 * history
 *        05-Jan-2004 - rob creation
 * </pre>
 */
public final class StyleSheet {
	
	protected static Hashtable imagesByName;
	//#ifdef polish.images.backgroundLoad
		//# private static Hashtable scheduledImagesByName;
		//# //private static final Boolean TRUE = new Boolean( true );
		//# private static Timer timer;
	//#endif
	//#ifdef polish.LibraryBuild
		//# /** default style */
		//# public static Style defaultStyle = new Style( 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0x000000, Font.getDefaultFont(), null, null, null, null );
		//# /** default style for focused/hovered items */
		//# public static Style focusedStyle = new Style( 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0xFF0000, Font.getDefaultFont(), null, null, null, null );
		//# /** default style for labels */
		//# public static Style labelStyle = new Style( 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, Item.LAYOUT_NEWLINE_AFTER, 0x000000, Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL ), null, null, null, null );
		//# /** default style for the commands menu */
		//# public static Style menuStyle = new Style( 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, Item.LAYOUT_NEWLINE_AFTER, 0x000000, Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL ), null, null, null, null );
		//# private static Hashtable stylesByName = new Hashtable();
	//#endif


		private StyleSheet() {
			// disallow instantion
			//#if false
				//# // use Graphics so that the import is being kept:
				//# System.out.println(Graphics.LEFT);
			//#endif
		}

	
	
	// do not change the following line!
//$$IncludeStyleSheetDefinitionHere$$//
	public final static Background triangleDownBackground = new de.enough.polish.ui.backgrounds.TriangleBackground(0x777700, 1);
	public final static Background triangleUpBackground = new de.enough.polish.ui.backgrounds.TriangleBackground(0x777700, 0);
	public final static Background defaultBackground = null;
	public final static Border defaultBorder = null;
	public static Style defaultStyle;
	//static and referenced styles:
	public static Style browsertextbolditalicStyle;
	public static Style browsertextboldStyle;
	public static Style calendarweekdayStyle;
	public static Style menubarStyle;
	public static Style browsertextitalicStyle;
	public static Style titleStyle;
	public static Style browseroptionStyle;
	public static Style leftcommandStyle;
	public static Style scrollbarStyle;
	public static Style browseroptionitemStyle;
	public static Style browserStyle;
	public static Style rssdescriptionalertStyle;
	public static Style browsertextStyle;
	public static Style menuStyle;
	public static Style browserchoicegroupexclusiveStyle;
	public static Style browserradioStyle;
	public static Style rightcommandStyle;
	public static Style browsertextcodeStyle;
	public static Style calendarformStyle;
	public static Style browserchoicegroupmultipleStyle;
	public static Style browsercheckboxStyle;
	public static Style labelStyle;
	public static Style keydimensionStyle;
	public static Style keyspacedimensionStyle;
	public static Style keyblankStyle;
	public static Style keyboardStyle;
	public static Style keyboardviewStyle;
	public static Style keyboardviewheaderStyle;
	public static Style keypressedStyle;
	public static Style keyfocusedStyle;
	public static Style keyspacepressedStyle;
	public static Style keyspacefocusedStyle;
	public static Style keyrowfocusedStyle;
	public static Style keyboardviewsubmitfocusedStyle;
	public static Style keyboardviewsubmitpressedStyle;
	public static Style keyboardviewdisplayfocusedStyle;
	public static Style menuitemfocusedStyle;
	public static Style calendarmonthStyle;
	public static Style calendarfocusedStyle;
	public static Style browserlinkfocStyle;
	public static Style timefocusedStyle;
	public static Style calendardayinactivefocusedStyle;
	public static Style calendardayfocusedStyle;
	public static Style calendarcurrentdayfocusedStyle;
	public static Style browserinputfocStyle;
	public static Style browserchoicegrouppopupfocusedStyle;
	public static Style calendardayStyle;
	public static Style browserchoicegrouppopupStyle;
	public static Style timeStyle;
	public static Style calendarcurrentdayStyle;
	public static Style calendarStyle;
	public static Style browserinputStyle;
	public static Style menuitemStyle;
	public static Style calendardayinactiveStyle;
	public static Style browserlinkStyle;
	public static Style keyStyle;
	public static Style keyspaceStyle;
	public static Style keyshiftStyle;
	public static Style keydeleteStyle;
	public static Style keyclearStyle;
	public static Style keyabcStyle;
	public static Style key123Style;
	public static Style keyrowStyle;
	public static Style keyboardviewsubmitStyle;
	public static Style keyboardviewdisplayStyle;
	static final Hashtable stylesByName = new Hashtable(66);
static { // init styles:
	initStyles0();
}
private static final void initStyles0(){
	defaultStyle = new Style (
		"default", 
		Item.LAYOUT_DEFAULT,	// default layout
		defaultBackground, 
		defaultBorder, 
		new short[]{ },
		new Object[]{ }
	);
	browsertextbolditalicStyle = new Style (
		"browsertextbolditalic", 
		Item.LAYOUT_DEFAULT,	// default layout
		null,	// no background
		null, 	// no border
		new short[]{ 209, -14},
		new Object[]{ Style.FALSE, new Integer(Font.STYLE_BOLD|Font.STYLE_ITALIC)}
	);
	browsertextboldStyle = new Style (
		"browsertextbold", 
		Item.LAYOUT_DEFAULT,	// default layout
		null,	// no background
		null, 	// no border
		new short[]{ 209, -14, -17},
		new Object[]{ Style.FALSE, new Integer(Font.STYLE_BOLD), new Color( 0x000000, false)}
	);
	calendarweekdayStyle = new Style (
		"calendarweekday", 
		Item.LAYOUT_CENTER,
		null,	// no background
		null, 	// no border
		new short[]{ -14, 58},
		new Object[]{ new Integer(Font.STYLE_ITALIC|Font.STYLE_BOLD), new Dimension( 12, true )}
	);
	menubarStyle = new Style (
		"menubar", 
		Item.LAYOUT_DEFAULT,	// default layout
		new de.enough.polish.ui.backgrounds.GradientVerticalBackground(0xaaaaaa, 0xffffff,Graphics.SOLID,10,100,true),
		null, 	// no border
		new short[]{ 32712, -6, -9},
		new Object[]{ new Dimension(0, false), new Dimension(2, false), new Dimension(3, false)}
	);
	browsertextitalicStyle = new Style (
		"browsertextitalic", 
		Item.LAYOUT_DEFAULT,	// default layout
		null,	// no background
		null, 	// no border
		new short[]{ 209, -14},
		new Object[]{ Style.FALSE, new Integer(Font.STYLE_ITALIC)}
	);
	titleStyle = new Style (
		"title", 
		Item.LAYOUT_CENTER|Item.LAYOUT_EXPAND,
		new de.enough.polish.ui.backgrounds.SimpleBackground( new Color( 0x5a79ef, false)),
		null,	// border:none was specified
		new short[]{ -6, -16, -14, -17, -15},
		new Object[]{ new Dimension( 85, 10 ), new Integer( Font.FACE_PROPORTIONAL ), new Integer(Font.STYLE_BOLD), new Color( 0xFFFFFF, false), new Integer( Font.SIZE_LARGE )}
	);
	browseroptionStyle = new Style (
		"browseroption", 
		Item.LAYOUT_LEFT|Item.LAYOUT_EXPAND,
		null,	// no background
		null, 	// no border
		new short[]{ 209},
		new Object[]{ Style.FALSE}
	);
	leftcommandStyle = new Style (
		"leftcommand", 
		Item.LAYOUT_DEFAULT,	// default layout
		null,	// no background
		null, 	// no border
		new short[]{ 32712, -10, -14, -17, -15, 89, 88},
		new Object[]{ new Dimension(0, false), new Dimension(0, false), new Integer(Font.STYLE_BOLD), new Color( 0xFFFFFF, false), new Integer( Font.SIZE_SMALL ), new Color( 0x333333, false), new de.enough.polish.ui.texteffects.ShadowTextEffect()}
	);
	scrollbarStyle = new Style (
		"scrollbar", 
		Item.LAYOUT_DEFAULT,	// default layout
		null,	// no background
		null, 	// no border
		new short[]{ 119, 303, 120, 31},
		new Object[]{ new Dimension(4, false), Style.TRUE, new Color( 0x333333, false), new Dimension( 85, true )}
	);
	browseroptionitemStyle = new Style (
		"browseroptionitem", 
		Item.LAYOUT_DEFAULT,	// default layout
		null,	// no background
		null, 	// no border
		new short[]{ 209},
		new Object[]{ Style.FALSE}
	);
	browserStyle = new Style (
		"browser", 
		Item.LAYOUT_DEFAULT,	// default layout
		null,	// no background
		null, 	// no border
		new short[]{ 209},
		new Object[]{ Style.FALSE}
	);
	rssdescriptionalertStyle = new Style (
		"rssdescriptionalert", 
		Item.LAYOUT_DEFAULT,	// default layout
		null,	// no background
		null, 	// no border
		new short[]{ 209},
		new Object[]{ Style.FALSE}
	);
	browsertextStyle = new Style (
		"browsertext", 
		Item.LAYOUT_DEFAULT,	// default layout
		null,	// no background
		null, 	// no border
		new short[]{ 209},
		new Object[]{ Style.FALSE}
	);
	menuStyle = new Style (
		"menu", 
		Item.LAYOUT_DEFAULT,	// default layout
		new de.enough.polish.ui.backgrounds.BorderedRoundRectBackground( 0xa5bef7,8, 8, 0x5a79ef, 2),
		null, 	// no border
		new short[]{ -2, -6, -14, -17, -15},
		new Object[]{ new Dimension(2, false), new Dimension(2, false), new Integer(Font.STYLE_BOLD), new Color( 0xFFFFFF, false), new Integer( Font.SIZE_LARGE )}
	);
	browserchoicegroupexclusiveStyle = new Style (
		"browserchoicegroupexclusive", 
		Item.LAYOUT_DEFAULT,	// default layout
		null,	// no background
		null, 	// no border
		new short[]{ 209},
		new Object[]{ Style.FALSE}
	);
	browserradioStyle = new Style (
		"browserradio", 
		Item.LAYOUT_DEFAULT,	// default layout
		null,	// no background
		null, 	// no border
		new short[]{ 209},
		new Object[]{ Style.FALSE}
	);
	rightcommandStyle = new Style (
		"rightcommand", 
		Item.LAYOUT_RIGHT,
		null,	// no background
		null, 	// no border
		new short[]{ 32712, -10, -14, -17, -15, 89, 88},
		new Object[]{ new Dimension(0, false), new Dimension(0, false), new Integer(Font.STYLE_BOLD), new Color( 0xFFFFFF, false), new Integer( Font.SIZE_SMALL ), new Color( 0x333333, false), new de.enough.polish.ui.texteffects.ShadowTextEffect()}
	);
	browsertextcodeStyle = new Style (
		"browsertextcode", 
		Item.LAYOUT_DEFAULT,	// default layout
		null,	// no background
		null, 	// no border
		new short[]{ 209, -16},
		new Object[]{ Style.FALSE, new Integer( Font.FACE_MONOSPACE )}
	);
	calendarformStyle = new Style (
		"calendarform", 
		Item.LAYOUT_CENTER|Item.LAYOUT_TOP,
		new de.enough.polish.ui.backgrounds.GradientVerticalBackground(0xFFFFFF, 0xaaaaaa,Graphics.SOLID,70,100,true),
		null, 	// no border
		new short[]{ -9},
		new Object[]{ new Dimension(3, false)}
	);
	browserchoicegroupmultipleStyle = new Style (
		"browserchoicegroupmultiple", 
		Item.LAYOUT_DEFAULT,	// default layout
		null,	// no background
		null, 	// no border
		new short[]{ 209},
		new Object[]{ Style.FALSE}
	);
	browsercheckboxStyle = new Style (
		"browsercheckbox", 
		Item.LAYOUT_DEFAULT,	// default layout
		null,	// no background
		null, 	// no border
		new short[]{ 209},
		new Object[]{ Style.FALSE}
	);
	labelStyle = new Style (
		"label", 
		Item.LAYOUT_DEFAULT,	// default layout
		null,	// no background
		null, 	// no border
		new short[]{ -17, 89, 88},
		new Object[]{ new Color( 0xffffff, false), new Color( 0x333333, false), new de.enough.polish.ui.texteffects.ShadowTextEffect()}
	);
	keydimensionStyle = new Style (
		"keydimension", 
		Item.LAYOUT_DEFAULT,	// default layout
		null,	// no background
		null, 	// no border
		new short[]{ 144, 58},
		new Object[]{ new Dimension( 15, true ), new Dimension( 15, true )}
	);
	keyspacedimensionStyle = new Style (
		"keyspacedimension", 
		Item.LAYOUT_DEFAULT,	// default layout
		null,	// no background
		null, 	// no border
		new short[]{ 144, 58},
		new Object[]{ new Dimension( 15, true ), new Dimension( 45, true )}
	);
	keyblankStyle = new Style (
		"keyblank", 
		Item.LAYOUT_DEFAULT,	// default layout
		null,	// no background
		null, 	// no border
		new short[]{ 144, 58},
		new Object[]{ new Dimension( 15, true ), new Dimension( 15, true )}
	);
	keyboardStyle = new Style (
		"keyboard", 
		Item.LAYOUT_DEFAULT,	// default layout
		null,	// no background
		null, 	// no border
		new short[]{ -5, -4, -6},
		new Object[]{ new Dimension( 3, true ), new Dimension( 3, true ), new Dimension(0, false)}
	);
	keyboardviewStyle = new Style (
		"keyboardview", 
		Item.LAYOUT_VCENTER|Item.LAYOUT_CENTER,
		new de.enough.polish.ui.backgrounds.SimpleBackground( new Color( 0xFFFFFF, false)),
		null, 	// no border
		new short[]{ -6},
		new Object[]{ new Dimension(0, false)}
	);
	keyboardviewheaderStyle = new Style (
		"keyboardviewheader", 
		Item.LAYOUT_DEFAULT,	// default layout
		null,	// no background
		null, 	// no border
		new short[]{ 4, -6, 381},
		new Object[]{ new Integer(2), new Dimension(0, false), Style.TRUE}
	);
	keypressedStyle = new Style (
		"keypressed", 
		Item.LAYOUT_CENTER|Item.LAYOUT_VCENTER,
		new de.enough.polish.ui.backgrounds.SimpleBackground( new Color( 0x000000, false)),
		new de.enough.polish.ui.borders.SimpleBorder( 0x000000, 1),
		new short[]{ -17, -15, 32712, 388, 144, 58},
		new Object[]{ new Color( 0xFFFFFF, false), new Integer( Font.SIZE_LARGE ), new Dimension( 1, true ), new Integer(Item.LAYOUT_CENTER|Item.LAYOUT_VCENTER), new Dimension( 15, true ), new Dimension( 15, true )}
	);
	keyfocusedStyle = new Style (
		"keyfocused", 
		Item.LAYOUT_CENTER|Item.LAYOUT_VCENTER,
		null,	// no background
		new de.enough.polish.ui.borders.SimpleBorder( 0x000000, 1),
		new short[]{ 32712, -17, -15, 388, 144, 58},
		new Object[]{ new Dimension( 1, true ), new Color( 0x000000, false), new Integer( Font.SIZE_LARGE ), new Integer(Item.LAYOUT_CENTER|Item.LAYOUT_VCENTER), new Dimension( 15, true ), new Dimension( 15, true )}
	);
	keyspacepressedStyle = new Style (
		"keyspacepressed", 
		Item.LAYOUT_CENTER|Item.LAYOUT_VCENTER,
		new de.enough.polish.ui.backgrounds.SimpleBackground( new Color( 0x000000, false)),
		new de.enough.polish.ui.borders.SimpleBorder( 0x000000, 1),
		new short[]{ -17, -15, 32712, 144, 58},
		new Object[]{ new Color( 0xFFFFFF, false), new Integer( Font.SIZE_LARGE ), new Dimension( 1, true ), new Dimension( 15, true ), new Dimension( 45, true )}
	);
	keyspacefocusedStyle = new Style (
		"keyspacefocused", 
		Item.LAYOUT_CENTER|Item.LAYOUT_VCENTER,
		null,	// no background
		new de.enough.polish.ui.borders.SimpleBorder( 0x000000, 1),
		new short[]{ 32712, -17, -15, 144, 58},
		new Object[]{ new Dimension( 1, true ), new Color( 0x000000, false), new Integer( Font.SIZE_LARGE ), new Dimension( 15, true ), new Dimension( 45, true )}
	);
	keyrowfocusedStyle = new Style (
		"keyrowfocused", 
		Item.LAYOUT_CENTER,
		null,	// no background
		null, 	// no border
		new short[]{ 39, -6},
		new Object[]{ new de.enough.polish.ui.containerviews.HorizontalContainerView(), new Dimension(0, false)}
	);
	keyboardviewsubmitfocusedStyle = new Style (
		"keyboardviewsubmitfocused", 
		Item.LAYOUT_VCENTER,
		null,	// no background
		new de.enough.polish.ui.borders.SimpleBorder( 0x000000, 1),
		new short[]{ 32712, 144, 58, 388},
		new Object[]{ new Dimension( 1, true ), new Dimension( 15, true ), new Dimension( 15, true ), new Integer(Item.LAYOUT_CENTER|Item.LAYOUT_VCENTER)}
	);
	keyboardviewsubmitpressedStyle = new Style (
		"keyboardviewsubmitpressed", 
		Item.LAYOUT_VCENTER,
		null,	// no background
		new de.enough.polish.ui.borders.SimpleBorder( 0x000000, 1),
		new short[]{ 32712, 144, 58, 388},
		new Object[]{ new Dimension( 1, true ), new Dimension( 15, true ), new Dimension( 15, true ), new Integer(Item.LAYOUT_CENTER|Item.LAYOUT_VCENTER)}
	);
	keyboardviewdisplayfocusedStyle = new Style (
		"keyboardviewdisplayfocused", 
		Item.LAYOUT_EXPAND|Item.LAYOUT_VCENTER,
		null,	// no background
		new de.enough.polish.ui.borders.SimpleBorder( 0x000000, 1),
		new short[]{ 32712, 388, 369, 412},
		new Object[]{ new Dimension( 1, true ), new Integer(Item.LAYOUT_LEFT|Item.LAYOUT_VCENTER), new Integer(1), new Integer(1)}
	);
	menuitemfocusedStyle = new Style (
		"menuitemfocused", 
		Item.LAYOUT_LEFT|Item.LAYOUT_EXPAND,
		new de.enough.polish.ui.backgrounds.SimpleBackground( new Color( 0x5a79ef, false)),
		null, 	// no border
		new short[]{ -14, -17, -15, -4, -7, -6},
		new Object[]{ new Integer(Font.STYLE_BOLD), new Color( 0xFFFFFF, false), new Integer( Font.SIZE_MEDIUM ), new Dimension(2, false), new Dimension(5, false), new Dimension(2, false)}
	);
	calendarmonthStyle = new Style (
		"calendarmonth", 
		Item.LAYOUT_EXPAND|Item.LAYOUT_CENTER,
		null,	// no background
		null, 	// no border
		new short[]{ -14, -17, -15, 190, 191},
		new Object[]{ new Integer(Font.STYLE_BOLD), new Color( 0x222222, false), new Integer( Font.SIZE_SMALL ), "/arrow-left.png", "/arrow-right.png"}
	);
	calendarfocusedStyle = new Style (
		"calendarfocused", 
		Item.LAYOUT_EXPAND,
		null,	// no background
		null, 	// no border
		null, null	// no additional attributes have been defined
	);
	browserlinkfocStyle = new Style (
		"browserlinkfoc", 
		Item.LAYOUT_DEFAULT,	// default layout
		null,	// no background
		null, 	// no border
		new short[]{ 209, -10, -8, -14, -17},
		new Object[]{ Style.FALSE, new Dimension(1, false), new Dimension(1, false), new Integer(Font.STYLE_BOLD), new Color( 0xFF0000, false)}
	);
	timefocusedStyle = new Style (
		"timefocused", 
		Item.LAYOUT_CENTER,
		null,	// no background
		null, 	// no border
		new short[]{ -17, -15, 378, 380, 379},
		new Object[]{ new Color( 0x222222, false), new Integer( Font.SIZE_LARGE ), triangleUpBackground, new Dimension(60, false), triangleDownBackground}
	);
	calendardayinactivefocusedStyle = new Style (
		"calendardayinactivefocused", 
		Item.LAYOUT_RIGHT,
		new de.enough.polish.ui.backgrounds.SimpleBackground( new Color( 0x666666, false)),
		null, 	// no border
		new short[]{ -14, -17, -6},
		new Object[]{ new Integer(Font.STYLE_ITALIC), new Color( 0xffffff, false), new Dimension(2, false)}
	);
	calendardayfocusedStyle = new Style (
		"calendardayfocused", 
		Item.LAYOUT_RIGHT,
		new de.enough.polish.ui.backgrounds.SimpleBackground( new Color( 0x222222, false)),
		null, 	// no border
		new short[]{ -14, -17, -6},
		new Object[]{ new Integer(Font.STYLE_ITALIC), new Color( 0xefefef, false), new Dimension(2, false)}
	);
	calendarcurrentdayfocusedStyle = new Style (
		"calendarcurrentdayfocused", 
		Item.LAYOUT_RIGHT,
		new de.enough.polish.ui.backgrounds.SimpleBackground( new Color( 0x11aa11, false)),
		null, 	// no border
		new short[]{ -14, -17, -6},
		new Object[]{ new Integer(Font.STYLE_ITALIC), new Color( 0xffffff, false), new Dimension(2, false)}
	);
	browserinputfocStyle = new Style (
		"browserinputfoc", 
		Item.LAYOUT_EXPAND|Item.LAYOUT_LEFT,
		null,	// no background
		new de.enough.polish.ui.borders.RoundRectBorder( 0x142850,2, 10, 10),
		new short[]{ -6, 209},
		new Object[]{ new Dimension(1, false), Style.FALSE}
	);
	browserchoicegrouppopupfocusedStyle = new Style (
		"browserchoicegrouppopupfocused", 
		Item.LAYOUT_DEFAULT,	// default layout
		new de.enough.polish.ui.backgrounds.SimpleBackground( new Color( 0xC0C0C0, false)),
		null, 	// no border
		new short[]{ 209},
		new Object[]{ Style.FALSE}
	);
	calendardayStyle = new Style (
		"calendarday", 
		Item.LAYOUT_RIGHT,
		null,	// no background
		null, 	// no border
		new short[]{ -6, -14, -17, 1},
		new Object[]{ new Dimension(2, false), new Integer(Font.STYLE_ITALIC), new Color( 0x222222, false), calendardayfocusedStyle}
	);
	browserchoicegrouppopupStyle = new Style (
		"browserchoicegrouppopup", 
		Item.LAYOUT_DEFAULT,	// default layout
		new de.enough.polish.ui.backgrounds.SimpleBackground( new Color( 0xFFFFFF, false)),
		null, 	// no border
		new short[]{ 209, 1},
		new Object[]{ Style.FALSE, browserchoicegrouppopupfocusedStyle}
	);
	timeStyle = new Style (
		"time", 
		Item.LAYOUT_CENTER,
		null,	// no background
		null, 	// no border
		new short[]{ -17, -15, 378, 380, 379, 1},
		new Object[]{ new Color( 0x666666, false), new Integer( Font.SIZE_LARGE ), triangleUpBackground, new Dimension(60, false), triangleDownBackground, timefocusedStyle}
	);
	calendarcurrentdayStyle = new Style (
		"calendarcurrentday", 
		Item.LAYOUT_RIGHT,
		new de.enough.polish.ui.backgrounds.SimpleBackground( new Color( 0x88ff88, false)),
		null, 	// no border
		new short[]{ 1, -6, -14, -17},
		new Object[]{ calendarcurrentdayfocusedStyle, new Dimension(2, false), new Integer(Font.STYLE_ITALIC), new Color( 0x222222, false)}
	);
	calendarStyle = new Style (
		"calendar", 
		Item.LAYOUT_EXPAND,
		null,	// no background
		null, 	// no border
		new short[]{ 3, 1},
		new Object[]{ calendarmonthStyle, calendarfocusedStyle}
	);
	browserinputStyle = new Style (
		"browserinput", 
		Item.LAYOUT_EXPAND|Item.LAYOUT_LEFT,
		null,	// no background
		new de.enough.polish.ui.borders.RoundRectBorder( 0x1e5556,1, 10, 10),
		new short[]{ 209, -6, 1},
		new Object[]{ Style.FALSE, new Dimension(2, false), browserinputfocStyle}
	);
	menuitemStyle = new Style (
		"menuitem", 
		Item.LAYOUT_LEFT,
		null,	// no background
		null, 	// no border
		new short[]{ -14, -17, -15, -4, -7, -6, 1},
		new Object[]{ new Integer(Font.STYLE_BOLD), new Color( 0x425594, false), new Integer( Font.SIZE_MEDIUM ), new Dimension(2, false), new Dimension(5, false), new Dimension(2, false), menuitemfocusedStyle}
	);
	calendardayinactiveStyle = new Style (
		"calendardayinactive", 
		Item.LAYOUT_RIGHT,
		null,	// no background
		null, 	// no border
		new short[]{ -14, -17, 1, -6},
		new Object[]{ new Integer(Font.STYLE_ITALIC), new Color( 0x666666, false), calendardayinactivefocusedStyle, new Dimension(2, false)}
	);
	browserlinkStyle = new Style (
		"browserlink", 
		Item.LAYOUT_DEFAULT,	// default layout
		null,	// no background
		null, 	// no border
		new short[]{ 209, -10, -8, -14, -17, 1},
		new Object[]{ Style.FALSE, new Dimension(1, false), new Dimension(1, false), new Integer(Font.STYLE_BOLD), new Color( 0x0000FF, false), browserlinkfocStyle}
	);
	keyStyle = new Style (
		"key", 
		Item.LAYOUT_CENTER|Item.LAYOUT_VCENTER,
		null,	// no background
		new de.enough.polish.ui.borders.SimpleBorder( 0x000000, 1),
		new short[]{ 32712, -17, -15, 388, 292, 1, 144, 58},
		new Object[]{ new Dimension( 1, true ), new Color( 0x000000, false), new Integer( Font.SIZE_LARGE ), new Integer(Item.LAYOUT_CENTER|Item.LAYOUT_VCENTER), keypressedStyle, keyfocusedStyle, new Dimension( 15, true ), new Dimension( 15, true )}
	);
	keyspaceStyle = new Style (
		"keyspace", 
		Item.LAYOUT_CENTER|Item.LAYOUT_VCENTER,
		null,	// no background
		new de.enough.polish.ui.borders.SimpleBorder( 0x000000, 1),
		new short[]{ 32712, -17, -15, 292, 1, 144, 58},
		new Object[]{ new Dimension( 1, true ), new Color( 0x000000, false), new Integer( Font.SIZE_LARGE ), keyspacepressedStyle, keyspacefocusedStyle, new Dimension( 15, true ), new Dimension( 45, true )}
	);
	keyshiftStyle = new Style (
		"keyshift", 
		Item.LAYOUT_CENTER|Item.LAYOUT_VCENTER,
		null,	// no background
		new de.enough.polish.ui.borders.SimpleBorder( 0x000000, 1),
		new short[]{ 32712, -17, -15, 388, 292, 1, 144, 58},
		new Object[]{ new Dimension( 1, true ), new Color( 0x000000, false), new Integer( Font.SIZE_LARGE ), new Integer(Item.LAYOUT_CENTER|Item.LAYOUT_VCENTER), keypressedStyle, keyfocusedStyle, new Dimension( 15, true ), new Dimension( 15, true )}
	);
	keydeleteStyle = new Style (
		"keydelete", 
		Item.LAYOUT_CENTER|Item.LAYOUT_VCENTER,
		null,	// no background
		new de.enough.polish.ui.borders.SimpleBorder( 0x000000, 1),
		new short[]{ 32712, -17, -15, 388, 292, 1, 144, 58},
		new Object[]{ new Dimension( 1, true ), new Color( 0x000000, false), new Integer( Font.SIZE_LARGE ), new Integer(Item.LAYOUT_CENTER|Item.LAYOUT_VCENTER), keypressedStyle, keyfocusedStyle, new Dimension( 15, true ), new Dimension( 15, true )}
	);
	keyclearStyle = new Style (
		"keyclear", 
		Item.LAYOUT_CENTER|Item.LAYOUT_VCENTER,
		null,	// no background
		new de.enough.polish.ui.borders.SimpleBorder( 0x000000, 1),
		new short[]{ 32712, -17, -15, 388, 292, 1, 144, 58},
		new Object[]{ new Dimension( 1, true ), new Color( 0x000000, false), new Integer( Font.SIZE_LARGE ), new Integer(Item.LAYOUT_CENTER|Item.LAYOUT_VCENTER), keypressedStyle, keyfocusedStyle, new Dimension( 15, true ), new Dimension( 15, true )}
	);
	keyabcStyle = new Style (
		"keyabc", 
		Item.LAYOUT_CENTER|Item.LAYOUT_VCENTER,
		null,	// no background
		new de.enough.polish.ui.borders.SimpleBorder( 0x000000, 1),
		new short[]{ 32712, -17, -15, 388, 292, 1, 144, 58},
		new Object[]{ new Dimension( 1, true ), new Color( 0x000000, false), new Integer( Font.SIZE_LARGE ), new Integer(Item.LAYOUT_CENTER|Item.LAYOUT_VCENTER), keypressedStyle, keyfocusedStyle, new Dimension( 15, true ), new Dimension( 15, true )}
	);
	key123Style = new Style (
		"key123", 
		Item.LAYOUT_CENTER|Item.LAYOUT_VCENTER,
		null,	// no background
		new de.enough.polish.ui.borders.SimpleBorder( 0x000000, 1),
		new short[]{ 32712, -17, -15, 388, 292, 1, 144, 58},
		new Object[]{ new Dimension( 1, true ), new Color( 0x000000, false), new Integer( Font.SIZE_LARGE ), new Integer(Item.LAYOUT_CENTER|Item.LAYOUT_VCENTER), keypressedStyle, keyfocusedStyle, new Dimension( 15, true ), new Dimension( 15, true )}
	);
	keyrowStyle = new Style (
		"keyrow", 
		Item.LAYOUT_CENTER,
		null,	// no background
		null, 	// no border
		new short[]{ 39, -6, 1},
		new Object[]{ new de.enough.polish.ui.containerviews.HorizontalContainerView(), new Dimension(0, false), keyrowfocusedStyle}
	);
	keyboardviewsubmitStyle = new Style (
		"keyboardviewsubmit", 
		Item.LAYOUT_VCENTER,
		null,	// no background
		new de.enough.polish.ui.borders.SimpleBorder( 0x000000, 1),
		new short[]{ 32712, 144, 58, 388, 1, 292},
		new Object[]{ new Dimension( 1, true ), new Dimension( 15, true ), new Dimension( 15, true ), new Integer(Item.LAYOUT_CENTER|Item.LAYOUT_VCENTER), keyboardviewsubmitfocusedStyle, keyboardviewsubmitpressedStyle}
	);
	keyboardviewdisplayStyle = new Style (
		"keyboardviewdisplay", 
		Item.LAYOUT_EXPAND|Item.LAYOUT_VCENTER,
		null,	// no background
		new de.enough.polish.ui.borders.SimpleBorder( 0x000000, 1),
		new short[]{ 32712, 388, 369, 412, 1},
		new Object[]{ new Dimension( 1, true ), new Integer(Item.LAYOUT_LEFT|Item.LAYOUT_VCENTER), new Integer(1), new Integer(1), keyboardviewdisplayfocusedStyle}
	);

	//register referenced and dynamic styles:
	stylesByName.put( "browsertextbolditalic", browsertextbolditalicStyle );
	stylesByName.put( "browsertextbold", browsertextboldStyle );
	stylesByName.put( "calendarweekday", calendarweekdayStyle );
	stylesByName.put( "menubar", menubarStyle );
	stylesByName.put( "browsertextitalic", browsertextitalicStyle );
	stylesByName.put( "title", titleStyle );
	stylesByName.put( "browseroption", browseroptionStyle );
	stylesByName.put( "leftcommand", leftcommandStyle );
	stylesByName.put( "scrollbar", scrollbarStyle );
	stylesByName.put( "browseroptionitem", browseroptionitemStyle );
	stylesByName.put( "browser", browserStyle );
	stylesByName.put( "rssdescriptionalert", rssdescriptionalertStyle );
	stylesByName.put( "browsertext", browsertextStyle );
	stylesByName.put( "menu", menuStyle );
	stylesByName.put( "browserchoicegroupexclusive", browserchoicegroupexclusiveStyle );
	stylesByName.put( "browserradio", browserradioStyle );
	stylesByName.put( "default", defaultStyle );
	stylesByName.put( "rightcommand", rightcommandStyle );
	stylesByName.put( "browsertextcode", browsertextcodeStyle );
	stylesByName.put( "calendarform", calendarformStyle );
	stylesByName.put( "browserchoicegroupmultiple", browserchoicegroupmultipleStyle );
	stylesByName.put( "browsercheckbox", browsercheckboxStyle );
	stylesByName.put( "label", labelStyle );
	stylesByName.put( "keydimension", keydimensionStyle );
	stylesByName.put( "keyspacedimension", keyspacedimensionStyle );
	stylesByName.put( "keyblank", keyblankStyle );
	stylesByName.put( "keyboard", keyboardStyle );
	stylesByName.put( "keyboardview", keyboardviewStyle );
	stylesByName.put( "keyboardviewheader", keyboardviewheaderStyle );
	stylesByName.put( "keypressed", keypressedStyle );
	stylesByName.put( "keyfocused", keyfocusedStyle );
	stylesByName.put( "keyspacepressed", keyspacepressedStyle );
	stylesByName.put( "keyspacefocused", keyspacefocusedStyle );
	stylesByName.put( "keyrowfocused", keyrowfocusedStyle );
	stylesByName.put( "keyboardviewsubmitfocused", keyboardviewsubmitfocusedStyle );
	stylesByName.put( "keyboardviewsubmitpressed", keyboardviewsubmitpressedStyle );
	stylesByName.put( "keyboardviewdisplayfocused", keyboardviewdisplayfocusedStyle );
	stylesByName.put( "menuitemfocused", menuitemfocusedStyle );
	stylesByName.put( "calendarmonth", calendarmonthStyle );
	stylesByName.put( "calendarfocused", calendarfocusedStyle );
	stylesByName.put( "browserlinkfoc", browserlinkfocStyle );
	stylesByName.put( "timefocused", timefocusedStyle );
	stylesByName.put( "calendardayinactivefocused", calendardayinactivefocusedStyle );
	stylesByName.put( "calendardayfocused", calendardayfocusedStyle );
	stylesByName.put( "calendarcurrentdayfocused", calendarcurrentdayfocusedStyle );
	stylesByName.put( "browserinputfoc", browserinputfocStyle );
	stylesByName.put( "browserchoicegrouppopupfocused", browserchoicegrouppopupfocusedStyle );
	stylesByName.put( "calendarday", calendardayStyle );
	stylesByName.put( "browserchoicegrouppopup", browserchoicegrouppopupStyle );
	stylesByName.put( "time", timeStyle );
	stylesByName.put( "calendarcurrentday", calendarcurrentdayStyle );
	stylesByName.put( "calendar", calendarStyle );
	stylesByName.put( "browserinput", browserinputStyle );
	stylesByName.put( "menuitem", menuitemStyle );
	stylesByName.put( "calendardayinactive", calendardayinactiveStyle );
	stylesByName.put( "browserlink", browserlinkStyle );
	stylesByName.put( "key", keyStyle );
	stylesByName.put( "keyspace", keyspaceStyle );
	stylesByName.put( "keyshift", keyshiftStyle );
	stylesByName.put( "keydelete", keydeleteStyle );
	stylesByName.put( "keyclear", keyclearStyle );
	stylesByName.put( "keyabc", keyabcStyle );
	stylesByName.put( "key123", key123Style );
	stylesByName.put( "keyrow", keyrowStyle );
	stylesByName.put( "keyboardviewsubmit", keyboardviewsubmitStyle );
	stylesByName.put( "keyboardviewdisplay", keyboardviewdisplayStyle );
	stylesByName.put( "keydimension", keydimensionStyle );
	stylesByName.put( "keyspacedimension", keyspacedimensionStyle );
	stylesByName.put( "key", keyStyle );
	stylesByName.put( "keyspace", keyspaceStyle );
	stylesByName.put( "keyshift", keyshiftStyle );
	stylesByName.put( "keydelete", keydeleteStyle );
	stylesByName.put( "keyclear", keyclearStyle );
	stylesByName.put( "keyabc", keyabcStyle );
	stylesByName.put( "key123", key123Style );
	stylesByName.put( "keyblank", keyblankStyle );
	stylesByName.put( "keyrow", keyrowStyle );
	stylesByName.put( "keyboard", keyboardStyle );
	stylesByName.put( "keyboardview", keyboardviewStyle );
	stylesByName.put( "keyboardviewheader", keyboardviewheaderStyle );
	stylesByName.put( "keyboardviewsubmit", keyboardviewsubmitStyle );
	stylesByName.put( "keyboardviewdisplay", keyboardviewdisplayStyle );
}
	public static Style focusedStyle = new Style("focused", 0, new de.enough.polish.ui.backgrounds.SimpleBackground(0), null, new short[]{-17}, new Object[]{new Color(0xffffff, false)} );	// the focused-style is not defined.
	/** Access to the currently shown J2ME Polish screen, if any */
	public static Screen currentScreen;	
	/** Access to the application's Display */
	public static Display display;
	/** Access to the currently running MIDlet */
	public static MIDlet midlet;
	/** Access to the AnimationThread responsible for animating all user interface components */
	public static AnimationThread animationThread;
	/** default OK command */
	//#ifdef polish.i18n.useDynamicTranslations
		//# public static Command OK_CMD = new Command( "OK", Command.OK, 2 );
	//#elifdef polish.command.ok:defined
public static final Command OK_CMD = new Command("OK", Command.OK, 2 );
	//#else
		//# public static final Command OK_CMD = new Command("OK", Command.OK, 2 );
	//#endif
	/** default CANCEL command */
	//#ifdef polish.i18n.useDynamicTranslations
		//# public static Command CANCEL_CMD = new Command("Cancel", Command.CANCEL, 2 );
	//#elifdef polish.command.cancel:defined
public static final Command CANCEL_CMD = new Command("Cancel", Command.CANCEL, 3 );
	//#else
		//# public static final Command CANCEL_CMD = new Command("Cancel", Command.CANCEL, 3 );
	//#endif

	/**
	 * Retrieves the image with the given name.
	 * When the image has been cached before, it will be returned immediately.
	 * When it has not been cached before, it either will be loaded directly
	 * or in a background thread. This behaviour is set in the 
	 * <a href="../../../../definitions/polish_xml.html">polish.xml</a> file.
	 * 
	 * @param url the URL of the Image, e.g. "/background.png"
	 * @param parent the object which needs the image, when the image should be loaded
	 * 		   		in the background, the parent need to implement
	 * 				the ImageConsumer interface when it wants to be notified when
	 * 				the picture has been loaded.
	 * @param cache true when the image should be cached for later retrieval.
	 *              This costs RAM obviously, so you should decide carefully if
	 *              large images should be cached.
	 * @return the image when it either was cached or is loaded directly.
 	 *              When the should be loaded in the background, it will be later
	 *              set via the ImageConsumer.setImage()-method.
	 * @throws IOException when the image could not be loaded directly
	 * @see ImageConsumer#setImage(String, Image)
	 */
	public static Image getImage( String url, Object parent, boolean cache )
	throws IOException 
	{
		// check if the image has been cached before:
		//#if polish.allowImageCaching != false
			if ( imagesByName != null ) {
				Image image = (Image) imagesByName.get( url );
				if (image != null) {
					return image;
				}
			}
		//#endif
		//#if ! polish.images.backgroundLoad
			// when images should be loaded directly, try to do so now:
			//#ifdef polish.classes.ImageLoader:defined
				//#= Image image = ${ classname( polish.classes.ImageLoader ) }.loadImage( url );
			//#else
				Image image = null; 
				//#if polish.i18n.loadResources
					//# try {
				//#endif
						image = Image.createImage( url );
				//#if polish.i18n.loadResources
					//# } catch (IOException e) {
						//# if (Locale.LANGUAGE == null || Locale.LANGUAGE.length() == 0) {
							//# throw e;
						//# }
						//# String localeUrl = "/" + Locale.LANGUAGE + url;
						//# image = Image.createImage( localeUrl );
					//# }
				//#endif
			//#endif
			//#if polish.allowImageCaching != false
				if (cache) {
					if (imagesByName == null ) {
						imagesByName = new Hashtable();
					}
					imagesByName.put( url, image );
				}
			//#endif
			return image;
		//#else
			//# // when images should be loaded in the background, 
			//# // tell the background-thread to do so now:		
			//# if ( ! (parent instanceof ImageConsumer)) {
				//#debug error
				//# System.out.println("StyleSheet.getImage(..) needs an ImageConsumer when images are loaded in the background!");
				//# return null;
			//# }
			//# if (scheduledImagesByName == null ) {
				//# scheduledImagesByName = new Hashtable();
			//# }
			//# ImageQueue queue = (ImageQueue) scheduledImagesByName.get(url);
			//# if (queue != null) {
				//# // this image is already scheduled to load:
				//# queue.addConsumer((ImageConsumer) parent);
				//# return null;
			//# }
			//# scheduledImagesByName.put( url, new ImageQueue( (ImageConsumer) parent, cache ) );
			//# if (imagesByName == null ) {
				//# imagesByName = new Hashtable();
			//# }
			//# if (timer == null) {
				//# timer = new Timer();
			//# }
			//# ImageTask task = new ImageTask( url );
			//# timer.schedule( task, 10 );
			//# return null;
		//#endif
	}
	
	//#ifdef polish.images.backgroundLoad
	//# /**
	 //# * Notifies the GUI items which requested images about the successful loading of thoses images.
	 //# * 
	 //# * @param name the URL of the image
	 //# * @param image the image 
	 //# */
	//# public static void notifyImageConsumers( String name, Image image ) {
		//# ImageQueue queue = (ImageQueue) scheduledImagesByName.remove(name);
		//# if (queue != null) {
			//# if (queue.cache) {
				//# imagesByName.put( name, image );
			//# }
			//# queue.notifyConsumers(name, image);
			//# if (true) {
				//# return;
			//# }
			//# if (currentScreen != null) {
				//# currentScreen.repaint();
			//# }
		//# }
	//# }
	//#endif
	
	/**
	 * Gets the style with the specified name.
	 * 
	 * @param name the name of the style
	 * @return the specified style or null when no style with the given 
	 * 	       name has been defined.
	 */
	public static Style getStyle( String name ) {
		Style style =  (Style) stylesByName.get( name );
		if (style == null) {
			style =  (Style) stylesByName.get( name.toLowerCase() );
		}
		return style;
	}
	
	/**
	 * Retrieves all registered styles in a Hashtable.
	 * 
	 * @return all registered styles in a Hashtable.
	 */
	public static Hashtable getStyles()
	{
		return stylesByName;
	}
	
	//#ifdef polish.useDynamicStyles
	/**
	 * Retrieves the style for the given item.
	 * This function is only available when the &lt;buildSetting&gt;-attribute
	 * [useDynamicStyles] is enabled.
	 * This function allows to set styles without actually using the preprocessing-
	 * directive //#style. Beware that this dynamic style retrieval is not as performant
	 * as the direct-style-setting with the //#style preprocessing directive.
	 *  
	 * @param item the item for which the style should be retrieved
	 * @return the appropriate style. When no specific style is found,
	 *         the default style is returned.
	 */
	public static Style getStyle( Item item ) {
		if (item.screen == null) {
			//#debug info
			//# System.out.println("unable to retrieve style for item [" + item.getClass().getName() + "] without screen.");
			return defaultStyle;
		}
		String itemCssSelector = item.cssSelector;
		String screenCssSelector = item.screen.cssSelector;
		Style style = null;
		String fullStyleName;
		StringBuffer buffer = new StringBuffer();
		buffer.append( screenCssSelector );
		if (item.parent == null) {
			//#debug
			//# System.out.println("item.parent == null");
			buffer.append('>').append( itemCssSelector );
			fullStyleName = buffer.toString();
			style = (Style) stylesByName.get( fullStyleName );
			if (style != null) {
				return style;
			}
			style = (Style) stylesByName.get( screenCssSelector + " " + itemCssSelector );
		} else if (item.parent.parent == null) {
			//#debug
			//# System.out.println("Item has one parent.");
			// this item is propably in a form or list,
			// typical hierarchy is for example "form>container>p"                                                 
			Item parent = item.parent;
			String parentCssSelector = parent.cssSelector;
			if (parentCssSelector == null) {
				parentCssSelector = parent.createCssSelector();
			}
			//#debug
			//# System.out.println( parent.getClass().getName() + "-css-selector: " + parentCssSelector );
			buffer.append('>').append( parentCssSelector )
				  .append('>').append( itemCssSelector );
			fullStyleName = buffer.toString();
			//#debug
			//# System.out.println("trying " + fullStyleName);
			style = (Style) stylesByName.get( fullStyleName );
			if (style != null) {
				return style;
			}
			// 1. try: "screen item":
			String styleName = screenCssSelector + " " + itemCssSelector;
			//#debug
			//# System.out.println("trying " + styleName);
			style = (Style) stylesByName.get( styleName );
			if (style == null) {
				// 2. try: "screen*item":
				styleName = screenCssSelector + "*" + itemCssSelector;
				//#debug
				//# System.out.println("trying " + styleName);
				style = (Style) stylesByName.get( styleName );
				if (style == null) {
					// 3. try: "parent>item"
					styleName = parentCssSelector + ">" + itemCssSelector;
					//#debug
					//# System.out.println("trying " + styleName);
					style = (Style) stylesByName.get( styleName );
					if (style == null) {
						// 4. try: "parent item"
						styleName = parentCssSelector + " " + itemCssSelector;
						//#debug
						//# System.out.println("trying " + styleName);
						style = (Style) stylesByName.get( styleName );
					}
				}
			}
			//#debug
			//# System.out.println("found style: " + (style != null));
		} else {
			//#debug
			//# System.out.println("so far unable to set style: complex item setup");
			// this is a tiny bit more complicated....
			fullStyleName = null;
		}
		if (style == null) {
			// try just the item:
			//#debug
			//# System.out.println("trying " + itemCssSelector);
			if (itemCssSelector != null) {
				style = (Style) stylesByName.get( itemCssSelector );
			}
			if (style == null) {
				//#debug
				//# System.out.println("Using default style for item " + item.getClass().getName() );
				style = defaultStyle;
			}
			//#ifdef polish.debug.debug
				//# else {
					//#debug
					//# System.out.println("Found style " + itemCssSelector );
				//# }
			//#endif
		}
		if ( fullStyleName != null && style != null ) {
			stylesByName.put( fullStyleName, style );
		}
		return style;
	}
	//#endif

	//#ifdef polish.useDynamicStyles
	/**
	 * Retrieves a dynamic style for the given screen.
	 * 
	 * @param screen the screen for which a style should be retrieved
	 * @return the style for the given screen.
	 */
	public static Style getStyle(Screen screen) {
		Style style = (Style) stylesByName.get( screen.cssSelector );
		if (style == null) {
			return defaultStyle;
		}
		return style;
	}		
	//#endif
	
	//#if !polish.css.mediaquery
	/**
	 * placeholder for showNotify method which is added when using media queries
	 */
	public static void showNotify() {
		// placeholder for showNotify method which is added when using media queries
		
	}
	//#endif
	
	//#if polish.css.mediaquery
		//# /**
		 //# * Adds a media query to this set of styles if the condition is fullfilled.
		 //# * This method is only accessible when the preprocesing symbol <code>polish.css.mediaquery</code> is true.
		 //# * 
		 //# * @param condition the condition, compare http://www.w3.org/TR/css3-mediaqueries/
		 //# * @param styles the styles that should be modified by this condition
		 //# */
		//# public static void addMediaQuery( String condition, Style[] styles ) {
			//# if (isMediaQueryFulfilled(condition)) {
				//# for (int i = 0; i < styles.length; i++) {
					//# Style style = styles[i];
					//# Style parent = getStyle( style.name );
					//# if (parent != null) {
						//# if (style.background != null) {
							//# parent.background = style.background;
						//# }
						//# if (style.background != null) {
							//# parent.border = style.border;
						//# }
						//# short[] keys = style.getRawAttributeKeys();
						//# if (keys != null) {
							//# Object[] values = style.getRawAttributeValues();
							//# for (int j = 0; j < values.length; j++) {
								//# parent.addAttribute(keys[j], values[j]);
							//# }
						//# }
					//# }
				//# }
			//# }
		//# }
	//#endif
		
	//#if polish.css.mediaquery
		//# private static boolean isMediaQueryFulfilled(String condition) {
			//# String[] conditions = TextUtil.split(condition, ',');
			//# StringBuffer buffer = new StringBuffer();
			//# for (int i = 0; i < conditions.length; i++) {
				//# condition = conditions[i];
				//# try {
					//# if (isMediaQueryConditionFulfilled(condition, buffer)) {
						//# return true;
					//# }
				//# } catch (Exception e) {
					//#debug error
					//# System.out.println("Unable to parse media query condition " + condition + e);
				//# }
			//# }
			//# return false;
		//# }
		//# private static boolean isMediaQueryConditionFulfilled(String condition, StringBuffer buffer) {
			//# buffer.delete(0, buffer.length());
			//# int screenWidth = Display.getScreenWidth();
			//# int screenHeight = Display.getScreenHeight();
//# 			
			//# char c;
			//# boolean notFound = false;
			//# boolean isFirstPart = true;
			//# String part;
			//# boolean result = true;
			//# boolean isMinFound = false;
			//# boolean isMaxFound = false;
			//# String lastFeature = null;
			//# boolean isExpectingFeatureName = false;
			//# boolean isExpectingValue = false;
			//# int length = condition.length()-1;
			//# for (int i=0; i<=length;i++) {
				//# c = condition.charAt(i);
				//# if (c != ' ' && c != ')') {
					//# if (c == '(') {
						//# isExpectingFeatureName = true;
					//# } else if (c == ':') {
						//# isExpectingValue = true;
					//# } else {
						//# buffer.append(c);
					//# }
					//# if (i<length) {
						//# continue;
					//# }
				//# }
				//# if (buffer.length() > 0) {
					//# // part ending here:
					//# part = buffer.toString().toLowerCase();
					//# //System.out.println("part=" + part + ", expectingFeatureName=" + isExpectingFeatureName + ", isExpectingValue=" + isExpectingValue);
					//# buffer.delete(0, buffer.length());
					//# if (isFirstPart && "only".equals(part)) {
						//# continue;
					//# }
					//# if (isFirstPart && "not".equals(part)) {
						//# notFound = true;
						//# continue;
					//# }
					//# if (isFirstPart) {
						//# isFirstPart = false;
						//# if (!isExpectingFeatureName) {
							//# if ("touchscreen".equals(part)) {
								//# result = DeviceInfo.hasPointerEvents();
								//# System.out.println("found touchscreen, result=" + result);
							//# } else if (!("all".equals(part) || "screen".equals(part))) {
								//# // this is a unsupported media type (otherwise a parentheses would have been necessary):
								//# result = false;
							//# }
							//# continue;
						//# }
					//# }
					//# if ("and".equals(part)) {
						//# // now we (should) have this information for evaluating the last term:
						//# isExpectingValue = false;
						//# isMinFound = false;
						//# isMaxFound = false;
						//# continue;
					//# }
					//# if (isExpectingFeatureName) {
						//# // either this is an unknown media type or this is a feature that needs to be supported:
						//# if (part.startsWith("min-")) {
							//# isMinFound = true;
							//# part = part.substring(4);//"min-".length());
						//# } else if (part.startsWith("max-")) {
							//# isMaxFound = true;
							//# part = part.substring(4);//"max-".length());
						//# }
						//# lastFeature = part;
						//# isExpectingFeatureName = false;
						//# if (!isExpectingValue) {
							//# // this is a feature without value:
							//# if ("color".equals(lastFeature)) {
								//# result = (Display.getInstance().numColors() > 2);
							//# } else {
								//# result = false;
							//# }
						//# }
					//# } else if (isExpectingValue){
						//# int pixelValue = -1;
						//# if (part.endsWith("px")) {
							//# pixelValue = Integer.parseInt(part.substring(0, part.length() - 2));
						//# }
						//# if ("width".equals(lastFeature) || "device-width".equals(lastFeature)) {
							//# if (isMinFound) {
								//# result = (screenWidth >= pixelValue);
							//# } else if (isMaxFound) {
								//# result = (screenWidth <= pixelValue);
							//# } else {
								//# result = (screenWidth == pixelValue);
							//# }
						//# } else if ("height".equals(lastFeature) || "device-height".equals(lastFeature)) {
							//# //TODO when using height we should use the viewport height which is less than the screenheight in fullscreen mode...
							//# if (isMinFound) {
								//# result = (screenHeight >= pixelValue);
							//# } else if (isMaxFound) {
								//# result = (screenHeight <= pixelValue);
							//# } else {
								//# result = (screenHeight == pixelValue);
							//# }
						//# } else if ("orientation".equals(lastFeature)) {
							//# if ("landscape".equals(part)) {
								//# result = screenWidth > screenHeight;
							//# } else if ("portrait".equals(part)) {
								//# result = screenHeight >= screenWidth;
							//# }
						//# } else if ("aspect-ratio".equals(lastFeature) || "device-aspect-ratio".equals(lastFeature)) {
							//# int splitPos = part.indexOf('/');
							//# if (splitPos == -1) {
								//# result = false;
							//# } else {
								//# int w = Integer.parseInt(part.substring(0, splitPos));
								//# int h = Integer.parseInt(part.substring(splitPos+1));
								//# int given = (screenWidth << 8) / screenHeight;
								//# int expected = (w << 8) / h;
								//# if (isMinFound) {
									//# result = (given >= expected);
								//# } else if (isMaxFound) {
									//# result = (given <= expected);
								//# } else {
									//# result = (given == expected);
								//# }
							//# }
						//# } else if ("color".equals(lastFeature)) {
							//# int given = Display.getInstance().numColors();
							//# int expected = 2 ^ Integer.parseInt(part);
							//# if (isMinFound) {
								//# result = (given >= expected);
							//# } else if (isMaxFound) {
								//# result = (given <= expected);
							//# } else {
								//# result = (given == expected);
							//# }
						//# } else if ("vendor".equals(lastFeature)){
							//# String given = DeviceInfo.getVendorName();
							//# if (given == null) {
								//# given = "unknown";
							//# } else {
								//# given = given.toLowerCase();
							//# }
							//# result = (part.equals(given));
						//# }
					//#if polish.debug.debug
					//# } else {
						//#debug
						//# System.out.println("Warning: encountered invalid state in media query at term " + part);
					//#endif
					//# }
					//# if (!result) {
						//# break;
					//# }
				//# } // found space
			//# } // for each character
			//# if (notFound) {
				//# result = !result;
			//# }
			//# return result;
		//# }
	//#endif
	
	/**
	 * Releases all (memory intensive) resources such as images or RGB arrays of this style sheet.
	 */
	public static void releaseResources() {
		//#if polish.allowImageCaching != false
		if (imagesByName != null) {
			imagesByName.clear();
		}
		//#endif
		//#ifdef polish.useDynamicStyles
			Enumeration enumeration = stylesByName.elements();
			while (enumeration.hasMoreElements()) {
				Style style = (Style) enumeration.nextElement();
				style.releaseResources();
			}
		//#endif
		//#ifdef polish.StyleSheet.releaseResources:defined
			//#include ${polish.StyleSheet.releaseResources}
		//#endif
	}


	public static Style[] getDynamicStyles() {
		//#if polish.inSkinEditor == true
			//# return (Style[]) dynamicStylesList.toArray( new Style[ dynamicStylesList.size() ] );
			//# }
		//#else
//			java.util.Enumeration enumeration = dynamicStylesByName.elements();
//			Style[] styles = new Style[ dynamicStylesByName.size() ];
//			for (int i=0; i<styles.length; i++) {
//				styles[i] = (Style) enumeration.nextElement();
//			}
//			return styles;
			return new Style[]{ defaultStyle, focusedStyle };
		//#endif
	}


	
	
//#ifdef polish.StyleSheet.additionalMethods:defined
	//#include ${polish.StyleSheet.additionalMethods}
//#endif

}
