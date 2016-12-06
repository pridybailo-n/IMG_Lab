package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("IMG LAB");
        primaryStage.setScene(new Scene(root, 543, 324));
        primaryStage.setResizable(false);
        primaryStage.show();
        stage = primaryStage;

    }

    public static Stage getMainStage(){
        return stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
