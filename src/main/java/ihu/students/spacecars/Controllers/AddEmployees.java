package ihu.students.spacecars.Controllers;

import ihu.students.spacecars.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddEmployees implements Initializable {
    @FXML
    private TextField nameTextField;

    @FXML
    private TextField ageTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField categoryTextField;

    boolean update = false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void save() {

        String name = nameTextField.getText();
        String age = ageTextField.getText();
        String phone = phoneTextField.getText();
        String category = categoryTextField.getText();


        try {
            if (name.isEmpty() || age.isEmpty() || phone.isEmpty() || category.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please Fill All Data");
                alert.showAndWait();

            } else {
                if (!update) {
                    DatabaseConnection.statement.executeQuery("SELECT add_emp('" + name + "', " + age + ", '" + phone + "', '" + category + "');");
                } else {
                    DatabaseConnection.statement.executeQuery("SELECT update_emp('" + name + "', " + age + ", '" + phone + "', '" + category + "');");
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            clean();
        }

    }


    void setTextField(String name, Integer age, String phone, String category) {

        nameTextField.setText(name);
        ageTextField.setText("" + age);
        phoneTextField.setText(phone);
        categoryTextField.setText(category);
    }

    @FXML
    private void clean() {
        nameTextField.setText(null);
        ageTextField.setText(null);
        phoneTextField.setText(null);
        categoryTextField.setText(null);
    }

}
