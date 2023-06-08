package com.Store.ShoesStore.controller;

import com.Store.ShoesStore.enity.Product;
import com.Store.ShoesStore.services.ProductService;
import com.Store.ShoesStore.services.CategoryService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String listProduct(Model model){
        List<Product> Products = productService.getAllBook();
        model.addAttribute("Products", Products);
        return "Product/shop";
    }

    @GetMapping("/add")
    public String addBookForm(Model model){
        model.addAttribute("book", new Product());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "Product/add";
    }
    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") Product Product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategory());
            return "Product/add";
        }

        productService.addBook(Product);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") long id, Model model){
        Product editProduct = productService.getBookById(id);
        if(editProduct != null){
            model.addAttribute("book", editProduct);
            model.addAttribute("categories", categoryService.getAllCategory());
            return "Product/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public String editBook(@Valid @ModelAttribute("book") Product updateProduct, BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()){
            model.addAttribute("categories", categoryService.getAllCategory());
            return "Product/edit";
        }
        productService.getAllBook().stream()
                .filter(book -> book.getId() == updateProduct.getId())
                .findFirst()
                .ifPresent(book -> {

                    productService.updateBook(updateProduct);
                });
        return "redirect:/books";
    }
    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") long id){
        productService.deleteBook(id);
        return "redirect:/books";
    }
}
