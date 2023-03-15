package com.expensetracker.MCT.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.expensetracker.MCT.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);
	
//	@Query(value = "SELECT * FROM tbl_user WHERE id = :userId", nativeQuery = true)
//	User findByUserId(int userId);

	@Query(value = "SELECT * FROM tbl_user WHERE email = :userEmail", nativeQuery = true)
	User findByUserEmail(String userEmail);

}
