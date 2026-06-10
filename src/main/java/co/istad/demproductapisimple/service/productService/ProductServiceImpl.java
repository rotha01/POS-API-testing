package co.istad.demproductapisimple.service.productService;

import co.istad.demproductapisimple.dto.productDto.ProductRequest;
import co.istad.demproductapisimple.dto.productDto.ProductResponse;
import co.istad.demproductapisimple.dto.productDto.UpdateProductRequest;
import co.istad.demproductapisimple.entity.Product;
import co.istad.demproductapisimple.repository.ProductRepository;
import co.istad.demproductapisimple.repository.ProductRepositoryOld;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

//    private final ProductRestController productRestController;
//    private final ProductRepositoryOld productRepositoryOld;
//    private int nextId = 1005;

    private final ProductRepository productRepository;

    //    public ProductServiceImpl(ProductRestController productRestController) {
//        this.productRestController = productRestController;
//    }
    //mapper
    //mapToResponse -> convert Entity to response
    private ProductResponse mapToProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    };

    private Product mapToProduct(ProductRequest product) {
        Product products = new Product();
        products.setName(product.name());
        products.setDescription(product.description());
        products.setPrice(product.price());

        return products;

    }



    @Override
    public ProductResponse addProduct(ProductRequest request) {
        var product = mapToProduct(request);
        //set static userID
        product.setUserId(1);
        return mapToProductResponse(productRepository.save(product));
    }

    @Override
    public List<ProductResponse> findAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToProductResponse)
                .toList();
    }

    @Override
    public ProductResponse updateProduct(Integer id, UpdateProductRequest request) {
        //Find existing product
        var existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException
                ("Product with id " + id + " not found!"));


        if(request.price()!=null) {
            existingProduct.setPrice(request.price());
        }
        if(request.name() != null) {
            existingProduct.setName(request.name());
        }
        if(request.description() != null) {
            existingProduct.setDescription(request.description());
        }


        //update product
        productRepository.save(existingProduct);
        return mapToProductResponse(existingProduct);
    }

    @Override
    public boolean deleteProduct(Integer id) {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;

    }
    @Override
    public ProductResponse findProductById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Product with id " + id + " not found!"));
        return mapToProductResponse(product);
    }
}
