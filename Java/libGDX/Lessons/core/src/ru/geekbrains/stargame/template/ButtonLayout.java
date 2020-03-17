package ru.geekbrains.stargame.template;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.stargame.intefaces.Drawable;
import ru.geekbrains.stargame.intefaces.Resizable;
import ru.geekbrains.stargame.intefaces.Touchable;

import java.util.ArrayList;
import java.util.List;

public class ButtonLayout extends Rect implements Resizable, Touchable, Drawable {
    private float[] paddingRate = {0,0,0,0}; // слева, сверху, снизу, справа
    private float separateSizeRate = 0;
    private List<GameButton> buttonList = new ArrayList<>();
    public enum align {TOP , BOTTOM, CENTER};
    public enum layout {HORIZONTAL, VERTICAL}

    private align currentAlign = align.BOTTOM;
    private layout currentLayout = layout.HORIZONTAL;

    private Rect drawRect = new Rect();

    public void addButton(GameButton button) {
        if (button != null) {
            this.buttonList.add(button);
        }
    }

    @Override
    public void touchDown(Vector2 vector2, int pointer, int button) {
        if (this.isInside(vector2)) {
            for (GameButton gameButton : this.buttonList) {
                gameButton.touchDown(vector2, pointer, button);
            }
        }
    }

    @Override
    public void touchUp(Vector2 vector2, int pointer, int button) {
        for (GameButton gameButton : this.buttonList) {
            gameButton.touchUp(vector2, pointer, button);
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        for (GameButton gameButton : this.buttonList) {
            gameButton.draw(spriteBatch);
        }
    }

    @Override
    public void resize(Rect rect) {
        this.getPosition(rect);
        this.getDrawRect(this);

        this.resizeElements();
    }

    private void getPosition(Rect rect) {
        float newRectY = 0f;

        if(currentAlign.equals(align.BOTTOM)) newRectY = rect.getBottom() + this.getHalfHeight();
        else if(currentAlign.equals(align.TOP)) newRectY = rect.getTop() - this.getHalfHeight();
        else if(currentAlign.equals(align.CENTER)) newRectY = rect.getCenter().y;

        this.setSize(rect.getWidth(), this.getHeight());
        this.setCenter(rect.getCenter().x, newRectY);

    }

    private void resizeElements() {
        int elementCount = this.buttonList.size();
        if (elementCount > 0 ) {

            float elementSize = this.getNewElementSize(elementCount);

            for (int i =0; i < this.buttonList.size(); i++) {
                GameButton gameButton = this.buttonList.get(i);
                float newX = 0;
                float newY = 0;
                if (this.currentLayout.equals(layout.HORIZONTAL)) {
                    newX = this.drawRect.getLeft() + (i + 0.5f) * this.drawRect.getWidth()/elementCount;
                    newY = this.drawRect.getCenter().y;
                    gameButton.resize(new Rect(newX,newY, elementSize/2f, this.drawRect.getHeight()/2f));
                } else if (this.currentLayout.equals(layout.VERTICAL)){
                    //TODO не в центре и не правиильно
                    newX = this.drawRect.getCenter().x;
                    newY = this.drawRect.getTop() - (i +0.5f) * this.drawRect.getHeight()/elementCount;
                    gameButton.resize(new Rect(newX,newY, this.drawRect.getWidth()/2f,elementSize/2f));
                }
            }
        }
    }

    private float getNewElementSize(float elementCount) {
        float separatingSize;
        float elementSize;

        if (this.currentLayout.equals(layout.HORIZONTAL)) {
            separatingSize = (elementCount - 1) * this.drawRect.getWidth() * separateSizeRate;
            elementSize = (this.drawRect.getWidth() - separatingSize) / (elementCount);
        } else {
            separatingSize = (elementCount - 1) * this.drawRect.getHeight() * separateSizeRate;
            elementSize = (this.drawRect.getHeight() - separatingSize) / (elementCount);
        }

         return elementSize;
    }

    public void getDrawRect(Rect rect) {
        float newHalfHeight = rect.getHalfHeight() - (this.paddingRate[1] + this.paddingRate[2]) * rect.getHalfHeight();
        float newHalfWidth = rect.getHalfWidth() - (this.paddingRate[0] + this.paddingRate[3]) * rect.getHalfWidth();
        float newX = rect.getLeft() + rect.getWidth()*this.paddingRate[0] + newHalfWidth;
        float newY = rect.getBottom() + rect.getHeight()*this.paddingRate[2] + newHalfHeight;

        this.drawRect.setCenter(newX, newY);
        this.drawRect.setHalfHeight(newHalfHeight);
        this.drawRect.setHalfWidth(newHalfWidth);
    }

    public void setAlign(align currentAlign) {
        this.currentAlign = currentAlign;
    }

    public void setLayout(layout currentLayout) {
        this.currentLayout = currentLayout;
    }
}
