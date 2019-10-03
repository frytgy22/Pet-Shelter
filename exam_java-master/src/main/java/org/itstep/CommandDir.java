package org.itstep;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class CommandDir extends Command {
    @Override
    public void dir(File file) {
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
                    System.out.println(fmt);
                }
                System.out.println("Directories: " + countDirectory + "\nFiles: " + countFiles);
            }
        } catch (NullPointerException e) {
            System.err.println("Access error");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
