package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.stargame.gameobject.Background;
import ru.geekbrains.stargame.gameobject.Player;
import ru.geekbrains.stargame.template.MyScreen;
import ru.geekbrains.stargame.template.Rect;

public class GameScene extends MyScreen {
    private TextureAtlas textureAtlas;
    private Background background;
    private Player player;

    public GameScene(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        this.textureAtlas = new TextureAtlas("mainAtlas.tpack");
        this.background = new Background(new TextureRegion(new Texture("background.png")));
        this.player = new Player(0.1f , this.textureAtlas);
        this.player.setCenter(0, -25f);
        this.player.setSpeedRate(0.5f);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        this.player.calculate();

        this.spriteBatch.begin();

            this.background.draw(this.spriteBatch);
            this.player.draw(this.spriteBatch);

        this.spriteBatch.end();
    }
  
    @Override
    public void dispose() {
        super.dispose();
        this.textureAtlas.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        this.player.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        this.player.keyUp(keycode);
        return false;
    }

    @Override
    public void touchDown(Vector2 vector2, int pointer, int button) {
        this.player.touchDown(vector2, pointer, button);
    }

    @Override
    public void touchUp(Vector2 vector2, int pointer, int button) {
        this.player.touchUp(vector2, pointer, button);
    }

    @Override
    public void resize(Rect world) {
        this.background.resize(world);
        this.player.resize(world);
    }

}
