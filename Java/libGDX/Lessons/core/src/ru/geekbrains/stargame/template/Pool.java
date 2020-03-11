package ru.geekbrains.stargame.template;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.geekbrains.stargame.intefaces.Updatable;
import ru.geekbrains.stargame.intefaces.Drawable;
import ru.geekbrains.stargame.intefaces.PoolFree;
import ru.geekbrains.stargame.intefaces.Resizable;

import java.util.*;

public abstract class Pool <T extends  Sprite > implements Updatable, Drawable, Resizable, PoolFree<T> {
    private List<T> listOfFree = new ArrayList<>();
    private List<T> listOfUse = new ArrayList<>();
    private Rect worldBounds = new Rect();

    @Override
    public void update(float dTime) {
        for (T element : this.getListOfUse()) {
                ((Updatable) element).update(dTime);
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        for (T element : this.listOfUse) {
            element.draw(spriteBatch);
        }
    }

    @Override
    public void imFree(T object) {
        this.listOfUse.remove(object);
        this.listOfFree.add(object);
    }

    @Override
    public void resize(Rect rect) {
        this.worldBounds = rect;
        for (T element : this.listOfUse) {
            element.resize(rect);
        }
    }

    protected abstract T getNewPoolElement();

    public T getFreeObject() {
        T object;
        if (this.listOfFree.size() > 0) {
            object = this.listOfFree.get(0);
            this.listOfFree.remove(object);
        } else {
            object = this.getNewPoolElement();
        }
        object.resize(this.worldBounds);
        this.listOfUse.add(object);
        return object;
    }

    public int getUseSize() {
        return this.listOfUse.size();
    }

    public Rect getWorldBounds() {
        return worldBounds.clone();
    }

    public List<T> getListOfUse() {
        return new ArrayList<>(listOfUse);
    }
}
