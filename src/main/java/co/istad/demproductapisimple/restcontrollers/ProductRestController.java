package co.istad.demproductapisimple.restcontrollers;

import co.istad.demproductapisimple.dto.productDto.ProductRequest;
import co.istad.demproductapisimple.dto.productDto.ProductResponse;
import co.istad.demproductapisimple.dto.productDto.UpdateProductRequest;
import co.istad.demproductapisimple.service.productService.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductRestController {
    private final ProductService productService;
    //method handler
//    @GetMapping
//    public List<ProductResponse> getProducts() {
//      return productService.findAllProducts();
//    }
    @PostMapping
    public ProductResponse createNewProduct(@Valid @RequestBody ProductRequest productRequest) {
        return productService.addProduct(productRequest);
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Integer id) {
        return productService.findProductById(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteProductById(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }

    @PatchMapping("/{id}")
    public ProductResponse updateProductById(@PathVariable Integer id,
                                             @RequestBody UpdateProductRequest request) {
        return productService.updateProduct(id,request);
    }
    @GetMapping
    public Page<ProductResponse> findAllProducts(Pageable pageable) {
        return productService.findAllProducts(pageable);
    }

    @PutMapping("/{id}")
    public ProductResponse softDeleteProductById(@PathVariable Integer id,
                                                 @RequestBody UpdateProductRequest request) {
        return productService.softDeleteProduct(id, request);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductResponse>> searchProducts(
            @RequestParam String keyword,
            Pageable pageable
    ) {

        Page<ProductResponse> products =
                productService.searchProducts(keyword, pageable);

        return ResponseEntity.ok(products);
    }


}
