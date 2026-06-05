package co.istad.demproductapisimple.service.categoryService;

import co.istad.demproductapisimple.dto.categoryDto.CategoryRequest;
import co.istad.demproductapisimple.dto.categoryDto.CategoryResponse;
import co.istad.demproductapisimple.dto.categoryDto.UpdateCategoryRequest;
import co.istad.demproductapisimple.dto.productDto.ProductRequest;
import co.istad.demproductapisimple.dto.productDto.ProductResponse;
import co.istad.demproductapisimple.dto.productDto.UpdateProductRequest;

import java.util.List;

public interface CategoryService {
    CategoryResponse addCategory(CategoryRequest category);
    List<CategoryResponse> findAllCategory();
    CategoryResponse updateCategory(Integer id, UpdateCategoryRequest productRequest);
    boolean deleteCategory(Integer id);
    CategoryResponse findCategoryById(Integer categoryId);
}
