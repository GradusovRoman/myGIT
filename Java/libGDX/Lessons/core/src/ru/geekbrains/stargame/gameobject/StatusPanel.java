package ru.geekbrains.stargame.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import ru.geekbrains.stargame.intefaces.Drawable;
import ru.geekbrains.stargame.intefaces.Resizable;
import ru.geekbrains.stargame.template.Rect;

public class StatusPanel extends BitmapFont implements Resizable, Drawable{
    private Rect worldBound = new Rect();
    private StringBuffer stringBuffer = new StringBuffer();
    private float padding = 1f;
    private String[] labels = {"Frags", "HP", "Level"};
    private int[] status = {0, 0, 0};
    private int[] aligns = {Align.left, Align.center, Align.right};

    public StatusPanel(String fontFile, String imageFile) {
        super(Gdx.files.internal(fontFile), Gdx.files.internal(imageFile), false, false);
        getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public void setSize(float size) {
        this.getData().setScale(size / this.getCapHeight());
    }

    private GlyphLayout draw(Batch batch, CharSequence str, float x, float y, int hAlign) {
        return super.draw(batch, str, x, y, 0f, hAlign, false);
    }


    private void drawStatus(SpriteBatch spriteBatch, StringBuffer stringBuffer, float XPos, int align) {
        this.draw(spriteBatch, stringBuffer, XPos, this.worldBound.getTop() - padding, align);
    }

    private StringBuffer getStatusByIndex(int number) {
        this.stringBuffer.setLength(0);
        return this.stringBuffer.append(this.labels[number]).append(":").append(this.status[number]);
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        this.drawStatus(spriteBatch, this.getStatusByIndex(0), this.worldBound.getLeft() + this.padding, this.aligns[0]);
        this.drawStatus(spriteBatch, this.getStatusByIndex(1), this.worldBound.getCenter().x, this.aligns[1]);
        this.drawStatus(spriteBatch, this.getStatusByIndex(2), this.worldBound.getRight() - this.padding + this.padding, this.aligns[2]);
    }

    @Override
    public void resize(Rect rect) {
        this.worldBound.setRect(rect);
    }

    public void setStatus(int frags, int hp, int level) {
        this.status[0] = frags;
        this.status[1] = hp;
        this.status[2] = level;
    }
}