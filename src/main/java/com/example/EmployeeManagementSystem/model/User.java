package com.example.EmployeeManagementSystem.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Size(min = 2, max=30)
    private String firstName;

    @NotNull
    @Size(min = 2, max=30)
    private String lastName;

    @Email(message = "Email is not valid", regexp= "^(?=.{1,64}@)[a-z0-9_-]*@[a-z]*(\\.[a-z]{2,})$")
    @NotEmpty(message = "Email cannot be empty")
    private String email;
}



