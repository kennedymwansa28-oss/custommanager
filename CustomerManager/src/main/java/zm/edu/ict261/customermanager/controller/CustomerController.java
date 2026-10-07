package zm.edu.ict261.customermanager.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import zm.edu.ict261.customermanager.model.Customer;
import zm.edu.ict261.customermanager.service.CustomerService;

/** Controller: responds to UI actions, validates input and calls the service. */
public class CustomerController {

    @FXML private TextField nameField;
    @FXML private ComboBox<String> provinceBox;
    @FXML private Button saveButton;
    @FXML private Button deleteButton;
    @FXML private Label nameLabel;
    @FXML private Label status;
    @FXML private TableView<Customer> table;
    @FXML private TableColumn<Customer, String> nameCol;
    @FXML private TableColumn<Customer, String> provinceCol;

    private final CustomerService service = new CustomerService();

    @FXML
    private void initialize() {
        // Form
        provinceBox.getItems().addAll(service.getProvinces());
        nameLabel.setLabelFor(nameField);          // accessibility
        saveButton.setDefaultButton(true);         // Enter activates Save

        // Table: connect list and columns (PropertyValueFactory reads getName()/getProvince())
        table.setItems(service.getCustomers());
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        provinceCol.setCellValueFactory(new PropertyValueFactory<>("province"));
        table.setPlaceholder(new Label("No customers yet. Add one using the form."));

        // Delete is only available when a row is selected
        deleteButton.disableProperty().bind(
                table.getSelectionModel().selectedItemProperty().isNull());

        // Start with the cursor in the name field
        Platform.runLater(nameField::requestFocus);
    }

    /** Validate first, then add. Fields are cleared only after a successful save. */
    @FXML
    private void onSave() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            showStatus("Enter the customer name.", true);
            nameField.requestFocus();
            return;
        }

        String province = provinceBox.getValue();
        if (province == null) {
            showStatus("Choose a province.", true);   // name is kept
            provinceBox.requestFocus();
            return;
        }

        try {
            service.createCustomer(name, province);
        } catch (Exception ex) {
            ex.printStackTrace();                      // technical details go to the log
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Save failed");
            alert.setHeaderText("Customer could not be saved");
            alert.setContentText("Check the details and try again.");
            alert.showAndWait();                       // form values are kept
            return;
        }

        showStatus("Customer saved.", false);
        clearForm();
    }

    /** Ask for confirmation. Cancel or closing the dialog leaves the record unchanged. */
    @FXML
    private void onDelete() {
        Customer selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showStatus("Select a customer to delete.", true);
            return;
        }

        ButtonType delete = new ButtonType("Delete");
        Alert ask = new Alert(Alert.AlertType.CONFIRMATION,
                "Delete the selected customer?\n" + selected,
                delete, ButtonType.CANCEL);
        ask.setTitle("Delete customer");
        ask.setHeaderText("Confirm deletion");

        if (ask.showAndWait().orElse(ButtonType.CANCEL) == delete) {
            service.deleteCustomer(selected);
            showStatus("Customer deleted.", false);
        } else {
            showStatus("Deletion cancelled.", false);
        }
    }

    @FXML
    private void onClear() {
        clearForm();
        showStatus("Form cleared.", false);
    }

    @FXML
    private void onClose() {
        Platform.exit();
    }

    @FXML
    private void onAbout() {
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setTitle("About");
        about.setHeaderText("Customer Manager");
        about.setContentText("ICT261 Advanced Java Programming - Lecture 3 lab.\n"
                + "Data is stored in memory only; closing the app loses it.");
        about.showAndWait();
    }

    private void clearForm() {
        nameField.clear();
        provinceBox.setValue(null);
        nameField.requestFocus();
    }

    private void showStatus(String message, boolean error) {
        status.setText(message);
        status.getStyleClass().removeAll("status-error", "status-ok");
        status.getStyleClass().add(error ? "status-error" : "status-ok");
    }
}
