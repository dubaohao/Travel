package com.example.travel.service.impl;

import com.example.travel.dao.TOrder;
import com.example.travel.repository.TOrderRepository;
import com.example.travel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private TOrderRepository repository;

    @Override
    public TOrder create(TOrder tOrder) {
        return repository.save(tOrder);
    }

    @Override
    public TOrder update(TOrder tOrder) {
        return repository.save(tOrder);
    }

    @Override
    public TOrder findOne(Integer orderId) {
        return repository.findOne(orderId);
    }

    @Override
    public TOrder findOrderNumber(String orderNumber) {
        return repository.findByOrderNumber(orderNumber);
    }

    @Override
    public List<TOrder> findVUserOrder(Integer vId) {
        return repository.findByVId(vId);
    }

    @Override
    public Long findGUserOrderNumber(Integer gId) {
        return repository.countByGId(gId);
    }

    @Override
    public List<TOrder> findGUserOrder(Integer gId) {
        return repository.findByGId(gId);
    }

    @Override
    public Page<TOrder> findGUserOrderPage(Integer gId, Pageable pageable) {
        return repository.findByGId(gId,pageable);
    }


//    @Override
//    public Page<TOrder> findGOrder(Integer gId, Pageable pageable) {
//        Page<TOrder> order1=repository.findByGId(gId,pageable);
//        return null;
//    }

    @Override
    public List<TOrder> findGroupOrder(String groupNumber) {
        return repository.findByGroupNumber(groupNumber);
    }

    @Override
    public TOrder cancel(TOrder tOrder) {
        return repository.save(tOrder);
    }

    @Override
    public TOrder finish(TOrder tOrder) {
        return repository.save(tOrder);
    }

    @Override
    public TOrder paid(TOrder tOrder) {
        return repository.save(tOrder);
    }
}
