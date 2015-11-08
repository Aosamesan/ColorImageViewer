package pixels;

import java.awt.*;

public class HSIPixel extends Pixel{
    static float saturationRatio;
    static float hueRatio;

    byte hue;
    byte saturation;
    byte intensity;

    public static void setRatio(float satRatio, float hueRatio){
        HSIPixel.saturationRatio = satRatio;
        HSIPixel.hueRatio = hueRatio;
    }

    @Override
    public Color getPixelColor() {
        float intesnity = (float)((short)this.intensity | 0xFF);
        float hue = this.hue * hueRatio;
        float saturation = this.saturation * saturationRatio;

        return null;
    }

    public static int getPixelSize() {
        return 3;
    }

    @Override
    public void setPixel(byte... bytes) {
        if(bytes.length == getPixelSize()){
            hue = bytes[0];
            saturation = bytes[1];
            intensity = bytes[2];
        }
    }

    public static Pixel createInstance(){
        return new HSIPixel();
    }
}
