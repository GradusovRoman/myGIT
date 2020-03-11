package ru.geekbrains.stargame.template.configclass;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BulletConfig {
    public float heightProportion;
    public TextureRegion[] textureRegions;
    public float speedRate;
    public int damage;

    public void setTextureRegions(TextureRegion... textureRegions) {
        this.textureRegions = textureRegions;
    }

    public void setConfig(BulletConfig bulletConfig) {
        this.heightProportion = bulletConfig.heightProportion;
        this.textureRegions = bulletConfig.textureRegions;
        this.speedRate = bulletConfig.speedRate;
        this.damage = bulletConfig.damage;
    }
}
