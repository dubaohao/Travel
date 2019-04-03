package com.example.travel.repository;

import com.example.travel.dao.Hodometer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HodometerRepository extends JpaRepository<Hodometer,Integer> {
    List<Hodometer> findByGId(Integer gId);
    Page<Hodometer> findByGId(Integer gId, Pageable pageable);
    Page<Hodometer> findByGIdAndStatus(Integer gId,String status, Pageable pageable);
    Long countBygId(Integer gId);
    List<Hodometer> findByHoIdIn(List<Integer> hodometerList);
}
