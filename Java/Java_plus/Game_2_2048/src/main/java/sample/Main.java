package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {
    private GamePane gamePane;
    private double minSize = 800;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.gamePane = new GamePane();
        primaryStage.setTitle("2048");
        primaryStage.setMinHeight(minSize);
        primaryStage.setMinWidth(minSize);
        primaryStage.setScene(new Scene(this.gamePane, minSize, minSize));
        primaryStage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (!gamePane.getGame().isEndGame()) {
                    if (keyEvent.getCode().equals(KeyCode.LEFT))
                        gamePane.getGame().getGameField().slidingBorder(Command.LEFT);
                    else if (keyEvent.getCode().equals(KeyCode.RIGHT))
                        gamePane.getGame().getGameField().slidingBorder(Command.RIGHT);
                    else if (keyEvent.getCode().equals(KeyCode.UP))
                        gamePane.getGame().getGameField().slidingBorder(Command.UP);
                    else if (keyEvent.getCode().equals(KeyCode.DOWN))
                        gamePane.getGame().getGameField().slidingBorder(Command.DOWN);
                    gamePane.ubdateSourceByMap();
                } else {
                    showMsg();
                    gamePane.getGame().initialize();
                    gamePane.ubdateSourceByMap();
                }
            }
        });
        primaryStage.show();
        gamePane.ubdateSourceByMap();
    }

    public void showMsg(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("2048");
        alert.setContentText("Игра окончена");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
