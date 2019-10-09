package org.itstep.commands;

import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
public class Find implements Command {
    private List<String> list = new ArrayList<>();

    @Override
    public String execute(String... arg) {
        if (arg.length > 0) {
            String fileNameForSearch = arg[0];
            File[] paths = File.listRoots();
            for (File file : paths) {
                list = showFilesAndDirectories(file, list, fileNameForSearch);
            }
            return list.toString();
        }
        return "Ошибка ввода";
    }

    public List<String> showFilesAndDirectories(File file, List<String> list, String fileNameForSearch) {
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
        return list;
    }
}

