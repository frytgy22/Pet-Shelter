package org.itstep.commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Cat implements Command {
    @Override
    public String execute(String... arg) {
        if (arg.length > 0) {
            String fileName = arg[0];
            if (new File(fileName).exists()) {
                try {
                    return Files.lines(Paths.get(fileName)).collect(Collectors.joining(" \n"));
                } catch (IOException e) {
                    return "Системе не удается найти указанный файл";
                }
            }
        }
        return "Ошибка ввода";
    }
}


