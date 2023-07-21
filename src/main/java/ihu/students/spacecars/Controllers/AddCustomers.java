package ihu.students.spacecars.Controllers;

import ihu.students.spacecars.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class AddCustomers implements Initializable {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField carIDTextField;
    @FXML
    private TextField priceTextField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void cleancustomer() {
        nameTextField.setText(null);
        phoneTextField.setText(null);

    }

    public void order() {
        String name = nameTextField.getText();
        String phone = phoneTextField.getText();
        String carid = carIDTextField.getText();

        try {
            if (name.isEmpty() || phone.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please Fill All Data");
                alert.showAndWait();

            } else {
                DatabaseConnection.statement.executeQuery("SELECT add_customer('" + name + "', '" + phone + "', " + carid + ");");
                cleancustomer();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }



    void setTextFieldTwo(int carid, int price) {
        carIDTextField.setText("" + carid);
        priceTextField.setText("" + price);


    }

}
