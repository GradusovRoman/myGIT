package Xokyopo.hw_1.team.players;

import Xokyopo.hw_1.team.players.template.Athlete;

public class Dog extends Athlete {
    public Dog(String name, double runDistance, double jumpHeight, double swimDistance) {
        super(name, "Собака", runDistance, jumpHeight, swimDistance);
    }

    public Dog(String name) {
        super(name, "Собака", 500, 5, 20);
    }
}
