package com.example.travel.controller;

import com.example.travel.VO.ResultVO;
import com.example.travel.service.GroupServcice;
import com.example.travel.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/TGroup")
public class TGroupController {
    @Autowired
    private GroupServcice groupServcice;

    @GetMapping("/find")
    public ResultVO find(){
        return ResultUtil.success(groupServcice.findOne(1));
    }

    @GetMapping("/findGroupByGId")
    public ResultVO findOrderByGId(@RequestParam("gId") Integer gId,
                                   @RequestParam(value = "page", defaultValue = "0") Integer page,
                                   @RequestParam(value = "size", defaultValue = "10") Integer size){
        PageRequest request =new PageRequest(page,size);
        return ResultUtil.success(groupServcice.findGUserGroupPage(gId,request));
    }
}
