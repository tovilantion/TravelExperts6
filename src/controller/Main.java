package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    private double x, y;
    static Stage stg;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stg = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
        stg.setScene(new Scene(root));
        //set stage borderless
        stg.initStyle(StageStyle.UNDECORATED);

        //drag it here
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {

            stg.setX(event.getScreenX() - x);
            stg.setY(event.getScreenY() - y);

        });
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
