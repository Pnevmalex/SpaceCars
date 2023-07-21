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
import java.sql.SQLException;

public class RegisterController {

    @FXML
    private Button loginBtn;
    @FXML
    private Button registerBtn;
    @FXML
    private Label registerFailed;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passTextField;
    @FXML
    private TextField repassTextField;

    public void loginBtnOnAction(ActionEvent e) {
        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource("login.fxml"));
        } catch (IOException x) {
            throw new RuntimeException(x);
        }
        Stage window = (Stage) registerBtn.getScene().getWindow();
        window.setScene(new Scene(root, 720, 500));

    }

    public void registerBtnOnAction() {
        String email = emailTextField.getText();
        String name = nameTextField.getText();
        String username = usernameTextField.getText();
        String pass = passTextField.getText();
        String repass = repassTextField.getText();

        if (pass.equals(repass)) {

            try {
                DatabaseConnection.statement.executeQuery("SELECT add_admin('" + email + "', '" + name + "', '" + username + "', '" + pass + "');");
                registerFailed.setStyle("-fx-text-fill: #00c800");
                registerFailed.setText("Register Completed. Please login.");
                emailTextField.setText(null);
                nameTextField.setText(null);
                usernameTextField.setText(null);
                passTextField.setText(null);
                repassTextField.setText(null);
            } catch (SQLException s) {
                s.printStackTrace();
            }

        } else {
            emailTextField.setText(null);
            nameTextField.setText(null);
            usernameTextField.setText(null);
            passTextField.setText(null);
            repassTextField.setText(null);

            registerFailed.setStyle("-fx-text-fill: red");
            registerFailed.setText("Register Failed. Please retry!");
        }

    }


}
