import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {
    private final int id;
    private String name;
    private String category;
    private BigDecimal price;
    private int stock;

    public Product(int id, String name, String category, BigDecimal price, int stock) {
        if (price == null || price.signum() < 0 || stock < 0) {
            throw new IllegalArgumentException("Price and stock must be non-negative.");
        }
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price.setScale(2, RoundingMode.HALF_UP);
        this.stock = stock;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public BigDecimal getPrice() { return price; }
    public int getStock() { return stock; }

    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }

    public void setPrice(BigDecimal price) {
        if (price == null || price.signum() < 0) throw new IllegalArgumentException("Invalid price.");
        this.price = price.setScale(2, RoundingMode.HALF_UP);
    }

    public void addStock(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive.");
        stock += quantity;
    }

    public boolean isAvailable(int quantity) {
        return quantity > 0 && quantity <= stock;
    }

    public void reduceStock(int quantity) {
        if (!isAvailable(quantity)) throw new IllegalArgumentException("Insufficient stock.");
        stock -= quantity;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | %-22s | %-12s | ₹%s | Stock: %d",
                id, name, category, price.toPlainString(), stock);
    }
}
