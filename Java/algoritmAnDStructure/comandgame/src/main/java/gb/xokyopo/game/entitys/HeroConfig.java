package gb.xokyopo.game.entitys;

public class HeroConfig {
    private String name;
    private Classes type;
    private int maxHealth;
    private int damage;
    private int healing;
    private int criticalChance;
    private int criticalMultiply;

    public HeroConfig() {
    }

    public HeroConfig(String name, Classes type, int maxHealth, int damage, int healing, int criticalChance, int criticalMultiply) {
        this.setConfig(name, type, maxHealth, damage, healing, criticalChance, criticalMultiply);
    }

    public void setConfig(String name, Classes type, int maxHealth, int damage, int healing, int criticalChance, int criticalMultiply) {
        this.name = name;
        this.type = type;
        this.maxHealth = maxHealth;
        this.damage = damage;
        this.healing = healing;
        this.criticalChance = criticalChance;
        this.criticalMultiply = criticalMultiply;
    }

    public String getName() {
        return name;
    }

    public Classes getType() {
        return type;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealing() {
        return healing;
    }

    public int getCriticalChance() {
        return criticalChance;
    }

    public int getCriticalMultiply() {
        return criticalMultiply;
    }
}
