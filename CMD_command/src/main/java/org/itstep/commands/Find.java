package org.itstep.commands;

import lombok.Data;
import org.itstep.CommandsExecute;
import org.itstep.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
public class Find implements CommandsExecute {
    private List<String> list = new ArrayList<>();
    Context context;

    public Find(Context context) {
        this.context = context;
    }

    /**
     * Метод выполняет поиск файла в файловой системе
     */

    @Override
    public String execute(String... args) {
        if (args.length > 0) {
            String fileName = args[0];
            File[] paths = File.listRoots();
            for (File file : paths) {
                showFilesAndDirectories(file, list, fileName);
            }
        }
        return list.toString();
    }

    private void showFilesAndDirectories(File file, List<String> list, String fileNameForSearch) {
        try {
            if (file != null && file.canRead()) {
                File[] files = file.listFiles();
                if (files != null) {
                    for (File p : files) {
                        if (p != null) {
                            if (p.isDirectory()) {
                                if (p.getName().contains(fileNameForSearch)) {
                                    list.add(p.getAbsolutePath());
                                }
                                showFilesAndDirectories(p, list, fileNameForSearch);
                            } else {
                                if (p.getName().contains(fileNameForSearch)) {
                                    list.add(p.getAbsolutePath());
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка доступа");
        }
    }
}
