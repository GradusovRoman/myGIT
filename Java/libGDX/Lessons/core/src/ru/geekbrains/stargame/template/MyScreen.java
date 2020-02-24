package ru.geekbrains.stargame.template;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.math.MathUtils;

public abstract class MyScreen implements Screen, InputProcessor {
    protected SpriteBatch spriteBatch;
    protected Rect screenRect;
    protected Rect worldRect;
    protected final float WORLDSIZE = 100f;
    protected final Rect GlRECT = new Rect(1f,1f);
    protected Matrix4 worldToGl;
    protected Matrix3 screenToWorld;

    //TODO конечная цель что бы в спрайт банч лежал наш шаблон конвертирования матриц.
    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        this.spriteBatch = new SpriteBatch();
        this.screenRect = new Rect(0,0);
        this.worldRect = new Rect(0,0);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void dispose() {
        this.spriteBatch.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    public boolean touchDown(Vector2 vector2, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 normVector = new Vector2(screenX, this.screenRect.getHeight() - screenY).mul(this.screenToWorld);
        return this.touchDown(normVector, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public void resize(Rect world) {

    }

    @Override
    public void resize(int width, int height) {
        this.screenRect.setSize(width, height);
        this.screenRect.setCenter(width/2, height/2);

        this.worldRect.setSize(this.WORLDSIZE * width/(float)height, this.WORLDSIZE);

        this.worldToGl = MathUtils.calcTransitionMatrix4(this.worldRect, this.GlRECT);

        this.screenToWorld = MathUtils.calcTransitionMatrix3(this.screenRect, this.worldRect);

        this.spriteBatch.setProjectionMatrix(this.worldToGl);

        this.resize(this.worldRect.clone());
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
