package com.example.travel.service.impl;

import com.example.travel.dao.VUser;
import com.example.travel.dao.VUser;
import com.example.travel.repository.VUserRepository;
import com.example.travel.service.VUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VUserServiceImpl implements VUserService {
    @Autowired
    private VUserRepository repository;

    @Override
    public VUser create(VUser vUser) {
        return repository.save(vUser);
    }

    @Override
    public String delete(Integer vId) {
        repository.delete(vId);
        return "success";
    }

    @Override
    public VUser update(VUser vUser) {
        return repository.save(vUser);
    }

    @Override
    public VUser find(Integer vId) {
        return repository.findOne(vId);
    }

    @Override
    public String login(String username, String psw) {
        String password1=repository.findVUserByUsername(username).getPassword();
        /**判断数据库password1是否等于输入password*/
        if(password1.equals(psw)){
            return "success";
        }else {
            return "failed";
        }
    }
}
