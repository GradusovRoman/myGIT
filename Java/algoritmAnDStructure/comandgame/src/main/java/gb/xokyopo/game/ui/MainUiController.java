package gb.xokyopo.game.ui;

import gb.xokyopo.game.core.GameCore;
import gb.xokyopo.game.entitys.Classes;
import gb.xokyopo.game.entitys.Hero;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;

//TODO присвоение названий команд, при запуске.
public class MainUiController {
    private final GameCore gameCore;
    private final int MAX_TRY_TURN = 1000;

    @FXML
    private TextField teamSize;
    @FXML
    private Button leftAddHeroButton;
    @FXML
    private Button rightAddHeroButton;
    @FXML
    private TextField leftTeamNameField;
    @FXML
    private TextField rightTeamNameField;
    @FXML
    private ListView<Hero> leftHeroList;
    @FXML
    private ListView<Hero> rightHeroList;
    @FXML
    private TextArea combatLog;
    @FXML
    private CheckBox changeBoxUnique;
    @FXML
    private ComboBox<Classes> leftHeroClassBox;
    @FXML
    private ComboBox<Classes> rightHeroClassBox;

    public MainUiController() {
        this.gameCore = new GameCore();
    }

    @FXML
    private void initialize() {
        //TODO реализовать выбор количества героев.
        this.leftTeamNameField.setText("N1");
        this.rightTeamNameField.setText("N2");
        this.gameCore.setHeroTeamName(this.leftTeamNameField.getText());
        this.gameCore.setEnemyTeamName(this.rightTeamNameField.getText());
        this.teamSize.setText("3");
        this.gameCore.setMaxHero(3);
        this.leftHeroClassBox.getItems().addAll(Classes.values());
        this.leftHeroClassBox.setValue(this.leftHeroClassBox.getItems().get(0));
        this.rightHeroClassBox.getItems().addAll(Classes.values());
        this.rightHeroClassBox.setValue(this.rightHeroClassBox.getItems().get(0));
        this.changeBoxUnique.setSelected(this.gameCore.isOnlyUniqueHero());
        this.rightTeamNameField.textProperty().addListener(this::applyRightTeamName);
        this.leftTeamNameField.textProperty().addListener(this::applyLeftTeamName);
        this.teamSize.textProperty().addListener(this::applyTeamSize);

        //TODO установить значения палей.
    }


    @FXML
    public void changeUniqueSetup() {
        try {
            this.gameCore.changeOnlyUniqueHero();
        } catch (Exception e) {
            this.showDeleteHeroError(e.getMessage());
        }
        this.updateHeroList();
        this.changeBoxUnique.setSelected(this.gameCore.isOnlyUniqueHero());
    }

    @FXML
    public void runProgram() {
        if (this.gameCore.isReady()) {
            this.lockAllElement();
            int count = 0;
            this.combatLog.setText("");
            while (this.gameCore.canTurn() && count < this.MAX_TRY_TURN) {
                count ++;
                String log = this.gameCore.runTurn();
                if (this.gameCore.canTurn()) {
                    this.addCombatLog("Ход №" + count, log);
                } else {
                    this.addCombatLog("", log);
                }
                this.updateHeroList();
            }
            if (count >= this.MAX_TRY_TURN) {
                this.combatLog.setText("Ничья так как бой не смог закончиться за " + count + " ходов\n" + this.combatLog.getText());
            } else {
                this.combatLog.setText("Бой закончился за " + (count - 1) + " ходов\n" + this.combatLog.getText());
            }
            this.unLockAllElement();
        } else {
            this.runGameError();
        }
    }

    private void addCombatLog(String loopCountText, String message){
        this.combatLog.setText(loopCountText + "\n" + message + "\n\n" + this.combatLog.getText());
    }

    private void runGameError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Ошибка запуска");
        alert.setHeaderText("Размер команд должен быть: " + this.teamSize.getText() + "\nТак же поля названия команд не должны быть пустыми!");
        alert.setContentText(
                "Команда " + this.leftTeamNameField.getText() + " имеет: " + this.gameCore.getHeroList().size() + " из " + this.teamSize.getText() + " героев\n" +
                "Команда " + this.rightTeamNameField.getText() + " имеет: " + this.gameCore.getEnemyList().size() + " из " + this.teamSize.getText() + " героев");

        alert.showAndWait();
    }

    @FXML
    public void addRightHero() {
        Classes classes = this.rightHeroClassBox.getValue();
        try {
            this.gameCore.addHeroToEnemyTeam(classes);
            this.updateHeroList();
        } catch (Exception e) {
            this.showAddHeroError(e.getMessage());
        }
    }

    @FXML
    public void addLeftHero(){
        Classes classes = this.leftHeroClassBox.getValue();
        try {
            this.gameCore.addHeroToHeroTeam(classes);
            this.updateHeroList();
        } catch (Exception e) {
            this.showAddHeroError(e.getMessage());
        }
    }

    private void showAddHeroError(String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Героя нельзя добавить");
        alert.setHeaderText("Не возможно добавить героя");
        alert.setContentText(text);

        alert.showAndWait();
    }

    private void showDeleteHeroError(String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Удалены герои");
        alert.setHeaderText("Ваши герои были удалены");
        alert.setContentText(text);

        alert.showAndWait();
    }

    private void lockAllElement() {
        this.setDisabled(true);
    }

    private void unLockAllElement() {
        this.setDisabled(false);
    }

    private void setDisabled(boolean value) {
        this.changeBoxUnique.setDisable(value);
        this.leftAddHeroButton.setDisable(value);
        this.leftTeamNameField.setDisable(value);
        this.rightAddHeroButton.setDisable(value);
        this.rightTeamNameField.setDisable(value);
    }

    private void updateHeroList() {
        this.leftHeroList.getItems().clear();
        this.leftHeroList.getItems().addAll(this.gameCore.getHeroList());
        this.rightHeroList.getItems().clear();
        this.rightHeroList.getItems().addAll(this.gameCore.getEnemyList());
    }

    @FXML
    public void applyLeftTeamName(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if(!newValue.equals("")) {
            this.gameCore.setEnemyTeamName(newValue);
        } else{
            this.teamNameNotNull();
        }
    }

    @FXML
    public void applyRightTeamName(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if(!newValue.equals("")) {
            this.gameCore.setEnemyTeamName(newValue);
        } else {
            this.teamNameNotNull();
        }
    }

    @FXML
    public void applyTeamSize(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        try {
            int a = Integer.parseInt(newValue);
            if (a!=0) {
                this.gameCore.setMaxHero(a);
            } else {
                teamSizeError();
            }
        } catch (Exception e) {
            this.teamSize.setText(oldValue);
        }
    }

    private void teamSizeError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Размер команды");
        alert.setHeaderText("Не коректный размер команд");
        alert.setContentText("развер команд не должен быть равным нулю.");

        alert.showAndWait();
    }

    private void teamNameNotNull(){
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Ошибка в имени команды");
        alert.setHeaderText("Не задано имя команды");
        alert.setContentText("Поле Имени команды не должно быть пустым");

        alert.showAndWait();
    }
}
