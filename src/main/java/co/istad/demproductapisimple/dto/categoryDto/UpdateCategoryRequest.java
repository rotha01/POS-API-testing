package co.istad.demproductapisimple.dto.categoryDto;

public record UpdateCategoryRequest(
        String name,
        String description,
        Boolean isActive
) {
}
