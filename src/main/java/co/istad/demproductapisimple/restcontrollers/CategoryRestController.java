package co.istad.demproductapisimple.restcontrollers;

import co.istad.demproductapisimple.dto.categoryDto.CategoryRequest;
import co.istad.demproductapisimple.dto.categoryDto.CategoryResponse;
import co.istad.demproductapisimple.dto.categoryDto.UpdateCategoryRequest;
import co.istad.demproductapisimple.entity.Category;
import co.istad.demproductapisimple.service.categoryService.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryRestController {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> getCategories(){
        return categoryService.findAllCategory();
    }

    @PostMapping
    public CategoryResponse addCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        return categoryService.addCategory(categoryRequest);
    }

    @PatchMapping("/{id}")
    public CategoryResponse updateCategory(@PathVariable Integer id,@RequestBody UpdateCategoryRequest categoryRequest){
        return categoryService.updateCategory(id,categoryRequest);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteCategory(@PathVariable Integer id){
        return categoryService.deleteCategory(id);
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategory(@PathVariable Integer id){
        return categoryService.findCategoryById(id);
    }


}
