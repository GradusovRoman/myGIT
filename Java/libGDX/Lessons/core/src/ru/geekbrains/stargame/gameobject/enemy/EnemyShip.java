package ru.geekbrains.stargame.gameobject.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.geekbrains.stargame.template.Pool;
import ru.geekbrains.stargame.template.Ship;

public class EnemyShip extends Ship {
    private Pool<EnemyShip> enemyShipPool;

    public EnemyShip(float heightProportion, TextureRegion... textureRegions) {
        super(heightProportion, textureRegions);
    }

    @Override
    protected boolean isOutOfBound() {
        return !this.getWorldBound().isTouch(this);
    }

    @Override
    public void imOutOfBound() {
        if (this.enemyShipPool != null) this.enemyShipPool.imFree(this);
    }

    public void config(float startX, float speedRate) {
        this.setSpeedRate(speedRate);
        this.setCenter(startX, this.getWorldBound().getTop() + this.getHalfHeight());
        this.setSpeed(this.getShipOrientation());
    }

    public EnemyShip clone(Pool<EnemyShip> shipPool) {
        EnemyShip enemyShip = new EnemyShip(this.getHeightProportion(), getTextureRegions());
        enemyShip.setShotSound(this.getShotSound());
        enemyShip.setBulletPool(this.getBulletPool());
        enemyShip.setEnemyShipPool(shipPool);
        enemyShip.setAngle(this.getAngle());
        return enemyShip;
    }

    private void setEnemyShipPool(Pool<EnemyShip> enemyShipPool) {
        this.enemyShipPool = enemyShipPool;
    }

    //TODO текстуры врагов вверх ногами, а я все завязал на поворот, так как задумался о том что корабли могут летать и под углами.
    //TODO это конечно было жест ькогда кораблики не туда полетели :)
    @Override
    public void draw(SpriteBatch spriteBatch) {
        TextureRegion texture = this.getTextureRegions()[this.getFrame()];
        spriteBatch.draw(texture,
                this.getLeft() , this.getBottom(),
                this.getHalfWidth(), this.getHalfHeight(),
                this.getWidth(), this.getHeight(),
                1, 1 ,
                this.getAngle() - 180);
    }

}
