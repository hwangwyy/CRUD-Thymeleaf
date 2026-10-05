package vn.iostar.model;

import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor
public class Product {
    private Long id;
    private String name;
    private double price;
    private boolean inStock;
}
