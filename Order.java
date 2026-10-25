import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    public enum Status { CONFIRMED, PACKED, SHIPPED, DELIVERED, CANCELLED }

    private final int id;
    private final int customerId;
    private final List<OrderItem> items = new ArrayList<>();
    private final BigDecimal total;
    private final LocalDateTime createdAt;
    private Status status = Status.CONFIRMED;

    public Order(int id, int customerId, List<OrderItem> items, BigDecimal total) {
        this.id = id;
        this.customerId = customerId;
        this.items.addAll(items);
        this.total = total;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() { return id; }
    public int getCustomerId() { return customerId; }
    public BigDecimal getTotal() { return total; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public List<OrderItem> getItems() { return Collections.unmodifiableList(items); }

    @Override
    public String toString() {
        return "Order #" + id + " | " + createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                + " | Total: ₹" + total + " | Status: " + status;
    }
}
