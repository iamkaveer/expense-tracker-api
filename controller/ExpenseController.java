package com.expensetracker.MCT.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.expensetracker.MCT.model.Expense;
import com.expensetracker.MCT.model.User;
import com.expensetracker.MCT.repository.ExpenseRepository;
import com.expensetracker.MCT.repository.UserRepository;
import com.expensetracker.MCT.service.ExpenseService;

@RestController
public class ExpenseController {
	@Autowired
	ExpenseService expenseService;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ExpenseRepository expenseRepo;
	
	//create expense
	@PostMapping("/add-expense/email/{userEmail}")
	public ResponseEntity<String> addExpense(@RequestBody Expense expense, @PathVariable String userEmail){
		User user = userRepo.findByUserEmail(userEmail);
		if(user != null) {
			expenseService.addExpense(expense);
			return ResponseEntity.status(HttpStatus.CREATED).body("Expense created successfully");
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("user does not exist");
		
	}
	
	//find all by user id
	@GetMapping("/find-all/userEmail/{email}")
	public ResponseEntity<List<Expense>> findByUserId(@PathVariable String email){
		List<Expense> expenseList = expenseService.getAllExpense(email);
		if(expenseList != null) {
			return ResponseEntity.ok(expenseList);
		}
		return ResponseEntity.notFound().build();
	}
	
	//delete by user id and expense id
	@DeleteMapping("/delete-by-email/userEmail/{userEmail}/expense-id/{expId}")
	public ResponseEntity<String> deleteExpense(@PathVariable String userEmail, @PathVariable int expId){
		User user = userRepo.findByUserEmail(userEmail);
		Expense expense = expenseRepo.findByExpenseId(expId);
		if(user != null && expense != null) {
			expenseService.deleteByEmail(userEmail, expId);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("deleted");
		}else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("user email or expense id does not exist");
		}
		
	}
	//update 
	@PutMapping("update/user-email/{userEmail}/expense-id/{expId}")
	public ResponseEntity<String> deleteExpense(@RequestBody Expense newExpense, @PathVariable String userEmail, @PathVariable int expId){
		User user = userRepo.findByUserEmail(userEmail);
		Expense expense = expenseRepo.findByExpenseId(expId);
		if(user != null && expense != null) {
			expense.setId(newExpense.getId());
			expense.setTitle(newExpense.getTitle());
			expense.setDate(newExpense.getDate());
			expense.setUserEmail(newExpense.getUserEmail());
			expense.setPrice(newExpense.getPrice());
			expenseService.updateById(expense);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("updated");
		}else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("user or expense id does not exist");
		}
	}
	
	//find expenditure
	
	@GetMapping("/find-expense-by-month/userEmail/{userEmail}/month/{date}")
	public String findByMonth(@PathVariable String userEmail, @PathVariable Date date){
		User user = userRepo.findByUserEmail(userEmail);
		Integer total = expenseService.totalExpenditure(userEmail, date);
		if(user != null && total > 0) {
			return total.toString();
		}
		return "no data found";
	}
	
	

}
