package org.itstep.commands;

import java.io.File;

public class Cd implements Command {
    @Override
    public String execute(String... arg) {
        if (arg.length > 1) {
            String wayDirectory = arg[0];
            File file = new File(wayDirectory);
            File existFile = new File(arg[1]);
            if (file.exists()) {
                return file.getAbsolutePath();
            } else {
                File[] files = existFile.listFiles();
                if (files != null) {
                    for (File p : files) {
                        if (p != null && p.isDirectory()) {
                            if (p.getName().equals(wayDirectory)) {
                                return p.getAbsolutePath();
                            }
                        }
                    }
                }
            }
            System.out.println("Системе не удается найти указанный путь.");
            return null;
        }
        System.out.println("Ошибка ввода");
        return null;
    }
}
