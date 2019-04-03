package com.example.travel.service.impl;

import com.example.travel.dao.Hodometer;
import com.example.travel.repository.HodometerRepository;
import com.example.travel.service.HodometerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HodometerServiceImpl implements HodometerService {
    @Autowired
    private HodometerRepository repository;

    @Override
    public Hodometer create(Hodometer hodometer) {
        return repository.save(hodometer);
    }

    @Override
    public String delete(Integer hoId) {
        repository.delete(hoId);
        return "success";
    }

    @Override
    public Hodometer update(Hodometer hodometer) {
        return repository.save(hodometer);
    }

    @Override
    public Hodometer findOne(Integer hoId) {
        return repository.findOne(hoId);
    }

    @Override
    public Long findHodoNumber(Integer gId) {
        return repository.countBygId(gId);
    }

    @Override
    public List<Hodometer> findList(Integer gId) {
        return repository.findByGId(gId);
    }

    @Override
    public Page<Hodometer> findListPage(Integer gId, Pageable pageable) {
        return repository.findByGId(gId,pageable);
    }


    @Override
    public Page<Hodometer> findUpList(Integer gId, String status, Pageable pageable) {
        return repository.findByGIdAndStatus(gId,status,pageable);
    }

    @Override
    public Page<Hodometer> findDownList(Integer gId, String status, Pageable pageable) {
        System.out.println(repository.findByGIdAndStatus(gId,status,pageable));
        return repository.findByGIdAndStatus(gId,status,pageable);
    }

    @Override//未使用，controller直接使用了update
    public Hodometer updatestatus(Integer gId, String status) {
        return null;
    }

    @Override
    public List<Hodometer> findHoIdByGIdIn(List<Integer> hodometerList) {
        return repository.findByHoIdIn(hodometerList);
    }
}
