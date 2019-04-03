package com.example.travel.controller;

import com.example.travel.dao.GUser;
import com.example.travel.VO.ResultVO;
import com.example.travel.service.GUserService;
import com.example.travel.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/GUser")
public class GUserController {
    @Autowired
    private GUserService gUserService;

    @GetMapping("/create")
    public ResultVO create(@RequestParam("username") String username,@RequestParam("password") String password,
                           @RequestParam("age") String age,@RequestParam("tel") String tel,
                           @RequestParam("description") String description,@RequestParam("wxappid") String wxappid,
                           @RequestParam("company") String company){
        GUser gUser=new GUser(username,password,age,tel,description,wxappid,company,0);
        return ResultUtil.success(gUserService.create(gUser));
    }

    @GetMapping("/delete")
    public ResultVO delete(@RequestParam("gId") Integer gId){
        return ResultUtil.success(gUserService.delete(gId));
    }

    @GetMapping("/update")
    public ResultVO update(@RequestParam("gId") Integer gId,@RequestParam("username") String username,
                           @RequestParam("password") String password, @RequestParam("age") String age,
                           @RequestParam("tel") String tel, @RequestParam("description") String description,
                           @RequestParam("company") String company){
        GUser gUser = gUserService.find(gId);
        gUser.setUsername(username);
        gUser.setPassword(password);
        gUser.setAge(age);
        gUser.setTel(tel);
        gUser.setDescription(description);
        gUser.setCompany(company);
        return ResultUtil.success(gUserService.update(gUser));
    }
/**
*@Author:dubaohao
*@Description:实际中，密码password不能返回哦！！此处为测试~~~~find这个接口也不会使用！
*@Date:_
*/
    @GetMapping("/find")
    public ResultVO find(@RequestParam("gId") Integer gId){
        return ResultUtil.success(gUserService.find(gId));
    }

    @GetMapping("/login")
    public ResultVO login(@RequestParam("username") String username,@RequestParam("password") String password){
        return ResultUtil.success(gUserService.login(username,password));
    }

    @GetMapping("/GLogin")
    public ResultVO GLogin(@RequestParam("username") String username,@RequestParam("password") String password){
        if(gUserService.login(username,password).equals("login-success")){
            return ResultUtil.success(gUserService.GFind(username));
        }else if(gUserService.login(username,password).equals("login-failed1")){
            return ResultUtil.error(0,"用户不存在");
        }else if(gUserService.login(username,password).equals("login-failed2")){
            return ResultUtil.error(0, "密码错误");
        }else {
            return ResultUtil.error(0, "未知错误");
        }

    }
}
