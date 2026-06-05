package co.istad.demproductapisimple.service.productService;

import co.istad.demproductapisimple.dto.productDto.ProductRequest;
import co.istad.demproductapisimple.dto.productDto.ProductResponse;
import co.istad.demproductapisimple.dto.productDto.UpdateProductRequest;
import co.istad.demproductapisimple.entity.Product;
import co.istad.demproductapisimple.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

//    private final ProductRestController productRestController;
    private final ProductRepository productRepository;
    private int nextId = 1005;

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
        product.setId(nextId++);
        return mapToProductResponse(productRepository.createProduct(product));
    }

    @Override
    public List<ProductResponse> findAllProducts() {
        return productRepository.getAllProduct().stream()
                .map(this::mapToProductResponse)
                .toList();
    }

    @Override
    public ProductResponse updateProduct(Integer id, UpdateProductRequest request) {
        //Find existing product
        var existingProduct = productRepository.findProductById(id);
        if (existingProduct == null) {
            System.out.println("No product with id " + id + " exists");
        }

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
        productRepository.updateProduct(existingProduct);
        return mapToProductResponse(existingProduct);
    }

    @Override
    public boolean deleteProduct(Integer id) {
        ProductResponse productResponse = this.findProductById(id);
        try{
            if(productResponse.id() != id){
                System.out.println("Cannot delete product because id is incorrect");
            }

            else{
                productRepository.deleteProduct(id);
                System.out.println("Product deleted successfully");
                return true;
            }
        }catch(Exception e){
            System.out.println("Cannot delete product because id is incorrect");
        }
        return false;
    }

    @Override
    public ProductResponse findProductById(Integer id) {
        Product product = productRepository.findProductById(id);
        if(product == null) {
            System.out.println("Product not found with id " + id);
            return null;
        }
        return mapToProductResponse(product);
    }
}
