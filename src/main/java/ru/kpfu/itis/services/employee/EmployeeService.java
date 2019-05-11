package ru.kpfu.itis.services.employee;

import ru.kpfu.itis.forms.EmployeeForm;
import ru.kpfu.itis.forms.LoginForm;
import ru.kpfu.itis.models.Employee;
import ru.kpfu.itis.models.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    void signUp(EmployeeForm employeeForm);
    Optional<String> signIn(LoginForm loginForm);
    Long signInBySession(LoginForm loginForm);
    boolean isExistByCookie(String cookieValue);
    Employee getEmployeeByCookie(String cookie);
    Employee getEmployee(HttpServletRequest request);
    void makeChanges(Long employeeId, Boolean is_accepted, Long orderId);
    List<Order> findAllOrders();
    void deleteCookie(String cookie);
}
