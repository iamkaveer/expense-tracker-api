package com.expensetracker.MCT.Dto;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @Email(message = "Invalid email")
    @Column(unique = true)
    private String email;
    @Size(min = 6, max = 30, message = "Password should be between 6 and 30 characters")
    private String password;

    // getters and setters
}
