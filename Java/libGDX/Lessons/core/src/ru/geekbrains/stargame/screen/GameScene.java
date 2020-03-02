package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.stargame.gameobject.Player.BulletPool;
import ru.geekbrains.stargame.gameobject.Player.Player;
import ru.geekbrains.stargame.gameobject.enemy.EnemyShip;
import ru.geekbrains.stargame.gameobject.enemy.EnemyShipPool;
import ru.geekbrains.stargame.gameobject.gamearea.Background;
import ru.geekbrains.stargame.gameobject.gamearea.Star;
import ru.geekbrains.stargame.gameobject.gamearea.StarEmulator;
import ru.geekbrains.stargame.math.GameUtils;
import ru.geekbrains.stargame.template.Bullet;
import ru.geekbrains.stargame.template.MyScreen;
import ru.geekbrains.stargame.template.Rect;

public class GameScene extends MyScreen {
    private TextureAtlas textureAtlas;
    private TextureAtlas starAtlas;
    private Background background;
    private Player player;
    private StarEmulator starEmulator;
    private BulletPool bulletPool;
    private Sound bulletSound;
    private Music backgroundSound;
    private EnemyShipPool enemyShipPool;
    private BulletPool enemyBulletPool;

    public GameScene(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        this.bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
        this.backgroundSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        this.textureAtlas = new TextureAtlas("textures/mainAtlas.tpack");
        this.starAtlas = new TextureAtlas("textures/button.pack");
        this.background = new Background(new TextureRegion(new Texture("textures/background.png")));
        this.player = new Player(0.1f , GameUtils.getTextureRegion(textureAtlas, "main_ship",2, 2, 1));
        this.player.setCenter(0, -40f);
        this.player.setSpeedRate(0.5f);

        this.starEmulator = new StarEmulator(new Star(0.005f, this.starAtlas.findRegion("star")), 25);
        this.bulletPool = new BulletPool(new Bullet(0.01f, this.textureAtlas.findRegion("bulletMainShip")));
        this.player.setBulletPool(this.bulletPool);
        this.player.setShotSound(this.bulletSound);
        this.backgroundSound.play();
        this.backgroundSound.setLooping(true);

        this.enemyBulletPool = new BulletPool(new Bullet(0.01f, this.textureAtlas.findRegion("bulletEnemy")));
        EnemyShip enemyShip = new EnemyShip(0.1f, GameUtils.getTextureRegion(textureAtlas, "enemy0",2, 2, 1));
        enemyShip.setBulletPool(enemyBulletPool);
        enemyShip.setAngle(180);
        this.enemyShipPool = new EnemyShipPool(enemyShip);

    }

    @Override
    public void calculate() {
        super.calculate();
        this.player.calculate();
        this.starEmulator.calculate();
        this.bulletPool.calculate();
        this.enemyShipPool.calculate();
        this.enemyBulletPool.calculate();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        this.spriteBatch.begin();

        this.background.draw(this.spriteBatch);
        this.starEmulator.draw(this.spriteBatch);
        this.bulletPool.draw(this.spriteBatch);
        this.enemyBulletPool.draw(this.spriteBatch);
        this.enemyShipPool.draw(this.spriteBatch);
        this.player.draw(this.spriteBatch);

        this.spriteBatch.end();
    }
  
    @Override
    public void dispose() {
        super.dispose();
        this.textureAtlas.dispose();
        this.starAtlas.dispose();
        this.bulletSound.dispose();
        this.backgroundSound.stop();
        this.backgroundSound.dispose();
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
        this.starEmulator.resize(world);
        this.bulletPool.resize(world);
        this.enemyShipPool.resize(world);
        this.enemyBulletPool.resize(world);
    }

}
