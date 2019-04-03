package com.example.travel.controller;

import com.example.travel.VO.ResultVO;
import com.example.travel.dao.VUser;
import com.example.travel.service.VUserService;
import com.example.travel.service.VUserService;
import com.example.travel.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("VUser")
public class VUserController {
    @Autowired
    private VUserService vUserService;

    @GetMapping("/create")
    public ResultVO create(@RequestParam("username") String username,@RequestParam("password") String password,
                           @RequestParam("age") String age,@RequestParam("tel") String tel,
                           @RequestParam("description") String description,@RequestParam("wxappid") String wxappid
                           ){
        VUser vUser=new VUser(username,password,age,tel,description,wxappid,0);
        return ResultUtil.success(vUserService.create(vUser));
    }

    @GetMapping("/delete")
    public ResultVO delete(@RequestParam("vId") Integer vId){
        return ResultUtil.success(vUserService.delete(vId));
    }

    @GetMapping("/update")
    public ResultVO update(@RequestParam("vId") Integer vId,@RequestParam("username") String username,
                           @RequestParam("password") String password, @RequestParam("age") String age,
                           @RequestParam("tel") String tel, @RequestParam("description") String description
                           ){
        VUser vUser = vUserService.find(vId);
        vUser.setUsername(username);
        vUser.setPassword(password);
        vUser.setAge(age);
        vUser.setTel(tel);
        vUser.setDescription(description);
        return ResultUtil.success(vUserService.update(vUser));
    }
    /**
     *@Author:dubaohao
     *@Description:实际中，密码password不能返回哦！！此处为测试~~~~find这个接口也不会使用！
     *@Date:_
     */
    @GetMapping("/find")
    public ResultVO find(@RequestParam("vId") Integer vId){
        return ResultUtil.success(vUserService.find(vId));
    }

    @GetMapping("/login")
    public ResultVO login(@RequestParam("username") String username,@RequestParam("password") String password){
        return ResultUtil.success(vUserService.login(username,password));
    }
}
