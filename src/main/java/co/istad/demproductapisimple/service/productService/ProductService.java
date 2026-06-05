package co.istad.demproductapisimple.service.productService;

import co.istad.demproductapisimple.dto.productDto.ProductRequest;
import co.istad.demproductapisimple.dto.productDto.ProductResponse;
import co.istad.demproductapisimple.dto.productDto.UpdateProductRequest;

import java.util.List;
//losing coupling design
public interface ProductService {
    ProductResponse addProduct(ProductRequest product);
    List<ProductResponse> findAllProducts();
    ProductResponse updateProduct(Integer id, UpdateProductRequest productRequest);
    boolean deleteProduct(Integer id);
    ProductResponse findProductById(Integer productId);
}
