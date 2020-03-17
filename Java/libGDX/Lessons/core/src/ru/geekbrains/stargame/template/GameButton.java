package ru.geekbrains.stargame.template;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.stargame.intefaces.Commandable;
import ru.geekbrains.stargame.intefaces.Touchable;

public abstract class GameButton extends Sprite implements Touchable, Commandable {
    private float PRESSSCALE = 0.9f;
    private float NORMSCALE = 1f;
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
            this.setHeightProportion(this.PRESSSCALE);
        }
    }

    @Override
    public void touchUp(Vector2 vector2, int pointer, int button) {
        if(this.pressed && this.currentButton == button && this.currentPointer == pointer && this.isInside(vector2)) {
            this.run();
        }
        this.pressed = false;
        this.setHeightProportion(this.NORMSCALE);
    }

    @Override
    public void run() {

    }

    @Override
    public void resize(Rect rectWorld) {
        super.resize(rectWorld);
        this.setCenter(rectWorld.getCenter());
        this.heightProportionFixForButton(rectWorld);
    }

    private void heightProportionFixForButton(Rect rectWorld) {
        this.setHeightProportion(Math.min(1f, 1f * (this.getCurrentTextureRegion().getRegionHeight()/rectWorld.getHeight())/( this.getCurrentTextureRegion().getRegionWidth()/rectWorld.getWidth())));
        this.NORMSCALE = this.getHeightProportion();
        this.PRESSSCALE = NORMSCALE * 0.9f;
    }

}
