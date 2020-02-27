package ru.geekbrains.stargame.gameobject.buttons;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.geekbrains.stargame.screen.GameScene;
import ru.geekbrains.stargame.template.GameButton;


public class PlayButton extends GameButton {
    private Game game;
    public PlayButton(TextureAtlas textureAtlas, Game game) {
        super(textureAtlas, "bt_play");
        this.game = game;
    }

    @Override
    public void run() {
        game.setScreen(new GameScene(this.game));
    }
}
