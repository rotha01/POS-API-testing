package co.istad.demproductapisimple.dto.categoryDto;

public record CategoryResponse(
        int id,
        String name,
        String description,
        Boolean isActive
) {
}
