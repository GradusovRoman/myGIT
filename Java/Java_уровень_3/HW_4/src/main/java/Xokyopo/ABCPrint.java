package Xokyopo;

public class ABCPrint extends Thread{
    private int count;
    private String text;
    private final Main main;
    private int position;
    private int range;

    public ABCPrint(String[] arr, int position, int count, Main main) {
        this.text = arr[position];
        this.count = count;
        this.main = main;
        this.position = position;
        this.range = arr.length;
    }

    @Override
    public void run() {
        synchronized (this.main) {
            while (this.count > 0) {
                if (this.main.count % range == position) {
                    System.out.print(this.text);
                    this.count--;
                    this.main.count++;
                    this.main.notifyAll();
                } else {
                    try {
                        this.main.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
