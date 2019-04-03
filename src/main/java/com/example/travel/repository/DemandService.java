package com.example.travel.repository;

import com.example.travel.dao.Demand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DemandService {
    /** 创建需求. */
    Demand create(Demand Demand);

    /** 查询单个需求. */
    Demand findOne(String orderId);

    /** 查询需求列表. */
    Page<Demand> findList(String buyerOpenid, Pageable pageable);

    /** 取消需求. */
    Demand cancel(Demand Demand);

    /** 完结需求-收入团单. */
    Demand finish(Demand Demand);



}
