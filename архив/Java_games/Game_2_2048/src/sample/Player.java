package sample;

import java.util.Scanner;

public class Player {
    private int comand = -1;
    private String msg = "Выберите куда сдвинуть стену (Left, Right, Up , Down): ";
    private Scanner scanner = new Scanner(System.in);

    public int getPlayerStep(){
        this.comand = -1;
        do {
            System.out.print(this.msg);
            String ansver = scanner.nextLine();
            switch (ansver) {
                case ("Left"): this.comand = Comand.Keys.LEFT;
                break;
                case ("Right"): this.comand = Comand.Keys.RIGHT;
                break;
                case ("Up"): this.comand = Comand.Keys.UP;
                break;
                case ("Down"): this.comand = Comand.Keys.DOWN;
            }
        } while (this.comand < 0);
        return comand;
    }
}
