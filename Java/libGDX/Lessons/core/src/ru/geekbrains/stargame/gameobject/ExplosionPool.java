package ru.geekbrains.stargame.gameobject;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.geekbrains.stargame.template.Pool;
import ru.geekbrains.stargame.template.Rect;

public class ExplosionPool extends Pool<Explosion> {
    private Sound explosionSound;
    private TextureRegion[] textureRegion;

    public ExplosionPool(Sound explosionSound, TextureRegion[] textureRegion) {
        this.explosionSound = explosionSound;
        this.textureRegion = textureRegion;
    }

    @Override
    protected Explosion getNewPoolElement() {
        return new Explosion(this, this.explosionSound, this.textureRegion);
    }

    @Override
    public void resize(Rect rect) {}
}
