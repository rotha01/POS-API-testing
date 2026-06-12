package co.istad.demproductapisimple.dto.productDto;

import co.istad.demproductapisimple.dto.categoryDto.CategoryResponse;
import co.istad.demproductapisimple.entity.Category;

public record ProductResponse(
        int id,
        String name,
        String description,
        Float price,

        Boolean isDeleted,
        CategoryResponse category
) {

}
