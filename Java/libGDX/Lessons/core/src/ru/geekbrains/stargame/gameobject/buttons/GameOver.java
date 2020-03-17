package ru.geekbrains.stargame.gameobject.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.geekbrains.stargame.template.GameButton;

public class GameOver extends GameButton {
    public GameOver(TextureAtlas textureAtlas) {
        super(textureAtlas, "message_game_over");
    }

    @Override
    public void run() {
        Gdx.app.exit();
    }
}
