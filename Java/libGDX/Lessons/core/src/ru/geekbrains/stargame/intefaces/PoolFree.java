package ru.geekbrains.stargame.intefaces;

import ru.geekbrains.stargame.template.Sprite;

public interface PoolFree <T extends Sprite> {
    void imFree(T object);
}
