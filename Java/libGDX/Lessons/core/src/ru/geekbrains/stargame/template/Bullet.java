package ru.geekbrains.stargame.template;


import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.stargame.intefaces.Updatable;
import ru.geekbrains.stargame.intefaces.Damageable;
import ru.geekbrains.stargame.template.configclass.BulletConfig;

public class Bullet extends Sprite implements Updatable, Damageable {
    private Rect worldBound = new Rect();
    private Vector2 speed = new Vector2();
    private Pool<Bullet> parentPool;
    private int bulletDamage;
    private float bulletSpedRate;

    public Bullet(Pool<Bullet> parentPool) {
        this.parentPool = parentPool;
    }

    public void setBulletConfig(BulletConfig bulletConfig) {
        this.bulletSpedRate = bulletConfig.speedRate;
        this.bulletDamage = bulletConfig.damage;
        this.setTextureRegions(bulletConfig.textureRegions);
        this.setHeightProportion(bulletConfig.heightProportion);
        super.resize(this.worldBound);
    }

    @Override
    public void update(float dTime) {
        this.setCenter(this.getCenter().add(this.speed.cpy().scl(this.bulletSpedRate)));
        if (!this.worldBound.isTouch(this)) {
            this.imDestroyed();
        }
    }

    @Override
    public void resize(Rect rectWorld) {
        super.resize(rectWorld);
        this.worldBound = rectWorld;
    }

    public void config(Ship owner, BulletConfig bulletConfig) {
        this.setBulletConfig(bulletConfig);
        this.setCenter(owner.getCenter().add(owner.getShipOrientation().scl(owner.getHalfHeight())));
        //TODO нужно не забывать что у корабля есть скорость и нужно отталкиваться от нее.
        //TODO стер теперь сам не помню что там было
        this.speed.set(owner.getShipOrientation());
    }

    private void imDestroyed() {
        if (parentPool != null) {
            parentPool.imFree(this);
        }
    }

    @Override
    public void takeDamage(Damageable damage) {
        this.bulletDamage -= damage.getDamage();
        if (this.bulletDamage <= 0) this.imDestroyed();
    }

    @Override
    public int getDamage() {
        return this.bulletDamage;
    }
}
