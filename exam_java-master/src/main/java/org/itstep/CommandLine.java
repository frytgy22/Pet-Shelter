package org.itstep;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public static void main(String[] args) throws IOException {
        Configurations configurations = new Configurations();
        File file = new File(".");
        List<String> listJobs = Collections.synchronizedList(new ArrayList<>());
        String line;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Добро пожаловать в Java Command Line!");
        while (true) {
            System.out.print(file.getAbsolutePath() + "> ");
            line = input.readLine();
            line = line == null ? "exit" : line.trim();
            if ("dir".equalsIgnoreCase(line)) {
                GetClass.getsConfigurationClass(configurations, line, file, null, 1);
            } else if ("cd".equalsIgnoreCase(line.substring(0, 2))) {
                file = GetClass.getsConfigurationClass(configurations, line.substring(0, 2), file, line.substring(3) + File.separator, 3);
                // new CommandCd().cd(line.substring(3) + File.separator,file);
            } else if ("pwd".equalsIgnoreCase(line)) {
                GetClass.getsConfigurationClass(configurations, line, file, null, 1);
            } else if ("cat".equalsIgnoreCase(line.substring(0, 3))) {
                GetClass.getsConfigurationClass(configurations, line.substring(0, 3), file, file.getPath() + File.separator + line.substring(4), 4);
            } else if ("find".equalsIgnoreCase(line.substring(0, 4))) {
                GetClass.getsConfigurationClass(configurations, line.substring(0, 4), file, line.substring(5), 4);
            } else if ("jobs".equalsIgnoreCase(line)) {
                listJobs.forEach(System.out::println);
            } else if ("help".equalsIgnoreCase(line)) {
                usage();
            } else if ("exit".equalsIgnoreCase(line)) {
                break;
            } else if ("download".equalsIgnoreCase(line.substring(0, 8))) {
                GetClass.getsConfigurationClass(configurations, line.substring(0, 8), file, line.substring(9), 2);
            }
        }
    }


    private static void usage() {
        System.out.println("Java Command Line\n\n" +
                "Применение: java CommandLine.class \"команда\"  \"аргумент №1\" \"аргумент №2\" ... \"аргумент №N\"\n" +
                "Где \"команда\":\n" +
                "    dir — выводит список файлов в текущей директории\n" +
                "    cd «путь» — перейти в директорию, путь к которой задан первым аргументом\n" +
                "    pwd — вывести полный путь до текущей директории\n" +
                "    cat «имя_файла» - выводит содержимое текстового файла «имя_файла»\n");
    }
}
//cd C:\Users\Win10\Desktop