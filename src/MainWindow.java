import components.RGBImageFileFilter;
import components.RawImageFileFilter;
import components.ImagePanel;
import components.ImageScrollPane;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainWindow extends JFrame{
    MainWindow mw;
    JMenuBar mainMenu;
    JFileChooser openFileChooser;
    JTabbedPane tabbedPane;

    public static void main(String[] args){
        MainWindow w = new MainWindow();
        w.setVisible(true);
    }

    MainWindow(){
        mw = this;
        setSize(800, 600);
        setTitle("Color Image Viewer");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setTabbedPane();
        setFileChooser();
        setMainMenu();
    }

    void setTabbedPane(){
        tabbedPane = new JTabbedPane();

        setContentPane(tabbedPane);
    }

    void setMainMenu() {
        mainMenu = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem openMenu = new JMenuItem("Open...");
        openMenu.addActionListener((e) -> openFileChooser.showOpenDialog(mw));
        fileMenu.add(openMenu);
        JMenuItem tabCloseMenu = new JMenuItem("Close");
        tabCloseMenu.addActionListener((e) -> {
            Component s = tabbedPane.getSelectedComponent();
            if(s != null){
                if((JOptionPane.showConfirmDialog(mw, "Really?", "TabClose", JOptionPane.OK_CANCEL_OPTION)
                        == JOptionPane.OK_OPTION)){
                    tabbedPane.remove(s);
                }
            }
        });
        fileMenu.add(tabCloseMenu);
        fileMenu.addSeparator();
        JMenuItem closeMenu = new JMenuItem("Exit");
        closeMenu.addActionListener((v) -> System.exit(0));
        fileMenu.add(closeMenu);
        mainMenu.add(fileMenu);

        JMenu settingMenu = new JMenu("Setting");
        JMenuItem widthSettingMenu = new JMenuItem("Width Setting...");
        widthSettingMenu.addActionListener((e) -> {
            ImageScrollPane pane = (ImageScrollPane)tabbedPane.getSelectedComponent();
            if(pane != null){
                pane.setImageWidth();
            } else {
                System.out.println("pane is null");
            }
        });
        settingMenu.add(widthSettingMenu);
        mainMenu.add(settingMenu);

        this.setJMenuBar(mainMenu);
    }

    void setFileChooser(){
        openFileChooser = new JFileChooser();

        openFileChooser.setAcceptAllFileFilterUsed(false);
        openFileChooser.setMultiSelectionEnabled(false);

        openFileChooser.addChoosableFileFilter(new RawImageFileFilter());
        openFileChooser.addChoosableFileFilter(new RGBImageFileFilter());

        openFileChooser.addActionListener((e) -> {
            JFileChooser chooser = (JFileChooser)e.getSource();
            File f = chooser.getSelectedFile();
            if(f != null){
                String[] foo = f.getName().split("\\.");
                String ext = foo[foo.length - 1];
                String s = JOptionPane.showInputDialog("Input Width (Initial Value : 256px)", 256);
                try {
                    if(s == null)
                        return;
                    int width = Integer.parseInt(s);
                    if (width == 0)
                        width = 256;
                    ImageScrollPane pane = ImageScrollPane.createImageScrollPane(f.getPath(), width, ext);
                    tabbedPane.addTab(f.getName(), pane);
                    tabbedPane.setSelectedComponent(pane);
                } catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }
}
