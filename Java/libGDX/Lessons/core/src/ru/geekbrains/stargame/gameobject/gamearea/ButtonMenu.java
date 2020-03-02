package ru.geekbrains.stargame.gameobject.gamearea;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.stargame.intefaces.Drawable;
import ru.geekbrains.stargame.intefaces.Resizable;
import ru.geekbrains.stargame.intefaces.Touchable;
import ru.geekbrains.stargame.template.Rect;
import ru.geekbrains.stargame.template.Sprite;

import java.util.ArrayList;
import java.util.List;

public class ButtonMenu extends Rect implements Resizable, Touchable, Drawable {
    private float[] paddingRate = {0,0,0,0}; // слева, сверху, снизу, справа
    private float separateSizeRate = 0;
    private List<Resizable> resizableList;
    private List<Touchable> touchableList;
    private List<Drawable> drawableList;
    private enum alignList {TOP , BOTTOM};
    private alignList align = alignList.BOTTOM;

    public ButtonMenu() {
        this.init();
    }

    public void init () {
        this.resizableList = new ArrayList<>();
        this.touchableList = new ArrayList<>();
        this.drawableList = new ArrayList<>();
    }

    public void addButton(Sprite button) {
        if (button instanceof Resizable) {
            this.resizableList.add(button);
        }
        if (button instanceof Drawable) {
            this.drawableList.add(button);
        }
        if (button instanceof Touchable) {
            this.touchableList.add((Touchable) button);
        }
    }

    @Override
    public void touchDown(Vector2 vector2, int pointer, int button) {
        if (this.isInside(vector2)) {
            for (Touchable element : this.touchableList) {
                element.touchDown(vector2, pointer, button);
            }
        }
    }

    @Override
    public void touchUp(Vector2 vector2, int pointer, int button) {
        for (Touchable element : this.touchableList) {
            element.touchUp(vector2, pointer, button);
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        for (Drawable element : this.drawableList) {
            element.draw(spriteBatch);
        }
    }

    @Override
    public void resize(Rect rect) {
        float newRectY;
        if(align.equals(alignList.BOTTOM)) newRectY = rect.getBottom() + this.getHalfHeight();
        else newRectY = rect.getTop() - this.getHalfHeight();

        this.setSize(rect.getWidth(), this.getHeight());
        this.setCenter(rect.getCenter().x, newRectY);

        this.resizeElements();
    }

    private void resizeElements() {
        int elementCount = this.resizableList.size();
        if (elementCount > 0 ) {
            Rect drawRect = this.getDrawRect(this);
            float elementRectSize = this.getNewElementSize(drawRect, elementCount);

            int count = 0;
            for (Resizable resizable : this.resizableList) {
                float newX = drawRect.getLeft() + (count + 0.5f) * drawRect.getWidth()/elementCount;
                float newY = drawRect.getCenter().y;
                resizable.resize(new Rect( newX, newY , elementRectSize/2, elementRectSize/2));
                count++;
            }
        }
    }

    private float getNewElementSize(Rect rect , float elementCount) {
        float elementWidth = rect.getWidth();
        elementWidth -= (elementCount - 1) * rect.getWidth() * separateSizeRate;
        elementWidth /= elementCount;

         return Math.min(rect.getHeight(), elementWidth);
    }

    public Rect getDrawRect(Rect rect) {
        float newHalfHeight = rect.getHalfHeight() - (this.paddingRate[1] + this.paddingRate[2]) * rect.getHalfHeight();
        float newHalfWidth = rect.getHalfWidth() - (this.paddingRate[0] + this.paddingRate[3]) * rect.getHalfWidth();
        float newX = rect.getLeft() + rect.getWidth()*this.paddingRate[0] + newHalfWidth;
        float newY = rect.getBottom() + rect.getHeight()*this.paddingRate[2] + newHalfHeight;

        return new Rect(newX, newY, newHalfWidth, newHalfHeight);
    }
}
