//#condition polish.usePolishGui
/*
 * Created on March 10, 2009 at 8:46:44 AM.
 * 
 * Copyright (c) 2009 Robert Virkus / Enough Software
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
package de.enough.polish.ui.backgrounds;

import javax.microedition.lcdui.Graphics;

import de.enough.polish.ui.Background;
import de.enough.polish.ui.ClippingRegion;
import de.enough.polish.ui.Dimension;
import de.enough.polish.ui.Item;
import de.enough.polish.ui.Screen;
import de.enough.polish.ui.Style;

/**
 * <p>Provides a background consisting of several other backgrounds.</p>
 * <p>Usage example:
 * <pre>
backgrounds {
	contentTop {
		type: partial-gradient;
		start: 0%;
		end: 10%;
		top-color: #ccc;
		bottom-color: #fff;
	}
	contentBottom {
		type: partial-gradient;
		start: 90%;
		end: 100%;
		top-color: #fff;
		bottom-color: #ccc;		
	}
	contentBg {
		type: image;
		image: url(logo.png);
		anchor: hcenter | vcenter;
		color: #fff;
	}
}

.myScreen {
	padding: 2%;
	<b>background {
		type: layer;
		layers: contentTop, contentBottom, contentBg;
	}</b>
}
 * </pre>
 * </p>
 *
 * <p>Copyright Enough Software 2009</p>
 * @author Robert Virkus, j2mepolish@enough.de
 */
public class LayerBackground extends Background
{

	private final Background[] layers;
	protected final Dimension[] margins;

	/**
	 * Creates a new layer background.
	 * 
	 * @param layers the nested backgrounds 
	 */
	public LayerBackground( Background[] layers )
	{
		this( layers, null );
	}
	
	/**
	 * Creates a new layer background.
	 * 
	 * @param layers the nested backgrounds 
	 */
	public LayerBackground( Background[] layers, Dimension[] margins )
	{
		this.layers = layers;
		this.margins = margins;
	}


	/* (non-Javadoc)
	 * @see de.enough.polish.ui.Background#paint(int, int, int, int, javax.microedition.lcdui.Graphics)
	 */
	public void paint(int x, int y, int width, int height, Graphics g)
	{
		int m = 0;
		Dimension[] mrgs = this.margins;
		Background[] bgs = this.layers;
		for (int i = bgs.length; --i >= 0; )
		{
			Background background = bgs[i];
			if (mrgs != null) {
				m = mrgs[i].getValue(width);
			}
			background.paint( x + m, y + m, width - (m * 2), height - (m * 2), g );
		}
	}


	/* (non-Javadoc)
	 * @see de.enough.polish.ui.Background#animate(de.enough.polish.ui.Screen, de.enough.polish.ui.Item, long, de.enough.polish.ui.ClippingRegion)
	 */
	public void animate(Screen screen, Item parent, long currentTime,
			ClippingRegion repaintRegion)
	{
		Background[] bgs = this.layers;
		for (int i = bgs.length; --i >= 0; )
		{
			Background background = bgs[i];
			background.animate(screen, parent, currentTime, repaintRegion);
		}
	}
	

	/* (non-Javadoc)
	 * @see de.enough.polish.ui.Background#showNotify()
	 */
	public void showNotify()
	{
		Background[] bgs = this.layers;
		for (int i = bgs.length; --i >= 0; )
		{
			Background background = bgs[i];
			background.showNotify();
		}
	}

	/* (non-Javadoc)
	 * @see de.enough.polish.ui.Background#hideNotify()
	 */
	public void hideNotify()
	{
		Background[] bgs = this.layers;
		for (int i = bgs.length; --i >= 0; )
		{
			Background background = bgs[i];
			background.hideNotify();
		}
	}

	/* (non-Javadoc)
	 * @see de.enough.polish.ui.Background#releaseResources()
	 */
	public void releaseResources()
	{
		Background[] bgs = this.layers;
		for (int i = bgs.length; --i >= 0; )
		{
			Background background = bgs[i];
			background.releaseResources();
		}
	}

	//#if polish.css.animations
	//# /* (non-Javadoc)
	 //# * @see de.enough.polish.ui.Background#setStyle(de.enough.polish.ui.Style)
	 //# */
	//# public void setStyle(Style style)
	//# {
		//# Background[] bgs = this.layers;
		//# for (int i = bgs.length; --i >= 0; )
		//# {
			//# Background background = bgs[i];
			//# background.setStyle(style);
		//# }
	//# }
	//#endif
	
}
