import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.RED;

public class ClickHandler implements EventHandler<MouseEvent> {
    private final Button _button;
    private GameManager _gameManager;
    public ClickHandler(Button button) {
        _button = button;
        _gameManager = GameManager.get_instance();
    }

    @Override
    public void handle(MouseEvent event) {
        //TODO CHANGING THE TURN OF CURRENT PLAYER && MAKE CURRENT TURN VISIBLE ( PUT THE CODE IN BEST PLACE )
        if (_button.getText().equals("")) {
            if (_gameManager.get_turn() == 0) {
                _button.setText("X");
                _button.setTextFill(RED);
                _button.setFont(Font.font(90));

            } else if (_gameManager.get_turn() == 1) {
                _button.setText("O");
                _button.setTextFill(BLUE);
                _button.setFont(Font.font(90));
            }
            GameManager.get_instance().checkWinner();
        }
    }
}