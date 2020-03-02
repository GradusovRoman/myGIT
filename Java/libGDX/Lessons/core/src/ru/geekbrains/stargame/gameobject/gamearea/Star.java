package ru.geekbrains.stargame.gameobject.gamearea;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.stargame.intefaces.Calculatable;
import ru.geekbrains.stargame.math.GameUtils;
import ru.geekbrains.stargame.template.Rect;
import ru.geekbrains.stargame.template.Sprite;

public class Star extends Sprite implements Calculatable {
    private Rect worldBounds = new Rect();
    private int timer = 60;
    private int count = 0;
    private float scaleSpeed = 0.05f;
    private float scaleMin = 0.0005f;
    private float scaleMax = 0.01f;
    private float spedRateMin = 0.01f;
    private float spedRateMax = 0.3f;
    private float spedRate = 1f;
    private Vector2 speed = new Vector2(0f,-1f);

    public Star(float heightProportion, TextureRegion textureRegion) {
        super(heightProportion, textureRegion);
    }

    public void init () {
        this.setRandomScaleSpeed();
        this.setRandomCenter();
        this.setRandomSpeedRate();
    }

    private void setRandomSpeedRate() {
        this.spedRate = GameUtils.getRandomByRange(this.spedRateMin, this.spedRateMax);
    }

    private void setRandomCenter() {
        this.setCenter(GameUtils.getRandomByRange(this.worldBounds.getLeft(), this.worldBounds.getRight()), this.worldBounds.getTop() + this.getHalfHeight());
    }

    private void setRandomScaleSpeed() {
        this.scaleSpeed = GameUtils.getRandomByRange(this.scaleMin, this.scaleMax);
    }

    @Override
    public void resize(Rect rectWorld) {
        super.resize(rectWorld);
        this.worldBounds = rectWorld;
    }

    @Override
    public void calculate() {
        if (count >= timer) {
            count = 0;
        } else {
            count++;
            this.setScale(this.getScale() - this.scaleSpeed);
        }

        if (this.getScale() <= 0.3f) this.setScale(1f);

        this.setCenter(this.getCenter().add(this.speed.cpy().scl(this.spedRate)));

        if (!this.worldBounds.isTouch(this)) this.init();
    }

    public Star getNewStar(Rect worldBounds) {
        Star star = new Star(this.getHeightProportion(), this.getTextureRegion());
        star.resize(worldBounds);
        star.init();

        return star;
    }
}
