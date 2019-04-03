package com.example.travel.controller;

import com.example.travel.VO.ResultVO;
import com.example.travel.service.GroupServcice;
import com.example.travel.service.HodometerService;
import com.example.travel.service.OrderService;
import com.example.travel.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/Common")
public class CommonController {
    @Autowired
    private HodometerService hodometerService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private GroupServcice groupServcice;

    @GetMapping("/findGUserNumber")
    public ResultVO findOrderNumberByGId(@RequestParam("gId") Integer gId){
        Map<String,Long> map =new HashMap<String, Long>();
        map.put("HodoCount", hodometerService.findHodoNumber(gId));
        map.put("OrderCount",orderService.findGUserOrderNumber(gId));
        map.put("GroupCount",groupServcice.findGUserGroupNumber(gId));
        return ResultUtil.success(map);
    }
}
