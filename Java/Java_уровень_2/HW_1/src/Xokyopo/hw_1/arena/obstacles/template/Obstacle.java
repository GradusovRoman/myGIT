package Xokyopo.hw_1.arena.obstacles.template;

import Xokyopo.hw_1.team.players.template.Athlete;

public abstract class Obstacle implements Overcome {
    private double cap;
    private String name;

    public Obstacle(String name, double cap) {
        this.name = name;
        this.cap = cap;
    }

    @Override
    public boolean isOvercoming(Athlete _athlete) {
        return true;
    }

    public double getCap() {
        return this.cap;
    }

    public String getName() {
        return name;
    }

    public void printingInfo() {
        System.out.println("препядствие " + this.name + "\t" + this.cap + " м");
    }
}
