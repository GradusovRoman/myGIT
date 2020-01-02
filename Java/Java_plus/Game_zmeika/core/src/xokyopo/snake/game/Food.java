package xokyopo.snake.game;

//просто еда (разрушаемый объект)

public class Food extends GameObject {
    private final String TYPE = "food";

    public Food (int[] _xy) {
        super(_xy[0] , _xy[1] , true);
    }

    @Override
    public String getType() {
        return this.TYPE;
    }
}
