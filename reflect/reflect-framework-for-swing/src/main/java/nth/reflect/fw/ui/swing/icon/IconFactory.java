package nth.reflect.fw.ui.swing.icon;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class IconFactory {

	public static ImageIcon create(URL iconURL) {
		if (iconURL == null) {
			return null;
		}
			return new ImageIcon(iconURL);
	}

	public static ImageIcon create(URL iconUrl, int maxSize) {
		ImageIcon icon = create(iconUrl);
		if (icon != null) {
			BufferedImage bi = new BufferedImage(maxSize, maxSize, BufferedImage.TRANSLUCENT);
			Graphics2D g2d = (Graphics2D) bi.createGraphics();
			g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
			double ratio = ((double) icon.getIconWidth()/icon.getIconHeight());
			int width;
			int height;
			int x;
			int y;
			if (ratio<1) {
				width = (int) (maxSize*ratio);
				height=maxSize;
				x=(maxSize-width)/2;
				y=0;		
			} else {
				width = maxSize;
				height=(int) (maxSize/ratio);
				x=0;
				y=(maxSize-height)/2;
			}
			g2d.drawImage(icon.getImage(), x, y, width, height , null);
			g2d.dispose();
			icon.setImage(bi);
		}
		return icon;
	}

	public static Icon create(String iconUrl) {
		if (iconUrl == null) {
			return null;
		}
		try {
			URL url = new URL(iconUrl);
			return create(url);
		} catch (MalformedURLException e) {
			return null;
		}
	}

	public static Icon create(String iconUri, int maxSize) {
		if (iconUri == null) {
			return null;
		}
		try {
			URL url= new URL(iconUri);
			return create(url, maxSize);
		} catch (MalformedURLException e) {
			return null;
		}
	}

}
