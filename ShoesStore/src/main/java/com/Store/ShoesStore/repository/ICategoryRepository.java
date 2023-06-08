package com.Store.ShoesStore.repository;

import com.Store.ShoesStore.enity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository  extends JpaRepository<Category, Long> {
}
