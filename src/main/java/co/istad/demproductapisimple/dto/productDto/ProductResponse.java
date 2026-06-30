package co.istad.demproductapisimple.dto.productDto;

import co.istad.demproductapisimple.dto.categoryDto.CategoryResponse;
import co.istad.demproductapisimple.entity.Category;

import java.util.Set;

public record ProductResponse(
        int id,
        String name,
        String description,
        Float price,

        Boolean isDeleted,
        CategoryResponse category,
        Set<String> tags
) {

}
