package com.example.travel.service;

import com.example.travel.dao.Category;
import com.example.travel.dao.VUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    /** 创建类目. */
    Category create(Category category);

    /** 删除类目. */
    String delete(Integer cId);

    /** 更新类目. */
    Category update(Category category);

    /** 查询类目. */
    Category find(Integer cId);

    /** 查询类目列表. */
    List<Category> findList(Integer gId);
}
