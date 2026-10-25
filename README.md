# E-Commerce Management System (Java)

A console-based E-Commerce Management System built in Java using Object-Oriented Programming (OOP) principles. The project adapts the structure of a loan-management application to the workflow of a small online store.

## Project Overview

The application demonstrates the main steps in a basic online shopping workflow. Customers can browse and search a product catalogue, add products to a cart, and place orders. The program validates requested quantities against available stock and updates inventory when an order is placed. An administrator can manage products and stock, while a sales report provides a simple view of order activity.

This is an educational command-line project. It is intended to demonstrate Java classes, object relationships, collections, validation, and service-layer logic rather than operate as a production online store.

## Features

- **Product Catalogue:** View available products and their details.
- **Product Search:** Find products through the console menu.
- **Customer Registration:** Create customer records for shopping sessions.
- **Shopping Cart:** Add products, manage quantities, view items, and calculate the cart total.
- **Quantity Validation:** Check requested quantities against available stock.
- **Checkout and Orders:** Convert a cart into an order and retain order details during the current run.
- **Inventory Management:** Reduce stock when an order is successfully placed.
- **Order History and Status:** View recorded orders and their status.
- **Admin Product Management:** Add or manage products and update stock through the admin menu.
- **Sales Report:** Generate a basic summary from the orders recorded in the current session.
- **In-Memory Storage:** Store demo data in Java collections; data resets when the program exits.

## OOP Concepts Used

- **Abstraction:** `Person` is an abstract base class for shared person-related structure.
- **Inheritance:** `Customer` and `Admin` extend `Person`.
- **Encapsulation:** Classes keep related data and behavior together, using methods to access or modify object state.
- **Composition / Object Relationships:** A cart contains cart items, and orders contain order items.
- **Separation of Responsibilities:** Product operations, order operations, storage, and sales reporting are organized into separate classes.

## Shopping Workflow

1. Start the application from the console.
2. Browse or search the product catalogue.
3. Register or select a customer as prompted by the application.
4. Add products and quantities to the shopping cart.
5. Review the cart and its calculated total.
6. Proceed to checkout to create an order.
7. The application validates stock and updates inventory for a successful order.
8. View order history or the sales report from the relevant menu options.

Exact menu prompts and available actions are defined in `Main.java`.

## Order and Inventory Handling

The cart represents items a customer intends to purchase. Each cart item associates a product with a quantity. During checkout, the order service coordinates order creation and inventory changes. Orders store order-item details so that the purchase can be reviewed after checkout within the current application session.

The current implementation is a learning demo. It does not provide database transactions, concurrent stock locking, refunds, shipping integrations, or real payment processing.

## Data Storage

The project uses in-memory Java collections managed through `DataStore.java`.

- Data is available while the program is running.
- Products, customers, and orders are not saved to a persistent database or file.
- Restarting the application resets session data to the program's initial state.

A future version could use MySQL or PostgreSQL for persistent storage and add repository/DAO classes to separate database access from business logic.

## Project Structure

```text
E-Commerce-Management-System/
├── Main.java           # Console entry point and menu workflow
├── Person.java         # Abstract base class for people
├── Customer.java       # Customer model
├── Admin.java          # Administrator model
├── Product.java        # Product details and stock-related behavior
├── Cart.java           # Shopping cart operations
├── CartItem.java       # Product and quantity in a cart
├── Order.java          # Order details and status
├── OrderItem.java      # Product, quantity, and price details for an order
├── DataStore.java      # In-memory collections used by the application
├── ProductService.java # Product catalogue and inventory operations
├── OrderService.java   # Checkout and order-related operations
├── SalesReport.java    # Basic sales reporting
└── README.md           # Project documentation
```

## Requirements

- Java Development Kit (JDK) 17 or later
- Terminal / Command Prompt, or an IDE such as IntelliJ IDEA or VS Code with Java support

Check your Java installation:

```bash
java -version
javac -version
```

## How to Run

1. Download or clone this repository.
2. Open a terminal in the directory containing the `.java` files.
3. Compile the source files:

   ```bash
   javac *.java
   ```

4. Run the application:

   ```bash
   java Main
   ```

Alternatively, open the folder in IntelliJ IDEA and run `Main.java` using JDK 17 or later.

## Demo Admin Login

- Admin name: `Store Admin`
- Demo password: `admin123`

This credential is hard-coded for demonstration only. It is not secure authentication and should not be reused in a real application.

## Testing Checklist

Use the console menus to check the main workflows:

- [ ] View the product catalogue.
- [ ] Search for a product.
- [ ] Register a customer.
- [ ] Add a valid product quantity to the cart.
- [ ] Try requesting more units than are in stock and confirm that validation works.
- [ ] Review the cart total.
- [ ] Complete checkout and confirm an order is created.
- [ ] Confirm that stock is updated after a successful order.
- [ ] View order history and order status.
- [ ] Use the admin menu to manage products or stock.
- [ ] Generate the sales report.

These are suggested manual checks; they are not a claim that an automated test suite is included.

## Current Limitations

- Console-based interface; no web storefront or mobile application.
- In-memory storage; records do not persist after exit.
- Demo admin credentials; no production-grade authentication or authorization.
- No real payment gateway, tax calculation, shipping, returns, or refund workflow.
- Sales reporting is basic and limited to data available in the current run.
- Not designed for concurrent users or production deployment.

## Future Enhancements

1. **Database Integration:** Persist products, customers, and orders using MySQL or PostgreSQL.
2. **Secure Authentication:** Add password hashing and role-based access for customers and administrators.
3. **Web Interface:** Build a responsive storefront using HTML, CSS, and JavaScript, connected to a Java backend such as Spring Boot.
4. **REST API:** Expose product, cart, and order functionality through documented endpoints.
5. **Payment Integration:** Integrate a supported payment provider in a test environment before any live use.
6. **Product Images and Categories:** Add image URLs, category filters, sorting, and product details pages.
7. **Order Fulfilment:** Add shipping details, delivery tracking, cancellations, and returns.
8. **Automated Tests:** Add unit tests for cart totals, stock validation, checkout, and sales calculations.
9. **Deployment:** Package and deploy the web version with environment-based configuration and secure secrets management.

## Author

Created as a Java OOP learning project.
