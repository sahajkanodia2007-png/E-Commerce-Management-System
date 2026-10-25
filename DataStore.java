import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DataStore {
    public final List<Product> products = new ArrayList<>();
    public final List<Customer> customers = new ArrayList<>();
    public final List<Order> orders = new ArrayList<>();
    public final Admin admin = new Admin(1, "Store Admin", "admin@example.com");

    public DataStore() {
        products.add(new Product(101, "Wireless Mouse", "Electronics", new BigDecimal("599.00"), 15));
        products.add(new Product(102, "Mechanical Keyboard", "Electronics", new BigDecimal("2499.00"), 8));
        products.add(new Product(103, "Notebook Set", "Stationery", new BigDecimal("199.00"), 30));
        products.add(new Product(104, "Water Bottle", "Lifestyle", new BigDecimal("349.00"), 20));
        products.add(new Product(105, "Desk Lamp", "Home", new BigDecimal("899.00"), 6));
    }

    public Product findProduct(int id) {
        return products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public Customer findCustomer(int id) {
        return customers.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }
}
