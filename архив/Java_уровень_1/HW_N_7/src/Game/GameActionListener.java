package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameActionListener implements ActionListener{
    private GameButton gameButton;


    public GameActionListener(GameButton _gameButton){
        this.gameButton = _gameButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //обязательный метод
        if (this.updateByPlayersData() && !this.isEnd()){
            this.gameButton.getBoard().getGame().passTurn();
            this.updateByAiDate();
            if (!this.isEnd()) {
                this.gameButton.getBoard().getGame().passTurn();
            }
        }
    }

    public boolean updateByPlayersData(){
        //ход человека
        boolean result = false;
        int x = this.gameButton.get_buttonIndex()/this.gameButton.getBoard().get_mapSize();
        int y = this.gameButton.get_buttonIndex()%this.gameButton.getBoard().get_mapSize();

        if (this.gameButton.getBoard().isTurnable(x,y)){
            this.gameButton.getBoard().updateGameFiedl(x,y);
            result = true;
        }else{
            this.gameButton.getBoard().getGame().showMessage("Упс, тут уже занято");
        }
        return result;
    }

    public void updateByAiDate(){
        //ход АИ
        int[] coord = {0,0};
        if (this.gameButton.getBoard().getGame().get_mode()){
            do{
                coord[0] = new Random().nextInt(this.gameButton.getBoard().get_mapSize());
                coord[1] = new Random().nextInt(this.gameButton.getBoard().get_mapSize());
            }while(!this.gameButton.getBoard().isTurnable(coord[0],coord[1]));
        }else {
            //логика сложного AI
            //создаем класс с искуственным интелектом
            GameAI ai = new GameAI(this.gameButton.getBoard().getGameField(), this.gameButton.getBoard().getGame().get_aiChar(), this.gameButton.getBoard().getGame().get_playerChar(), this.gameButton.getBoard().getGame().get_emptyChar());
            coord = ai.getXY();
        }
        this.gameButton.getBoard().updateGameFiedl(coord[0],coord[1]);
    }

    public boolean isEnd(){
        boolean result = false;
        String msg ="";
        if(this.gameButton.getBoard().checkWin() || this.gameButton.getBoard().isFull()){
            msg = (this.gameButton.getBoard().isFull())? "В этой игре нет победителя": "победил " + ((this.gameButton.getBoard().getGame().getCurentPlayer().isRealPlayer())? "Игрок":"Компьютер");
            this.gameButton.getBoard().getGame().showMessage(msg);
            this.gameButton.getBoard().emptyField();
            result = true;
        }
        return result;
    }


}
