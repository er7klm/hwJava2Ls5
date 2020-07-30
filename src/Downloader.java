import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Downloader extends Thread {

    private CountDownLatch cdl2;
    private Semaphore sem2;
    private int id;

    public Downloader(CountDownLatch cdl2, Semaphore sem2, int id) {
        this.cdl2 = cdl2;
        this.sem2 = sem2;
        this.id = id;
    }

    public synchronized void run() {
        try {
            sem2.acquire();
            System.out.println(id + " пользователь начал скачивать файл (100 МБ/с)");
            sleep(5000);
            System.out.println(id + " пользователь закончил скачивать.");
            sleep(1000);
            sem2.release();
            cdl2.countDown();
            cdl2.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
