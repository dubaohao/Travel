package com.example.travel.repository;

import com.example.travel.dao.GUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GUserRepository extends JpaRepository<GUser,Integer> {
    GUser findGUserByUsername(String username);
}
