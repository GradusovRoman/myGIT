package ru.geekbrains.stargame.template;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.stargame.intefaces.Calculatable;
import ru.geekbrains.stargame.intefaces.PoolFree;

public class Bullet extends Sprite implements Calculatable {
    private Rect worldBound = new Rect();
    private Vector2 speed = new Vector2();
    private PoolFree<Bullet> parentPool;

    private float speedRate = 1f;
    private Ship owner;

    public Bullet(float heightProportion, TextureRegion... textureRegions) {
        super(heightProportion, textureRegions);
    }

    @Override
    public void calculate() {
        this.setCenter(this.getCenter().add(this.speed.cpy().scl(this.speedRate)));
        if (!this.worldBound.isTouch(this)) {
            if (parentPool != null) {
                parentPool.imFree(this);
            } else {
                System.err.println("Утечка памяти в классе Bullet");
            }
        }
    }

    @Override
    public void resize(Rect rectWorld) {
        super.resize(rectWorld);
        this.worldBound = rectWorld;
    }

    public void config(Ship owner, float speedRate) {
        this.owner = owner;
        this.speedRate = speedRate;
        this.setCenter(owner.getCenter().add(owner.getShipOrientation().scl(owner.getHalfHeight())));
        this.speed.set(owner.getShipOrientation()).scl(this.speedRate);
    }

    private void setParentPool(PoolFree<Bullet> parentPool) {
        this.parentPool = parentPool;
    }

    public Bullet clone(PoolFree<Bullet> parentPool) {
        Bullet bullet= new Bullet(this.getHeightProportion(), this.getTextureRegions());
        bullet.setParentPool(parentPool);
        return bullet;
    }
}
