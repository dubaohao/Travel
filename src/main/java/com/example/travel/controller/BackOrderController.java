package com.example.travel.controller;

import com.example.travel.VO.GHodometerVO;
import com.example.travel.VO.GOrderVO;
import com.example.travel.VO.ResultVO;
import com.example.travel.dao.Hodometer;
import com.example.travel.dao.TOrder;
import com.example.travel.service.GroupServcice;
import com.example.travel.service.HodometerService;
import com.example.travel.service.OrderService;
import com.example.travel.utils.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/GOrder")
public class BackOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private HodometerService hodometerService;
    @Autowired
    private GroupServcice groupServcice;

    @GetMapping("/findGOrder")
    public ResultVO findGOrder(@RequestParam("gId") Integer gId,
                               @RequestParam(value = "page", defaultValue = "0") Integer page,
                               @RequestParam(value = "size", defaultValue = "10") Integer size) {


        // 查询导游的所有Order
        List<TOrder> tOrderList = orderService.findGUserOrder(gId);
        //查询导游的所有hodometer
        List<Hodometer> hodometerList = hodometerService.findList(gId);
        //查询方法（一次查询）
        List<Integer> hodometerGIdList = new ArrayList<>();
        //传统方法
        for (Hodometer hodometer : hodometerList) {
            hodometerGIdList.add(hodometer.getGId());
//            BeanUtils.copyProperties(hodometer, hodometerGIdList);
        }

        List<Hodometer> gHodometerList = hodometerService.findHoIdByGIdIn(hodometerGIdList);
//        System.out.println(hodometerGIdList);
//        System.out.println(gHodometerList);

        //数据拼装
        List<GOrderVO> gOrderVOList = new ArrayList<>();
        for (TOrder tOrder : tOrderList) {
            GOrderVO gOrderVO = new GOrderVO();
            BeanUtils.copyProperties(tOrder, gOrderVO);

            List<GHodometerVO> gHodometerVOList = new ArrayList<>();
            for (Hodometer hodometer : gHodometerList) {
                GHodometerVO gHodometerVO = new GHodometerVO();
                BeanUtils.copyProperties(hodometer, gHodometerVO);
                gHodometerVOList.add(gHodometerVO);
            }
            gOrderVO.setGHodometerVOList(gHodometerVOList);
            gOrderVOList.add(gOrderVO);
        }
        System.out.println(gOrderVOList);
        return ResultUtil.success(gOrderVOList);
    }
}
