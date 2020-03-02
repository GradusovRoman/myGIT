package ru.geekbrains.stargame.gameobject.gamearea;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.geekbrains.stargame.intefaces.Calculatable;
import ru.geekbrains.stargame.intefaces.Drawable;
import ru.geekbrains.stargame.intefaces.Resizable;
import ru.geekbrains.stargame.math.GameUtils;
import ru.geekbrains.stargame.template.Rect;

import java.util.ArrayList;
import java.util.List;

public class StarEmulator extends Rect implements Drawable, Calculatable, Resizable {
    private List<Star> starPool = new ArrayList<>();
    private Star starTemplate;
    private int count;

    public StarEmulator(Star starTemplate, int count) {
        this.starTemplate = starTemplate;
        this.count = count;
    }

    private void addStar() {
        while (this.starPool.size() < count) {
            Star star = this.starTemplate.getNewStar(this);
            star.setCenter(
                    GameUtils.getRandomByRange(this.getLeft(), this.getRight()),
                    GameUtils.getRandomByRange(this.getBottom(), this.getTop()));
            this.starPool.add(star);
        }
    }

    @Override
    public void calculate() {
        for(Star star : this.starPool) {
            star.calculate();
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        for(Star star : this.starPool) {
            star.draw(spriteBatch);
        }
    }

    @Override
    public void resize(Rect rect) {
        this.setRect(rect);

        if (this.getHalfHeight() > 0f) {
            this.addStar();
        }

        for(Star star : this.starPool) {
            star.resize(this);
        }
    }
}
