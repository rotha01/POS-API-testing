package co.istad.demproductapisimple.service.categoryService;

import co.istad.demproductapisimple.dto.categoryDto.CategoryRequest;
import co.istad.demproductapisimple.dto.categoryDto.CategoryResponse;
import co.istad.demproductapisimple.dto.categoryDto.UpdateCategoryRequest;
import co.istad.demproductapisimple.dto.productDto.ProductRequest;
import co.istad.demproductapisimple.dto.productDto.ProductResponse;
import co.istad.demproductapisimple.dto.productDto.UpdateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    CategoryResponse addCategory(CategoryRequest category);
    List<CategoryResponse> findAllCategory();
    CategoryResponse updateCategory(Integer id, UpdateCategoryRequest productRequest);
    void deleteCategory(Integer id);
    CategoryResponse softDeleteCategory(Integer id, UpdateCategoryRequest request);
    CategoryResponse findCategoryById(Integer categoryId);
    List<CategoryResponse> findCategoryByName(String categoryName);
    Page<CategoryResponse> searchCategory(String keyword, Pageable pageable);
    List<CategoryResponse> findCategoryDynamically(Integer userId);


}
