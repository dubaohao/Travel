package com.example.travel.controller;

import com.example.travel.VO.ResultVO;
import com.example.travel.dao.Category;
import com.example.travel.service.CategoryService;
import com.example.travel.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/Category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/create")
    public ResultVO create(@RequestParam("gId")Integer gId,@RequestParam("category") String category){
        Category category1=new Category(gId,category);
        return ResultUtil.success(categoryService.create(category1));
    }

    @GetMapping("/delete")
    public ResultVO delete(@RequestParam("cId")Integer cId){
        return ResultUtil.success(categoryService.delete(cId));
    }

    @GetMapping("/update")
    public ResultVO update(@RequestParam("cId") Integer cId,@RequestParam("gId") Integer gId,@RequestParam("category") String category){
        Category category1=new Category(gId,category);
        category1.setCId(cId);
        return ResultUtil.success(categoryService.update(category1));
    }

    @GetMapping("/find")
    public ResultVO find(){
        return ResultUtil.success(categoryService.find(1));
    }

    @GetMapping("/findList")
    public ResultVO findList(@RequestParam("gId") Integer gId){
        return ResultUtil.success(categoryService.findList(gId));
    }
}
