package tool;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageHandler {
	
	public static String GetLng(String path) throws IOException{
	    File f = new File(path);
	    BufferedImage image = ImageIO.read(f);
        String Lng;
        int p = image.getRGB(0, 0);
        Integer r = (p >> 16) & 0xff;
        Integer g = (p >> 8) & 0xff;
        Integer b = p & 0xff;
        if (g == 255 && b == 255) {
            Lng = "-" + r.toString();
        } else {
            Lng = r.toString();
        }
        p = image.getRGB(1, 0);
        r = (p >> 16) & 0xff;
        g = (p >> 8) & 0xff;
        b = p & 0xff;
        Lng = Lng + "." + (r>=10?r.toString():"0"+r.toString()) + (g>=10?g.toString():"0"+g.toString()) + (b>=10?b.toString():"0"+b.toString());
        return Lng;
    }
	
	public static String GetLat(String path) throws IOException{
	    File f = new File(path);
	    BufferedImage image = ImageIO.read(f);
        int height = image.getHeight();
        String Lat;
        Integer p = image.getRGB(0, height-1);
        Integer r = (p >> 16) & 0xff;
        Integer g = (p >> 8) & 0xff;
        Integer b = p & 0xff;
        if (g == 255 && b == 255) {
        	Lat = "-" + r.toString();
        } else {
            Lat = r.toString();
        }
        p = image.getRGB(1, height - 1);
        r = (p >> 16) & 0xff;
        g = (p >> 8) & 0xff;
        b = p & 0xff;
        Lat = Lat + "." + (r>=10?r.toString():"0"+r.toString()) + (g>=10?g.toString():"0"+g.toString()) + (b>=10?b.toString():"0"+b.toString());
        return Lat;
    }
    
}
