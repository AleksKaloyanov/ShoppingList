package org.example.shoppinglist.model.binding;

import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.example.shoppinglist.model.entity.CategoryEntity;
import org.example.shoppinglist.model.entity.enums.CategoryNameEnum;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AddProductBindingModel {
    @Size(min = 3, max = 20)
    private String name;
    @Size(min = 5)
    private String description;
    @Positive
    @NotNull
    private BigDecimal price;
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime neededBefore;
    @OneToOne
    @NotNull
    private CategoryNameEnum category;

    public AddProductBindingModel() {
    }

    public String getName() {
        return name;
    }

    public AddProductBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddProductBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getNeededBefore() {
        return neededBefore;
    }

    public AddProductBindingModel setNeededBefore(LocalDateTime neededBefore) {
        this.neededBefore = neededBefore;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AddProductBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }


    public CategoryNameEnum getCategory() {
        return category;
    }

    public AddProductBindingModel setCategory(CategoryNameEnum category) {
        this.category = category;
        return this;
    }
}
