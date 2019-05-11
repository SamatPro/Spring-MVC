package ru.kpfu.itis.services.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.forms.EmployeeForm;
import ru.kpfu.itis.forms.LoginForm;
import ru.kpfu.itis.models.Auth;
import ru.kpfu.itis.models.Employee;
import ru.kpfu.itis.models.Order;
import ru.kpfu.itis.repositories.auths.AuthRepository;
import ru.kpfu.itis.repositories.employees.EmployeesRepository;
import ru.kpfu.itis.repositories.orders.OrdersRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Component("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    @Qualifier("employeesRepositoryJdbcTemplateImpl")
    private EmployeesRepository employeesRepository;
    @Autowired
    @Qualifier("authRepositoryJdbcTemplateImpl")
    private AuthRepository authRepository;
    @Autowired
    @Qualifier("ordersRepositoryJdbcTemplateImpl")
    private OrdersRepository ordersRepository;
    private PasswordEncoder encoder;


    public EmployeeServiceImpl() {
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public void signUp(EmployeeForm employeeForm) {
        Employee employee = Employee.builder()
                .login(employeeForm.getLogin())
                .hashPassword(encoder.encode(employeeForm.getPassword()))
                .firstName(employeeForm.getFirstName())
                .lastName(employeeForm.getLastName())
                .middleName(employeeForm.getMiddleName())
                .position(employeeForm.getPosition())
                .build();
        employeesRepository.save(employee);
    }

    @Override
    public Optional<String> signIn(LoginForm loginForm) {
        Optional<Employee> employeeOptional = employeesRepository.findOneByLogin(loginForm.getEmail());
        if(employeeOptional.isPresent()){
            Employee employee = employeeOptional.get();
            if(encoder.matches(loginForm.getPassword(), employee.getHashPassword())){
                String cookieValue = UUID.randomUUID().toString();

                Auth auth = Auth.builder()
                        .employee(employee)
                        .cookieValue(cookieValue)
                        .build();
                authRepository.saveEmployee(auth);
                return Optional.of(cookieValue);

            }

        }
        return Optional.empty();
    }

    @Override
    public Long signInBySession(LoginForm loginForm) {
        Optional<Employee> employeeOptional = employeesRepository.findOneByLogin(loginForm.getEmail());
        if(employeeOptional.isPresent()){
            Employee employee = employeeOptional.get();
            if(encoder.matches(loginForm.getPassword(), employee.getHashPassword())){
                return employee.getId();
            }

        }
        return null;
    }

    @Override
    public boolean isExistByCookie(String cookieValue) {
        if(!authRepository.findByCookieValueEmployee(cookieValue).equals(Optional.empty())){
            return true;
        }
        return false;
    }

    @Override
    public Employee getEmployeeByCookie(String cookie) {
        return employeesRepository.findEmployeeByCookie(cookie).get();
    }

    @Override
    public Employee getEmployee(HttpServletRequest request) {
        Optional<Employee> employeeOptional;
        Employee employee = null;
        Long employeeId = null;

        Cookie cookies[] = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("auth")) {
                    if (isExistByCookie(cookie.getValue())) {
                        employeeOptional = employeesRepository.findEmployeeByCookie(cookie.getValue());
                        employee = employeeOptional.get();
                        employeeId = employee.getId();
                    }
                    else {
                        if (request.getSession().getAttribute("employee")!=null) {
                            employeeId = (Long) request.getSession().getAttribute("employee");
                            employeeOptional = employeesRepository.findOne(employeeId);
                            employee = employeeOptional.get();
                        }
                    }
                }else {
                    if (request.getSession().getAttribute("employee")!=null) {
                        employeeId = (Long) request.getSession().getAttribute("employee");
                        employeeOptional = employeesRepository.findOne(employeeId);
                        employee = employeeOptional.get();
                    }
                }
            }
        }

        else {
            if (request.getSession().getAttribute("employee")!=null) {
                employeeId = (Long) request.getSession().getAttribute("employee");
                employeeOptional = employeesRepository.findOne(employeeId);
                employee = employeeOptional.get();
            }
        }
        return employee;
    }

    @Override
    public void makeChanges(Long employeeId, Boolean is_accepted, Long orderId) {
        ordersRepository.makeChanges(employeeId, is_accepted, orderId);
    }

    @Override
    public List<Order> findAllOrders() {
        return ordersRepository.findAll();
    }

    @Override
    public void deleteCookie(String cookie) {
        authRepository.deleteCookie(cookie);
    }
}
