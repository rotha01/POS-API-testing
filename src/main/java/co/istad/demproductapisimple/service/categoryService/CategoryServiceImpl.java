package co.istad.demproductapisimple.service.categoryService;

import co.istad.demproductapisimple.advisor.ResourceAlreadyExistException;
import co.istad.demproductapisimple.dto.categoryDto.CategoryRequest;
import co.istad.demproductapisimple.dto.categoryDto.CategoryResponse;
import co.istad.demproductapisimple.dto.categoryDto.UpdateCategoryRequest;
import co.istad.demproductapisimple.dto.productDto.ProductResponse;
import co.istad.demproductapisimple.entity.Category;
import co.istad.demproductapisimple.mapper.CategoryMapper;
import co.istad.demproductapisimple.repository.CategoryRepository;
import co.istad.demproductapisimple.repository.CategoryRepositoryOld;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryMapper categoryMapper;
//    private final CategoryRepositoryOld categoryRepositoryOld;
//    private Integer id = 1004;
    private final CategoryRepository categoryRepository;
//    private CategoryResponse mapToCategoryResponse(Category category) {
//        return new CategoryResponse(
//                category.getId(),
//                category.getName(),
//                category.getDescription(),
//                category.getIsActive()
//        );
//    }

//    private Category mapToCategory(CategoryRequest categoryRequest) {
//        Category category = new Category();
//        category.setName(categoryRequest.name());
//        category.setDescription(categoryRequest.description());
//        category.setIsActive(categoryRequest.isActive());
//
//        return category;
//
//    }

    @Override
    public CategoryResponse addCategory(CategoryRequest category) {
        var categoryEntity = categoryMapper.toEntity(category);
        if(categoryRepository.existsByName(categoryEntity.getName())){
            throw new ResourceAlreadyExistException("Category with name " + categoryEntity.getName() + " already exists");
        }
        categoryEntity.setIsDelete(true);
        return categoryMapper.toResponse(categoryRepository.save(categoryEntity));
    }

    @Override
    public List<CategoryResponse> findAllCategory() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toResponse)
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
//        if(request.isDelete() != null){
//            existingCategory.setIsDelete(request.isDelete());
//        }
        //update category
        categoryRepository.save(existingCategory);

        return categoryMapper.toResponse(existingCategory);
    }

    @Override
    public void deleteCategory(Integer id) {
        var existingCategory = categoryRepository.findById(id);
        if(!categoryRepository.existsById(id)){
            throw new NoSuchElementException("Category with id " + id + " not found!");
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryResponse softDeleteCategory(Integer id, UpdateCategoryRequest request) {
        var existingCategory = categoryRepository.findById(id).orElseThrow(() ->new NoSuchElementException("" +
                "Category with id " + id + " not found!"));

        if(request.isDelete()) existingCategory.setIsDelete(true);
        else existingCategory.setIsDelete(false);
       return categoryMapper.toResponse(existingCategory);
    }

    @Override
    public CategoryResponse findCategoryById(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Category with id " + id + " not found!")
        );
        return categoryMapper.toResponse(category);
    }

    @Override
    public List<CategoryResponse> findCategoryByName(String categoryName) {
        var existingCategory = categoryRepository.findByNameContainingIgnoreCase(categoryName);
        return List.of(existingCategory);
    }

    @Override
    public Page<CategoryResponse> searchCategory(String keyword, Pageable pageable) {
        var categories = categoryRepository.findByNameContainingIgnoreCase(keyword, pageable);
        return categories.map(categoryMapper::toResponse);
    }
}
