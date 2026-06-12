package co.istad.demproductapisimple.repository;

import co.istad.demproductapisimple.dto.categoryDto.CategoryResponse;
import co.istad.demproductapisimple.entity.Category;
import co.istad.demproductapisimple.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Boolean existsByName(String name);
    Page<Category> findByNameContainingIgnoreCase(
            String keyword,
            Pageable pageable
    );
}
