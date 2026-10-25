import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private final List<CartItem> items = new ArrayList<>();

    public List<CartItem> getItems() { return Collections.unmodifiableList(items); }
    public boolean isEmpty() { return items.isEmpty(); }

    public void add(Product product, int quantity) {
        if (!product.isAvailable(quantity)) throw new IllegalArgumentException("Quantity exceeds available stock.");
        for (CartItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                int newQuantity = item.getQuantity() + quantity;
                if (!product.isAvailable(newQuantity)) throw new IllegalArgumentException("Quantity exceeds available stock.");
                item.setQuantity(newQuantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    public void remove(int productId) {
        items.removeIf(item -> item.getProduct().getId() == productId);
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem item : items) total = total.add(item.getSubtotal());
        return total;
    }

    public void clear() { items.clear(); }
}
