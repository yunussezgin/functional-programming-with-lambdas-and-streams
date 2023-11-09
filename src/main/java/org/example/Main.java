package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //Adding Products
        List<Product> productsList = new ArrayList<Product>();
        productsList.add(new Product(1, "HP Laptop", 25000f, List.of()));
        productsList.add(new Product(2, "Dell Laptop", 30000f, List.of()));
        productsList.add(new Product(3, "Lenevo Laptop", 28000f, List.of()));
        productsList.add(new Product(4, "Sony Laptop", 28000f, List.of("X")));
        productsList.add(new Product(5, "Apple Laptop", 90000f, List.of("S", "M", "L")));

        System.out.println("Example 1:");
        List<Float> productPriceList = productsList.stream()
                .filter(p -> p.price > 25000f) // filtering data
                .map(p -> p.price)   // fetching price
                .collect(Collectors.toList());  // collecting as list
        System.out.println(productPriceList);

        System.out.println("Example 2:");
        productsList.stream()
                .filter(p -> p.price > 25000f)
                .limit(1)
                .forEach(System.out::println);

        // More precise code
        System.out.println("Example 3:");
        float total = productsList.stream()
                .map(product -> product.price)
                .reduce(0.0f, Float::sum);  // accumulating price, by referring method of Float class
        System.out.println("Reduce: " + total);

        // This is more compact approach for filtering data
        System.out.println("Example 4:");
        Float price1 = productsList.stream()
                .map(product -> product.price)
                .reduce(0.0f, (sum, price) -> sum + price);   // accumulating price
        System.out.println("Sum1: " + price1);

        // Using Collectors's method to sum the prices.
        double price2 = productsList.stream()
                .collect(Collectors.summingDouble(product -> product.price));
        System.out.println("Sum2: " + price2);

        System.out.println("Example 5:");
        // max() method to get max Product price
        Product maxProduct = productsList.stream()
                .max((product1, product2) -> product1.price > product2.price ? 1 : -1)
                .get();
        System.out.println(maxProduct.price);
        // min() method to get min Product price
        Product minProduct = productsList.stream()
                .min((product1, product2) -> product1.price > product2.price ? 1 : -1)
                .get();
        System.out.println(minProduct.price);

        System.out.println("Example 6:");
        long count = productsList.stream()
                .filter(p -> p.price > 25000f)
                .count();
        System.out.println(count);

        System.out.println("Example 7:");
        Set<Float> productPriceSet = productsList.stream()
                .filter(product -> product.price > 25000f)
                .map(product -> product.price)
                .collect(Collectors.toSet());
        productPriceSet.forEach(System.out::println);

        System.out.println("Example 8:");
        Map<Integer, String> productPriceMap = productsList.stream()
                .collect(Collectors.toMap(p -> p.id, p -> p.name));
        System.out.println(productPriceMap);

        System.out.println("Example 9:");
        List<Float> filteredProductPrice =
                productsList.stream()
                        .filter(p -> p.price > 28000f)
                        .map(Product::getPrice)
                        .collect(Collectors.toList());
        System.out.println(filteredProductPrice);

        System.out.println("Example 10:");
        List<String> sizes = productsList.stream().filter(product -> product.price > 25000f)
                .map(p -> p.getSizes())
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println(sizes);

    }
}