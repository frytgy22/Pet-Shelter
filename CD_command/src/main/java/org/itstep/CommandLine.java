package org.itstep;


import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommandLine {
    public static void main(String[] args) {
        Properties property = new Properties();
        Map<String, String> mapProperties = new TreeMap<>(Comparator.naturalOrder());
        try (FileInputStream fis = new FileInputStream("settings.properties")) {
            property.load(fis);
            for (Map.Entry<Object, Object> objectObjectEntry : property.entrySet()) {
                mapProperties.put((String) objectObjectEntry.getKey(), (String) objectObjectEntry.getValue());
            }
        } catch (IOException e) {
            System.err.println("Файл свойств отсуствует.");
        }
        String line;
        Command command;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Добро пожаловать в Java Command Line!");
        while (true) {
            try {
                System.out.print("> ");
                line = input.readLine();
                line = line == null ? "exit" : line.trim();
                String stringBeforeSpace = line.toLowerCase();
                String stringAfterSpace = line;
                if (line.contains(" ")) {
                    stringBeforeSpace = line.toLowerCase().substring(0, line.indexOf(" "));
                    stringAfterSpace = line.substring(line.indexOf(" ") + 1);
                }
                command = new Command(stringAfterSpace);

                if (mapProperties.containsKey(stringBeforeSpace)) {
                    Class<?> cls = Class.forName(property.getProperty(stringBeforeSpace));
                    CommandsExecute commandsExecute = (CommandsExecute) cls.getDeclaredConstructor(Command.class).newInstance(command);

                    if (line.endsWith("&")) {
                        command.getSingletonListJobs().listJobs.add(stringBeforeSpace);
                        ExecutorService executorService = Executors.newCachedThreadPool();
                        command.setFileName(stringAfterSpace.substring(0, stringAfterSpace.length() - 1));
                        executorService.submit(() -> {
                            System.out.println(commandsExecute.execute());
                        });
                        executorService.shutdown();
                        ForThread.getFinishedThread(executorService, stringBeforeSpace,command);
                    } else {
                        System.out.println(commandsExecute.execute());
                    }
                } else if ("help".equalsIgnoreCase(line)) {
                    Command.usage();
                } else if ("exit".equalsIgnoreCase(line)) {
                    break;
                } else {
                    System.out.println(line + " не является внутренней или внешней\n" +
                            "командой, исполняемой программой или пакетным файлом.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Ошибка ввода данных");
            }
        }
    }
}
