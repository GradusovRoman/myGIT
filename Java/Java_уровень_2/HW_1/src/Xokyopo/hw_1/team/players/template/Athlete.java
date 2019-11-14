package Xokyopo.hw_1.team.players.template;

import Xokyopo.hw_1.team.Team;

public abstract  class Athlete implements Abilities{
    private String name;
    private String type;
    private double runDistance;
    private double jumpHeight;
    private double swimDistance;

    public Athlete(String name, String type, double runDistance, double jumpHeight, double swimDistance) {
        this.name = name;
        this.type = type;
        this.runDistance = runDistance;
        this.jumpHeight = jumpHeight;
        this.swimDistance = swimDistance;
    }

    @Override
    public boolean isCanRun(double _runDistance) {
        return _runDistance <= this.runDistance;
    }

    @Override
    public boolean isCanJump(double _jumpHeight) {
        return _jumpHeight <= this.jumpHeight;
    }

    @Override
    public boolean isCanSwim(double _swimDistance) {
        return  _swimDistance <= this.swimDistance;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public void printingInfo() {
        System.out.println(this.type + "\t" + this.name);
        System.out.println("Может пробежать на \t" + this.runDistance + " м");
        System.out.println("Может прыгать на \t" + this.jumpHeight + " м");
        System.out.println("Может проплыть на\t" + this.swimDistance + " м");
    }
}
