package zm.edu.ict261.customermanager.model;

/** A customer groups a name and a province. Getters let table columns read them. */
public class Customer {
    private final String name;
    private final String province;

    public Customer(String name, String province) {
        this.name = name;
        this.province = province;
    }

    public String getName() { return name; }

    public String getProvince() { return province; }

    @Override
    public String toString() {
        return name + " (" + province + ")";
    }
}
