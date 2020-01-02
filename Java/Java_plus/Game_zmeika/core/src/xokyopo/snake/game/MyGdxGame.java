package xokyopo.snake.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	private int windowsSize = 800;
	private Game game = new Game();
	private int cellSize = 80;
	private GameActionListener GAL;
	SpriteBatch batch;

	Texture cell;
	Texture food;
	Texture head;
	Texture body;
	private int count = 120000;

	
	@Override
	public void create () {
		this.GAL = new GameActionListener(this.game);
		Gdx.input.setInputProcessor(this.GAL);
		this.windowsSize = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		cell = new Texture("cell.png");
		food = new Texture("food.png");
		head = new Texture("head.png");
		body = new Texture("body.png");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.graphics.setWindowedMode(this.windowsSize , this.windowsSize);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.cellSize = Gdx.graphics.getWidth() / this.game.getMapSize();

		batch.begin();

		this.drowingStatic();

		if (count >= 60) {
			this.game.gameLoop();
			count = 0;
		}

		count++;
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		cell.dispose();
		food.dispose();
		head.dispose();
		body.dispose();
	}

	private void drowingStatic() {
		this.drowEmptyMap();

		for (int row = 0 ; row < this.game.getMapSize() ; row++) {
			for (int col = 0 ; col < this.game.getMapSize() ; col++) {
				//отрисовка объектов
				if (this.game.getMap().getGameObjectByCoord(new int[] {row , col}) != null) {
					if (this.game.getMap().getGameObjectByCoord(new int[] {row , col}).getType().equals("food")) {
						this.batch.draw(this.food, row * cellSize , col * cellSize , cellSize , cellSize);
					} else if (this.game.getMap().getGameObjectByCoord(new int[] {row , col}).getType().equals("snake")) {
						this.batch.draw(this.body, row * cellSize , col * cellSize , cellSize , cellSize);
					} else if (this.game.getMap().getGameObjectByCoord(new int[] {row , col}).getType().equals("head")) {
						this.batch.draw(this.head, row * cellSize , col * cellSize , cellSize , cellSize);
					}
				}

			}
		}
	}

	private void drowEmptyMap(){
		for (int row = 0 ; row < this.game.getMapSize() ; row++) {
			for (int col = 0 ; col < this.game.getMapSize() ; col++) {
				this.batch.draw(this.cell , col * this.cellSize , row * this.cellSize  , this.cellSize , this.cellSize);
			}
		}
	}
}
