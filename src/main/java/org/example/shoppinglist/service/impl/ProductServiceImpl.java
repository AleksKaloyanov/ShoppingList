package org.example.shoppinglist.service.impl;

import org.example.shoppinglist.model.entity.CategoryEntity;
import org.example.shoppinglist.model.entity.ProductEntity;
import org.example.shoppinglist.model.entity.enums.CategoryNameEnum;
import org.example.shoppinglist.model.service.ProductServiceModel;
import org.example.shoppinglist.model.view.ProductViewModel;
import org.example.shoppinglist.repository.CategoryRepository;
import org.example.shoppinglist.repository.ProductRepository;
import org.example.shoppinglist.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              ModelMapper modelMapper
    ) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addProduct(ProductServiceModel productServiceModel) {
        ProductEntity product = modelMapper
                .map(productServiceModel, ProductEntity.class);

        product
                .setCategory(categoryRepository.findByName(productServiceModel.getCategory())
                        .orElse(null));

        productRepository.save(product);
    }

    @Override
    public List<ProductViewModel> findAllProductsByCategoryName(CategoryNameEnum categoryNameEnum) {
        return productRepository.findAllByCategory_Name(categoryNameEnum)
                .stream().map(productEntity -> modelMapper.map(productEntity, ProductViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void buyById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void buyAll() {
        productRepository.deleteAll();
    }

    @Override
    public BigDecimal findTotalSum() {
        return productRepository.findTotalProductSum()
                == null ? BigDecimal.valueOf(0) : productRepository.findTotalProductSum();
    }
}
