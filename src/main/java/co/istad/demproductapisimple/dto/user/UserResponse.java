package co.istad.demproductapisimple.dto.user;

import lombok.Builder;

@Builder
public record UserResponse(
        Long id ,
        String email,
        String profileUrl,
        String bio
) {
}
