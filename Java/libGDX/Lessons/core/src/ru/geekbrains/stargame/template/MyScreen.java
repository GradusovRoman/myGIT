package ru.geekbrains.stargame.template;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.stargame.intefaces.Drawable;
import ru.geekbrains.stargame.intefaces.Updatable;
import ru.geekbrains.stargame.intefaces.Resizable;
import ru.geekbrains.stargame.intefaces.Touchable;
import ru.geekbrains.stargame.math.GameUtils;

public abstract class MyScreen implements Screen, InputProcessor, Touchable, Resizable, Updatable, Drawable {
    protected SpriteBatch spriteBatch;
    protected Game game;
    private Rect screenRect;
    private Rect worldRect;
    private final float WORLDSIZE = 100f;
    private final Rect GlRECT = new Rect(1f,1f);
    private Matrix4 worldToGl;
    private Matrix3 screenToWorld;
    private boolean paused = false;

    public MyScreen(Game game) {
        this.game = game;
    }

    //TODO конечная цель что бы в спрайт банч лежал наш шаблон конвертирования матриц.

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        this.spriteBatch = new SpriteBatch();
        this.screenRect = new Rect(0,0);
        this.worldRect = new Rect(0,0);
    }

    @Override
    public void update(float dTime) {

    }

    @Override
    public void draw(SpriteBatch spriteBatch) {

    }

    @Override
    public void render(float delta) {
        if (!this.paused) this.update(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.spriteBatch.begin();
        draw(this.spriteBatch);
        this.spriteBatch.end();
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

    @Override
    public void touchDown(Vector2 vector2, int pointer, int button) {

    }

    @Override
    public void touchUp(Vector2 vector2, int pointer, int button) {

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 normVector = new Vector2(screenX, this.screenRect.getHeight() - screenY).mul(this.screenToWorld);
        this.touchDown(normVector, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector2 normVector = new Vector2(screenX, this.screenRect.getHeight() - screenY).mul(this.screenToWorld);
        this.touchUp(normVector, pointer, button);
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

    @Override
    public void resize(Rect rect) {

    }

    @Override
    public void resize(int width, int height) {
        this.screenRect.setSize(width, height);
        this.screenRect.setCenter(width/2, height/2);

        this.worldRect.setSize(this.WORLDSIZE * width/(float)height, this.WORLDSIZE);

        this.worldToGl = GameUtils.calcTransitionMatrix4(this.worldRect, this.GlRECT);

        this.screenToWorld = GameUtils.calcTransitionMatrix3(this.screenRect, this.worldRect);

        this.spriteBatch.setProjectionMatrix(this.worldToGl);

        this.resize(this.worldRect.clone());
    }

    @Override
    public void pause() {
        this.paused = true;
    }

    @Override
    public void resume() {
        this.paused = false;
    }

    @Override
    public void hide() {
        this.paused = true;
    }
}
