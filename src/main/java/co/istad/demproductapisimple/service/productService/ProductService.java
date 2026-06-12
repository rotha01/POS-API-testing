package co.istad.demproductapisimple.service.productService;

import co.istad.demproductapisimple.dto.productDto.ProductRequest;
import co.istad.demproductapisimple.dto.productDto.ProductResponse;
import co.istad.demproductapisimple.dto.productDto.UpdateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
//losing coupling design
public interface ProductService {
    ProductResponse addProduct(ProductRequest product);
    List<ProductResponse> findAllProducts();
    Page<ProductResponse> findAllProducts(Pageable pageable);
    ProductResponse updateProduct(Integer id, UpdateProductRequest productRequest);
    boolean deleteProduct(Integer id);
    ProductResponse softDeleteProduct(Integer id, UpdateProductRequest request);
    ProductResponse findProductById(Integer productId);
    Page<ProductResponse> searchProducts(String keyword, Pageable pageable);
}
