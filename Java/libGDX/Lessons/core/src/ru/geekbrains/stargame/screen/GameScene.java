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
import ru.geekbrains.stargame.gameobject.StatusPanel;
import ru.geekbrains.stargame.gameobject.buttons.GameOver;
import ru.geekbrains.stargame.gameobject.buttons.NewGame;
import ru.geekbrains.stargame.gameobject.enemy.EnemyShip;
import ru.geekbrains.stargame.gameobject.enemy.EnemyShipPool;
import ru.geekbrains.stargame.gameobject.gamearea.Background;
import ru.geekbrains.stargame.gameobject.Star;
import ru.geekbrains.stargame.gameobject.gamearea.StarEmulator;
import ru.geekbrains.stargame.intefaces.Updatable;
import ru.geekbrains.stargame.intefaces.Drawable;
import ru.geekbrains.stargame.intefaces.Resizable;
import ru.geekbrains.stargame.math.GameUtils;
import ru.geekbrains.stargame.template.Bullet;
import ru.geekbrains.stargame.template.ButtonLayout;
import ru.geekbrains.stargame.template.MyScreen;
import ru.geekbrains.stargame.template.Rect;

import java.util.ArrayList;
import java.util.List;

public class GameScene extends MyScreen {
    private TextureAtlas textureAtlas;
    private TextureAtlas starAtlas;
    private Background background;
    private Player player = new Player();
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
    private ButtonLayout buttonLayout = new ButtonLayout();
    private boolean endGame = false;
    private StatusPanel statusPanel;
    private float fontSize = 5f;
    private int frags;
    private int level;
    private int fragsToLevelUp = 10;

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
        this.buttonLayout.addButton(new GameOver(this.textureAtlas));
        this.buttonLayout.addButton(new NewGame(this.textureAtlas, this::startingNewGame));
        this.buttonLayout.setSize(100,20);
        this.buttonLayout.setAlign(ButtonLayout.align.CENTER);
        this.buttonLayout.setLayout(ButtonLayout.layout.VERTICAL);
        this.statusPanel = new StatusPanel("font/gamefont.fnt", "font/gamefont.png");
        this.statusPanel.setSize(this.fontSize);

        this.createPool();
        this.createObject();
        this.startingNewGame();
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
        this.gameObject.add(this.statusPanel);
    }

    @Override
    public void update(float dTime) {
        if (!this.endGame) {
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
                //TODO наш корабль и вражеский столкнулись
                if (enemyShip.isTouch(this.player)) {
                    this.player.takeDamage(enemyShip);
                    enemyShip.takeDamage(this.player);

                }
                if (enemyShip.getHealth() <= 0) this.frags++;
            }

            //TODO вражеские пули и наш корабль
            for (Bullet enemyBullet : this.enemyBulletPool.getListOfUse()) {
                if (enemyBullet.isTouch(this.player)) {
                    this.player.takeDamage(enemyBullet);
                    enemyBullet.takeDamage(this.player);
                }
            }

            if (player.getHealth() <= 0) this.endGame = true;
        }

        this.level = 1 + this.frags/this.fragsToLevelUp;
        this.enemyShipPool.setDiffLevel(this.level);
        this.statusPanel.setStatus(this.frags, this.player.getHealth(), this.level);

    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(this.spriteBatch);
        for (Object gameObj : this.gameObject) {
            if (gameObj instanceof Drawable) {
                ((Drawable) gameObj).draw(this.spriteBatch);
            }
        }

        if (this.endGame) {
            this.buttonLayout.draw(spriteBatch);
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
        if (!this.endGame) {
            this.player.touchDown(vector2, pointer, button);
        } else {
            this.buttonLayout.touchDown(vector2, pointer, button);
        }
    }

    @Override
    public void touchUp(Vector2 vector2, int pointer, int button) {
        if (!this.endGame) {
            this.player.touchUp(vector2, pointer, button);
        } else {
            this.buttonLayout.touchUp(vector2, pointer, button);
        }
    }

    @Override
    public void resize(Rect world) {
        for (Object gameObj : this.gameObject) {
            if (gameObj instanceof Resizable) {
                ((Resizable) gameObj).resize(world);
            }
        }

        this.buttonLayout.resize(world);
    }

    public void startingNewGame() {
        this.player.setShipConfig(this.shipConfiguration.getConfig("main_ship"));
        this.player.setCenter(0, -40f);

        this.playerBulletPool.releaseListOfUse();
        this.enemyBulletPool.releaseListOfUse();
        this.enemyShipPool.releaseListOfUse();
        this.explosionPool.releaseListOfUse();
        this.frags = 0;
        this.level = 1;
        this.endGame = false;
    }
}
