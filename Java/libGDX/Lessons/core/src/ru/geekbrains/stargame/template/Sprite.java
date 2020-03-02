package ru.geekbrains.stargame.template;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.geekbrains.stargame.intefaces.Drawable;
import ru.geekbrains.stargame.intefaces.Resizable;


public abstract class Sprite extends Rect implements Drawable, Resizable {
    private TextureRegion[] textureRegions;
    private int frame = 0;
    private float scale = 1f;
    private float angle = 0f;
    private float baseHeight;
    private float baseWidth;
    private float heightProportion = 1f;

    //TODO нужно помозговать.
    public Sprite(float heightProportion,  TextureRegion... textureRegions) {
        this.baseWidth = textureRegions[0].getRegionWidth();
        this.baseHeight = textureRegions[0].getRegionHeight();
        this.textureRegions = textureRegions;
        this.setHeightProportion(heightProportion);
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

    @Override
    public void resize(Rect rectWorld) {
        this.setHalfHeight(rectWorld.getHalfHeight() * this.heightProportion * this.scale);
        this.setHalfWidth(this.baseWidth/2 * this.getHeight()/this.baseHeight);
    }

    public void setHeightProportion(float heightProportion) {
        if (heightProportion > 0) {
            this.setHalfHeight(this.getHalfHeight() * heightProportion / this.heightProportion);
            this.setHalfWidth(this.getHalfWidth() * heightProportion / this.heightProportion);
            this.heightProportion = heightProportion;
        }
    }

    public void setScale(float scale) {
        if (this.scale > 0) {
            this.setHalfHeight(this.getHalfHeight() * scale / this.scale);
            this.setHalfWidth(this.getHalfWidth() * scale / this.scale);
            this.scale = scale;
        }
    }

    public float getHeightProportion() {
        return heightProportion;
    }

    public float getScale() {
        return scale;
    }

    public TextureRegion getTextureRegion() {
        return this.textureRegions[this.frame];
    }

    public TextureRegion[] getTextureRegions() {
        return textureRegions;
    }

    public void setAngle(float angle) {
        //TODO при вращении нужно будет переписать проверку на прикосновение, так так как обект будет под углом
        this.angle = angle;
    }

    public float getAngle() {
        return angle;
    }

    public int getFrame() {
        return frame;
    }
}
