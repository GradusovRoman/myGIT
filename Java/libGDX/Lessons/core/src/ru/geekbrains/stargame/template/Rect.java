package ru.geekbrains.stargame.template;

import com.badlogic.gdx.math.Vector2;

public class Rect {
    private Vector2 center;

    private float halfWidth;
    private float halfHeight;

    public Rect() {
        this(new Vector2(0,0), 0, 0);
    }

    public Rect(Vector2 center, float halfWidth, float halfHeight) {
        this.center = center;
        this.halfWidth = halfWidth;
        this.halfHeight = halfHeight;
    }

    public Rect(float x, float y, float halfWidth, float halfHeight) {
        this(new Vector2(x,y), halfWidth, halfHeight);
    }

    public Rect(float halfWidth, float halfHeight) {
        this(new Vector2(0,0), halfWidth, halfHeight);
    }

    public void setRect(Rect rect) {
        this.center.set(rect.getCenter());
        this.halfWidth = rect.getHalfWidth();
        this.halfHeight = rect.getHalfHeight();
    }

    public void setSize(float width, float height) {
        this.halfHeight = height/2f;
        this.halfWidth = width/2f;
    }

    public void setHalfWidth(float halfWidth) {
        this.halfWidth = halfWidth;
    }

    public void setHalfHeight(float halfHeight) {
        this.halfHeight = halfHeight;
    }

    public void setCenter(Vector2 center) {
        this.center = center;
    }

    public void setCenter(float x , float y) {
        this.center.set(x, y);
    }

    public Vector2 getCenter() {
        return center.cpy();
    }

    public float getLeft() {
        return this.center.x - this.halfWidth;
    }

    public float getRight() {
        return this.center.x + this.halfWidth;
    }

    public float getTop() {
        return this.center.y + this.halfHeight;
    }

    public float getBottom() {
        return this.center.y - this.halfHeight;
    }

    public float getHalfWidth() {
        return halfWidth;
    }

    public float getHalfHeight() {
        return halfHeight;
    }

    public float getWidth() {
        return this.halfWidth * 2f;
    }

    public float getHeight() {
        return this.halfHeight * 2f;
    }

    public boolean isInside(Vector2 pset) {
        return this.getLeft() <= pset.x
                && this.getRight() >= pset.x
                && this.getTop() >= pset.y
                && this.getBottom() <= pset.y;
    }

    public boolean isTouch(Rect rect) {
        // получить точку между векторами и проверяем лежит ли она внутри нашего квадрата (fix)
        Vector2 touchVector = rect.getCenter().sub(this.center).scl(
                new Vector2(
                        this.getHalfWidth()/(rect.getHalfWidth() + this.getHalfWidth()),
                        this.getHalfHeight()/(rect.getHalfHeight() + this.getHalfHeight())
                )).add(this.center);// где то так

        return this.isInside(touchVector) && rect.isInside(touchVector);
    }

    public boolean isOutOfBound(Rect rect) {
        //TODO но если будем вращать обьект то будет работать некоректно.
        return rect.getLeft() < this.getLeft()
                || rect.getRight() > this.getRight()
                || rect.getBottom() < this.getBottom()
                || rect.getTop() > this.getTop();
    }

    @Override
    public Rect clone() {
        return new Rect(this.center, this.halfWidth, this.halfHeight);
    }

    public void printingInfo() {
        System.out.println("this.center.x =" + this.center.x + "\tthis.center.y = " + this.center.y);
        System.out.println("this.halfHeight = " + this.halfHeight);
        System.out.println("this.halfWidth =" + this.halfWidth);
    }

}

