package com.example.travel.service;

import com.example.travel.dao.GUser;
import com.example.travel.dao.GUser;
import com.example.travel.dao.GUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GUserService {
    /** 创建导游用户. */
    GUser create(GUser GUser);

    /** 删除导游用户. */
    String delete(Integer vId);

    /** 更新导游用户. */
    GUser update(GUser GUser);

    /** 查询单个导游用户. */
    GUser find(Integer vId);

    /** 登录-导游用户. */
    String login(String username,String password);

    /** 登录-导游用户，返回所有信息. */
    GUser GFind(String username);
}
