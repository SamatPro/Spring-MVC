package ru.kpfu.itis.repositories.employees;

import ru.kpfu.itis.models.Client;
import ru.kpfu.itis.models.Employee;
import ru.kpfu.itis.repositories.CrudRepository;

import java.util.Optional;

public interface EmployeesRepository extends CrudRepository<Employee> {
    Optional<Employee> findOneByLogin(String login);
    Optional<Employee> findEmployeeByCookie(String cookieValue);


}
