import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static javafx.scene.paint.Color.*;

public class PaneOrganizer {

    private HBox _root = new HBox();
    private Pane _stackPane = new StackPane();
    private GridPane _board;
    private GridPane _scorePane;
    private Button _playAgainButton = new Button("Play Again");

    private Button[][] buttons = new Button[3][3];

    private Label _player1Label;
    private Label _player2Label;
    private Label _player1ScoreLabel = new Label("0");
    private Label _player2ScoreLabel = new Label("0");
    private Label _winnerLabel = new Label();

    public PaneOrganizer() {

        GameManager gameManager = new GameManager(this);
        initRoot();
        initButtons();
        initBoard();
        initScorePanel();


    }

    private void initRoot() {
        Image bgImage = new Image("file:xo.jpg");
        Background bg = new Background(new BackgroundImage(bgImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        _root.setBackground(bg);
        _root.setSpacing(15);
        _root.setPadding(new Insets(25));
    }

    public void initButtons() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = null;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button();
            }
        }

        for (Button[] bArray : buttons) {
            for (Button b : bArray) {
                b.setOnMouseClicked(new ClickHandler(b));
                b.setMinWidth(234);
                b.setMinHeight(200);
                b.setBorder(new Border(new BorderStroke(BLACK, BorderStrokeStyle.SOLID, null, BorderWidths.DEFAULT)));
            }
        }

    }

    public void addButtons(){
        _board.getChildren().clear();
        for (int i = 0; i < 3; i++) {
            _board.getChildren().addAll(buttons[i]);
        }
    }

    private void initBoard() {
        _board = new GridPane();
        _stackPane.getChildren().add(_board);
        _board.setPrefWidth(702);
        _board.setPrefHeight(600);
        addButtons();
        positionButtons();


        _root.getChildren().add(_stackPane);
    }

    public void positionButtons(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                GridPane.setColumnIndex(buttons[i][j], j);
                GridPane.setRowIndex(buttons[i][j], i);
            }
        }
    }

    private void initScorePanel() {
        _scorePane = new GridPane();
        _scorePane.setAlignment(Pos.CENTER);
        _scorePane.setHgap(15);
        _scorePane.setVgap(30);
        _scorePane.setBackground(new Background(new BackgroundFill(new Color(1, 1, 1, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        _player1Label = new Label("Player 1:");
        _player1Label.setFont(Font.font(40));
        _player1Label.setTextFill(RED);
        _player2Label = new Label("Player 2:");
        _player2Label.setFont(Font.font(40));
        _player2Label.setTextFill(BLUE);
        _player1ScoreLabel.setFont(Font.font(40));
        _player1ScoreLabel.setTextFill(BLACK);
        _player2ScoreLabel.setFont(Font.font(40));
        _player2ScoreLabel.setTextFill(BLACK);
        _scorePane.getChildren().addAll(_player1Label, _player2Label, _player1ScoreLabel, _player2ScoreLabel);
        GridPane.setRowIndex(_player1Label, 0);
        GridPane.setRowIndex(_player2Label, 1);
        GridPane.setColumnIndex(_player1Label, 0);
        GridPane.setColumnIndex(_player2Label, 0);
        GridPane.setRowIndex(_player1ScoreLabel, 0);
        GridPane.setRowIndex(_player2ScoreLabel, 1);
        GridPane.setColumnIndex(_player1ScoreLabel, 1);
        GridPane.setColumnIndex(_player2ScoreLabel, 1);

        _scorePane.getChildren().add(_playAgainButton);
        _playAgainButton.setMinWidth(230);
        GridPane.setRowIndex(_playAgainButton, 2);
        _playAgainButton.setOnMouseClicked(e -> {
            GameManager.get_instance().resetGame();
        });

        _winnerLabel.setFont(Font.font(40));
        _scorePane.getChildren().add(_winnerLabel);
        GridPane.setRowIndex(_winnerLabel, 5);

        _root.getChildren().add(_scorePane);
    }

    public HBox get_root() {
        return _root;
    }

    public Button[][] getButtons() {
        return buttons;
    }

    public GridPane get_board() {
        return _board;
    }

    public Pane get_stackPane() {
        return _stackPane;
    }

    public Label get_player1Label() {
        return _player1Label;
    }

    public Label get_player2Label() {
        return _player2Label;
    }

    public Label get_player1ScoreLabel() {
        return _player1ScoreLabel;
    }

    public Label get_player2ScoreLabel() {
        return _player2ScoreLabel;
    }

    public Label get_winnerLabel() {
        return _winnerLabel;
    }
}
