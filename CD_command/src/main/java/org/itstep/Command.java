package org.itstep;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Command {
    private String fileName;
    private List<String> list = new ArrayList<>();
    private SingletonListJobs singletonListJobs = SingletonListJobs.getInstance();//for command jobs
    private SingletonFile singletonFile = SingletonFile.getInstance();

    public Command(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Метод выводит список файлов в текущей директории
     */
    public String dir() {
        int countDirectory = 0;
        int countFiles = 0;
        try {
            System.out.println("Folder contents " + singletonFile.file.getAbsolutePath());
            if (singletonFile.file.exists() && singletonFile.file.canRead()) {
                File[] files = singletonFile.file.listFiles();
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

    /**
     * Метод,чтоб перейти в директорию, путь к которой задан первым аргументом
     */
    public String cd() {
        File wayDirectory = new File(fileName);
        if (wayDirectory.exists()) {
            singletonFile.file = new File(wayDirectory.getAbsolutePath());
            return singletonFile.file.getAbsolutePath();
        } else if (new File(singletonFile.file.getAbsolutePath() + File.separator + fileName).exists()) {
            singletonFile.file = new File(singletonFile.file.getAbsolutePath() + File.separator + fileName);
            return singletonFile.file.getAbsolutePath();
        } else {
            File[] files = singletonFile.file.listFiles();
            if (files != null) {
                for (File p : files) {
                    if (p != null && p.isDirectory()) {
                        if (p.getName().equals(fileName)) {
                            singletonFile.file = new File(p.getAbsolutePath());
                            return singletonFile.file.getAbsolutePath();
                        }
                    }
                }
            }
        }
        System.err.println("Системе не удается найти указанный путь.");
        return "";
    }

    /**
     * Метод, чтоб вывести полный путь до текущей директории
     */
    public String pwd() {
        return singletonFile.file.getAbsolutePath();
    }

    /**
     * Метод выводит содержимое текстового файла
     */
    public String cat() {
        if (new File(singletonFile.file.getAbsolutePath() + File.separator + fileName).exists()) {
            try {
                return Files.lines(Paths.get(singletonFile.file.getAbsolutePath() + File.separator + fileName)).collect(Collectors.joining("\n"));
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Ошибка доступа.");
                return "";
            }
        }
        System.err.println("Системе не удается найти указанный путь.");
        return "";
    }

    /**
     * Метод загружает файл
     */
    public String download() {
        try {
            URLConnection con = new URL(fileName).openConnection();
            con.connect();
            String name;
            if (fileName.substring(fileName.length() - 5).contains(".") && !fileName.endsWith(".")) {
                name = singletonFile.file.getAbsolutePath() + "\\" + fileName.substring(fileName.lastIndexOf('/') + 1);
            } else {
                if (fileName.contains("youtube") || fileName.contains("mp4")) {
                    name = singletonFile.file.getAbsolutePath() + "\\" + "fileDownload.mp4";
                } else if (fileName.contains("mp3")) {
                    name = singletonFile.file.getAbsolutePath() + "\\" + "fileDownload.mp3";
                } else {
                    name = singletonFile.file.getAbsolutePath() + "\\" + "fileDownload.zip";
                }
            }
            File file = new File(name);
            try (ReadableByteChannel in = Channels.newChannel(new URL(fileName).openStream());
                 FileChannel out = new FileOutputStream(file).getChannel()) {
                out.transferFrom(in, 0, Long.MAX_VALUE);
                return "File: " + name + "\nSize: " + con.getContentLength() + "\n";
            }
        } catch (IOException e) {
            System.err.println("Системе не удается найти указанный URL.");
        }
        return "";
    }

    /**
     * Метод выполняет поиск файла в файловой системе
     */
    public String find() {
        File[] paths = File.listRoots();
        for (File file : paths) {
            showFilesAndDirectories(file, list, fileName);
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

    public static void usage() {
        System.out.println("Java Command Line\n\n" +
                "    dir — выводит список файлов в текущей директории\n" +
                "    cd «путь» — перейти в директорию, путь к которой задан первым аргументом\n" +
                "    pwd — вывести полный путь до текущей директории\n" +
                "    cat «имя_файла» - выводит содержимое текстового файла «имя_файла»\n" +
                "    download «url» «имя_файла» - загружает файл\n" +
                "    find - выполняет поиск файла в файловой системе\n" +
                "    jobs - выводит список задач, которые выполняются в фоне\n");
    }

    /**
     * метод выводит список задач, которые выполняются в фоне
     */
    public String jobs() {
        return "В фоне:" + singletonListJobs.listJobs.size() + "\n" + singletonListJobs.listJobs.toString();
    }
}
