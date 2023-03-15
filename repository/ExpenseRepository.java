package com.expensetracker.MCT.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.expensetracker.MCT.model.Expense;

import jakarta.transaction.Transactional;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

	List<Expense> findAllById(int userId);
	
	@Query(value = "SELECT * FROM tbl_expense WHERE expense_user = :userId", nativeQuery = true)
	List<Expense> findAllByUserId(int userId);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM tbl_expense WHERE expense_id = :expenseId AND expense_user = :userId", nativeQuery = true)
	void deleteByUserIdExpenseId(int userId, int expenseId);

	@Query(value = "SELECT * FROM tbl_expense WHERE expense_id = :expId", nativeQuery = true)
	Expense findByExpenseId(int expId);

	@Modifying
	@Transactional
	@Query(value = "UPDATE tbl_expense WHERE expense_id = :expenseId AND expense_user = :userId", nativeQuery = true)
	void updateByUserIdExpenseId(int userId, int expenseId);

	
	@Query(value = "SELECT * FROM tbl_expense WHERE expense_user = :email", nativeQuery = true)
	List<Expense> findAllByUserEmail(String email);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM tbl_expense WHERE expense_id = :expenseId AND expense_user = :userEmail", nativeQuery = true)
	void deleteByUserEmail(String userEmail, int expenseId);

	@Query(value = "Select sum(expense_price) from tbl_expense where expense_user = :userEmail group by month(:month_no)",nativeQuery = true)
	int findExpediture(String userEmail, Date month_no);

}
