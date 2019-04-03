package com.example.travel.controller;

import com.example.travel.VO.ResultVO;
import com.example.travel.dao.Hodometer;
import com.example.travel.service.HodometerService;
import com.example.travel.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@CrossOrigin
@RestController
@RequestMapping("/Hodo")
public class HodometerController {
    @Autowired
    private HodometerService hodometerService;


    @GetMapping("/create")
    public ResultVO create(@RequestParam("hoName") String hoName, @RequestParam("picture") String picture,
                           @RequestParam("gId") Integer gId, @RequestParam("price") BigDecimal price,
                           @RequestParam("gCategory") String gCategory,
//                           @RequestParam("createDate") Timestamp createDate, @RequestParam("updateDate") Timestamp updateDate,
                           @RequestParam("status") String status, @RequestParam("progress") String progress,
                           @RequestParam("hoUrl") String hoUrl, @RequestParam("hoNumUp") Integer hoNumUp,
                           @RequestParam("hoNumDown") Integer hoNumDown, @RequestParam("strategyUrl") String strategyUrl){
        Hodometer hodometer=new Hodometer(hoName,picture,gId,price,gCategory,status,progress,hoUrl,hoNumUp,hoNumDown,strategyUrl);
        return ResultUtil.success(hodometerService.create(hodometer));
    }

    @GetMapping("/delete")
    public ResultVO delete(@RequestParam("hoId") Integer hoId){
        return ResultUtil.success(hodometerService.delete(hoId));
    }

    @GetMapping("update")
    public ResultVO cancel(@RequestParam("hoId") Integer hoId,@RequestParam("hoName") String hoName,
                           @RequestParam("picture") String picture, @RequestParam("gId") Integer gId,
                           @RequestParam("price") BigDecimal price, @RequestParam("gCategory") String gCategory,
//                           @RequestParam("createDate") Timestamp createDate,@RequestParam("updateDate") Timestamp updateDate,
                           @RequestParam("status") String status,@RequestParam("progress") String progress,
                           @RequestParam("hoUrl") String hoUrl,@RequestParam("hoNumUp") Integer hoNumUp,
                           @RequestParam("hoNumDown") Integer hoNumDown,@RequestParam("strategyUrl") String strategyUrl){
        Hodometer hodometer=new Hodometer(hoName,picture,gId,price,gCategory,status,progress,hoUrl,hoNumUp,hoNumDown,strategyUrl);
        hodometer.setHoId(hoId);
        return ResultUtil.success(hodometerService.update(hodometer));
    }

    @GetMapping("/findOne")
    public ResultVO findOne(@RequestParam("hoId") Integer hoId){
        return ResultUtil.success(hodometerService.findOne(hoId));
    }

    @GetMapping("/findList")
    public ResultVO findList(@RequestParam("gId") Integer gId,
                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size){
        PageRequest request =new PageRequest(page,size);
        return ResultUtil.success(hodometerService.findListPage(gId,request));
    }

    @GetMapping("/findUpDownList")
    public ResultVO findUpList(@RequestParam("gId") Integer gId,@RequestParam("status") String status,
                               @RequestParam(value = "page", defaultValue = "0") Integer page,
                               @RequestParam(value = "size", defaultValue = "10") Integer size){
        PageRequest request =new PageRequest(page,size);
        return ResultUtil.success(hodometerService.findUpList(gId,status,request));
    }


    @GetMapping("/updateStatus")
    public ResultVO updateStatus(@RequestParam("hoId") Integer hoId,@RequestParam("status") String status
                                 ){
        Hodometer hodometer = hodometerService.findOne(hoId);
        hodometer.setStatus(status);
        return ResultUtil.success(hodometerService.update(hodometer));
    }
}
