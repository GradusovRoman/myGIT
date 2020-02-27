package ru.geekbrains.stargame.gameobject;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.stargame.intefaces.Calculatable;
import ru.geekbrains.stargame.intefaces.KeyPressable;
import ru.geekbrains.stargame.intefaces.Touchable;
import ru.geekbrains.stargame.template.Rect;
import ru.geekbrains.stargame.template.Sprite;

public class Player extends Sprite implements Calculatable, Touchable, KeyPressable {
    private float speedRate = 1f;
    private Vector2 speed = new Vector2(0,0);
    private Vector2 dest = new Vector2();
    private int currentKey =  -1;
    private Rect worldBounds = new Rect();

    public Player(float heightProportion, TextureAtlas textureAtlas) {
        super(heightProportion, textureAtlas.findRegion("main_ship").split(390/2,287)[0]);
    }

    public void setSpeedRate(float speedRate) {
        this.speedRate = speedRate;
    }

    @Override
    public void calculate() {
        //TODO проверка на выход за экран
        if (this.isCanMove()) {
            this.setCenter(this.getCenter().add(this.speed.cpy().scl(speedRate)));
        }
    }

    public boolean isCanMove() {
        Rect rect = this.clone();
        rect.setCenter(this.getCenter().add(this.speed.cpy().scl(speedRate)));
        return !this.worldBounds.isOutOfBound(rect);
    }

    @Override
    public void resize(Rect rectWorld) {
        super.resize(rectWorld);
        this.worldBounds.setRect(rectWorld);
    }

    @Override
    public void keyDown(int keycode) {
        if (keycode == 21 || keycode == 29) {
            this.speed.x = -1f;
            this.currentKey = keycode;
        }else if (keycode == 22 || keycode == 32) {
            this.speed.x = 1f;
            this.currentKey = keycode;
        }
    }

    @Override
    public void keyUp(int keycode) {
        if (this.currentKey == keycode) {
            this.speed.x = 0;
        }
    }

    @Override
    public void touchDown(Vector2 vector2, int pointer, int button) {
        this.speed.x = vector2.sub(0,0).nor().x;
    }

    @Override
    public void touchUp(Vector2 vector2, int pointer, int button) {
        this.speed.x = 0;
    }
}
