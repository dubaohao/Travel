package com.example.travel.service.impl;

import com.example.travel.dao.Category;
import com.example.travel.repository.CategoryRepository;
import com.example.travel.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository repository;

    @Override
    public Category create(Category category) {
        return repository.save(category);
    }

    @Override
    public String delete(Integer cId) {
        repository.delete(cId);
        return "success";
    }

    @Override
    public Category update(Category category) {
        return repository.save(category);
    }

    @Override
    public Category find(Integer cId) {
        return repository.findOne(cId);
    }

    @Override
    public List<Category> findList(Integer gId) {
        return repository.findByGId(gId);
    }
}
