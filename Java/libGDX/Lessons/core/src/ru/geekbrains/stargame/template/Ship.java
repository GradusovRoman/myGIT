package ru.geekbrains.stargame.template;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.stargame.gameobject.Explosion;
import ru.geekbrains.stargame.intefaces.Updatable;
import ru.geekbrains.stargame.intefaces.Damageable;
import ru.geekbrains.stargame.template.configclass.BulletConfig;
import ru.geekbrains.stargame.template.configclass.ShipConfig;

public abstract class Ship extends Sprite implements Updatable, Damageable {
    private Sound shotSound;
    private Pool<Bullet> bulletPool;
    private Pool<Explosion> explosionPool;
    private float soundVolume = 0.1f;

    private Rect worldBound = new Rect();
    private Vector2 shipOrientation = new Vector2();
    private Vector2 speed = new Vector2();
    private BulletConfig bulletConfig = new BulletConfig();
    public float speedRate;
    public float reloadingTime;
    public int health;
    private float reloadingDTime = 0f;

    private boolean drawDamaged = false;
    private float drawDamageTimer = 0f;
    private float drawDamageSec = 0.25f;

    public Ship(ShipConfig shipConfig) {
        this.setShipConfig(shipConfig);
    }

    public Ship() {
    }

    public void setShipConfig(ShipConfig shipConfig) {
        this.bulletConfig.setConfig(shipConfig.bulletConfig);
        this.speedRate = shipConfig.speedRate;
        this.reloadingTime = shipConfig.reloadingTime;
        this.health = shipConfig.health;
        this.setTextureRegions(shipConfig.textureRegions);
        this.setHeightProportion(shipConfig.heightProportion);
        this.setTextureRotateAngle(shipConfig.textureRotateAngle);
        this.reloadingDTime = 0;
        super.resize(this.worldBound); //вызываем что бы изменить размеры можедльки.
    }

    protected boolean isOutOfBound() {
        return this.worldBound.isOutOfBound(this);
    }

    public abstract void imOutOfBound();

    @Override
    public void update(float dTime) {
        this.setCenter(this.getCenter().add(this.speed.cpy().scl(this.speedRate).scl(dTime)));

        if (this.reloadingDTime >= this.reloadingTime) {
            this.shot();
            this.reloadingDTime = 0f;
        } else {
            this.reloadingDTime +=dTime;
        }

        if (this.isOutOfBound()) {
            this.imOutOfBound();
        }

        if(this.drawDamaged) {
            this.drawDamageTimer+=(dTime/this.drawDamageSec);
            this.setFrame((int)Math.ceil(this.drawDamageTimer));
            if (this.getFrame() >= getTextureRegions().length) {
                this.setFrame(0);
                this.drawDamaged = false;
            }
        }
    }

    private void shot () {
        if (this.shotSound != null) this.shotSound.play(this.soundVolume);
        if(this.bulletPool != null) this.bulletPool.getFreeObject().config(this, this.bulletConfig);
    }

    //вектор опрееляющий куда смотрит нос корабля.
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

    public void setSpeed(Vector2 speed) {
        //TODO сделал нормализацию входящго вектора направления
        this.speed.set(speed).nor();
    }

    public void setShotSound(Sound shotSound) {
        this.shotSound = shotSound;
    }

    public void setBulletPool(Pool<Bullet> bulletPool) {
        this.bulletPool = bulletPool;
    }

    public Vector2 getShipOrientation() {
        return shipOrientation.cpy();
    }

    public Rect getWorldBound() {
        return worldBound.clone();
    }

    public void setExplosionPool(Pool<Explosion> explosionPool) {
        this.explosionPool = explosionPool;
    }

    protected void imDestroyed() {
        if (this.explosionPool != null) {
            this.explosionPool.getFreeObject().startOnRect(this);
        }
    }

    @Override
    public void takeDamage(Damageable damage) {
        this.health -= damage.getDamage();
        this.drawDamageTimer = 0f;
        this.drawDamaged = true;
        if (this.health <= 0) {
            this.drawDamaged = false;
            this.imDestroyed();
        }
    }

    @Override
    public int getDamage() {
        return this.health;
    }

    public void setSoundVolume(float soundVolume) {
        if (soundVolume <= 1 && soundVolume >= 0) {
            this.soundVolume = soundVolume;
        }
    }

    public float getReloadingDTime() {
        return reloadingDTime;
    }

    public float getReloadingTime() {
        return reloadingTime;
    }

    public void setReloadingDTime(float reloadingDTime) {
        this.reloadingDTime = reloadingDTime;
    }

    public int getHealth() {
        return health;
    }
}
