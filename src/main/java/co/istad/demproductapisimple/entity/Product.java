package co.istad.demproductapisimple.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "product_tbl")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Float price;
    private Integer userId; //User who create product
    private Boolean isDeleted;

    @ManyToOne(fetch =  FetchType.LAZY )
    @JoinColumn(name = "category_id")
    private Category category;
}
