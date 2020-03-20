package ru.geekbrains.stargame.gameobject.enemy;

import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.stargame.intefaces.Damageable;
import ru.geekbrains.stargame.template.Pool;
import ru.geekbrains.stargame.template.Ship;
import ru.geekbrains.stargame.template.configclass.ShipConfig;

public class EnemyShip extends Ship {
    private Pool<EnemyShip> enemyShipPool;

    public EnemyShip(Pool<EnemyShip> enemyShipPool) {
        this.enemyShipPool = enemyShipPool;
        this.setSoundVolume(1f);
    }

    private boolean imOnScreen() {
        return this.getWorldBound()
                .isInside(new Vector2(this.getLeft(), this.getTop()))
                && this.getWorldBound()
                .isInside(new Vector2(this.getRight(), this.getBottom()));
    }

    @Override
    public void update(float dTime) {
        if (this.imOnScreen()) {
            super.update(dTime);
        } else {
            this.setCenter(this.getCenter().add(this.getShipOrientation().cpy().scl(dTime * 5)));
        }
    }

    @Override
    protected boolean isOutOfBound() {
        return !this.getWorldBound().isTouch(this);
    }

    @Override
    public void imOutOfBound() {
        if (this.enemyShipPool != null) this.enemyShipPool.imFree(this);
    }

    public void config(float startX, ShipConfig shipConfig) {
        this.setShipConfig(shipConfig);

        float newX = startX;
        if(startX - this.getHalfWidth() < this.getWorldBound().getLeft()) {
            newX = this.getWorldBound().getLeft() + this.getHalfWidth();
        }
        if (startX + this.getHalfWidth() > this.getWorldBound().getRight()) {
            newX = this.getWorldBound().getRight() - this.getHalfWidth();
        }

        this.setCenter(newX, this.getWorldBound().getTop() + this.getHalfHeight());
        this.setAngle(-180); // вражеские корабли должны лететь вниз
        this.setSpeed(this.getShipOrientation());
    }

    @Override
    protected void imDestroyed() {
        super.imDestroyed();
        this.imOutOfBound();
    }

    @Override
    public void takeDamage(Damageable damage) {
        //TODO пока на экране полностью не отрисавался у корабля имунитет к урону.
        if(this.imOnScreen()) {
            super.takeDamage(damage);
        }
    }
}
