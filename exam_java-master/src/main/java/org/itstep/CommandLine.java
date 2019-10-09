package org.itstep;

import org.itstep.commands.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
Реализовать консольную программу на Java, которая бы представляла некую реализацию командной строки, то есть могла бы выполнять команды вводимые построчно пользователем.

Под командой понимается следующая строка: "имя команды" "аргумент №1" "аргумент №2" ... "аргумент №N"

Код, который выполняет необходимую команду пользователя, должен оформляться как отдельный Java класс.

Команда во время выполнения может так же взаимодействовать с пользователем используя стандартный ввод/вывод.

Программа должна поддерживать следующие команды:

dir — выводит список файлов в текущей директории
cd «путь» — перейти в директорию, путь к которой задан первым аргументом
pwd — вывести полный путь до текущей директории
cat «имя_файла» - выводит содержимое текстового файла «имя_файла»
Максимальная оценка задания: 10 баллов

Дополнительные задания:

Команда download «url» «имя_файла» - загружает файл (+1 балл)
Соответствие между классом и именем команды должно задаваться в конфигурационном файле программы. (+1 балл)
Программа должна поддерживать возможность запускать команды в фоновом режиме. Для этого достаточно в конце командной
строчки ввести знак "&". (+2 балла)
Команда find выполняет поиск файла в файловой системе. Так же программа должна поддерживать команду jobs, которая
выводит список задач, которые выполняются в фоне (+2 балла)
 */
//в конфигурационном файле программы- settings.properties
public class CommandLine {
    private static List<String> listJobs = Collections.synchronizedList(new ArrayList<>());//for command jobs

    public static void main(String[] args) {
        File file = new File(".");
        FileInputStream fis;
        Properties property = new Properties();
        Map<String, String> mapProperties = new TreeMap<>(Comparator.naturalOrder());
        try {
            fis = new FileInputStream("settings.properties");
            property.load(fis);

            for (Map.Entry<Object, Object> objectObjectEntry : property.entrySet()) {
                mapProperties.put((String) objectObjectEntry.getKey(), (String) objectObjectEntry.getValue());
            }
        } catch (IOException e) {
            System.err.println("Файл свойств отсуствует!");
        }
        String line;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Добро пожаловать в Java Command Line!");

        while (true) {
            try {
                System.out.print(file.getAbsolutePath() + "> ");
                line = input.readLine();
                line = line == null ? "exit" : line.trim();
                String stringBeforeSpace = line.toLowerCase();
                String stringAfterSpace = line;
                if (line.contains(" ")) {
                    stringBeforeSpace = line.toLowerCase().substring(0, line.indexOf(" "));
                    stringAfterSpace = line.substring(line.indexOf(" ") + 1);
                }
                if (mapProperties.containsKey(stringBeforeSpace)) {
                    Class cls = Class.forName(property.getProperty(stringBeforeSpace));
                    if (cls.isInstance(new Cat())) {
                        if (line.endsWith("&")) {
                            listJobs.add(stringBeforeSpace);
                            ExecutorService executorService = Executors.newCachedThreadPool();
                            String finalStringAfterSpace = stringAfterSpace.substring(0, stringAfterSpace.length() - 1);
                            File finalFile = file;
                            executorService.submit(() -> {
                                System.out.println(Thread.currentThread().getName());
                                System.out.println(new Cat().execute(finalFile.getPath() + File.separator + finalStringAfterSpace));
                            });
                            executorService.shutdown();
                            ForThread.getFinishedThread(executorService, listJobs, stringBeforeSpace);
                        } else {
                            System.out.println(new Cat().execute(file.getPath() + File.separator + stringAfterSpace));
                        }
                    } else if (cls.isInstance(new Cd())) {
                        String fileNew = new Cd().execute(stringAfterSpace, file.getAbsolutePath());
                        if (fileNew != null) {
                            file = new File(fileNew);
                        }
                    } else if (cls.isInstance(new Dir())) {
                        System.out.println(new Dir().execute(file.getAbsolutePath()));
                    } else if (cls.isInstance(new Download())) {
                        if (line.endsWith("&")) {
                            listJobs.add(stringBeforeSpace);
                            ExecutorService executorService = Executors.newCachedThreadPool();
                            String finalStringAfterSpace = stringAfterSpace.substring(0, stringAfterSpace.length() - 1);
                            File finalFile1 = file;
                            executorService.submit(() -> {
                                System.out.println(Thread.currentThread().getName());
                                System.out.println(new Download().execute(finalStringAfterSpace, finalFile1.getAbsolutePath()));
                            });
                            executorService.shutdown();
                            ForThread.getFinishedThread(executorService, listJobs, stringBeforeSpace);
                        } else {
                            System.out.println(new Download().execute(stringAfterSpace, file.getAbsolutePath()));
                        }
                    } else if (cls.isInstance(new Find())) {
                        if (line.endsWith("&")) {
                            listJobs.add(stringBeforeSpace);
                            ExecutorService executorService = Executors.newCachedThreadPool();
                            String finalStringAfterSpace = stringAfterSpace.substring(0, stringAfterSpace.length() - 1);
                            executorService.submit(() -> {
                                System.out.println(Thread.currentThread().getName());
                                System.out.println(new Find().execute(finalStringAfterSpace));
                            });
                            executorService.shutdown();
                            ForThread.getFinishedThread(executorService, listJobs, stringBeforeSpace);
                        } else {
                            System.out.println(new Find().execute(stringAfterSpace));
                        }
                    } else if (cls.isInstance(new Pwd())) {
                        System.out.println(new Pwd().execute(file.getAbsolutePath()));
                    }
                } else if (stringAfterSpace.equalsIgnoreCase("jobs")) {
                    System.out.println("В фоне:" + listJobs.size());
                    listJobs.forEach(System.out::println);
                } else if ("help".equalsIgnoreCase(line)) {
                    usage();
                } else if ("exit".equalsIgnoreCase(line)) {
                    break;
                } else {
                    System.out.println(line + " не является внутренней или внешней\n" +
                            "командой, исполняемой программой или пакетным файлом.");
                }


            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Ошибка ввода данных");
            }
        }
    }

    private static void usage() {
        System.out.println("Java Command Line\n\n" +
                "    dir — выводит список файлов в текущей директории\n" +
                "    cd «путь» — перейти в директорию, путь к которой задан первым аргументом\n" +
                "    pwd — вывести полный путь до текущей директории\n" +
                "    cat «имя_файла» - выводит содержимое текстового файла «имя_файла»\n" +
                "    download «url» «имя_файла» - загружает файл\n" +
                "    find - выполняет поиск файла в файловой системе\n" +
                "    jobs - выводит список задач, которые выполняются в фоне\n");

    }
}
