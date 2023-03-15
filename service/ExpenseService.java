package com.expensetracker.MCT.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expensetracker.MCT.model.Expense;
import com.expensetracker.MCT.repository.ExpenseRepository;

@Service
public class ExpenseService {
	@Autowired
	ExpenseRepository expenseRepo;
	
	//create expense
	public Expense addExpense(Expense expense) {
		return expenseRepo.save(expense);
	}
	
	//get all expense by userId
	public List<Expense> getAllExpense(String email){
		return expenseRepo.findAllByUserEmail(email);
	}
	
	//delete by id
	public void deleteById(int userId,int expenseId) {
		expenseRepo.deleteByUserIdExpenseId(userId,expenseId);
	}
	
	//update by id
	public void updateById(Expense expense) {
		expenseRepo.save(expense);
	}

	public void deleteByEmail(String userEmail, int expId) {
		// TODO Auto-generated method stub
		expenseRepo.deleteByUserEmail(userEmail,expId);
	}
	
	//total expenditure of given month
	public int totalExpenditure(String userEmail,Date month) {
		return expenseRepo.findExpediture(userEmail,month);
	}

}
