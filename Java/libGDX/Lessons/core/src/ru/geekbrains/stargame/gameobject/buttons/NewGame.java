package ru.geekbrains.stargame.gameobject.buttons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.geekbrains.stargame.intefaces.Commandable;
import ru.geekbrains.stargame.template.GameButton;

public class NewGame extends GameButton {
    private Commandable commandable;

    public NewGame(TextureAtlas textureAtlas, Commandable commandable) {
        super(textureAtlas, "button_new_game");
        this.commandable = commandable;
    }

    @Override
    public void run() {
        this.commandable.run();
    }
}
