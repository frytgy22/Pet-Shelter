package org.itstep.commands;

import lombok.Data;
import org.itstep.CommandsExecute;
import org.itstep.Context;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Data
public class Cat implements CommandsExecute {
    Context context;

    public Cat(Context context) {
        this.context = context;
    }

    /**
     * Метод выводит содержимое текстового файла
     */

    @Override
    public String execute(String... args) {
        if (args.length > 0) {
            File file = context.getFile();
            String fileName = args[0];
            if (new File(file.getAbsolutePath() + File.separator + fileName).exists()) {
                try {
                    return Files.lines(Paths.get(file.getAbsolutePath() + File.separator + fileName)).collect(Collectors.joining("\n"));
                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("Ошибка доступа.");
                    return "";
                }
            }
        }
        System.err.println("Системе не удается найти указанный путь.");
        return "";
    }
}
