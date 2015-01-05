package nth.introspect.ui.swing.icon;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import nth.introspect.Introspect;
import nth.introspect.provider.path.PathProvider;
import nth.introspect.ui.images.IntrospectImagePathIdentifier;

public class IconFactory {

	public static ImageIcon create(URI iconURI) {
		if (iconURI == null) {
			return null;
		}
		try {
			URL iconURL = iconURI.toURL();
			return new ImageIcon(iconURL);
		} catch (MalformedURLException e) {
			return null;
		}
	}

	public static ImageIcon create(URI iconUri, int maxSize) {
		ImageIcon icon = create(iconUri);
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

	public static Icon create(PathProvider pathProvider, IntrospectImagePathIdentifier identifier) {
		if (identifier == null) {
			return null;
		}
		URI iconURI = pathProvider.getImagePath(identifier);
		return create(iconURI);
	}

	public static Icon create(PathProvider pathProvider, IntrospectImagePathIdentifier identifier, int maxSize) {
		if (identifier == null) {
			return null;
		}
		URI iconURI = pathProvider.getImagePath(identifier);
		return create(iconURI, maxSize);
	}

}
