package com.example.travel.repository;

import com.example.travel.dao.GUser;
import com.example.travel.dao.VUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VUserRepository extends JpaRepository<VUser,Integer> {
    VUser findVUserByUsername(String username);
}
