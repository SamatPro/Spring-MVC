package ru.kpfu.itis.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.kpfu.itis.forms.LoginForm;
import ru.kpfu.itis.services.client.ClientService;
import ru.kpfu.itis.services.employee.EmployeeService;

import java.util.regex.Pattern;

@Component("loginValidator")
public class LoginValidator implements Validator {

  @Autowired
  @Qualifier("clientService")
  private ClientService clientService;

  @Autowired
  @Qualifier("employeeService")
  private EmployeeService employeeService;

  @Override
  public boolean supports(Class<?> clazz) {
    return LoginForm.class.equals(clazz);
  }

  @Override
  public void validate(Object obj, Errors err) {

    LoginForm form = (LoginForm) obj;

    ValidationUtils.rejectIfEmpty(err, "email", "email", "Не указан логин");
    ValidationUtils.rejectIfEmpty(err, "password", "password", "Не указан пароль");


  }

}