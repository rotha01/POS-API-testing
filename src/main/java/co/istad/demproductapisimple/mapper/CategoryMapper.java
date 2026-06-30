package co.istad.demproductapisimple.mapper;

//import ch.qos.logback.core.model.ComponentModel;
import co.istad.demproductapisimple.dto.categoryDto.CategoryRequest;
import co.istad.demproductapisimple.dto.categoryDto.CategoryResponse;
import co.istad.demproductapisimple.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toResponse(Category category);
    Category toEntity(CategoryRequest request);

    List<CategoryResponse> toResponse(List<Category> categories);

}
