package ru.geekbrains.stargame.gameobject.enemy;

import com.badlogic.gdx.audio.Sound;
import ru.geekbrains.stargame.gameobject.Explosion;
import ru.geekbrains.stargame.gameobject.ShipConfiguration;
import ru.geekbrains.stargame.math.GameUtils;
import ru.geekbrains.stargame.template.Bullet;
import ru.geekbrains.stargame.template.Pool;

public class EnemyShipPool extends Pool<EnemyShip> {
    private int maxCountEnemy = 2;
    private float shipSpawningTime = 5f;
    private float shipSpawningDTimer = 0;
    private Pool<Explosion> explosionPool;
    private Pool<Bullet> bulletPool;
    private Sound bulletSound;
    private ShipConfiguration shipConfiguration;

    public EnemyShipPool(Pool<Explosion> explosionPool, Pool<Bullet> bulletPool, Sound bulletSound, ShipConfiguration shipConfiguration) {
        this.shipConfiguration = shipConfiguration;
        this.explosionPool = explosionPool;
        this.bulletPool = bulletPool;
        this.bulletSound = bulletSound;
    }

    @Override
    protected EnemyShip getNewPoolElement() {
        EnemyShip enemyShip = new EnemyShip(this);
        enemyShip.setShotSound(this.bulletSound);
        enemyShip.setExplosionPool(this.explosionPool);
        enemyShip.setBulletPool(this.bulletPool);
        return enemyShip;
    }

    @Override
    public void update(float dTime) {
        super.update(dTime);
        if (this.getUseSize() < maxCountEnemy) {
            if (this.shipSpawningDTimer >= this.shipSpawningTime) {
                EnemyShip enemyShip2 = this.getFreeObject();
                enemyShip2.resize(this.getWorldBounds());
                enemyShip2.startOnXAxis(GameUtils.getRandomByRange(this.getWorldBounds().getLeft(), this.getWorldBounds().getRight()), this.shipConfiguration.getConfig(("enemy" + Math.round(GameUtils.getRandomByRange(0, 2)))));
                enemyShip2.setReloadingDTime(enemyShip2.getReloadingTime());
                this.shipSpawningDTimer = 0;
            } else {
                this.shipSpawningDTimer +=dTime;
            }
        }
    }
}
