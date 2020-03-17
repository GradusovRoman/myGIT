package ru.geekbrains.stargame.gameobject;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.stargame.intefaces.Updatable;
import ru.geekbrains.stargame.math.GameUtils;
import ru.geekbrains.stargame.template.Rect;
import ru.geekbrains.stargame.template.Sprite;

public class Star extends Sprite implements Updatable {
    private Rect worldBounds = new Rect();
    private float scaleSeed = 0.0005f;
    private float scaleOffset = this.scaleSeed/1.1f;
    private float spedSeed = 2f;
    private float spedRateOffset = this.spedSeed/2f;

    private Vector2 speed = new Vector2(0f,-1f);
    private float scaleSpeed;
    private float spedRate;
    private float scaleProportion = 5f;
    private float startHeightProportion;
    private float minHeightProportion;

    public Star(float heightProportion, TextureRegion textureRegion) {
        super(heightProportion, textureRegion);
        this.startHeightProportion = heightProportion;
        this.minHeightProportion = startHeightProportion / this.scaleProportion;
    }

    public void init () {
        this.setRandomScaleSpeed();
        this.setRandomCenter();
        this.setRandomSpeedRate();
    }

    private void setRandomSpeedRate() {
        this.spedRate = GameUtils.getRandomByRange(this.spedSeed - this.spedRateOffset, this.spedSeed + this.spedRateOffset);
    }

    private void setRandomCenter() {
        this.setCenter(GameUtils.getRandomByRange(this.worldBounds.getLeft(), this.worldBounds.getRight()), this.worldBounds.getTop() + this.getHalfHeight());
    }

    private void setRandomScaleSpeed() {
        this.scaleSpeed = GameUtils.getRandomByRange(this.scaleSeed - this.scaleOffset, this.scaleSeed + scaleOffset);
    }

    @Override
    public void resize(Rect rectWorld) {
        super.resize(rectWorld);
        this.worldBounds = rectWorld;
    }

    @Override
    public void update(float dTime) {
        if (this.getHeightProportion() > this.minHeightProportion) {
            this.setHeightProportion(this.getHeightProportion() - this.scaleSpeed*dTime);
        } else {
            this.setHeightProportion(this.startHeightProportion);
        }

        this.setCenter(this.getCenter().add(this.speed.cpy().scl(this.spedRate).scl(dTime)));

        if (!this.worldBounds.isTouch(this)) this.init();
    }

    public Star getNewStar(Rect worldBounds) {
        Star star = new Star(this.getHeightProportion(), this.getTextureRegions()[0]);
        star.resize(worldBounds);
        star.init();

        return star;
    }
}
