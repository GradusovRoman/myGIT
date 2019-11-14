package Xokyopo.hw_1.team;

import Xokyopo.hw_1.team.players.Cat;
import Xokyopo.hw_1.team.players.Dog;
import Xokyopo.hw_1.team.players.Human;
import Xokyopo.hw_1.team.players.NameGenerator;
import Xokyopo.hw_1.team.players.template.Athlete;

public class Team {
    private Athlete[] athletes;
    private NameGenerator nameGenerator = new NameGenerator();

    public static final class Type {
        public static final int DOG = 0;
        public static final int HUMAN = 1;
        public static final int CAT = 2;
    }

    public Team(int... _athletes) {
        this.athletes = new Athlete[_athletes.length];
        for (int i = 0 ; i < _athletes.length ; i++) {
            this.athletes[i] = getAthletsByType(_athletes[i]);
        }
    }

    private Athlete getAthletsByType(int _type) {
        Athlete resolve = null;
        switch (_type){
            case Type.CAT: resolve = new Cat(this.nameGenerator.getRandomName());
                break;
            case Type.DOG: resolve = new Dog(this.nameGenerator.getRandomName());
                break;
            case Type.HUMAN: resolve = new Human(this.nameGenerator.getRandomName());
                break;
        }
        return resolve;
    }

    public Athlete[] getAthlete() {
        return this.athletes.clone();
    }

    public void printingInfo() {
        System.out.println("Наши Учаскини");
        for (Athlete athlete : this.athletes) {
            athlete.printingInfo();
            System.out.println("");
        }
    }
}
