package org.itstep;

import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
public class CommandFind extends Command {
    private List<String> list = new ArrayList<>();

    @Override
    public void find(String fileNameForSearch) {
        File[] paths = File.listRoots();
        for (File file : paths) {
            list = showFilesAndDirectories(file, list, fileNameForSearch);
        }
        list.forEach(System.out::println);
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

