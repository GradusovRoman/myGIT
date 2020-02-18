package ru.geekbrains.stargame;

import com.badlogic.gdx.Game;
import ru.geekbrains.stargame.screen.MainMenu;

public class StarGame extends Game {
	@Override
	public void create() {
		this.setScreen(new MainMenu());
	}
}
