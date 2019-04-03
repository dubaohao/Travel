package com.example.travel.service.impl;

import com.example.travel.dao.TGroup;
import com.example.travel.repository.TGroupRepository;
import com.example.travel.service.GroupServcice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GroupServiceImpl implements GroupServcice {
    @Autowired
    private TGroupRepository repository;

    @Override
    public String create(TGroup tGroup) {
        repository.save(tGroup);
        return "success";
    }

    @Override
    public String update(TGroup tGroup) {
        repository.save(tGroup);
        return "success";
    }

    @Override
    public TGroup findOne(Integer groupId) {
        return repository.findOne(groupId);
    }

    @Override
    public TGroup findGroupNumber(String groupNumber) {
        return repository.findByGroupNumber(groupNumber);
    }

    @Override
    public Long findGUserGroupNumber(Integer gId) {
        return repository.countByGId(gId);
    }

    @Override
    public List<TGroup> findGUserGroup(Integer gId) {
        return repository.findByGId(gId);
    }

    @Override
    public Page<TGroup> findGUserGroupPage(Integer gId, Pageable pageable) {
        return repository.findByGId(gId,pageable);
    }

    @Override
    public TGroup cancel(TGroup tGroup) {
        return null;
    }

    @Override
    public TGroup finish(TGroup tGroup) {
        return null;
    }

    @Override
    public TGroup paid(TGroup tGroup) {
        return null;
    }
}
