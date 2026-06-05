package co.istad.demproductapisimple.dto.productDto;

public record ProductResponse(
        int id,
        String name,
        String description,
        Float price
) {

}
