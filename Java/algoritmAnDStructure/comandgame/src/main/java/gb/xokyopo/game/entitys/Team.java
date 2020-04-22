package gb.xokyopo.game.entitys;

import java.util.*;

public class Team {
    private String name;
    private final List<Hero> heroes;
    private final Random random;
    private boolean actualList;

    public Team() {
        this.heroes = new ArrayList<>();
        this.random = new Random();
        this.name = this.getClass().getSimpleName();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void requiredNotNullObject(Object object, String msg) {
        if (object == null) throw new NullPointerException(msg);
    }

    public int size() {
        this.deleteAllNotAliveHero();
        return this.heroes.size();
    }

    public boolean isEmpty() {
        this.deleteAllNotAliveHero();
        return this.size() <= 0;
    }

    public void addHero(Hero hero) throws Exception {
        this.requiredNotNullObject(hero, "Hero can't null");
        this.heroes.add(hero);
    }

    private void deleteAllNotAliveHero() {
        if (!this.actualList)
            this.heroes.removeIf(hero -> !hero.isAlive());
    }

    public Hero getRandomHero() {
        this.actualList = false;
        this.deleteAllNotAliveHero();
        if(!this.isEmpty()) {
            return this.heroes.get(this.getRandomInt(this.heroes.size()));
        } else {
            return null;
        }
    }

    private int getRandomInt(int amplitude) {
        return this.random.nextInt(amplitude);
    }

    public List<Hero> getAll() {
        this.deleteAllNotAliveHero();
        return new ArrayList<>(this.heroes);
    }

    public List<Hero> applyHeroFilter(HeroFilter<Hero> filter) {
        List<Hero> removesHero = new ArrayList<>();
        this.heroes.removeIf(hero-> {
            if (filter.canDelete(hero)) {
                removesHero.add(hero);
                return true;
            }
            return false;
        });
        return removesHero;
    }

    public interface HeroFilter<T> {
        boolean canDelete (T hero);
    }
}
