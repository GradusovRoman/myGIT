package Xokyopo.hw_1.team.players;

import Xokyopo.hw_1.team.players.template.Athlete;

public class Human extends Athlete {
    public Human(String name, double runDistance, double jumpHeight, double swimDistance) {
        super(name, "Человек", runDistance, jumpHeight, swimDistance);
    }

    public Human(String name) {
        super(name, "Человек", 5000, 30, 200);
    }
}
