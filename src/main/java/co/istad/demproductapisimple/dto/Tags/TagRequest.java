package co.istad.demproductapisimple.dto.Tags;

import lombok.Builder;

@Builder
public record TagRequest(
        String name
) {

}