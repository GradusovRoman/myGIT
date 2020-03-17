package ru.geekbrains.stargame.template;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.stargame.intefaces.Drawable;
import ru.geekbrains.stargame.intefaces.Resizable;


public abstract class Sprite extends Rect implements Drawable, Resizable {
    private TextureRegion[] textureRegions;
    protected int frame = 0;
    private float angle = 0f;
    private float heightProportion = 1f;
    private float textureRotateAngle = 0f;

    public Sprite(float heightProportion,  TextureRegion... textureRegions) {
            this.textureRegions = textureRegions;
            this.setHeightProportion(heightProportion);
    }

    public Sprite(){

    }

    public void draw(SpriteBatch spriteBatch) {
        if (this.getTextureRegions() != null) {
            spriteBatch.draw(this.getCurrentTextureRegion(),
                    this.getLeft(), this.getBottom(),
                    this.getHalfWidth(), this.getHalfHeight(),
                    this.getWidth(), this.getHeight(),
                    1, 1,
                    this.getAngle() + this.getTextureRotateAngle());
        } else {
            System.err.println("Sprite нет текстуры");
        }
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    @Override
    public void resize(Rect rectWorld) {
        if (this.getTextureRegions() != null) {
            this.setHalfHeight(rectWorld.getHalfHeight() * this.heightProportion);
            this.setHalfWidth(this.getCurrentTextureRegion().getRegionWidth() / 2f * this.getHeight() / this.getCurrentTextureRegion().getRegionHeight());
        }
    }

    public void setHeightProportion(float heightProportion) {
        this.setHalfHeight(this.getHalfHeight() * heightProportion / this.heightProportion);
        this.setHalfWidth(this.getHalfWidth() * heightProportion / this.heightProportion);
        this.heightProportion = heightProportion;
    }

    public float getHeightProportion() {
        return this.heightProportion;
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

    public float getTextureRotateAngle() {
        return textureRotateAngle;
    }

    public void setTextureRotateAngle(float textureRotateAngle) {
        this.textureRotateAngle = textureRotateAngle;
    }

    public void setTextureRegions(TextureRegion... textureRegions) {
        this.textureRegions = textureRegions;
        this.frame = 0;
    }

    public TextureRegion getCurrentTextureRegion() {
        return this.textureRegions[this.frame];
    }

    public int getFrame() {
        return frame;
    }

}
