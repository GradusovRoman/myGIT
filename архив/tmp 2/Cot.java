
public class Cot {

    protected String name;
    protected int appetit;//сколько за раз сьедает кот, не путать с объемом желудка кота
     int appetit1;// какой объес съел кот
    protected int cot_volume;// = 50; //объем желудка кота
    protected boolean sitost;// = false; //сытость кота
    private boolean miskaVol; //естьли еда в миске

    public Cot (int appetit, int cot_volume,boolean sitost) {
        this.appetit = appetit;
        this.sitost = sitost;
        this.cot_volume = cot_volume;
    }

    // кот ест
    public void  eatFromMiska (int appetit,int cot_volume, Miska mec) {

        do {
            appetit1+=appetit;
             miskaVol = mec.miskaEatCot(appetit);

//info();
            if (appetit1 == cot_volume) {
              sitost = true;
            }
      //      System.out.println("miskaVol " + miskaVol + "  sitost " + sitost);
        }
        while (sitost == false & miskaVol==true);
    }
    public void info() {

        System.out.println("sitost " + sitost + " " + appetit1);
    }
}



