import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
    private final List<Order> orders = new ArrayList<>();

    public Customer(int id, String name, String email) {
        super(id, name, email);
    }

    @Override
    public String getRole() { return "Customer"; }

    public List<Order> getOrders() { return orders; }

    public void addOrder(Order order) {
        orders.add(order);
    }
}
