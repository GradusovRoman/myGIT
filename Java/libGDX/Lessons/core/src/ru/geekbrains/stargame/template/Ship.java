package ru.geekbrains.stargame.template;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.stargame.intefaces.Calculatable;

/**
 * 1.должен летать по всем направлениям.
 * 2.должен уметь стрелять.
 * 3.действие при выходе за границы экрана
 **/

public abstract class Ship extends Sprite implements Calculatable {
    private Vector2 shipOrientation = new Vector2();
    private Rect worldBound = new Rect();
    private float speedRate = 1f;
    private Sound shotSound;
    private Pool<Bullet> bulletPool;
    private int shotWaitRate = 30;
    private int shotWaitCount = 0;
    private Vector2 speed = new Vector2();

    public Ship(float heightProportion, TextureRegion... textureRegions) {
        super(heightProportion, textureRegions);
    }

    protected boolean isOutOfBound() {
        return this.worldBound.isOutOfBound(this);
    }

    public abstract void imOutOfBound();

    @Override
    public void calculate() {
        this.setCenter(this.getCenter().add(this.speed.cpy().scl(this.speedRate)));
        this.shot();

        if (this.isOutOfBound()) {
            this.imOutOfBound();
        }
    }

    private void shot () {
        if (this.shotWaitCount >= this.shotWaitRate) {
            if (this.shotSound != null) this.shotSound.play(0.2f);
            if(this.bulletPool != null) this.bulletPool.getFreeObject().config(this, 1f);
            this.shotWaitCount = 0;
        } else {
            this.shotWaitCount++;
        }
    }

    private void getShipOrientationInWorld() {
        this.shipOrientation.set(this.getCenter().x, this.getTop())
                .sub(this.getCenter())
                .nor()
                .rotate(this.getAngle());
    }

    @Override
    public void resize(Rect rectWorld) {
        super.resize(rectWorld);
        this.worldBound = rectWorld;
        this.getShipOrientationInWorld();
    }

    @Override
    public void setAngle(float angle) {
        super.setAngle(angle);
        this.getShipOrientationInWorld();
    }

    public Vector2 getSpeed() {
        return speed.cpy();
    }

    public void setSpeed(Vector2 speed) {
        this.speed = speed;
    }


    public Sound getShotSound() {
        return shotSound;
    }

    public void setShotSound(Sound shotSound) {
        this.shotSound = shotSound;
    }

    public Pool<Bullet> getBulletPool() {
        return bulletPool;
    }

    public void setBulletPool(Pool<Bullet> bulletPool) {
        this.bulletPool = bulletPool;
    }

    public int getShotWaitRate() {
        return shotWaitRate;
    }

    public void setShotWaitRate(int shotWaitRate) {
        this.shotWaitRate = shotWaitRate;
    }

    public Vector2 getShipOrientation() {
        return shipOrientation.cpy();
    }

    public Rect getWorldBound() {
        return worldBound.clone();
    }

    public float getSpeedRate() {
        return speedRate;
    }

    public void setSpeedRate(float speedRate) {
        this.speedRate = speedRate;
    }
}
