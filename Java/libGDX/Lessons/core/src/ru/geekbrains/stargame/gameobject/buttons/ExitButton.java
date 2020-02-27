package ru.geekbrains.stargame.gameobject.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.geekbrains.stargame.template.GameButton;


public class ExitButton extends GameButton {
    public ExitButton(TextureAtlas textureAtlas) {
        super(textureAtlas, "bt_exit");
    }

    @Override
    public void run() {
        Gdx.app.exit();
    }
}
