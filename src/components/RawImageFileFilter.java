package components;

import java.io.File;

public class RawImageFileFilter extends javax.swing.filechooser.FileFilter {
    @Override
    public boolean accept(File f) {
        if (f.isDirectory())
            return true;
        String[] foo = f.getName().split("\\.");
        if (foo.length < 1)
            return false;
        String extension = foo[foo.length - 1].toLowerCase();

        if (extension.equals("raw")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getDescription() {
        return "Raw Image File(Gray, *.raw)";
    }
}
