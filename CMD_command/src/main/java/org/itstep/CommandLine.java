package org.itstep;

import lombok.Data;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Data
public class CommandLine {
    private Context context = new Context();
    private Map<String, CommandsExecute> commands = new HashMap<>();
    private MultiThreadJobs multiThreadJobs = new MultiThreadJobs();

    private CommandLine() {
        Properties property = new Properties();
        context.setFile(new File("."));
        try (FileInputStream fis = new FileInputStream("settings.properties")) {
            property.load(fis);
            for (Map.Entry<Object, Object> objectObjectEntry : property.entrySet()) {
                String key = (String) objectObjectEntry.getKey();
                String className = (String) objectObjectEntry.getValue();
                Class<?> cls = Class.forName(className);
                CommandsExecute commandsExecute = (CommandsExecute) cls.getDeclaredConstructor(Context.class).newInstance(context);
                commands.put(key, commandsExecute);
            }
        } catch (IOException e) {
            System.err.println("Файл свойств отсуствует.");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine();
        String line;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Добро пожаловать в Java Command Line!");
        while (true) {
            try {
                System.out.print(commandLine.context.getFile().getAbsolutePath() + "> ");
                line = input.readLine();
                line = line == null ? "exit" : line.trim();
                String stringBeforeSpace = line.toLowerCase();
                String stringAfterSpace = line;
                if (line.contains(" ")) {
                    stringBeforeSpace = line.toLowerCase().substring(0, line.indexOf(" "));
                    stringAfterSpace = line.substring(line.indexOf(" ") + 1);
                }
                if (commandLine.commands.containsKey(stringBeforeSpace)) {
                    if (line.endsWith("&")) {
                        commandLine.multiThreadJobs.getList().add(stringBeforeSpace);
                        ExecutorService executorService = Executors.newCachedThreadPool();
                        String finalStringBeforeSpace = stringBeforeSpace;
                        String finalStringAfterSpace = stringAfterSpace.substring(0, stringAfterSpace.length() - 1);
                        executorService.submit(() -> {
                            System.out.println(commandLine.commands.get(finalStringBeforeSpace).execute(finalStringAfterSpace.split("\\s+")));
                        });
                        executorService.shutdown();
                        commandLine.multiThreadJobs.getFinishedThread(executorService, stringBeforeSpace);
                    } else {
                        System.out.println(commandLine.commands.get(stringBeforeSpace).execute(stringAfterSpace.split("\\s+")));
                    }
                } else if ("help".equalsIgnoreCase(line)) {
                    usage();
                } else if ("jobs".equalsIgnoreCase(line)) {//TODO need a separate class?
                    commandLine.multiThreadJobs.jobs();
                } else if ("exit".equalsIgnoreCase(line)) {
                    break;
                } else {
                    System.out.println(line + " не является внутренней или внешней\n"
                            + "командой, исполняемой программой или пакетным файлом.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Ошибка ввода данных");
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
