package ru.geekbrains.stargame.gameobject;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.geekbrains.stargame.intefaces.Updatable;
import ru.geekbrains.stargame.template.Pool;
import ru.geekbrains.stargame.template.Rect;
import ru.geekbrains.stargame.template.Sprite;

//TODO Explosion не привязан к Дтайм.
public class Explosion extends Sprite implements Updatable {
    private Pool<Explosion> explosionPool;
    private Sound explosionSound;

    public Explosion( Pool<Explosion> explosionPool, Sound explosionSound, TextureRegion... textureRegions) {
        super(1f, textureRegions);
        this.explosionPool = explosionPool;
        this.explosionSound = explosionSound;
    }

    @Override
    public void update(float dTime) {
        if (++this.frame >= this.getTextureRegions().length) {
            this.setFrame(0);
            if (this.explosionPool != null) {
                this.explosionPool.imFree(this);
            }
            if (this.explosionSound != null) {
                this.explosionSound.stop();
            }
        }
    }

    public void startOnRect(Rect owner) {
        this.resize(owner);
        this.setCenter(owner.getCenter());
        if (this.explosionSound != null) {
            this.explosionSound.play(0.5f);
        }
    }

}
