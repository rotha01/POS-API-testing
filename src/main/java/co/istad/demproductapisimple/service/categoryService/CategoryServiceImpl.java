package co.istad.demproductapisimple.service.categoryService;

import co.istad.demproductapisimple.dto.categoryDto.CategoryRequest;
import co.istad.demproductapisimple.dto.categoryDto.CategoryResponse;
import co.istad.demproductapisimple.dto.categoryDto.UpdateCategoryRequest;
import co.istad.demproductapisimple.entity.Category;
import co.istad.demproductapisimple.repository.CategoryRepository;
import co.istad.demproductapisimple.repository.CategoryRepositoryOld;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
//    private final CategoryRepositoryOld categoryRepositoryOld;
//    private Integer id = 1004;
    private final CategoryRepository categoryRepository;

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
        return mapToCategoryResponse(categoryRepository.save(categoryEntity));
    }

    @Override
    public List<CategoryResponse> findAllCategory() {
        return categoryRepository.findAll().stream()
                .map(this::mapToCategoryResponse)
                .toList();
    }

    @Override
    public CategoryResponse updateCategory(Integer id, UpdateCategoryRequest request) {
        var existingCategory = categoryRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Category with id " + id + " not found!")
        );

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
        categoryRepository.save(existingCategory);

        return mapToCategoryResponse(existingCategory);
    }

    @Override
    public boolean deleteCategory(Integer id) {
        var existingCategory = categoryRepository.findById(id);
        if(existingCategory.isPresent()){
            categoryRepository.delete(existingCategory.get());
            return true;
        }
        return false;

    }

    @Override
    public CategoryResponse findCategoryById(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Category with id " + id + " not found!")
        );
        return mapToCategoryResponse(category);
    }
}
