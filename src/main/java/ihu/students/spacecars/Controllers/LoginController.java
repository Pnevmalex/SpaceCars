package ihu.students.spacecars.Controllers;

import ihu.students.spacecars.DatabaseConnection;
import ihu.students.spacecars.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private Button loginBtn;
    @FXML
    private Button registerBtn;
    @FXML
    private Label loginFailed;
    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    public void loginBtnOnAction(ActionEvent e) {

        try {

            ResultSet rs = DatabaseConnection.statement.executeQuery("SELECT user_login('" + usernameTextField.getText() + "', '" + passwordTextField.getText() + "') AS EMAIL;");

            while (rs.next()) {
                String mail = rs.getString("EMAIL");

                if (mail != null) {
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(Main.class.getResource("cars.fxml"));
                    } catch (IOException x) {
                        throw new RuntimeException(x);
                    }
                    Stage window = (Stage) loginBtn.getScene().getWindow();
                    window.setScene(new Scene(root, 1250, 750));
                } else {
                    loginFailed.setText("Authentication failed. Please try again.");
                    usernameTextField.setText(null);
                    passwordTextField.setText(null);
                    usernameTextField.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
                    passwordTextField.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
                }
            }
        } catch (SQLException a) {
            a.printStackTrace();
        }


    }


    public void registerBtnOnAction(ActionEvent e) {
        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource("register.fxml"));
        } catch (IOException x) {
            throw new RuntimeException(x);
        }
        Stage window = (Stage) registerBtn.getScene().getWindow();
        window.setScene(new Scene(root, 720, 500));
    }

}