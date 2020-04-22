package gb.xokyopo.game.factorys;

import gb.xokyopo.game.entitys.Classes;
import gb.xokyopo.game.entitys.Hero;
import gb.xokyopo.game.entitys.HeroConfig;

import java.util.Random;

public class HeroFactory {
    private static HeroFactory heroFactory;
    private HeroNameFactory nameFactory;
    private Random random;


    private final Classes[] classesArr = {Classes.WARRIOR, Classes.HEALER, Classes.ASSASSIN};
    private final int[] minDamage = {50, 0, 100};
    private final int[] maxDamage = {100, 0, 200};

    private final int[] minHealth = {1500, 500, 500};
    private final int[] maxHealth = {2000, 1500, 1000};

    private final int[] minHealing = {0, 100, 0};
    private final int[] maxHealing = {0, 200, 0};

    private final int[] minCriticalChance = {1, 20, 40};
    private final int[] maxCriticalChance = {20, 40, 60};

    private final int[] minCriticalMultiply = {125, 150, 200};
    private final int[] maxCriticalMultiply = {150, 200, 400};


    public HeroFactory() {
        this.random = new Random();
        this.nameFactory  = HeroNameFactory.getHeroNameFactory();
    }

    public static HeroFactory getHeroFactory() {
        if (HeroFactory.heroFactory == null)
            HeroFactory.heroFactory = new HeroFactory();
        return HeroFactory.heroFactory;
    }

    private void requiredNotNullObject(Object obj, String objName) {
        if (obj == null) throw new NullPointerException(objName + " can't be NULL");
    }

    public Hero getHero(Classes classes) {
        this.requiredNotNullObject(classes, "Classes");
        return new Hero(this.getHeroConfig(classes));
    }

    public HeroConfig getHeroConfig(Classes classes){
        int typeNumber = this.getClassIndex(classes);

        return new HeroConfig(
                this.nameFactory.getRandomName(),
                this.classesArr[typeNumber],
                this.getRandomInt(this.minHealth[typeNumber], this.maxHealth[typeNumber]),
                this.getRandomInt(this.minDamage[typeNumber], this.maxDamage[typeNumber]),
                this.getRandomInt(this.minHealing[typeNumber], this.maxHealing[typeNumber]),
                this.getRandomInt(this.minCriticalChance[typeNumber], this.maxCriticalChance[typeNumber]),
                this.getRandomInt(this.minCriticalMultiply[typeNumber], this.maxCriticalMultiply[typeNumber])
        );
    }

    public int getClassIndex(Classes classes) {
        for (int i = 0; i < this.classesArr.length; i++) {
            if (this.classesArr[i].equals(classes)) return i;
        }
        throw new RuntimeException(classes.name() + " нет настроек для такого класса героя");
    }

    public int getRandomInt(int start, int stop) {
        if ((stop - start) == 0) return 0;
        return start + this.random.nextInt(stop - start);
    }
}
