package org.itstep.commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Dir implements Command {
    @Override
    public String execute(String... arg) {
        if (arg.length > 0) {
            File file = new File(arg[0]);
            int countDirectory = 0;
            int countFiles = 0;
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
                        return fmt.toString();
                    }
                    System.out.println("Directories: " + countDirectory + "\nFiles: " + countFiles);
                }
            } catch (NullPointerException e) {
                System.err.println("Ошибка доступа.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "Ошибка ввода";
    }
}

