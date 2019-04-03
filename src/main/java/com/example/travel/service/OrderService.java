package com.example.travel.service;

import com.example.travel.dao.TOrder;
import com.example.travel.dao.TOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    /** 创建订单. */
    TOrder create(TOrder tOrder);

    /** 删除订单. */
//    String delete(Integer orderId);

    /** 更新订单. */
    TOrder update(TOrder tOrder);

    /** 查询单个订单ID. */
    TOrder findOne(Integer orderId);

    /** 查询单个订单Number. */
    TOrder findOrderNumber(String orderNumber);

    /** 查询用户所有订单. */
    List<TOrder> findVUserOrder(Integer vId);

    /** 查询导游所有订单数量. */
    Long findGUserOrderNumber(Integer gId);

    /** 查询导游所有订单-分页. */
    List<TOrder> findGUserOrder(Integer gId);

    /** 查询导游所有订单-分页. */
    Page<TOrder> findGUserOrderPage(Integer gId,Pageable pageable);

    /** 查询导游所有订单-为了GOrderController，多个表格融合. */
//    Page<TOrder> findGOrder(Integer gId,Pageable pageable);

    /** 查询某一个团的订单. */
    List<TOrder> findGroupOrder(String groupNumber);

    /** 取消订单. */
    TOrder cancel(TOrder tOrder);

    /** 完结订单. */
    TOrder finish(TOrder tOrder);

    /** 支付订单. */
    TOrder paid(TOrder tOrder);


}
