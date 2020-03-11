package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.stargame.gameobject.ExplosionPool;
import ru.geekbrains.stargame.gameobject.Player.BulletPool;
import ru.geekbrains.stargame.gameobject.Player.Player;
import ru.geekbrains.stargame.gameobject.ShipConfiguration;
import ru.geekbrains.stargame.gameobject.enemy.EnemyShip;
import ru.geekbrains.stargame.gameobject.enemy.EnemyShipPool;
import ru.geekbrains.stargame.gameobject.gamearea.Background;
import ru.geekbrains.stargame.gameobject.gamearea.Star;
import ru.geekbrains.stargame.gameobject.gamearea.StarEmulator;
import ru.geekbrains.stargame.intefaces.Updatable;
import ru.geekbrains.stargame.intefaces.Drawable;
import ru.geekbrains.stargame.intefaces.Resizable;
import ru.geekbrains.stargame.math.GameUtils;
import ru.geekbrains.stargame.template.Bullet;
import ru.geekbrains.stargame.template.MyScreen;
import ru.geekbrains.stargame.template.Rect;

import java.util.ArrayList;
import java.util.List;

public class GameScene extends MyScreen {
    private TextureAtlas textureAtlas;
    private TextureAtlas starAtlas;
    private Background background;
    private Player player;
    private StarEmulator starEmulator;
    private BulletPool playerBulletPool;
    private Sound bulletSound;
    private Sound laserSound;
    private Music backgroundSound;
    private EnemyShipPool enemyShipPool;
    private BulletPool enemyBulletPool;
    private ExplosionPool explosionPool;
    private Sound explosionSound;
    private ShipConfiguration shipConfiguration;
    private List<Object> gameObject;

    public GameScene(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        this.gameObject = new ArrayList<>();

        this.createSound();
        this.createAtlas();

        this.shipConfiguration = new ShipConfiguration(textureAtlas);

        this.background = new Background(new TextureRegion(new Texture("textures/background.png")));
        this.player = new Player(this.shipConfiguration.getConfig("main_ship"));
        this.player.setCenter(0, -40f);

        this.createPool();
        this.createObject();
    }

    private void createSound() {
        this.bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
        this.backgroundSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        this.explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));
        this.laserSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));

        this.backgroundSound.play();
        this.backgroundSound.setLooping(true);
    }

    public void createAtlas() {
        this.textureAtlas = new TextureAtlas("textures/mainAtlas.tpack");
        this.starAtlas = new TextureAtlas("textures/button.pack");
    }

    public void createPool() {
        this.starEmulator = new StarEmulator(new Star(0.005f, this.starAtlas.findRegion("star")), 25);
        this.playerBulletPool = new BulletPool();
        this.enemyBulletPool = new BulletPool();

        this.explosionPool = new ExplosionPool(this.explosionSound, GameUtils.getTextureRegion(this.textureAtlas, "explosion",74,9,9));

        this.enemyShipPool = new EnemyShipPool(this.explosionPool, this.enemyBulletPool, this.laserSound, this.shipConfiguration);

        this.player.setBulletPool(this.playerBulletPool);
        this.player.setExplosionPool(this.explosionPool);
        this.player.setShotSound(this.bulletSound);
    }

    private void createObject() {
        this.gameObject.add(this.background);
        this.gameObject.add(this.starEmulator);
        this.gameObject.add(this.enemyBulletPool);
        this.gameObject.add(this.playerBulletPool);
        this.gameObject.add(this.enemyShipPool);
        this.gameObject.add(this.player);
        this.gameObject.add(this.explosionPool);
    }

    @Override
    public void update(float dTime) {
        super.update(dTime);

        for (Object gameObj : this.gameObject) {
            if (gameObj instanceof Updatable) {
                ((Updatable) gameObj).update(dTime);
            }
        }

        //TODO проверка на попадание.
        //TODO КОСТЫЛЬ ПОКА ЧТО
        //TODO наши пули и вражесткие корабли.

        for (EnemyShip enemyShip : this.enemyShipPool.getListOfUse()) {
            for (Bullet bullet : this.playerBulletPool.getListOfUse()) {
                if (enemyShip.isTouch(bullet)) {
                    enemyShip.takeDamage(bullet);
                    bullet.takeDamage(enemyShip);
                }
            }
            //TODO наш корабль и вражеский стлкнулись
            if (enemyShip.isTouch(this.player)) {
                this.player.takeDamage(enemyShip);
                enemyShip.takeDamage(this.player);
            }
        }

        //TODO вражеские пули и наш корабль
        for (Bullet enemyBullet : this.enemyBulletPool.getListOfUse()) {
            if (enemyBullet.isTouch(this.player)) {
                this.player.takeDamage(enemyBullet);
                enemyBullet.takeDamage(this.player);
            }
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(this.spriteBatch);
        for (Object gameObj : this.gameObject) {
            if (gameObj instanceof Drawable) {
                ((Drawable) gameObj).draw(this.spriteBatch);
            }
        }
    }
  
    @Override
    public void dispose() {
        super.dispose();
        this.explosionSound.stop();
        this.backgroundSound.stop();
        this.bulletSound.stop();

        this.textureAtlas.dispose();
        this.starAtlas.dispose();
        this.bulletSound.dispose();
        this.backgroundSound.dispose();
        this.explosionSound.dispose();
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
        for (Object gameObj : this.gameObject) {
            if (gameObj instanceof Resizable) {
                ((Resizable) gameObj).resize(world);
            }
        }
    }

}
