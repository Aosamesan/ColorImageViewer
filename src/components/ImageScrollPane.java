package components;

import pixels.GrayScalePixel;
import pixels.HSIPixel;
import pixels.RGBPixel;

import javax.swing.*;

public class ImageScrollPane extends JScrollPane{
    ImagePanel imagePanel;

    public ImageScrollPane(){
        imagePanel = new ImagePanel();
        setViewportView(imagePanel);
    }

    public ImageScrollPane(ImagePanel pane){
        setViewportView(pane);
        imagePanel = pane;
    }

    public void setImageWidth(){
        String w = JOptionPane.showInputDialog(this, "Set width", imagePanel.getImageWidth());
        try {
            if(w == null)
                return;
            int width = Integer.parseInt(w);
            if (width > 0) {
                imagePanel.setImageWidth(width);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void openFile(String filepath, int width, Class type){
        if(imagePanel != null){
            try {
                imagePanel.openImage(filepath, width, type);
            } catch( Exception e){
                e.printStackTrace();
            }
        }
    }

    public static ImageScrollPane createImageScrollPane(String filepath, int width, String ext){
        ImageScrollPane pane = new ImageScrollPane();
        switch(ext){
            case "raw":
                pane.openFile(filepath, width, GrayScalePixel.class);
                break;
            case "rgb":
                pane.openFile(filepath, width, RGBPixel.class);
                break;
            case "hsi":
                pane.openFile(filepath, width, HSIPixel.class);
                break;
        }

        return pane;
    }
}