package org.itstep;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public interface ForThread {
    /**
     * Метод ждет, когдо завершится поток executorService и удаляет задачу с listJobs
     *
     * @param executorService        поток ExecutorService
     * @param finalStringBeforeSpace имя задачи
     */
    static void getFinishedThread(ExecutorService executorService, String finalStringBeforeSpace, Command command) {
        new Thread(() -> {
            while (!executorService.isTerminated()) {
                try {
                    executorService.awaitTermination(60, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Команда завершена.");
            command.getSingletonListJobs().listJobs.remove(finalStringBeforeSpace);
        }).start();
    }
}
