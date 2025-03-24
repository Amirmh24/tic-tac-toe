import javafx.geometry.Insets;
import javafx.scene.control.Button;

import javafx.geometry.Point2D;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.Random;

public class GameManager {

    private static GameManager _instance;
    private PaneOrganizer _paneOrganizer;
    private int _turn;
    private int _winner = -1;
    private Button[][] _buttons;
    private int _oScore = 0;
    private int _xScore = 0;
    private Line _line;
    public GameManager(PaneOrganizer paneOrganizer) {
        _paneOrganizer = paneOrganizer;
        _instance = this;
        _buttons = paneOrganizer.getButtons();
    }

    public void checkWinner() {
        String str = "OOO";
        if (get_turn() == 0) str = "XXX";
        turnChanged();
        boolean found = false;
        Button[] winButtons = new Button[3];
        StringBuilder diameter1 = new StringBuilder();
        diameter1.append(_buttons[0][0].getText()).append(_buttons[1][1].getText()).append(_buttons[2][2].getText());
        StringBuilder diameter2 = new StringBuilder();
        diameter2.append(_buttons[0][2].getText()).append(_buttons[1][1].getText()).append(_buttons[2][0].getText());
        if (diameter1.toString().equals(str)) {
            found = true;
            winButtons = new Button[]{_buttons[0][0], _buttons[1][1], _buttons[2][2]};
        }
        if (diameter2.toString().equals(str)) {
            found = true;
            winButtons = new Button[]{_buttons[0][2], _buttons[1][1], _buttons[2][0]};
        }
        if (found) {
            drawWinRow(winButtons);
            gameEnd();
        }
        for (int i = 0; i < 3 &&!found; i++) {
            StringBuilder row = new StringBuilder();
            StringBuilder column = new StringBuilder();
            for (int j = 0; j < 3; j++) {
                row.append(_buttons[i][j].getText());
                column.append(_buttons[j][i].getText());
            }
            if (row.toString().equals(str)) {
                found = true;
                winButtons = new Button[]{_buttons[i][0], _buttons[i][1], _buttons[i][2]};
            }
            if (column.toString().equals(str)) {
                found = true;
                winButtons = new Button[]{_buttons[0][i], _buttons[1][i], _buttons[2][i]};
            }
            if (found) {
                drawWinRow(winButtons);
                gameEnd();
                break;
            }
        }
        int count=0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(_buttons[i][j].getText().equals("X")||_buttons[i][j].getText().equals("O")){
                    count++;
                }
            }
        }
        if (count==9){
            found=true;
            _turn=2;
            gameEnd();
        }
        if (!found) {
            showTurn(_turn);
        }
    }

    private int determineWinState() {
        if (_turn == 1) {
            _winner = 0;
        } else if (_turn == 0) {
            _winner = 1;
        } else {
            _winner = -1;
        }
        return _winner;
    }

    private void changeButtons(boolean isDisable) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (_buttons[i][j].getText().equals("")) {
                    _buttons[i][j].setDisable(isDisable);
                }
            }
        }
    }

    private void gameEnd() {
        changeButtons(true);
        switch (determineWinState()) {
            case 1:
                System.out.println("O Wins");
                _paneOrganizer.get_winnerLabel().setText("O Player Won!");
                _oScore++;
                _turn = 1;
                break;
            case 0:
                System.out.println("X Wins");
                _paneOrganizer.get_winnerLabel().setText("X Player Won!");
                _xScore++;
                _turn = 0;
                break;
            case -1:
                System.out.println("Draw");
                _paneOrganizer.get_winnerLabel().setText("Draw!");
                _turn = new Random().nextInt(2);
                break;
        }
    }

    public void turnChanged() {
        if (_turn == 1) {
            _turn = 0;
        } else {
            _turn = 1;
        }
    }

    public void showTurn(int turn) {
        if (turn == 1) {
            _paneOrganizer.get_player1Label().setDisable(true);
            _paneOrganizer.get_player2Label().setDisable(false);
        } else {
            _paneOrganizer.get_player1Label().setDisable(false);
            _paneOrganizer.get_player2Label().setDisable(true);
        }
    }

    public void resetGame() {
        changeButtons(false);
        _paneOrganizer.get_player1ScoreLabel().setText(_xScore + "");
        _paneOrganizer.get_player2ScoreLabel().setText(_oScore + "");
        _paneOrganizer.get_stackPane().getChildren().remove(_line);
        _paneOrganizer.get_winnerLabel().setText("");
        _paneOrganizer.initButtons();
        _paneOrganizer.addButtons();
        _paneOrganizer.positionButtons();

    }

    private void drawWinRow(Button... winBtns) {
        for (Button b : winBtns) {
            b.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
            b.setTextFill(Color.WHITE);
        }
    }

    public int get_turn() {
        return _turn;
    }

    public int get_winner() {
        return _winner;
    }

    public static GameManager get_instance() {
        return _instance;
    }
}
