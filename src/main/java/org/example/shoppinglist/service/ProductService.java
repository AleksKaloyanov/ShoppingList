package org.example.shoppinglist.service;

import org.example.shoppinglist.model.entity.enums.CategoryNameEnum;
import org.example.shoppinglist.model.service.ProductServiceModel;
import org.example.shoppinglist.model.view.ProductViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void addProduct(ProductServiceModel productServiceModel);

    BigDecimal findTotalSum();

    List<ProductViewModel> findAllProductsByCategoryName(CategoryNameEnum categoryNameEnum);

    void buyById(Long id);

    void buyAll();

}
