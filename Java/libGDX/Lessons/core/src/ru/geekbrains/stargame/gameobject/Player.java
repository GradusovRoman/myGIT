package ru.geekbrains.stargame.gameobject;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.template.Rect;
import ru.geekbrains.stargame.template.Sprite;

public class Player extends Sprite {
    private float speedRate;
    private Vector2 speed;
    private Vector2 dest;

    public Player(float width, float height, TextureRegion... textureRegions) {
        super(width, height, textureRegions);
        this.init();
    }

    public Player(float scale, TextureRegion... textureRegions) {
        super(scale, textureRegions);
        this.init();
    }

    private void init() {
        this.speed = new Vector2();
        this.speedRate = 0;
        this.dest = new Vector2();
    }

    public void setSpeedRate(float speedRate) {
        this.speedRate = speedRate;
    }

    public void setDest(Vector2 dest) {
        this.dest.set(dest);
        this.speed.set(this.dest).sub(this.getCenter()).nor();
    }

    public void goingToDest() {
        if (this.getCenter().sub(this.dest).len() > 0) {

            //TODO проверяю как изменится направление вектора
            Vector2 destVector = this.getCenter().sub(this.dest).scl(-1f);

            Vector2 mySpeed = (this.speedRate < destVector.len()) ? this.speed.cpy().scl(this.speedRate) : destVector;

            this.setCenter(this.getCenter().add(mySpeed));
        }
    }

    public boolean isCanMove(Rect world) {
        boolean result = false;
        if (this.getCenter().sub(this.dest).len() > 0) {
            Rect rect = this.clone();
            rect.setCenter(rect.getCenter().add(this.speed.cpy().scl(this.speedRate)));
            result = !world.isOutOfBound(rect);
            if (!result) {
                this.setDest(this.getCenter());
            }
        }
        return result;
    }
}
