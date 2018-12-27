package ru.kpfu.itis.services;

import ru.kpfu.itis.forms.EmployeeForm;
import ru.kpfu.itis.forms.LoginForm;

public interface EmployeeService {
    void signUp(EmployeeForm employeeForm);
    void signIn(LoginForm loginForm);
}
