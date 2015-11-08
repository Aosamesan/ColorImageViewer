package image;

import pixels.Pixel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Image {
    private Pixel[] pixels;
    private Class<Pixel> pixelType;
    private long imageSize;
    private long fileSize;
    private int pixelSize;
    private int imageWidth;
    private int imageHeight;
    private boolean isOpen;

    public Image(Class<Pixel> type) throws Exception{
        pixelType = type;
        pixelSize = (int)type.getMethod("getPixelSize").invoke(null);
        dispose();
    }

    public void dispose(){
        imageSize = fileSize = imageWidth = imageHeight = 0;
        pixels = null;
        isOpen = false;
    }

    public long getImageSize(){
        return imageSize;
    }

    public long getFileSize(){
        return fileSize;
    }

    public int getImageWidth(){
        return imageWidth;
    }

    public int getImageHeight(){
        return imageHeight;
    }

    public void setImageWidth(int width){
        if(width <= 0)
            return;
        imageWidth = width;
        imageHeight = (int)(imageSize / imageWidth);
    }

    public int getPixelSize(){
        return pixelSize;
    }

    public final Pixel[] getPixels(){
        return pixels;
    }

    public boolean isOpened(){
        return isOpen;
    }

    public boolean openImage(String filepath, int width){
        byte[] buffer = new byte[pixelSize];
        try {
            File f = new File(filepath);
            if(!f.exists())
                return isOpen;
            fileSize = f.length();
            if(fileSize == 0)
                return isOpen;
            imageSize = fileSize / pixelSize;
            imageWidth = width;
            imageHeight = (int)imageSize / imageWidth;
            FileInputStream fs = new FileInputStream(f);
            Pixel p;
            int read;
            pixels = new Pixel[imageHeight * imageWidth];
            for(int i = 0; i < imageHeight; i++){
                for(int j = 0; j < imageWidth; j++) {
                    read = fs.read(buffer, 0, pixelSize);
                    if(read == -1)
                        break;
                    p = (Pixel) pixelType.getMethod("createInstance").invoke(null);
                    p.setPixel(buffer);
                    pixels[i * imageWidth + j] = p;
                }
            }

            fs.close();
            isOpen = true;
        } catch(Exception e){
            e.printStackTrace();
        }

        return isOpen;
    }

    public java.awt.Image getImage(){
        if(isOpen){
            java.awt.Image image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.getGraphics();
            g.setColor(Color.black);
            for(int i = 0; i < imageHeight; i++){
                for(int j = 0; j < imageWidth; j++){
                    if(pixels[i * imageWidth + j] != null)
                        g.setColor(pixels[i * imageWidth + j].getPixelColor());
                    g.drawRect(j, i, 1, 1);
                }
            }
            g.dispose();

            return image;
        }
        return null;
    }

}
