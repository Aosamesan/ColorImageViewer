package components;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class RGBImageFileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        if (f.isDirectory())
            return true;
        String[] foo = f.getName().split("\\.");
        if (foo.length < 1)
            return false;
        String extension = foo[foo.length - 1].toLowerCase();

        if (extension.equals("rgb")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getDescription() {
        return "RGB Image File(24bit, [RGB], *.rgb)";
    }
}
