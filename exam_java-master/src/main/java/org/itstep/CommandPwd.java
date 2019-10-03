package org.itstep;

import java.io.File;

public class CommandPwd extends Command {
    @Override
    public void pwd(File file) {
        System.out.println(file.getAbsolutePath());
    }
}
