package org.itstep.commands;

import lombok.Data;
import org.itstep.CommandsExecute;
import org.itstep.Context;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Data
public class Dir implements CommandsExecute {
    Context context;

    public Dir(Context context) {
        this.context = context;
    }

    /**
     * Метод выводит список файлов в текущей директории
     */

    @Override
    public String execute(String... args) {
        int countDirectory = 0;
        int countFiles = 0;
        File file = context.getFile();
        try {
            System.out.println("Folder contents " + file.getAbsolutePath());
            if (file.exists() && file.canRead()) {
                File[] files = file.listFiles();
                if (files != null) {
                    Formatter fmt = new Formatter();
                    for (File value : files) {
                        BasicFileAttributes attributes = Files.readAttributes(value.toPath(), BasicFileAttributes.class);
                        Date creationDate = new Date(attributes.creationTime().to(MILLISECONDS));
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
                        fmt = (fmt.format("%15s %10s %s\n", simpleDateFormat.format(creationDate), value.isDirectory() ? " <DIR>\t\t " : "\t\t" + value.length(), value.getName()));
                        int i = value.isDirectory() ? countDirectory++ : countFiles++;
                    }
                    return fmt.toString() + "\nDirectories: " + countDirectory + "\nFiles: " + countFiles;
                }
            }
        } catch (NullPointerException | IOException e) {
            System.err.println("Ошибка доступа.");
        }
        return "";
    }
}
