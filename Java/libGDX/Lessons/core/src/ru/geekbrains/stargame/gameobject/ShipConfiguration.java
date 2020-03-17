package ru.geekbrains.stargame.gameobject;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.geekbrains.stargame.math.GameUtils;
import ru.geekbrains.stargame.template.configclass.ShipConfig;

import java.util.HashMap;
import java.util.Map;

public class ShipConfiguration {
    private Map<String, ShipConfig> shipConfigList = new HashMap<>();

    public ShipConfiguration(TextureAtlas textureAtlas) {
        if (textureAtlas != null ) {
            //проверяем есть ли используемые нами регионы
            if (textureAtlas.findRegion("bulletMainShip") != null
                    && textureAtlas.findRegion("main_ship") != null
                    && textureAtlas.findRegion("bulletEnemy") != null
                    && textureAtlas.findRegion("enemy0")  != null
                    && textureAtlas.findRegion("enemy1")  != null
                    && textureAtlas.findRegion("enemy2")  != null) {
                this.createAllConfig(textureAtlas);
            } else {
                throw new IllegalArgumentException("Отсутвуют использующиеся регионы");
            }
        } else {
            throw new NullPointerException("textureAtlas is Null");
        }
    }

    private void createAllConfig(TextureAtlas textureAtlas) {
        this.createMainShip(textureAtlas);
        this.createEnemy0(textureAtlas);
        this.createEnemy1(textureAtlas);
        this.createEnemy3(textureAtlas);
    }

    private void createMainShip(TextureAtlas atlas) {
        ShipConfig shipConfig = new ShipConfig();

        shipConfig.textureRegions = GameUtils.getTextureRegion(atlas,"main_ship",2,2,1);
        shipConfig.bulletConfig.textureRegions =  GameUtils.getTextureRegion(atlas, "bulletMainShip",1,1,1);
        shipConfig.textureRotateAngle = 0f;

        shipConfig.heightProportion = 0.1f;
        shipConfig.speedRate = 25f;
        shipConfig.reloadingTime = 1f;
        shipConfig.health = 10;

        shipConfig.bulletConfig.heightProportion = 0.01f;
        shipConfig.bulletConfig.speedRate = 1f;
        shipConfig.bulletConfig.damage = 1;

        this.shipConfigList.put("main_ship", shipConfig);
    }

    private void createEnemy0(TextureAtlas atlas) {
        ShipConfig shipConfig = new ShipConfig();

        shipConfig.textureRegions = GameUtils.getTextureRegion(atlas,"enemy0",2,2,1);
        shipConfig.bulletConfig.textureRegions = GameUtils.getTextureRegion(atlas, "bulletEnemy",1,1,1);
        shipConfig.textureRotateAngle = -180f;

        shipConfig.heightProportion = 0.1f;
        shipConfig.speedRate = 1.75f;
        shipConfig.reloadingTime = 5f;
        shipConfig.health = 2;

        shipConfig.bulletConfig.heightProportion = 0.01f;
        shipConfig.bulletConfig.speedRate = 0.3f;
        shipConfig.bulletConfig.damage = 1;

        this.shipConfigList.put("enemy0", shipConfig);
    }

    private void createEnemy1(TextureAtlas atlas) {
        ShipConfig shipConfig = new ShipConfig();

        shipConfig.textureRegions = GameUtils.getTextureRegion(atlas,"enemy1",2,2,1);
        shipConfig.bulletConfig.textureRegions = GameUtils.getTextureRegion(atlas, "bulletEnemy",1,1,1);
        shipConfig.textureRotateAngle = -180f;

        shipConfig.heightProportion = 0.1f;
        shipConfig.speedRate = 0.8f;
        shipConfig.reloadingTime = 10;
        shipConfig.health = 5;

        shipConfig.bulletConfig.heightProportion = 0.015f;
        shipConfig.bulletConfig.speedRate = 0.1f;
        shipConfig.bulletConfig.damage = 2;

        this.shipConfigList.put("enemy1", shipConfig);
    }

    private void createEnemy3(TextureAtlas atlas) {
        ShipConfig shipConfig = new ShipConfig();

        shipConfig.textureRegions = GameUtils.getTextureRegion(atlas,"enemy2",2,2,1);
        shipConfig.bulletConfig.textureRegions = GameUtils.getTextureRegion(atlas, "bulletEnemy",1,1,1);
        shipConfig.textureRotateAngle = -180f;

        shipConfig.heightProportion = 0.2f;
        shipConfig.speedRate = 0.4f;
        shipConfig.reloadingTime = 33;
        shipConfig.health = 10;

        shipConfig.bulletConfig.heightProportion = 0.05f;
        shipConfig.bulletConfig.speedRate = 0.05f;
        shipConfig.bulletConfig.damage = 10;

        this.shipConfigList.put("enemy2", shipConfig);
    }

    public ShipConfig getConfig(String name) {
        return this.shipConfigList.get(name);
    }
}
