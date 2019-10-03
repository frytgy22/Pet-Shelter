package org.itstep;

import java.io.File;
import java.util.ArrayList;

public class CommandFind extends Command {
    @Override
    public void find(String filenameForSearch) {
        File[] paths = File.listRoots();
        for (File file : paths) {
            new SearchRunnable(filenameForSearch, file);
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
    private File path;
    private ArrayList<File> found = new ArrayList<>();
    private ArrayList<Thread> threads = new ArrayList<>();
    private File[] files;
    private Thread thr;

    public SearchRunnable(String search, File path) {
        super();
        this.search = search;
        this.path = path;
        files = path.listFiles();
        thr = new Thread(this);
        thr.start();
    }

    public void searching(File[] files) {
        if (files == null) return;
        for (File i : files) {
            if (i.isFile() && i.getName().contains(search)) {
                System.out.println(i.getAbsolutePath());
                found.add(i);
            }
            if (i.isDirectory()) {
                Thread newThread = new Thread(new SearchingThread(i.listFiles(), this));
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
            System.out.println("Системе не удается найти указанный файл.");
        }
    }
}