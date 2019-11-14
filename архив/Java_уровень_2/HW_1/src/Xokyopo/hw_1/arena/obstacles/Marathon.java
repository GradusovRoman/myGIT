package Xokyopo.hw_1.arena.obstacles;

import Xokyopo.hw_1.arena.obstacles.template.Obstacle;
import Xokyopo.hw_1.team.players.template.Athlete;

public class Marathon extends Obstacle {

    public Marathon(double _length) {
        super("Марафон", _length);
    }

    @Override
    public boolean isOvercoming(Athlete _athlete) {
        return _athlete.isCanRun(this.getCap());
    }
}
