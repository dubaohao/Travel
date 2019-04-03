package com.example.travel.controller;

import com.example.travel.dao.Hodometer;
import com.example.travel.dao.TOrder;
import com.example.travel.service.HodometerService;
import com.example.travel.service.OrderService;
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
import java.util.Map;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/sell/order")
public class SellOrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private HodometerService hodometerService;
    /**
     * 订单列表
     * @param page 第几页, 从1页开始
     * @param size 一页有多少条数据
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam("gId") Integer gId,
                             @RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<TOrder> orderDTOPage = orderService.findGUserOrderPage(gId,request);



        map.put("orderDTOPage", orderDTOPage);
        map.put("currentPage", page);
        map.put("size", size);
//        orderDTOPage.getTotalPages()
        return new ModelAndView("order/list", map);
    }
    /*
     * @Author dubaohao
     * @Description //详情页
     * @Date 15:50 2018/11/26
     * @Param
     * @return
     **/
    @GetMapping("/detail")
    public ModelAndView detial(@RequestParam("orderNumber") String orderNumber){
        TOrder tOrder = orderService.findOrderNumber(orderNumber);
        Hodometer hodometer = hodometerService.findOne(tOrder.getHoId());

        Map<String,Object>map = new HashMap<String,Object>();
        map.put("Hodometer",hodometer);
        map.put("OrderDetail",tOrder);
        log.info("map={}",map);
        return new ModelAndView("order/detail",map);
    }
}
