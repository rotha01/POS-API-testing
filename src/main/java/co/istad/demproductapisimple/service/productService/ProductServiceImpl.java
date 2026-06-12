package co.istad.demproductapisimple.service.productService;

import co.istad.demproductapisimple.advisor.ResourceAlreadyExistException;
import co.istad.demproductapisimple.dto.productDto.ProductRequest;
import co.istad.demproductapisimple.dto.productDto.ProductResponse;
import co.istad.demproductapisimple.dto.productDto.UpdateProductRequest;
import co.istad.demproductapisimple.entity.Category;
import co.istad.demproductapisimple.entity.Product;
import co.istad.demproductapisimple.mapper.ProductMapper;
import co.istad.demproductapisimple.repository.CategoryRepository;
import co.istad.demproductapisimple.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    //    public ProductServiceImpl(ProductRestController productRestController) {
//        this.productRestController = productRestController;
//    }
    //mapper
    //mapToResponse -> convert Entity to response
//    private ProductResponse mapToProductResponse(Product product) {
//        return new ProductResponse(
//                product.getId(),
//                product.getName(),
//                product.getDescription(),
//                product.getPrice()
//        );
//    };

//    private Product mapToProduct(ProductRequest product) {
//        Product products = new Product();
//        products.setName(product.name());
//        products.setDescription(product.description());
//        products.setPrice(product.price());
//
//        return products;
//
//    }



    @Override
    public ProductResponse addProduct(ProductRequest request) {
        var product = productMapper.toProduct(request);



        if(request.categoryId() != null){
            Category category = categoryRepository
                    .findById(request.categoryId())
                    .orElseThrow(() -> new NoSuchElementException("Category not found"));
            product.setCategory(category);
        }

        if(productRepository.existsByName(product.getName())){
            throw new ResourceAlreadyExistException("Product with name " + product.getName() + " already exists");
        }
        //set static userID
        product.setUserId(1);
        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Override
    public List<ProductResponse> findAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductResponse)
                .toList();
    }

    @Override
    public Page<ProductResponse> findAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::toProductResponse);
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
        if(request.categoryId() != null) {
            Category category = categoryRepository
                    .findById(request.categoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existingProduct.setCategory(category);
        }

        //update product
        productRepository.save(existingProduct);
        return productMapper.toProductResponse(existingProduct);
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
    public ProductResponse softDeleteProduct(Integer id, UpdateProductRequest request) {
        var existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException
                        ("Product with id " + id + " not found!"));

        if(!request.isDeleted()){
            existingProduct.setIsDeleted(false);
        }else{
            existingProduct.setIsDeleted(true);
        }
        productRepository.save(existingProduct);
        return productMapper.toProductResponse(existingProduct);
    }


    @Override
    public ProductResponse findProductById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Product with id " + id + " not found!"));
        return productMapper.toProductResponse(product);
    }

    @Override
    public Page<ProductResponse> searchProducts(String keyword, Pageable pageable) {
        var products =
                productRepository.findByNameContainingIgnoreCase(
                        keyword,
                        pageable
                );
        return products.map(productMapper::toProductResponse);
    }
}
