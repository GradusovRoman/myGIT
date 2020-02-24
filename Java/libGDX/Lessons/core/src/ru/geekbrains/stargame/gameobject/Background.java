package ru.geekbrains.stargame.gameobject;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.stargame.template.Sprite;

public class Background extends Sprite {

    public Background(float width, float height, TextureRegion... textureRegions) {
        super(width, height, textureRegions);
    }

    public Background(TextureRegion... textureRegions) {
        super(textureRegions);
    }
}
