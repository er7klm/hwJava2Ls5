import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {

        Semaphore sem = new Semaphore(1, true);
        CountDownLatch cdl = new CountDownLatch(1);
        new Uploader(cdl, sem).start();
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Semaphore sem2 = new Semaphore(3, true);
        CountDownLatch cdl2 = new CountDownLatch(10);
        for (int i = 1; i < 11; i++) {
            new Downloader(cdl2, sem2, i).start();
        }
        try {
            cdl2.await();
            System.out.println("Все пользователи скачали файл.");
            System.out.println("Файл удалён.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
