package xokyopo.race.objects.interfaces;

import java.util.ArrayList;

public interface Player{
    String getName();
    int getSpeed();
    void go(ArrayList<Walkable> stages, ReadyChecker readyChecker, Finished message);
}
