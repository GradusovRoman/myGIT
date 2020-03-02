package ru.geekbrains.stargame.gameobject.enemy;

import ru.geekbrains.stargame.math.GameUtils;
import ru.geekbrains.stargame.template.Pool;

public class EnemyShipPool extends Pool<EnemyShip> {
    private final EnemyShip template;
    private int maxCountEnemy = 2;

    public EnemyShipPool(EnemyShip template) {
        this.template = template;
    }

    @Override
    protected EnemyShip getNewPoolElement() {
        return this.template.clone(this);
    }

    public int getMaxCountEnemy() {
        return maxCountEnemy;
    }

    public void setMaxCountEnemy(int maxCountEnemy) {
        this.maxCountEnemy = maxCountEnemy;
    }

    @Override
    public void calculate() {
        super.calculate();
        if (this.getUseSize() <= maxCountEnemy) {
            EnemyShip enemyShip2 = this.getFreeObject();
            enemyShip2.config(GameUtils.getRandomByRange(this.getWorldBounds().getLeft(), this.getWorldBounds().getRight()), 0.2f);
        }
    }
}
