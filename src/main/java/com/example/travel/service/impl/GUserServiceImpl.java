package com.example.travel.service.impl;

import com.example.travel.dao.GUser;
import com.example.travel.repository.GUserRepository;
import com.example.travel.service.GUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GUserServiceImpl implements GUserService {
    @Autowired
    private GUserRepository repository;

    @Override
    public GUser create(GUser gUser) {
        return repository.save(gUser);
    }

    @Override
    public String delete(Integer vId) {
        repository.delete(vId);
        return "success";
    }

    @Override
    public GUser update(GUser gUser) {
        return repository.save(gUser);
    }

    @Override
    public GUser find(Integer gId) {
        return repository.findOne(gId);
    }

    @Override
    public String login(String username, String psw) {
        try {
            String password = repository.findGUserByUsername(username).getPassword();
            if (password.equals(psw)) {
                return "login-success";
            }
        }catch(Exception e){
            return "login-failed1";//用户不存在
        }
            return "login-failed2";//密码不正确
    }

    @Override
    public GUser GFind(String username) {
        GUser gUser = new GUser();
        gUser = repository.findGUserByUsername(username);
        gUser.setPassword("******");
        return gUser;
    }
}
