package ru.geekbrains.stargame.gameobject.Player;

import ru.geekbrains.stargame.template.Bullet;
import ru.geekbrains.stargame.template.Pool;

public class BulletPool extends Pool<Bullet> {
    private Bullet template;

    public BulletPool(Bullet template) {
        this.template = template;
    }

    @Override
    protected Bullet getNewPoolElement() {
        return this.template.clone(this);
    }

}
