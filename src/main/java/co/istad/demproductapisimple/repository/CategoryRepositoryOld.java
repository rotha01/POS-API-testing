package co.istad.demproductapisimple.repository;

import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepositoryOld {
//    private final List<Category> categories  = new ArrayList<>(){{
//        add(new Category(1001,"Drink","For drinking",true,1));
//        add(new Category(1002,"Food","For eating",true,2));
//        add(new Category(1003,"Electronic","For electricity",true,3));
//    }};
//
//    public List<Category> getAllCategories() {
//        return categories;
//    }
//    public Category getCategoryById(int id) {
//        return categories.stream().filter(category -> category.getId() == id)
//                .findFirst()
//                .orElseThrow(()->new NoSuchElementException("Category not found!"));
//    }
//
//    public Category updateCategoryById(Category updatedCategory) {
//        for(int i=0;i<categories.size();i++){
//            var product = categories.get(i);
//            if(categories.get(i).getId()==updatedCategory.getId()){
//                categories.set(i,updatedCategory);
//            }
//        }
//        return null;
//    }
//
//    public boolean deleteCategoryById(int id) {
//        return categories.removeIf(category -> category.getId() == id);
//    }
//
//    public Category createCategory(Category category) {
//        categories.add(category);
//        return category;
//    }
}
