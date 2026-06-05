package co.istad.demproductapisimple.service.categoryService;

import co.istad.demproductapisimple.dto.categoryDto.CategoryRequest;
import co.istad.demproductapisimple.dto.categoryDto.CategoryResponse;
import co.istad.demproductapisimple.dto.categoryDto.UpdateCategoryRequest;
import co.istad.demproductapisimple.entity.Category;
import co.istad.demproductapisimple.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    private Integer id = 1004;

    private CategoryResponse mapToCategoryResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getIsActive()
        );
    }

    private Category mapToCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.name());
        category.setDescription(categoryRequest.description());
        category.setIsActive(categoryRequest.isActive());

        return category;

    }

    @Override
    public CategoryResponse addCategory(CategoryRequest category) {
        var categoryEntity = mapToCategory(category);
        categoryEntity.setIsActive(true);
        categoryEntity.setId(id++);

        return mapToCategoryResponse(categoryEntity);
    }

    @Override
    public List<CategoryResponse> findAllCategory() {
        return categoryRepository.getAllCategories().stream()
                .map(this::mapToCategoryResponse)
                .toList();
    }

    @Override
    public CategoryResponse updateCategory(Integer id, UpdateCategoryRequest request) {
        var existingCategory = categoryRepository.getCategoryById(id);
        if(existingCategory == null){
            System.out.println("Not found with category id " + id);
        }

        if(request.name() != null ){
            existingCategory.setName(request.name());
        }
        if(request.description() != null ){
            existingCategory.setDescription(request.description());
        }
        if(request.isActive() != null){
            existingCategory.setIsActive(request.isActive());
        }
        //update category
        categoryRepository.updateCategoryById(existingCategory);

        return mapToCategoryResponse(existingCategory);
    }

    @Override
    public boolean deleteCategory(Integer id) {
        CategoryResponse categoryResponse = this.findCategoryById(id) ;
        try{
            if(categoryResponse == null){
                System.out.println("Not found with category id " + id);
                return false;
            }else{
                categoryRepository.deleteCategoryById(id);
                System.out.println("Category has been deleted successfully");
                return true;
            }
        }catch(Exception e){
            System.out.println("Category has been deleted successfully");
            return false;
        }

    }

    @Override
    public CategoryResponse findCategoryById(Integer categoryId) {
        Category category = categoryRepository.getCategoryById(categoryId);
        if(category == null){
            System.out.println("Not found with category id " + categoryId);
        }

        return mapToCategoryResponse(category);
    }
}
