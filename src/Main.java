import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Product> products = new ArrayList<>();

        products.add(new Product(1L, "Hulk", "Books", 5.0));
        products.add(new Product(2L, "Il signore degli anelli", "Books", 34.0));
        products.add(new Product(3L, "Spiderman", "Baby", 20.0));
        products.add(new Product(4L, "DragonBall", "Baby", 1501.0));
        products.add(new Product(5L, "Avengers", "Books", 180.0));
        products.add(new Product(6L, "Il gladiatore", "Boys", 3620.0));
        products.add(new Product(7L, "Noah", "Books", 510.0));
        products.add(new Product(8L, "Io sono leggenda", "Boys", 590.0));
        products.add(new Product(9L, "Il pianeta delle scimmie", "Baby", 12900.0));
        products.add(new Product(10L, "DeadPoll vs Wolverine", "Boys", 1450.0));
        products.add(new Product(11L, "X-Man", "Boys", 2180.0));
        products.add(new Product(12L, "I Puffi", "Baby", 3320.0));

        List<Product> filteredProductsBooks = products.stream() // interfaccia generica "List" per la costruzione dell array , usiamo stream per potere usare .filter
                .filter(product -> "Books".equals(product.getCategory())) // e' come se usassimo un arrow function di js chiamata  " lambda expression "
                .filter(product -> product.getPrice() > 100) // verra visualizzato solo il prodotto con prezzo maggiore di 100
                .collect(Collectors.toList()); // utilizzando il metodo collet  .toList() trasformiamo il risultato in una lista

        System.out.println();
        System.out.println("Prodotti nella categoria Books:");
        System.out.println();

        filteredProductsBooks.forEach(product -> System.out.println(product));

        List<Product> filteredProductsBaby = products.stream() // interfaccia generica "List" per la costruzione dell array , usiamo stream per potere usare .filter
                .filter(product -> "Baby".equals(product.getCategory())) // e' come se usassimo un arrow function di js chiamata  " lambda expression "
                .collect(Collectors.toList());

        System.out.println();
        System.out.println("Prodotti nella categoria Baby:");
        System.out.println();
        filteredProductsBaby.forEach(product -> System.out.println(product));

        List<Product> filteredProductsBoys = products.stream()
                .filter(product -> "Boys".equals(product.getCategory()))
                .map(product -> {
                    // Applichiamo lo sconto del 10% sul prezzo del prodotto
                    product.setPrice(product.getPrice() * 0.9);
                    return product;
                })
                .collect(Collectors.toList());

        System.out.println();
        System.out.println("Prodotti nella categoria Boys con sconto 10% del prezzo originale:");
        System.out.println();
        filteredProductsBoys.forEach(product -> System.out.println(product));

        List<Order> orders = List.of(
                new Order(1L, "Pending", LocalDate.of(2024, 1, 3), List.of(products.get(0), products.get(1)), new Customer(1L, "Frodo", 2)),
                new Order(2L, "Delivered", LocalDate.of(2021, 5, 7), List.of(products.get(2), products.get(3)), new Customer(2L, "Gandalf", 2)),
                new Order(3L, "Pending", LocalDate.of(2023, 8, 5), List.of(products.get(4), products.get(5)), new Customer(3L, "Aragon", 3)),
                new Order(4L, "Delivered", LocalDate.of(2021, 2, 3), List.of(products.get(6), products.get(7)), new Customer(4L, "Legolas", 1)),
                new Order(5L, "Pending", LocalDate.of(2021, 2, 3), List.of(products.get(8), products.get(9)), new Customer(5L, "Ghimili", 2)),
                new Order(6L, "Delivered", LocalDate.of(2021, 2, 3), List.of(products.get(10), products.get(11)), new Customer(6L, "Set", 3))
        );

        LocalDate startDate = LocalDate.of(2021, 2, 1);
        LocalDate endDate = LocalDate.of(2021, 4, 1);

        List<Product> productsOrderedByTier2Customers = orders.stream()
                .filter(order -> order.getOrderDate() != null)
                .filter(order -> !order.getOrderDate().isBefore(startDate) && !order.getOrderDate().isAfter(endDate))
                .filter(order -> order.getCustomer().getTier() == 2)
                .flatMap(order -> order.getProducts().stream())
                .collect(Collectors.toList());


        System.out.println();
        System.out.println("Prodotti ordinati da clienti di tier 2 tra Febbraio e Aprile 2021:");
        System.out.println();
        productsOrderedByTier2Customers.forEach(Product -> System.out.println(Product));


    }
}