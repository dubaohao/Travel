package com.example.travel.repository;

import com.example.travel.dao.TOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TOrderRepository extends JpaRepository<TOrder,Integer> {
    Page<TOrder> findByGId(Integer orderId, Pageable pageable);
    List<TOrder> findByVId(Integer vId);
    Long countByGId(Integer gId);
    List<TOrder> findByGId(Integer gId);
    List<TOrder> findByGroupNumber(String  groupNumber);
    TOrder findByOrderNumber(String orderNumber);

}
