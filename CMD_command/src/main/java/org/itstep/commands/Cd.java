package org.itstep.commands;

import lombok.Data;
import org.itstep.CommandsExecute;
import org.itstep.Context;

import java.io.File;

@Data
public class Cd implements CommandsExecute {
    Context context;

    public Cd(Context context) {
        this.context = context;
    }

    /**
     * Метод,чтоб перейти в директорию, путь к которой задан первым аргументом
     */

    @Override
    public String execute(String... args) {
        if (args.length > 0) {
            File file = context.getFile();
            String fileName = args[0];
            File wayDirectory = new File(fileName);
            if (wayDirectory.exists()) {
                file = new File(wayDirectory.getAbsolutePath());
                context.setFile(file);
                return "Вы перешли: " + file.getAbsolutePath();
            } else if (new File(file.getAbsolutePath() + File.separator + fileName).exists()) {
                file = new File(file.getAbsolutePath() + File.separator + fileName);
                context.setFile(file);
                return "Вы перешли: " + file.getAbsolutePath();
            } else {
                File[] files = file.listFiles();
                if (files != null) {
                    for (File p : files) {
                        if (p != null && p.isDirectory()) {
                            if (p.getName().equals(fileName)) {
                                file = new File(p.getAbsolutePath());
                                context.setFile(file);
                                return "Вы перешли: " + file.getAbsolutePath();
                            }
                        }
                    }
                }
            }
        }
        System.err.println("Системе не удается найти указанный путь.");
        return "";
    }
}
