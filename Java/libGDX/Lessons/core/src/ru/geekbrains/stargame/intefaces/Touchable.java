package ru.geekbrains.stargame.intefaces;

import com.badlogic.gdx.math.Vector2;

public interface Touchable {
    void touchDown(Vector2 vector2, int pointer, int button);
    void touchUp(Vector2 vector2, int pointer, int button);

}
