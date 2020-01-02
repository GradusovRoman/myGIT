package xokyopo.snake.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import xokyopo.snake.game.MyGdxGame;

public class DesktopLauncher {
	private static int Size = 800;
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = Size;
		config.width = Size;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
