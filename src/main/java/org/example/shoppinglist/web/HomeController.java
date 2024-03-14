package org.example.shoppinglist.web;

import org.example.shoppinglist.model.entity.enums.CategoryNameEnum;
import org.example.shoppinglist.repository.ProductRepository;
import org.example.shoppinglist.service.ProductService;
import org.example.shoppinglist.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final ProductService productService;

    public HomeController(CurrentUser currentUser, ProductService productService
    ) {
        this.currentUser = currentUser;
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (!currentUser.isLogged()) {
            return "index";
        }

        model
                .addAttribute("totalSum", productService.findTotalSum())
                .addAttribute("drinks", productService
                        .findAllProductsByCategoryName(CategoryNameEnum.DRINK))
                .addAttribute("foods", productService
                        .findAllProductsByCategoryName(CategoryNameEnum.FOOD))
                .addAttribute("households", productService
                        .findAllProductsByCategoryName(CategoryNameEnum.HOUSEHOLD))
                .addAttribute("others", productService
                        .findAllProductsByCategoryName(CategoryNameEnum.OTHER));

        return "home";
    }


}
