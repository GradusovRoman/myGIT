package ru.geekbrains.stargame.gameobject.Player;

import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.stargame.intefaces.KeyPressable;
import ru.geekbrains.stargame.intefaces.Touchable;
import ru.geekbrains.stargame.template.Ship;

//TODO пофиксить нажатие клавишь вынести движения в отдельный метод
public class Player extends Ship implements Touchable, KeyPressable {
    private int currentKey = -1;
    private int currentMouseButton = -1;
    private int currentPointer = -1;

    private void moveLeft() {
        this.setSpeed(new Vector2(-1, 0));
    }

    private void moveRight() {
        this.setSpeed(new Vector2(1, 0));
    }

    private void moveStop() {
        this.setSpeed(new Vector2(0, 0));
    }

    @Override
    public void keyDown(int keycode) {
        if (keycode == 21 || keycode == 29) {
            this.moveLeft();
            this.currentKey = keycode;
        } else if (keycode == 22 || keycode == 32) {
            this.moveRight();
            this.currentKey = keycode;
        }
    }

    @Override
    public void keyUp(int keycode) {
        if (this.currentKey == keycode) {
            this.moveStop();
        }
    }

    @Override
    public void touchDown(Vector2 vector2, int pointer, int button) {
        this.currentPointer = pointer;
        this.currentMouseButton = button;
        if (vector2.sub(0, 0).nor().x > 0) this.moveRight();
        else this.moveLeft();
    }

    @Override
    public void touchUp(Vector2 vector2, int pointer, int button) {
        if (currentPointer == pointer && currentMouseButton == button) {
            this.moveStop();
        }
    }

    @Override
    public void imOutOfBound() {
        if (this.getLeft() < this.getWorldBound().getLeft()) {
            this.setCenter(this.getWorldBound().getLeft() + this.getHalfWidth(), this.getCenter().y);
        } else if (this.getRight() > this.getWorldBound().getRight()) {
            this.setCenter(this.getWorldBound().getRight() - this.getHalfWidth(), this.getCenter().y);
        }
    }

}
