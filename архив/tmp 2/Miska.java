public class Miska {

    protected boolean miska_Vol; //= true; //миска пустая
    protected int miskaOst;// = 170; //остаток в миске



    public  Miska (boolean miska_Vol, int miskaOst) {
        this.miska_Vol=miska_Vol;
        this.miskaOst=miskaOst;
    }

    // кот уменьшает еду в миске
   public boolean miskaEatCot (int cotAppetit) {
       miskaOst-=cotAppetit;
    //   System.out.println ("miskaOst " + miskaOst + "  cotAppetit "+cotAppetit);//   info();
       return (miskaOst >=cotAppetit) ? true: false;
   }

    //проверка полноты миски
    public boolean miskaVol (int cot_volume, int miskaOst){
        return (miskaOst>=cot_volume) ? true : false;


    }
    //заполнение миски
    public int miskaVolume (int miskaOst) {
        return miskaOst=170;
    }

    public void info() {
        System.out.println( "Еды меньше, чем аппетит кота");
      // System.out.println ("miskaOst " + miskaOst + "  cotAppetit "+cotAppetit);
    }

}
