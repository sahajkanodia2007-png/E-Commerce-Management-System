import java.math.BigDecimal;

public class SalesReport {
    private final DataStore store;

    public SalesReport(DataStore store) { this.store = store; }

    public void printReport() {
        BigDecimal revenue = BigDecimal.ZERO;
        int completedOrActiveOrders = 0;
        for (Order order : store.orders) {
            if (order.getStatus() != Order.Status.CANCELLED) {
                revenue = revenue.add(order.getTotal());
                completedOrActiveOrders++;
            }
        }
        System.out.println("\n--- SALES REPORT ---");
        System.out.println("Customers registered: " + store.customers.size());
        System.out.println("Orders (excluding cancelled): " + completedOrActiveOrders);
        System.out.println("Recorded order value: ₹" + revenue);
        System.out.println("\nLow-stock products (5 or fewer):");
        boolean any = false;
        for (Product p : store.products) {
            if (p.getStock() <= 5) { System.out.println(p); any = true; }
        }
        if (!any) System.out.println("None");
    }
}
