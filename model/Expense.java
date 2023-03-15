package com.expensetracker.MCT.model;

import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="tbl_expense")
public class Expense {
	@Id
    @Column(name = "expense_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "expense_title")
	private String title;
	
	@Column(name = "expense_price")
	private int price;
	
	@Column(name = "expense_date")
	private Date date;
	
	@Column(name = "expense_user")
	private String userEmail;
	

}
