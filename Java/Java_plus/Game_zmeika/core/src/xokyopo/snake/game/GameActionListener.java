package xokyopo.snake.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class GameActionListener implements InputProcessor {
    private Game game;
    private Snake snake;

    public GameActionListener(Game _game) {
        this.game = _game;
        this.snake = _game.getPlayer();
    }

    @Override
    public boolean keyDown(int keycode) {
        int[] up = {0 , -1};
        int[] down = {0 , 1};
        int[] left = {1 , 0};
        int[] right = {-1 , 0};
        switch (keycode){
            case Input.Keys.W: case Input.Keys.UP:
                snake.setVector(up);
                break;
            case Input.Keys.S: case Input.Keys.DOWN:
                snake.setVector(down);
                break;
            case Input.Keys.A: case Input.Keys.LEFT:
                snake.setVector(left);
                break;
            case Input.Keys.D: case Input.Keys.RIGHT:
                snake.setVector(right);
                break;
        }
        return true;
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
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
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
}
