package org.example.shoppinglist.service.impl;

import org.example.shoppinglist.model.entity.CategoryEntity;
import org.example.shoppinglist.model.entity.enums.CategoryNameEnum;
import org.example.shoppinglist.repository.CategoryRepository;
import org.example.shoppinglist.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initDatabase() {
        if (categoryRepository.count() == 0) {
            for (CategoryNameEnum value : CategoryNameEnum.values()) {

                CategoryEntity category = new CategoryEntity();
                String description = "";
                switch (value) {
                    case FOOD -> description = "item is food";
                    case DRINK -> description = "item is drink";
                    case OTHER -> description = "item is other";
                    case HOUSEHOLD -> description = "item is household";
                }
                category.setName(value).setDescription(description);
                categoryRepository.save(category);
            }
        }
    }
}
