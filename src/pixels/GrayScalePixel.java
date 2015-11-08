package pixels;

import java.awt.*;

public class GrayScalePixel extends Pixel{
    byte intensity;

    public Color getPixelColor(){
        return new Color(0xFF & intensity, 0xFF & intensity, 0xFF & intensity);
    }

    public static int getPixelSize() {
        return 1;
    }

    @Override
    public void setPixel(byte... bytes) {
        if(bytes.length == getPixelSize()){
            intensity = bytes[0];
        }
    }

    public static Pixel createInstance(){
        return new GrayScalePixel();
    }
}
