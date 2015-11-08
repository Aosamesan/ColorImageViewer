package pixels;

import java.awt.*;

public class RGBPixel extends Pixel {
    byte r;
    byte g;
    byte b;

    @Override
    public Color getPixelColor() {
        return new Color(0x00FF & r, 0x00FF & g, 0x00FF & b);
    }

    public static int getPixelSize() {
        return 3;
    }

    @Override
    public void setPixel(byte... bytes) {
        if(bytes.length == getPixelSize()){
            r = bytes[0];
            g = bytes[1];
            b = bytes[2];
        }
    }

    public static Pixel createInstance(){
        return new RGBPixel();
    }
}
