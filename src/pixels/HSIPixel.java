package pixels;

import java.awt.*;

public class HSIPixel extends Pixel{
    static double saturationRatio;
    static double hueRatio;

    byte hue;
    byte saturation;
    byte intensity;

    public static void setRatio(double satRatio, double hueRatio){
        HSIPixel.saturationRatio = satRatio;
        HSIPixel.hueRatio = hueRatio;
    }

    @Override
    public Color getPixelColor() {
        int intensity = this.intensity & 0xFF;
        double hue = (this.hue & 0xFF) * hueRatio;
        double saturation = (this.saturation & 0xFF) * saturationRatio;
        double normalHue = hue <= 2 * Math.PI / 3 ? hue : hue <= 4 * Math.PI / 3 ? hue - 2 * Math.PI / 3 : hue - 4 * Math.PI / 3;
        double comp = intensity * (1 + saturation * Math.cos(normalHue) / Math.cos(Math.PI / 3 - normalHue));
        double simp = intensity * (1 - saturation);
        int red, green, blue;

        if(hue <= 2 * Math.PI / 3){
            red = (int)comp;
            blue = (int)simp;
            green = 3 * intensity - red - blue;
        } else if(hue <= 4 * Math.PI / 3){
            red = (int)simp;
            green = (int)comp;
            blue = 3 * intensity - red - green;
        } else {
            green = (int)simp;
            blue = (int)comp;
            red = 3 * intensity - green - blue;
        }

        red = red < 0 ? 0 : red > 255 ? 255 : red;
        blue = blue < 0 ? 0 : blue > 255 ? 255 : blue;
        green = green < 0 ? 0 : green > 255 ? 255 : green;

        return new Color(red, green, blue);
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
