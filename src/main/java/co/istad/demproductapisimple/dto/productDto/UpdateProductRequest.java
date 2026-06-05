package co.istad.demproductapisimple.dto.productDto;

public record UpdateProductRequest (
        String name,
        String description,
        Float price
){
}
