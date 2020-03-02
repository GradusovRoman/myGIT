package ru.geekbrains.stargame.gameobject.gamearea;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.geekbrains.stargame.template.Sprite;

public class Background extends Sprite {

    public Background( TextureRegion... textureRegions) {
        super(1f, textureRegions);
    }
}
