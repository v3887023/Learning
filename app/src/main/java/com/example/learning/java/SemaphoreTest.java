package com.example.learning.java;

import java.util.concurrent.Semaphore;

/**
 * @Description:
 * @Author: zcx
 * @Copyright: 浙江集商优选电子商务有限公司
 * @CreateDate: 2019/12/27 10:57
 * @Version: 1.0.0
 */
public class SemaphoreTest {
    private static final Semaphore A = new Semaphore(1);
    private static final Semaphore B = new Semaphore(1);
    private static volatile int count = 0;

    public static void main(String[] args) throws InterruptedException {
        B.acquire();



        new Thread(() -> {
            while (count++ < 10) {
                try {
                    B.acquire();
                    Utils.println("B 获取资源");
                    A.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (count++ < 10) {
                try {
                    A.acquire();
                    Utils.println("A 获取资源");
                    B.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Semaphore semaphore = new Semaphore(1);

        Thread thread = new Thread(()->{
            semaphore.acquireUninterruptibly(3);
            Utils.println("interrupt");

        });
        thread.start();


        Thread.sleep(2000);
        semaphore.release(2);
        thread.interrupt();
    }
}
