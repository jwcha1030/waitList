package waiting;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.TextField;


public class WaitList extends Application
{
    public static Application app;

    public WaitList()
    {
        WaitList.app = this;
    }
    @Override
    public void start(Stage primaryStage) throws Exception

    {
        primaryStage.setTitle("대기 순번 표 출력");
        primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png")));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("waitList_layout.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);

        primaryStage.show();
        TextField tf=(TextField) scene.lookup("#nameTextField");
        tf.requestFocus();
    }
}
