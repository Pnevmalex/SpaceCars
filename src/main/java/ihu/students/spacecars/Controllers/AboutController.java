package ihu.students.spacecars.Controllers;

import ihu.students.spacecars.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AboutController implements Initializable {
    @FXML
    private Button carsBtn;


    @FXML
    private Button customersBtn;

    @FXML
    private Button employeesBtn;

    @FXML
    private Button aboutBtn;
    @FXML
    private Button logoutBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    //menu
    @FXML
    private void handleClicks(ActionEvent event) {
        CarsController.MakeMenu(event, carsBtn, customersBtn, employeesBtn, aboutBtn);
    }

    //logout
    public void logoutBtnOnAction(ActionEvent e) {
        //petakse to login
        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource("login.fxml"));
        } catch (IOException x) {
            throw new RuntimeException(x);
        }
        Stage window = (Stage) logoutBtn.getScene().getWindow();
        window.setScene(new Scene(root, 720, 500));
    }
}
