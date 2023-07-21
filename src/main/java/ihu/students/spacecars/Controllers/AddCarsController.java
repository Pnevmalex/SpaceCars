package ihu.students.spacecars.Controllers;

import ihu.students.spacecars.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCarsController implements Initializable {
    @FXML
    private TextField idAdd;

    @FXML
    private TextField madeAdd;

    @FXML
    private TextField modelAdd;

    @FXML
    private TextField yearAdd;

    @FXML
    private TextField priceAdd;

    boolean update = false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    @FXML
    private void save() {

        String id = idAdd.getText();
        String made = madeAdd.getText();
        String model = modelAdd.getText();
        String year = yearAdd.getText();
        String price = priceAdd.getText();

        try {
            if (id.isEmpty() || made.isEmpty() || model.isEmpty() || year.isEmpty() || price.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please Fill All Data");
                alert.showAndWait();

            } else {
                if (!update) {
                    DatabaseConnection.statement.executeQuery("SELECT add_car(" + id + ", '" + made + "', '" + model + "', " + year + ", " + price + ");");
                } else {
                    DatabaseConnection.statement.executeQuery("SELECT update_car(" + id + ", '" + made + "', '" + model + "', " + year + ", " + price + ");");
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            clean();
        }

    }

    void setTextField(int carid, String made, String model, int year, int price) {

        idAdd.setText("" + carid);
        madeAdd.setText(made);
        modelAdd.setText(model);
        yearAdd.setText("" + year);
        priceAdd.setText("" + price);

    }

    @FXML
    private void clean() {
        idAdd.setText(null);
        madeAdd.setText(null);
        modelAdd.setText(null);
        yearAdd.setText(null);
        priceAdd.setText(null);

    }

}
