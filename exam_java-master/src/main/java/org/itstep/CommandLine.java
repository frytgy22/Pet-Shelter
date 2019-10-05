package org.itstep;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        Configurations configurations = new Configurations();
        File file = new File(".");

        String line = "";
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Добро пожаловать в Java Command Line!");
        while (true) {
            try {
                System.out.print(file.getAbsolutePath() + "> ");
                line = input.readLine();
                line = line == null ? "exit" : line.trim();
                String stringBeforeSpace = line;
                String stringAfterSpace = line;
                if (line.contains(" ")) {
                    stringBeforeSpace = line.substring(0, line.indexOf(" "));
                    stringAfterSpace = line.substring(line.indexOf(" ") + 1);
                }
                if ("dir".equalsIgnoreCase(line)) {
                    GetClass.getsConfigurationClass(configurations, line, file, null, 1);
                } else if ("cd".equalsIgnoreCase(stringBeforeSpace)) {
                    file = GetClass.getsConfigurationClass(configurations, stringBeforeSpace, file, stringAfterSpace + File.separator, 3);
                } else if ("pwd".equalsIgnoreCase(line)) {
                    GetClass.getsConfigurationClass(configurations, line, file, null, 1);
                } else if ("cat".equalsIgnoreCase(stringBeforeSpace)) {
                    if (line.endsWith("&")) {
                        listJobs.add(stringBeforeSpace);
                        ExecutorService executorService = Executors.newCachedThreadPool();
                        String finalStringAfterSpace = stringAfterSpace.substring(0, stringAfterSpace.length() - 1);
                        File finalFile2 = file;
                        String finalStringBeforeSpace2 = stringBeforeSpace;
                        executorService.submit(() -> {
                            System.out.println(Thread.currentThread().getName());
                            GetClass.getsConfigurationClass(configurations, finalStringBeforeSpace2, finalFile2, finalFile2.getPath() + File.separator + finalStringAfterSpace, 4);
                        });
                        executorService.shutdown();
                        ForThread.getFinishedThread(executorService, listJobs, stringBeforeSpace);
                    } else {
                        GetClass.getsConfigurationClass(configurations, stringBeforeSpace, file, file.getPath() + File.separator + stringAfterSpace, 4);
                    }
                } else if ("find".equalsIgnoreCase(stringBeforeSpace)) {
                    if (line.endsWith("&")) {
                        listJobs.add(stringBeforeSpace);
                        ExecutorService executorService = Executors.newCachedThreadPool();
                        String finalStringAfterSpace = stringAfterSpace.substring(0, stringAfterSpace.length() - 1);
                        File finalFile1 = file;
                        String finalStringBeforeSpace1 = stringBeforeSpace;
                        executorService.submit(() -> {
                            System.out.println(Thread.currentThread().getName());
                            GetClass.getsConfigurationClass(configurations, finalStringBeforeSpace1, finalFile1, finalStringAfterSpace, 4);
                        });
                        executorService.shutdown();
                        ForThread.getFinishedThread(executorService, listJobs, finalStringBeforeSpace1);
                    } else {
                        GetClass.getsConfigurationClass(configurations, stringBeforeSpace, file, stringAfterSpace, 4);
                    }
                } else if ("jobs".equalsIgnoreCase(line)) {
                    System.out.println("В фоне:" + listJobs.size());
                    listJobs.forEach(System.out::println);
                } else if ("help".equalsIgnoreCase(line)) {
                    usage();
                } else if ("exit".equalsIgnoreCase(line)) {
                    break;
                } else if ("download".equalsIgnoreCase(stringBeforeSpace)) {
                    if (line.endsWith("&")) {
                        listJobs.add(stringBeforeSpace);
                        ExecutorService executorService = Executors.newCachedThreadPool();
                        File finalFile = file;
                        String finalStringBeforeSpace = stringBeforeSpace;
                        String finalStringAfterSpace = stringAfterSpace.substring(0, stringAfterSpace.length() - 1);
                        executorService.submit(() -> {
                            System.out.println(Thread.currentThread().getName());
                            GetClass.getsConfigurationClass(configurations, finalStringBeforeSpace, finalFile, finalStringAfterSpace, 2);
                        });
                        executorService.shutdown();
                        ForThread.getFinishedThread(executorService, listJobs, finalStringBeforeSpace);
                    } else {
                        GetClass.getsConfigurationClass(configurations, stringBeforeSpace, file, stringAfterSpace, 2);
                    }
                } else {
                    System.out.println(line + " не является внутренней или внешней\n" +
                            "командой, исполняемой программой или пакетным файлом.");
                }
            } catch (Exception e) {
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
