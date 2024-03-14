package org.example.shoppinglist.repository;

import jakarta.validation.constraints.NotNull;
import org.example.shoppinglist.model.entity.CategoryEntity;
import org.example.shoppinglist.model.entity.ProductEntity;
import org.example.shoppinglist.model.entity.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query("select sum(p.price) from ProductEntity p")
    BigDecimal findTotalProductSum();

    Optional<ProductEntity> findAllByCategory_Name(CategoryNameEnum categoryNameEnum);
}
