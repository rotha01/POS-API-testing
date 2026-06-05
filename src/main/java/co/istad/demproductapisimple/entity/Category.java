package co.istad.demproductapisimple.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {
    private Integer id;
    private String name;
    private String description;
    private Boolean isActive;
    private Integer userId;
}
