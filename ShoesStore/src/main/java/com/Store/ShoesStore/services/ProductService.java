package com.Store.ShoesStore.services;

import com.Store.ShoesStore.enity.Product;
import com.Store.ShoesStore.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private IProductRepository bookRepository;
    public List<Product> getAllBook(){
        return bookRepository.findAll();
    }
    public Product getBookById(Long id){
        Optional<Product> optionalBook = bookRepository.findById(id);
        return optionalBook.orElse(null);
    }
    public void addBook(Product newbook){
        bookRepository.save(newbook);
    }
    public void updateBook(Product newbook){
        bookRepository.save(newbook);
    }
    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }
}
