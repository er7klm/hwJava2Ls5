import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Uploader extends Thread {

    private CountDownLatch cdl;
    private Semaphore sem;

    public Uploader(CountDownLatch cdl, Semaphore sem) {
        this.cdl = cdl;
        this.sem = sem;
    }

    public synchronized void run() {
        try {
            sem.acquire();
            System.out.println("Загружается файл (500 МБ) на сервер со скоростью 20 МБ/с. ");
            for (int i = 0; i <= 100; i++) {
                ProgressBar.progressBar(100, i);
                try {
                    Thread.sleep(250); // время загрузки файла 500МБ/20МБ/с=25 сек (250000 мс или 100х250мс)
                } catch (InterruptedException e) {
                    System.out.println("Ошибка!!!");
                    e.printStackTrace();
                }
            }

            sem.release();
            System.out.println(" файл загружен на сервер.");
            sleep(2000);
            cdl.countDown();
            cdl.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
