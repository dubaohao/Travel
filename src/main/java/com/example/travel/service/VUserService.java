package com.example.travel.service;

import com.example.travel.dao.VUser;
import com.example.travel.dao.VUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
/** 游客用户CURD - Service层. */
public interface VUserService {
    /** 创建游客用户. */
    VUser create(VUser VUser);

    /** 删除游客用户. */
    String delete(Integer vId);

    /** 更新游客用户. */
    VUser update(VUser VUser);

    /** 查询单个游客用户. */
    VUser find(Integer vId);

    /** 登录-游客用户. */
    String login(String username,String password);
}
