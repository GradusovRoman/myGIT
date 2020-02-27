package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.stargame.gameobject.Background;
import ru.geekbrains.stargame.gameobject.ButtonMenu;
import ru.geekbrains.stargame.gameobject.buttons.ExitButton;
import ru.geekbrains.stargame.gameobject.buttons.PlayButton;
import ru.geekbrains.stargame.template.MyScreen;
import ru.geekbrains.stargame.template.Rect;

public class GameMenu extends MyScreen {
    private Background background;
    private TextureAtlas atlas;
    private ButtonMenu buttonMenu;
    private PlayButton playButton;
    private ExitButton exitButton;

    public GameMenu(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        this.background = new Background(new TextureRegion(new Texture("background.png")));
        this.atlas = new TextureAtlas("button.pack");
        this.playButton = new PlayButton(atlas, this.game);
        this.exitButton = new ExitButton(atlas);
        this.buttonMenu = new ButtonMenu();
        this.buttonMenu.addButton(exitButton);
        this.buttonMenu.addButton(playButton);

        this.buttonMenu.setSize(100,25);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        this.spriteBatch.begin();

        this.background.draw(this.spriteBatch);
        this.buttonMenu.draw(this.spriteBatch);

        this.spriteBatch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        this.atlas.dispose();
    }

    @Override
    public void resize(Rect world) {
        this.background.resize(world);
        this.buttonMenu.resize(world);
    }

    @Override
    public void touchDown(Vector2 vector2, int pointer, int button) {
        this.buttonMenu.touchDown(vector2, pointer, button);
    }

    @Override
    public void touchUp(Vector2 vector2, int pointer, int button) {
        this.buttonMenu.touchUp(vector2, pointer, button);
    }
}
