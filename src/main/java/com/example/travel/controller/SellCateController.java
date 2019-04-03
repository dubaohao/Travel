package com.example.travel.controller;

import com.example.travel.dao.Category;
import com.example.travel.dao.TGroup;
import com.example.travel.service.CategoryService;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/sell/category")
public class SellCateController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 订单列表
     * @param gId
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam("gId") Integer gId) {
        List<Category> categoryList = categoryService.findList(gId);
        System.out.println(categoryList);

        Map<String,Object>map = new HashMap<String, Object>();
        map.put("categoryList", categoryList);
//        orderDTOPage.getTotalPages()
        return new ModelAndView("category/list", map);
    }

}
