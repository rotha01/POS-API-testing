package co.istad.demproductapisimple.dto.categoryDto;

import co.istad.demproductapisimple.entity.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CategoryRequest(
        @NotBlank(message = "name is required")
        @Size(min = 1, max = 100)
        String name,
        @NotBlank(message = "description is required")
        @Size(min = 1, max = 200)
        String description,
        @NotNull(message = "active is required")
        Boolean isDelete

) {
}
