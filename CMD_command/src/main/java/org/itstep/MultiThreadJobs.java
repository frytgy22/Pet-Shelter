package org.itstep;

import lombok.Data;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@Data
public class MultiThreadJobs {
    private List<String> list;

    public MultiThreadJobs() {
        list = Collections.synchronizedList(new ArrayList<>());
    }

    /**
     * Метод ждет, когдо завершится поток executorService и удаляет задачу с listJobs
     *
     * @param executorService        поток ExecutorService
     * @param finalStringBeforeSpace имя задачи
     */
    public void getFinishedThread(ExecutorService executorService, String finalStringBeforeSpace) {
        new Thread(() -> {
            while (!executorService.isTerminated()) {
                try {
                    executorService.awaitTermination(60, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Команда завершена.");
            list.remove(finalStringBeforeSpace);
        }).start();
    }

    public void jobs() {
        System.out.println("В фоне:" + list.size() + "\n" + list.toString());
    }
}


