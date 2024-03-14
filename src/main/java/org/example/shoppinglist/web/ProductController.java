package org.example.shoppinglist.web;

import jakarta.validation.Valid;
import org.example.shoppinglist.model.binding.AddProductBindingModel;
import org.example.shoppinglist.model.service.ProductServiceModel;
import org.example.shoppinglist.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add() {
        return "product-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid AddProductBindingModel addProductBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes rAtt) {

        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("addProductBindingModel", addProductBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addProductBindingModel",
                            bindingResult);

            return "redirect:add";
        }

        productService
                .addProduct(modelMapper
                        .map(addProductBindingModel, ProductServiceModel.class));

        return "redirect:/";
    }

    @ModelAttribute
    public AddProductBindingModel addProductBindingModel() {
        return new AddProductBindingModel();
    }

    @GetMapping("/buy/{id}")
    public String buy(@PathVariable Long id) {
        productService.buyById(id);
        return "redirect:/";
    }

    @GetMapping("/buy/all")
    public String buyAll() {
        productService.buyAll();
        return "redirect:/";
    }
}
