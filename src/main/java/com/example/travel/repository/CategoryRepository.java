package com.example.travel.repository;

import com.example.travel.dao.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    List<Category> findByGId( Integer gId);
}
