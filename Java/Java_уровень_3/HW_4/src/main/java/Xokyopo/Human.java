package Xokyopo;

public class Human extends Thread{
    private MFUDriver printer;
    private String name;
    private int actionNamber;
    private String[] actionName = {"сканировать","копировать","печатать"};

    public Human(String name, MFUDriver printer, int actionNamber) {
        if (actionNamber < 0 || actionNamber >= this.actionName.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.name = name;
        this.printer = printer;
        this.actionNamber = actionNamber;
    }

    @Override
    public void run() {
        System.out.println(name + ": я подошел к принтеру что бы " + this.actionName[this.actionNamber]);
        if (this.actionNamber == 0) {
            this.printer.scan(this.name);
        } else if (this.actionNamber == 1) {
            this.printer.copy(this.name);
        } else if (this.actionNamber == 2) {
            this.printer.print(this.name);
        }
        System.out.println(name + ": я закончил " + this.actionName[this.actionNamber]);
    }
}
