package components;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    private image.Image myImage;
    private Image image;
    private Dimension size;

    public void openImage(String filepath, int width, Class type) throws Exception{
        myImage = new image.Image(type);
        myImage.openImage(filepath, width);
        this.image = myImage.getImage();
        size = new Dimension(this.image.getWidth(null), this.image.getHeight(null));
        this.setSize(size);
    }

    public void setImageWidth(int width){
        if(myImage != null){
            myImage.setImageWidth(width);
            this.image = myImage.getImage();
            size = new Dimension(this.image.getWidth(null), this.image.getHeight(null));
            this.setSize(size);
        }
    }

    public void setImage(Image image){
        this.image = image;
        size = new Dimension(image.getWidth(null), image.getHeight(null));
        this.setSize(size);
    }

    public int getImageWidth(){
        return myImage.getImageWidth();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(image != null)
            g.drawImage(image, 0, 0, null);
    }

    @Override
    public Dimension getPreferredSize(){
        return size;
    }
}
