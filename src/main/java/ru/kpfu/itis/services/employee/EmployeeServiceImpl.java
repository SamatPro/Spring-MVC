package ru.kpfu.itis.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kpfu.itis.forms.EmployeeForm;
import ru.kpfu.itis.forms.LoginForm;
import ru.kpfu.itis.models.Employee;
import ru.kpfu.itis.repositories.AuthRepository;
import ru.kpfu.itis.repositories.employees.EmployeesRepository;

import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeesRepository employeesRepository;
    private AuthRepository authRepository;
    private PasswordEncoder encoder;


    public EmployeeServiceImpl(EmployeesRepository employeesRepository, AuthRepository authRepository) {
        this.employeesRepository = employeesRepository;
        this.encoder = new BCryptPasswordEncoder();
        this.authRepository = authRepository;
    }

    @Override
    public void signUp(EmployeeForm employeeForm) {
        System.out.println(encoder.encode(employeeForm.getPassword()));
        Employee employee = Employee.builder()
                .login(employeeForm.getEmail())
                .hashPassword(encoder.encode(employeeForm.getPassword()))
                .firstName(employeeForm.getFirstName())
                .lastName(employeeForm.getLastName())
                .middleName("")
                .position("")
                .build();
        employeesRepository.save(employee);
    }

    @Override
    public void signIn(LoginForm loginForm) {
        Optional<Employee> employeeOptional = employeesRepository.findOneByLogin(loginForm.getEmail());
        if(employeeOptional.isPresent()){
            Employee employee = employeeOptional.get();
            if(!encoder.matches(loginForm.getPassword(), employee.getHashPassword())){
                throw new IllegalArgumentException("Wrong password or login");
            }
        }else{
            throw new IllegalArgumentException("Wrong password or login");
        }

    }
}
