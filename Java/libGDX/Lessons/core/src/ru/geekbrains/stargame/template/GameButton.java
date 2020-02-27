package ru.geekbrains.stargame.template;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.stargame.intefaces.Commandable;
import ru.geekbrains.stargame.intefaces.Touchable;

public class GameButton extends Sprite implements Touchable, Commandable {
    private final float PRESSSCALE = 0.9f;
    private int currentButton;
    private int currentPointer;
    private boolean pressed;

    public GameButton(TextureAtlas textureAtlas, String name) {
        super(1f, textureAtlas.findRegion(name));
    }

    @Override
    public void touchDown(Vector2 vector2, int pointer, int button) {
        if(this.isInside(vector2) && !this.pressed) {
            this.currentButton = button;
            this.pressed = true;
            this.currentPointer = pointer;
            this.setScale(this.PRESSSCALE);
        }
    }

    @Override
    public void touchUp(Vector2 vector2, int pointer, int button) {
        if(this.pressed && this.currentButton == button && this.currentPointer == pointer && this.isInside(vector2)) {
            this.run();
        }
        this.pressed = false;
        this.setScale(1f);
    }

    @Override
    public void run() {

    }

    @Override
    public void resize(Rect rectWorld) {
        super.resize(rectWorld);
        this.setCenter(rectWorld.getCenter());
    }
}
