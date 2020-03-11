package ru.geekbrains.stargame.template.configclass;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ShipConfig {
    public float heightProportion;
    public TextureRegion[] textureRegions;
    public float textureRotateAngle;
    public float speedRate;
    public float reloadingTime;
    public int health;
    public BulletConfig bulletConfig = new BulletConfig();

    public void setTextureRegions(TextureRegion... textureRegions) {
        this.textureRegions = textureRegions;
    }

    public void setConfig(ShipConfig shipConfig) {
        this.bulletConfig.setConfig(shipConfig.bulletConfig);

        this.heightProportion = shipConfig.heightProportion;
        this.textureRegions = shipConfig.textureRegions;
        this.textureRotateAngle = shipConfig.textureRotateAngle;
        this.speedRate = shipConfig.speedRate;
        this.reloadingTime = shipConfig.reloadingTime;
        this.health = shipConfig.health;
    }
}
