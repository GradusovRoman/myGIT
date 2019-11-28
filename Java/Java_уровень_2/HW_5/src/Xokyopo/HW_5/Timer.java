package Xokyopo.HW_5;

public class Timer {
    private long strtTimer = 0;
    private long stopTimer = 0;

    public void timerStart() {
        this.stopTimer = 0;
        this.strtTimer = System.currentTimeMillis();
    }

    public long timerStop() {
        this.stopTimer = System.currentTimeMillis();
        if (this.stopTimer < this.strtTimer) {
            return -1;
        }
        return this.stopTimer - this.strtTimer;
    }
}
