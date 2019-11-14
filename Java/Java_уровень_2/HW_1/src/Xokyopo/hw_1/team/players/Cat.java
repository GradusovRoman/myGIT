package Xokyopo.hw_1.team.players;

import Xokyopo.hw_1.team.players.template.Athlete;

public class Cat extends Athlete {
    public Cat(String name, double runDistance, double jumpHeight, double swimDistance) {
        super(name, "Кот", runDistance, jumpHeight, swimDistance);
    }

    public Cat(String name) {
        super(name, "Кот", 200, 20, 0);
    }
}
