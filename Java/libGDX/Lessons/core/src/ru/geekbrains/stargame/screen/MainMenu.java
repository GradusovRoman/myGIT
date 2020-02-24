package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.gameobject.Background;
import ru.geekbrains.stargame.gameobject.Player;
import ru.geekbrains.stargame.template.MyScreen;
import ru.geekbrains.stargame.template.Rect;

public class MainMenu extends MyScreen {
    private Background background;
    private Player player;

    @Override
    public void show() {
        super.show();
      
        this.background = new Background(new TextureRegion(new Texture("background.png")));
        this.player = new Player(10, 10 ,new TextureRegion(new Texture("badlogic.jpg")));

        this.player.setSpeedRate(0.5f);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if (this.player.isCanMove(this.worldRect)) {
            this.player.goingToDest();
        }

        this.spriteBatch.begin();

            this.background.draw(this.spriteBatch);
            this.player.draw(this.spriteBatch);

        this.spriteBatch.end();
    }
  
    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 vector2, int pointer, int button) {
        this.player.setDest(vector2);
        return false;
    }

    @Override
    public void resize(Rect world) {
        this.background.setSize(world.getWidth(), world.getHeight());
    }

}
