package com.example.travel.service;

import com.example.travel.dao.TGroup;
import com.example.travel.dao.TGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GroupServcice {
    /** 创建团单. */
    String create(TGroup tGroup);

    /** 删除团单. */
//    String delete(Integer groupId);

    /** 更新团单. */
    String update(TGroup tGroup);

    /** 查询单个团单ID. */
    TGroup findOne(Integer groupId);

    /** 查询单个团单Number. */
    TGroup findGroupNumber(String groupNumber);

    /** 查询导游所有团单数量. */
    Long findGUserGroupNumber(Integer gId);

    /** 查询导游所有团单. */
    List<TGroup> findGUserGroup(Integer gId);

    /** 查询导游所有团单. */
    Page<TGroup> findGUserGroupPage(Integer gId,Pageable pageable);

//    /** 查询某一个团的团单. */
//    List<TGroup> findGroupGroup(String groupNumber);

    /** 取消团单. */
    TGroup cancel(TGroup tGroup);

    /** 完结团单. */
    TGroup finish(TGroup tGroup);

    /** 支付团单. */
    TGroup paid(TGroup tGroup);
}
