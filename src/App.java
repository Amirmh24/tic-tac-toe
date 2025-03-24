import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new PaneOrganizer().get_root(),1100,650);
        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe");
        stage.setResizable(false);
        stage.show();
    }
}
