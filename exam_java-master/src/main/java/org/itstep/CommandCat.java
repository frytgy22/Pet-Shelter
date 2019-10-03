package org.itstep;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class CommandCat extends Command {
    @Override
    public void cat(String fileName) throws IOException {

        if (new File(fileName).exists()) {
            String string= Files.lines(Paths.get(fileName)).collect(Collectors.joining(" \n"));
            System.out.println(string);
        } else {
            System.out.println("Системе не удается найти указанный файл");
        }
    }
}
