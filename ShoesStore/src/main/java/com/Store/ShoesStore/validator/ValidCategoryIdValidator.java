package com.Store.ShoesStore.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import com.Store.ShoesStore.enity.Category;
import com.Store.ShoesStore.validator.annotation.ValidCategoryId;

public class ValidCategoryIdValidator implements ConstraintValidator<ValidCategoryId, Category> {
    @Override
    public boolean isValid(Category category, ConstraintValidatorContext context){
        return (category != null && category.getId() != 0L);
    }
}
