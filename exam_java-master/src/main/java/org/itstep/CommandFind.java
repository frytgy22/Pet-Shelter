package org.itstep;

import java.io.File;
import java.util.ArrayList;

public class CommandFind extends Command {
    @Override
    public void find(String fileNameForSearch) {
        File[] paths = File.listRoots();
        for (File file : paths) {
            System.out.println("Поиск на диске: " + file.getAbsolutePath());
            new SearchRunnable(fileNameForSearch, file);
        }
    }
}

class SearchingThread implements Runnable {
    private SearchRunnable searchRunnable;
    private File[] files;

    public SearchingThread(File[] files, SearchRunnable searchRunnable) {
        this.searchRunnable = searchRunnable;
        this.files = files;
    }

    @Override
    public void run() {
        searchRunnable.searching(files);
    }
}

class SearchRunnable implements Runnable {
    private String search;

    private ArrayList<File> found = new ArrayList<>();
    private ArrayList<Thread> threads = new ArrayList<>();
    private File[] files;
    private Thread thread;

    public SearchRunnable(String search, File path) {
        super();
        this.search = search;
        files = path.listFiles();
        thread = new Thread(this);
        thread.start();
    }

    public void searching(File[] files) {
        if (files == null) return;
        for (File file : files) {
            if (file.isFile() && file.getName().contains(search)) {
                //System.out.println(file.getAbsolutePath());
                found.add(file);
            }
            if (file.isDirectory()) {
                Thread newThread = new Thread(new SearchingThread(file.listFiles(), this));
                newThread.start();
                threads.add(newThread);
            }
        }
    }

    @Override
    public void run() {
        searching(files);
        for (int i = 0; i < threads.size(); i++) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (found.size() == 0) {
            System.out.println("Системе не удается найти указанный файл");//вывод отдельно на каждый диск
        } else {
            found.forEach(System.out::println);
        }
    }
}