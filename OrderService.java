import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private final DataStore store;
    private int nextOrderId = 1001;

    public OrderService(DataStore store) { this.store = store; }

    public Order checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) throw new IllegalArgumentException("Your cart is empty.");

        // Validate all items before changing any stock.
        for (CartItem item : cart.getItems()) {
            if (!item.getProduct().isAvailable(item.getQuantity())) {
                throw new IllegalArgumentException("Insufficient stock for " + item.getProduct().getName());
            }
        }

        List<OrderItem> snapshot = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            Product p = item.getProduct();
            snapshot.add(new OrderItem(p.getId(), p.getName(), p.getPrice(), item.getQuantity()));
        }

        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceStock(item.getQuantity());
        }

        Order order = new Order(nextOrderId++, customer.getId(), snapshot, cart.getTotal());
        store.orders.add(order);
        customer.addOrder(order);
        cart.clear();
        return order;
    }

    public void listAllOrders() {
        if (store.orders.isEmpty()) { System.out.println("No orders yet."); return; }
        for (Order order : store.orders) System.out.println(order);
    }
}
