package xokyopo.race.objects.template;

import xokyopo.race.objects.interfaces.Player;
import xokyopo.race.objects.interfaces.Walkable;

public abstract class Stage implements Walkable {
    protected int length;
    protected String description;

    public String getDescription() {
        return description;
    }

    @Override
    public void go(Player c) {

    }
}
