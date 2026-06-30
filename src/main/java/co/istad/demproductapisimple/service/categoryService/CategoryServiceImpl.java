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
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        categoryEntity.setIsDeleted(true);
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

        if(request.isDelete()) existingCategory.setIsDeleted(true);
        else existingCategory.setIsDeleted(false);
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
    public Page<CategoryResponse> searchCategory(String keyword,@PageableDefault(page = 0 ,size = 50) Pageable pageable) {
        var categories = categoryRepository.findByNameContainingIgnoreCase(keyword, pageable);
        return categories.map(categoryMapper::toResponse);
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CategoryResponse> findCategoryDynamically(Integer userId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        Root<Category> category = cq.from(Category.class);
        cq.select(category);
        List<Predicate> predicates = new ArrayList<>();
        if (userId != null) {
            // ប្រសិនបើមានបញ្ចូល department -> WHERE employee.department = :department
            predicates.add(cb.equal(category.get("userId"), userId));
        }
        if (!predicates.isEmpty()) {
            cq.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        TypedQuery<Category> query = entityManager.createQuery(cq);
        return categoryMapper.toResponse(query.getResultList());
    }
}
