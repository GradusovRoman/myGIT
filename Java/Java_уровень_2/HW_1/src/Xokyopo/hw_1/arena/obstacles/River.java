package Xokyopo.hw_1.arena.obstacles;

import Xokyopo.hw_1.arena.obstacles.template.Obstacle;
import Xokyopo.hw_1.team.players.template.Athlete;

public class River extends Obstacle {

    public River(double _width) {
        super("Река", _width);
    }

    @Override
    public boolean isOvercoming(Athlete _athlete) {
        return _athlete.isCanSwim(this.getCap());
    }
}
