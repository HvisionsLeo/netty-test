package com.unitill.task;

import java.util.concurrent.*;

/**
 * @Description:
 * @Author: Leo
 * @Date: 2018-04-27 上午 9:39
 */
public class ScheduleJava {

    private static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

    public static void main(String[] args) {
        ScheduledFuture<?> future = scheduledExecutorService.schedule(()->{
            System.out.println("Now it is 10 seconds later");
            return 1;
        }, 10, TimeUnit.SECONDS);
        System.out.println("this is test!");
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        scheduledExecutorService.shutdown();
    }
}
