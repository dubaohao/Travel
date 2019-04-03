package com.example.travel.repository;

import com.example.travel.dao.Demand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandRepository extends JpaRepository<Demand,Integer> {
}
