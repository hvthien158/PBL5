package com.pbl5.PBL5_Elearning.repository;

import com.pbl5.PBL5_Elearning.entity.User_Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTestRepository extends JpaRepository<User_Test, Integer> {
}
