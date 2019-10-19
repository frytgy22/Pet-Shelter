package org.itstep;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CommandLine {

    static class Context {
        private Path currentPath;

        public void setCurrentPath(Path path) {
            this.currentPath = path;
        }

        public Path getCurrentPath() {
            return currentPath;
        }
    }

    Context conetex = new Context();
    Map<String, CommandsExecute> commands = new HashMap<>();

    CommandLine() {
        Properties property = new Properties();
        conetex.setCurrentPath(Paths.get("."));
        try (FileInputStream fis = new FileInputStream("settings.properties")) {
            property.load(fis);
            for (Map.Entry<Object, Object> objectObjectEntry : property.entrySet()) {
                String key = (String) objectObjectEntry.getKey();
                String className = (String) objectObjectEntry.getValue();
                Class<?> cls = Class.forName(className);
                CommandsExecute commandsExecute = (CommandsExecute) cls.getDeclaredConstructor(Command.class)
                        .newInstance(context);

                commands.put(key, commandsExecute);
            }
        } catch (IOException e) {
            System.err.println("Файл свойств отсуствует.");
        }
    }

    public static void main(String[] args) {
        Properties property = new Properties();
        Map<String, String> mapProperties = new TreeMap<>(Comparator.naturalOrder());

        try (FileInputStream fis = new FileInputStream("settings.properties")) {
            property.load(fis);
            for (Map.Entry<Object, Object> objectObjectEntry : property.entrySet()) {
                String key = (String) objectObjectEntry.getKey();
                String className = (String) objectObjectEntry.getValue();
                mapProperties.put(key, className);
                Class<?> cls = Class.forName(className);
                CommandsExecute commandsExecute = (CommandsExecute) cls.getDeclaredConstructor(Command.class)
                        .newInstance(context);

                commands.put(key, commandsExecute);
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

                // if(commands.containsKey(stringBeforeSpace)) {
                // commands.get(stringBeforeSpace).execute(stringAfterSpace.split("\\s+"));
                // }
                if (mapProperties.containsKey(stringBeforeSpace)) {
                    Class<?> cls = Class.forName(property.getProperty(stringBeforeSpace));
                    CommandsExecute commandsExecute = (CommandsExecute) cls.getDeclaredConstructor(Command.class)
                            .newInstance(command);

                    if (line.endsWith("&")) {
                        command.getSingletonListJobs().listJobs.add(stringBeforeSpace);
                        ExecutorService executorService = Executors.newCachedThreadPool();
                        command.setFileName(stringAfterSpace.substring(0, stringAfterSpace.length() - 1));
                        executorService.submit(() -> {
                            System.out.println(commandsExecute.execute());
                        });
                        executorService.shutdown();
                        ForThread.getFinishedThread(executorService, stringBeforeSpace, command);
                    } else {
                        System.out.println(commandsExecute.execute());
                    }
                } else if ("help".equalsIgnoreCase(line)) {
                    Command.usage();
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
}
