# Customer Manager (ICT261 Lecture 3 lab)

JavaFX app: form (TextField + ComboBox), TableView bound to an ObservableList,
validation, confirm-on-delete, menu bar / toolbar, keyboard support.

## Run in IntelliJ IDEA
1. File > Open > select the `CustomerManager` folder (the one containing pom.xml) > "Open as Project".
2. Wait for Maven to download dependencies. Use JDK 17 or newer (File > Project Structure > SDK).
3. Open `Launcher.java` and click the green Run arrow next to `main`.
   (Or: Maven tool window > Plugins > javafx > javafx:run)

## Structure
- model/Customer         - name + province
- service/CustomerService - rules, ObservableList kept in memory
- controller/CustomerController - validation and UI actions
- resources/.../customer-view.fxml + style.css - the View
