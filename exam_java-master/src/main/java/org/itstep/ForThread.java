package org.itstep;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public interface ForThread {
    /**
     * Метод ждет, когдо завершится поток executorService и удоляет задачу с listJobs
     *
     * @param executorService        поток ExecutorService
     * @param listJobs               List со всеми задачами в фоне
     * @param finalStringBeforeSpace имя задачи
     * @param <T>                    обобщенный тип
     */
    static <T> void getFinishedThread(ExecutorService executorService, List<T> listJobs, T finalStringBeforeSpace) {
        new Thread(() -> {
            while (!executorService.isTerminated()) {
                try {
                    executorService.awaitTermination(60, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Команда завершена.");
            listJobs.remove(finalStringBeforeSpace);
            // System.out.println(listJobs);
        }).start();
    }
}
