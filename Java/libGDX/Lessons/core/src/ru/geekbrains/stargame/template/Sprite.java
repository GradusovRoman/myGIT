package ru.geekbrains.stargame.template;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Sprite extends Rect {
    private TextureRegion[] textureRegions;
    private int frame = 0;
    private float scaleByX = 1f;
    private float scaleByY = 1f;
    private float angle = 0f;
    private float baseHeight;
    private float baseWidth;

    //TODO нужно помозговать.
    public Sprite(float scale,  TextureRegion... textureRegions) {
        this(textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(), textureRegions);
        this.setScale(scale);
    }

    public Sprite(float width, float height,  TextureRegion... textureRegions) {
        super(width, height);
        this.textureRegions = textureRegions;
        this.baseHeight = height;
        this.baseWidth = width;
    }

    public Sprite(TextureRegion... textureRegions) {
        this(textureRegions[0].getRegionWidth(), textureRegions[0].getRegionHeight(), textureRegions);
    }

    public void draw(SpriteBatch spriteBatch) {
        TextureRegion texture = this.textureRegions[frame];
        spriteBatch.draw(texture,
                this.getLeft() , this.getBottom(),
                this.getHalfWidth(), this.getHalfHeight(),
                this.getWidth(), this.getHeight(),
                1, 1 ,
                this.angle);
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public void setScale(float byXY) {
        this.setScale(byXY, byXY);
    }

    public void setScale(float byX, float byY) {
        this.scaleByX = byX;
        this.scaleByY = byY;
        super.setSize(this.baseWidth * this.scaleByX, this.baseHeight * this.scaleByY);
    }

    @Override
    public void setSize(float width, float height) {
        this.setScale(width/this.baseWidth, height/this.baseHeight);
    }

    public void setAngle(float angle) {
        //TODO при вращении нужно будет переписать проверку на прикосновение, так так как обект будет под углом
        this.angle = angle;
    }
}
