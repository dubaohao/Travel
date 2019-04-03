package com.example.travel.repository;

import com.example.travel.dao.TGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TGroupRepository extends JpaRepository<TGroup,Integer> {
    List<TGroup> findByGId(Integer id);
    Page<TGroup> findByGId(Integer id, Pageable pageable);
    Long countByGId(Integer gId);
    TGroup findByGroupNumber(String groupNumber);
}
