package Xokyopo.hw_1.arena.obstacles;

import Xokyopo.hw_1.arena.obstacles.template.Obstacle;
import Xokyopo.hw_1.team.players.template.Athlete;

public class Wall extends Obstacle {

    public Wall(double _height) {
        super("Стена", _height);
    }

    @Override
    public boolean isOvercoming(Athlete _athlete) {
        return _athlete.isCanJump(this.getCap());
    }
}
