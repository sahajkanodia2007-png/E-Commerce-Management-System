import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final DataStore store = new DataStore();
    private static final ProductService productService = new ProductService(store);
    private static final OrderService orderService = new OrderService(store);
    private static final SalesReport salesReport = new SalesReport(store);
    private static int nextCustomerId = 1;

    public static void main(String[] args) {
        System.out.println("==================================");
        System.out.println("   E-COMMERCE MANAGEMENT SYSTEM");
        System.out.println("==================================");
        while (true) {
            System.out.println("\n1. Customer");
            System.out.println("2. Admin");
            System.out.println("0. Exit");
            int choice = readInt("Choose: ");
            switch (choice) {
                case 1 -> customerMenu();
                case 2 -> adminLogin();
                case 0 -> { System.out.println("Goodbye!"); return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void customerMenu() {
        System.out.print("Your name: ");
        String name = sc.nextLine().trim();
        System.out.print("Your email: ");
        String email = sc.nextLine().trim();
        if (name.isEmpty() || email.isEmpty()) {
            System.out.println("Name and email are required.");
            return;
        }
        Customer customer = new Customer(nextCustomerId++, name, email);
        store.customers.add(customer);
        Cart cart = new Cart();

        while (true) {
            System.out.println("\n--- CUSTOMER MENU (" + customer.getName() + ") ---");
            System.out.println("1. View products");
            System.out.println("2. Search products");
            System.out.println("3. Add product to cart");
            System.out.println("4. View cart");
            System.out.println("5. Remove product from cart");
            System.out.println("6. Checkout (demo only; no real payment)");
            System.out.println("7. My orders");
            System.out.println("0. Back");
            int choice = readInt("Choose: ");
            try {
                switch (choice) {
                    case 1 -> productService.listProducts();
                    case 2 -> {
                        System.out.print("Search name/category: ");
                        productService.search(sc.nextLine().trim());
                    }
                    case 3 -> {
                        productService.listProducts();
                        int id = readInt("Product ID: ");
                        int qty = readInt("Quantity: ");
                        Product p = store.findProduct(id);
                        if (p == null) System.out.println("Product not found.");
                        else { cart.add(p, qty); System.out.println("Added to cart."); }
                    }
                    case 4 -> showCart(cart);
                    case 5 -> {
                        int id = readInt("Product ID to remove: ");
                        cart.remove(id);
                        System.out.println("If it was in the cart, it has been removed.");
                    }
                    case 6 -> {
                        Order order = orderService.checkout(customer, cart);
                        System.out.println("Order placed successfully: " + order);
                        System.out.println("Demo checkout only — no payment was collected.");
                    }
                    case 7 -> {
                        if (customer.getOrders().isEmpty()) System.out.println("No orders yet.");
                        else for (Order order : customer.getOrders()) {
                            System.out.println(order);
                            for (OrderItem item : order.getItems()) System.out.println("  " + item);
                        }
                    }
                    case 0 -> { return; }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Could not complete action: " + e.getMessage());
            }
        }
    }

    private static void showCart(Cart cart) {
        System.out.println("\n--- YOUR CART ---");
        if (cart.isEmpty()) { System.out.println("Cart is empty."); return; }
        for (CartItem item : cart.getItems()) {
            System.out.println(item.getProduct().getName() + " x" + item.getQuantity()
                    + " = ₹" + item.getSubtotal());
        }
        System.out.println("TOTAL: ₹" + cart.getTotal());
    }

    private static void adminLogin() {
        System.out.print("Admin password: ");
        String password = sc.nextLine();
        if (!password.equals("admin123")) {
            System.out.println("Incorrect password.");
            return;
        }
        while (true) {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. View products");
            System.out.println("2. Add product");
            System.out.println("3. Restock product");
            System.out.println("4. View all orders");
            System.out.println("5. Sales report");
            System.out.println("0. Back");
            int choice = readInt("Choose: ");
            switch (choice) {
                case 1 -> productService.listProducts();
                case 2 -> addProduct();
                case 3 -> {
                    productService.listProducts();
                    productService.addStock(readInt("Product ID: "), readInt("Quantity to add: "));
                }
                case 4 -> orderService.listAllOrders();
                case 5 -> salesReport.printReport();
                case 0 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void addProduct() {
        System.out.print("Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Category: ");
        String category = sc.nextLine().trim();
        BigDecimal price = readMoney("Price (₹): ");
        int stock = readInt("Initial stock: ");
        if (name.isEmpty() || category.isEmpty() || price.signum() < 0 || stock < 0) {
            System.out.println("Invalid product details.");
            return;
        }
        try {
            productService.addProduct(name, category, price, stock);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = sc.nextLine().trim();
            try { return Integer.parseInt(value); }
            catch (NumberFormatException e) { System.out.println("Enter a valid whole number."); }
        }
    }

    private static BigDecimal readMoney(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                BigDecimal value = new BigDecimal(sc.nextLine().trim());
                if (value.signum() < 0) throw new NumberFormatException();
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid non-negative amount.");
            }
        }
    }
}
