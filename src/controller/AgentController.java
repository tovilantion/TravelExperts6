package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AgentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnSignin;

    @FXML
    private Label btnForgot;

    @FXML
    private Button btnSignup;

    @FXML
    private Label lblErrors;

    private double x, y;

    @FXML
    void handleButtonAction(MouseEvent event) {

    }

    @FXML
    void onActionBtnSignIn(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/home.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
            root.setOnMousePressed(event1 -> {
                x = event1.getSceneX();
                y = event1.getSceneY();
            });
            root.setOnMouseDragged(event1 -> {

                stage.setX(event1.getScreenX() - x);
                stage.setY(event1.getScreenY() - y);

            });
            Main.stg.close();
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onActonBtnSignUp(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert txtUsername != null : "fx:id=\"txtUsername\" was not injected: check your FXML file 'login.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'login.fxml'.";
        assert btnSignin != null : "fx:id=\"btnSignin\" was not injected: check your FXML file 'login.fxml'.";
        assert btnForgot != null : "fx:id=\"btnForgot\" was not injected: check your FXML file 'login.fxml'.";
        assert btnSignup != null : "fx:id=\"btnSignup\" was not injected: check your FXML file 'login.fxml'.";
        assert lblErrors != null : "fx:id=\"lblErrors\" was not injected: check your FXML file 'login.fxml'.";

    }
}
