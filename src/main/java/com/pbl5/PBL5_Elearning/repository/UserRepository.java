package com.pbl5.PBL5_Elearning.repository;

import com.pbl5.PBL5_Elearning.entity.Users;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
	public Users findByUsername(String username);

	@Query(nativeQuery = true, value = "call customFindUserById(:id)")
	public List<Map<String, ?>> customFindById(@PathVariable String id);
}
