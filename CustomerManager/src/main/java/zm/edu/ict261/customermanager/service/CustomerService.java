package zm.edu.ict261.customermanager.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import zm.edu.ict261.customermanager.model.Customer;

import java.util.List;

/**
 * Service layer: application rules for creating and deleting customers.
 * Data is kept in memory, so closing the app loses it. A DAO could later
 * replace the list with database operations without changing the controller.
 */
public class CustomerService {

    private static final List<String> PROVINCES = List.of(
            "Central", "Copperbelt", "Eastern", "Luapula", "Lusaka",
            "Muchinga", "Northern", "North-Western", "Southern", "Western");

    // The same list is kept for the whole run so the TableView stays connected.
    private final ObservableList<Customer> customers =
            FXCollections.observableArrayList();

    public CustomerService() {
        // Sample rows from the lecture slides
        customers.add(new Customer("Mary Banda", "Central"));
        customers.add(new Customer("John Phiri", "Lusaka"));
    }

    public ObservableList<Customer> getCustomers() { return customers; }

    public List<String> getProvinces() { return PROVINCES; }

    public Customer createCustomer(String name, String province) {
        Customer customer = new Customer(name, province);
        customers.add(customer);
        return customer;
    }

    public void deleteCustomer(Customer customer) {
        customers.remove(customer);
    }
}
