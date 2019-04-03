package com.example.travel.controller;

import com.example.travel.VO.ResultVO;
import com.example.travel.dao.TOrder;
import com.example.travel.service.HodometerService;
import com.example.travel.service.OrderService;
import com.example.travel.utils.RandomNumberUtil;
import com.example.travel.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Date;

@CrossOrigin
@RestController
@RequestMapping("/TOrder")
public class TOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private HodometerService hodometerService;

    @GetMapping("/create")
    public ResultVO create(@RequestParam("hoId") Integer hoId, @RequestParam("gId") Integer gId,
                         @RequestParam("vId") Integer vId, @RequestParam("orderNum") BigDecimal orderNum,
                          @RequestParam("name") String name,
                         @RequestParam("tel") String tel, @RequestParam("wx") String wx,
                         @RequestParam("goDate") Date goDate){
        //很多参数应该是从数据库查询或者后台生成，比如订单号，订单金额计算！！！
         TOrder order = new TOrder();
         order.setOrderNumber(RandomNumberUtil.orderNumber());
         order.setGroupNumber("0");//团单号默认为0
         order.setHoId(hoId);
         order.setGId(gId);
         order.setVId(vId);
         order.setOrderNum(orderNum);
         BigDecimal money=orderNum.multiply(hodometerService.findOne(hoId).getPrice());//通过传入行程查到的价格乘以数量进行计算价钱money
         order.setMoney(money);
         order.setName(name);
         order.setTel(tel);
         order.setWx(wx);
         order.setOrderStatus("未支付");
         order.setOrderProgress("未出发");
         order.setGoDate(goDate);
         return ResultUtil.success(orderService.create(order));
    }

    @GetMapping("/findOne")
    public ResultVO findOne(@RequestParam("orderId") Integer orderId){
        return ResultUtil.success(orderService.findOne(orderId));
    }

    @GetMapping("/findOrderNumber")
    public ResultVO findOrderNumber(@RequestParam("orderNumber") String orderNumber){
        return ResultUtil.success(orderService.findOrderNumber(orderNumber));
    }

    @GetMapping("/findVUserOrder")
    public ResultVO findVUserOrder(@RequestParam("vId") Integer vId){
        return ResultUtil.success(orderService.findVUserOrder(vId));
    }

    @GetMapping("/findGroupOrder")
    public ResultVO findGroupOrder(@RequestParam("groupNumber") String groupNumber){
        return ResultUtil.success(orderService.findGroupOrder(groupNumber));
    }
/**
*@Author:dubaohao
*@Description:停止使用，看CommonController，查询数量
*@Date:_
*/
    @GetMapping("/findOrderNumberByGId")
    public ResultVO findOrderNumberByGId(@RequestParam("gId") Integer gId){
        return ResultUtil.success(orderService.findGUserOrderNumber(gId));
    }

    @GetMapping("/findOrderByGId")
    public ResultVO findOrderByGId(@RequestParam("gId") Integer gId,
                                   @RequestParam(value = "page", defaultValue = "0") Integer page,
                                   @RequestParam(value = "size", defaultValue = "10") Integer size){
        PageRequest request =new PageRequest(page,size);
        return ResultUtil.success(orderService.findGUserOrderPage(gId,request));
    }


    @GetMapping("/cancel")
    public ResultVO cancle(@RequestParam("orderId") Integer orderId){
        TOrder order=orderService.findOne(orderId);
        order.setOrderProgress("取消订单");
        return ResultUtil.success(orderService.update(order));
    }

    @GetMapping("/finish")
    public ResultVO finish(@RequestParam("orderId") Integer orderId){
        TOrder order=orderService.findOne(orderId);
        order.setOrderProgress("完结订单");
        return ResultUtil.success(orderService.update(order));
    }

    @GetMapping("/pay")
    public ResultVO pay(@RequestParam("orderId") Integer orderId){
        TOrder order=orderService.findOne(orderId);
        order.setOrderStatus("已支付");
        return ResultUtil.success(orderService.update(order));
    }


}
