package org.itstep;

import java.io.File;

public class CommandCd extends Command {
    @Override
    public File cd(String wayDirectory,File existFile) {
        File file = new File(wayDirectory);
        if (file.exists()) {
            return file;
        }
        System.out.println("Системе не удается найти указанный путь.");
        return existFile;
    }
}
