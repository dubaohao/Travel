package com.example.travel.service;

import com.example.travel.dao.Hodometer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HodometerService {
    /** 创建行程. */
    Hodometer create(Hodometer hodometer);

    /** 删除行程. */
    String delete(Integer hoId);

    /** 更新行程. */
    Hodometer update(Hodometer hodometer);

    /** 查询单个行程. */
    Hodometer findOne(Integer hoId);

    /** 查询导游所有行程数量. */
    Long findHodoNumber(Integer gId);

    /** 查询导游行程列表. */
    List<Hodometer> findList(Integer gId);

    /** 查询导游行程列表. */
    Page<Hodometer> findListPage(Integer gId, Pageable pageable);

    /** 查询行程上架列表. */
    Page<Hodometer> findUpList(Integer gId,String status, Pageable pageable);

    /** 查询行程下架列表. */
    Page<Hodometer> findDownList(Integer gId,String status, Pageable pageable);

    /** 更新行程上下架列表. */
    Hodometer updatestatus(Integer gId,String status);

    /** 根据List<hodometerList>查询hodometer. */
    List<Hodometer> findHoIdByGIdIn(List<Integer> hodometerList);

}
