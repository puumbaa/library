package ru.orion.library.forms;

import lombok.Data;

import java.time.LocalDate;
@Data
public class AccountForm {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String login;
    private String password;

}
