package org.example.shoppinglist.model.entity;

import jakarta.persistence.*;
import org.example.shoppinglist.model.entity.enums.CategoryNameEnum;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity{
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private CategoryNameEnum name;
    private String description;

    public CategoryEntity() {
    }

    public CategoryNameEnum getName() {
        return name;
    }

    public CategoryEntity setName(CategoryNameEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CategoryEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
