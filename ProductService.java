import java.math.BigDecimal;

public class ProductService {
    private final DataStore store;
    private int nextProductId = 106;

    public ProductService(DataStore store) { this.store = store; }

    public void addProduct(String name, String category, BigDecimal price, int stock) {
        store.products.add(new Product(nextProductId++, name, category, price, stock));
        System.out.println("Product added successfully.");
    }

    public void listProducts() {
        System.out.println("\n--- PRODUCT CATALOGUE ---");
        for (Product product : store.products) System.out.println(product);
    }

    public void search(String query) {
        String q = query.toLowerCase();
        boolean found = false;
        for (Product p : store.products) {
            if (p.getName().toLowerCase().contains(q) || p.getCategory().toLowerCase().contains(q)) {
                System.out.println(p);
                found = true;
            }
        }
        if (!found) System.out.println("No matching products found.");
    }

    public void addStock(int productId, int quantity) {
        Product p = store.findProduct(productId);
        if (p == null) { System.out.println("Product not found."); return; }
        try {
            p.addStock(quantity);
            System.out.println("Stock updated. New stock: " + p.getStock());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
