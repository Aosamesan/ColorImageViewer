package pixels;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;

public abstract class Pixel {

    public Pixel(){}

    public abstract Color getPixelColor();
    public static int getPixelSize(){
        throw new NotImplementedException();
    }
    public abstract void setPixel(byte... bytes);
    public static Pixel createInstance(){
        throw new NotImplementedException();
    }
}
