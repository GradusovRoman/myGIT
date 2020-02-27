package ru.geekbrains.stargame;

import com.badlogic.gdx.Game;
import ru.geekbrains.stargame.screen.GameMenu;


public class StarGame extends Game {

	public void create() {
		this.setScreen(new GameMenu(this));
	}
}
