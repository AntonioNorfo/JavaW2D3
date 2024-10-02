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
                .filter(product -> "Boys".equals(product.getCategory()))  // Filtra i prodotti della categoria "Boys"
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


    }
}