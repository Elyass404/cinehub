package com.cinehub.service;

import com.cinehub.model.Category;
import com.cinehub.respository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryService implements CategoryServiceInterface {

    final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public Category save(Category category){
        // todo: hta nrj3 lhna ndir logic
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id){
        //we will use the optional to handle the exception of not finding the category
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category with ID " + id + " not found."));
    }

    @Override
    public void delete(Long id){

        if(!categoryRepository.existsById(id)){
            throw new NoSuchElementException("We can not delete the category with id:"+ id+"because it is not found!");
        }
         categoryRepository.deleteById(id);
    }

    //Now for the custom method to get the category by name
    @Override
    public Category findByName(String name){
        //this method mzl madertch l code dyalha
        //categoryRepository.findByName(name);
        return null;
    }


}
