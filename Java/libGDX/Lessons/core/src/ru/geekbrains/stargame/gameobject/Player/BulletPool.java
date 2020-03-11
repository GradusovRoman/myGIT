package ru.geekbrains.stargame.gameobject.Player;

import ru.geekbrains.stargame.template.Bullet;
import ru.geekbrains.stargame.template.Pool;

public class BulletPool extends Pool<Bullet> {

    @Override
    protected Bullet getNewPoolElement() {
        return new Bullet(this);
    }

}
