package co.istad.demproductapisimple.dto.categoryDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CategoryRequest(
        @NotBlank(message = "name is required")
        String name,
        @NotBlank(message = "description is required")
        String description,
        @NotNull(message = "active is required")
        Boolean isActive
) {
}
