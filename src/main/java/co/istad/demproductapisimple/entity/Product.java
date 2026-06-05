package co.istad.demproductapisimple.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    private int id;
    private String name;
    private String description;
    private float price;
    private int userId; //User who create product
}
