package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.stargame.screen.template.MyScreen;

public class MainMenu extends MyScreen {
    private Texture background;
    private Texture player;

    private Vector2 playerPos;
    private Vector2 playerDestinationPos;
    private float playerSpedRate;
    private float playerScale;
    private Vector2 playerSpeed;

    private int playerH;
    private int playerW;

    @Override
    public void show() {
        super.show();

        this.playerPos = new Vector2();
        this.playerDestinationPos = new Vector2();
        this.playerSpeed = new Vector2();
        this.playerSpedRate = 1f;
        this.playerScale = 0.5f;

        this.background = new Texture("background.png");
        this.player = new Texture("badlogic.jpg");

        //получаю размеры игрока что бы каждый раз не пересчитывать, тем самым повышая производительность
        this.playerH = (int)(this.playerScale * this.player.getHeight());
        this.playerW = (int)(this.playerScale * this.player.getWidth());
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        this.spriteBatch.begin();

        //если мы пришли куда хотели то зачем отрисовывать одно и тоже лишний раз.
        if (this.playerDestinationPos != this.playerPos) {

            this.spriteBatch.draw(this.background,0,0);

            this.calculatePlayerNewPos();

            this.spriteBatch.draw(this.player, this.playerPos.x, this.playerPos.y, this.playerW, this.playerH);
        }

        this.spriteBatch.end();
    }

    public void calculatePlayerNewPos() {
        //если длинна пути меньше рейтинга скорости то вычисляем скорость. (уберает дрозжание вызванное тем что игрок не может попать в нужные координаты)
        if (this.playerSpeed.set(this.playerDestinationPos).sub(this.playerPos).len() >= this.playerSpedRate) {
            this.playerSpeed.nor().scl(this.playerSpedRate);
        }

        //изменяем позицию игрока только если он при этом не выйдет за границы экрана
        if (this.isValidStep(this.playerPos, this.playerSpeed, this.playerH, this.playerW)) {
            this.playerPos.add(this.playerSpeed);
        }
    }

    //проверяем будет ли текущее смещение коректным
    private boolean isValidStep(Vector2 object, Vector2 speed, int playerH, int playerW) {
        return this.isObjectOnGameBoard(new Vector2(object).add(speed), playerH, playerW);
    }


    private boolean isObjectOnGameBoard(Vector2 object, int objectH, int objectW) {
        return this.isObjectBoundOnX(object, objectW) && this.isObjectBoundOnY(object, objectH);
    }

    private boolean isObjectBoundOnX(Vector2 object, int objectW) {
        return (object.x + objectW <= Gdx.graphics.getWidth()) && object.x >= 0;
    }

    private boolean isObjectBoundOnY(Vector2 object, int objectH) {
        return (object.y + objectH <= Gdx.graphics.getHeight()) && object.y >= 0;
    }

    @Override
    public void dispose() {
        super.dispose();
        this.background.dispose();
        this.player.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        this.playerDestinationPos.set(screenX, (Gdx.graphics.getHeight() - screenY));
        return false;
    }
}
