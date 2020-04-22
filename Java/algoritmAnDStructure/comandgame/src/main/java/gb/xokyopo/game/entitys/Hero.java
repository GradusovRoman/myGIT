package gb.xokyopo.game.entitys;

import java.util.Random;

public class Hero {
    private String name;
    private Classes type;
    private int maxHealth;
    private int damage;
    private int healing;
    private int criticalChance;
    private int criticalMultiply;
    private int currentHealth;
    private int criticalRate;
    private Random random;

    public Hero(String name, Classes type, int maxHealth, int damage, int healing, int criticalChance, int criticalMultiply) {
        this.init(name, type, maxHealth, damage, healing, criticalChance, criticalMultiply);
    }

    public Hero(HeroConfig heroConfig) {
        if (heroConfig == null) throw new NullPointerException("heroConfig can't be nuul");
        this.init(
                heroConfig.getName(),
                heroConfig.getType(),
                heroConfig.getMaxHealth(),
                heroConfig.getDamage(),
                heroConfig.getHealing(),
                heroConfig.getCriticalChance(),
                heroConfig.getCriticalMultiply());
    }

    public void init(String name, Classes type, int maxHealth, int damage, int healing, int criticalChance, int criticalMultiply) {
        this.name = name;
        this.type = type;
        this.maxHealth = maxHealth;
        this.damage = damage;
        this.healing = healing;
        this.criticalChance = criticalChance;
        this.criticalMultiply = criticalMultiply;
        this.currentHealth = this.maxHealth;
        this.random = new Random();
    }


    public Classes getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public int getDamage() {
        return (this.isCritical())? (this.damage * this.criticalMultiply / 100) : this.damage;
    }

    public int getHealing() {
        return (this.isCritical()) ? (this.healing * this.criticalMultiply / 100) : this.healing;
    }

    public boolean isAlive() {
        //TODO тут была ошибка
        return this.currentHealth > 0;
    }

    public void takeDamage(int damage) {
        this.currentHealth -= damage;
    }

    public void takeHealing(int healing) {
        this.currentHealth += healing;
        if (this.currentHealth > this.maxHealth) this.currentHealth = this.maxHealth;
    }

    public int getHealth() {
        return currentHealth;
    }

    private boolean isCritical() {
        if (this.random.nextInt(100) <= (this.criticalRate + this.criticalChance)) {
            this.criticalRate = 0;
            return true;
        } else {
            this.criticalRate += this.random.nextInt(this.criticalChance);
            return false;
        }
    }

    public boolean isCriticalHealing(int healing) {
        return this.healing < healing;
    }

    public boolean isCriticalDamage(int damage) {
        return this.damage < damage;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "\nname = '" + name + '\'' +
                "\ntype = '" + type +
                "\nHealth = " + currentHealth +
                "\nDamage = " + damage +
                "\nHealing = " + healing +
                "\nCriticalChance = " + criticalChance +
                "\nCriticalMultiply = " + criticalMultiply +
                "\nCriticalRate = " + criticalRate +
                '}';
    }
}
