package com.Store.ShoesStore.controller;

import com.Store.ShoesStore.enity.Category;
import com.Store.ShoesStore.enity.Product;
import com.Store.ShoesStore.services.ProductService;
import com.Store.ShoesStore.services.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/view")
    public String listProduct(@RequestParam(value = "page", defaultValue = "1") int page, Model model){
        int pageSize = 9; // Number of products per page
        Page<Product> productPage = productService.getPaginatedProducts(page, pageSize);
        List<Product> products = productPage.getContent();

        model.addAttribute("Products", products);
        model.addAttribute("categories", categoryService.getAllCategory());

        int totalPages = productPage.getTotalPages();
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);

        return "product/shop";
    }
    @GetMapping("/view/{id}")
    public String singleProduct(@PathVariable("id") long id, Model model){
        Product product = productService.getBookById(id);
        if(product == null)
            return "error/404";
        model.addAttribute("product", product);
        return "product/product-single";
    }
    @GetMapping("/search")
    public String searchProducts(@RequestParam(value = "page", defaultValue = "1") int page,
                                 @RequestParam("q") String query,
                                 Model model) {
        int pageSize = 9;
        Page<Product> productPage = productService.getProductByName(page, pageSize, query);
        List<Product> products = productPage.getContent();
        model.addAttribute("Products", products);
        model.addAttribute("categories", categoryService.getAllCategory());
        int totalPages = productPage.getTotalPages();
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("query", query);
        return "product/listsearch";
    }

    @GetMapping("/view/category/{id}")
    public String viewByCategory(@PathVariable("id") Long id, Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
        Category category = categoryService.getCategoryById(id);
        int pageSize = 9; // Number of products per page
        Page<Product> productPage = productService.getProductByCategory(page, pageSize, category);
        List<Product> products = productPage.getContent();
        model.addAttribute("category", category);
        model.addAttribute("Products", products);
        model.addAttribute("categories", categoryService.getAllCategory());

        int totalPages = productPage.getTotalPages();
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        return "product/listcate";
    }


    @GetMapping("/add")
    public String addBookForm(Model model){
        /*Random random = new Random();

        String[] namePro = {"Giày cao gót", "Dép Lào", "Ủng da", "Sneaker"};
        int[] quanity = {1,2,3,4,5};
        String[] origin = {"Japan", "China", "Việt Nam", "USA", "UK", "France"};
        String[] images = {"product-1.png", "product-2.png", "product-3.png","product-4.png"
        ,"product-5.png","product-6.png","product-7.png","product-8.png"};
        List<Category> allCategories = categoryService.getAllCategory();

        for(int i = 0; i < 50; i++){
            Product newProduct = new Product();
            int randomNumber = random.nextInt(namePro.length );
            newProduct.setName(namePro[randomNumber] +" " + i);
            newProduct.setDescription("Mô tả " + i);
            randomNumber = random.nextInt(quanity.length);
            newProduct.setCurrentQuantity(quanity[randomNumber] * 10 + i);
            randomNumber = random.nextInt(origin.length);
            newProduct.setOrigin(origin[randomNumber]);
            newProduct.setCostPrice(i * 100 + i * randomNumber * 9);
            newProduct.setSalePrice(i * 100 + i * randomNumber * 9 - 100);
            randomNumber = random.nextInt(images.length);
            newProduct.setImage(images[randomNumber]);
            newProduct.set_activated(true);
            long randomCategoryId = allCategories.get(random.nextInt(allCategories.size())).getId();
            Optional<Category> foundCategory = allCategories.stream()
                    .filter(category -> category.getId() == randomCategoryId)
                    .findFirst();
            foundCategory.ifPresent(category -> newProduct.setCategory(category));
            productService.addProduct(newProduct);
        }*/
        model.addAttribute("book", new Product());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "redirect:/products/view";
    }
}
