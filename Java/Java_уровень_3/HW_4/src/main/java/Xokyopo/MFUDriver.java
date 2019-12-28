package Xokyopo;

import java.io.IOException;

/*
2. Создать модель MFU с возможностью печати и сканирования(копирования) (данные процессы могут происходить параллельно).
 */
public class MFUDriver{
    //Метод слип в данном классе эмулирует время выполнения действия.
    private final int SlipTimer = 1000;
    private final Object scanner = new Object();
    private final Object printer = new Object();

    public void scan(String str) {
        synchronized (this.scanner) {
            System.err.println("Я сканирую для " + str);
            try {
                Thread.sleep(this.SlipTimer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void print(String str) {
        synchronized (this.printer) {
            System.err.println("Я сканирую для " + str);
            try {
                Thread.sleep(this.SlipTimer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void copy(String str) {
        synchronized (this.scanner) {
            synchronized (this.printer) {
                System.err.println("Я копирую для " + str);
                try {
                    Thread.sleep(this.SlipTimer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
