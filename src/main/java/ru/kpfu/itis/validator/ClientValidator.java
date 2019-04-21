package ru.kpfu.itis.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.kpfu.itis.forms.ClientForm;
import ru.kpfu.itis.models.Client;
import ru.kpfu.itis.services.client.ClientService;

import java.util.regex.Pattern;

@Component("clientValidator")
public class ClientValidator implements Validator {

    @Autowired
    @Qualifier("clientService")
    private ClientService clientService;

    @Override
    public boolean supports(Class<?> clazz) {
        return ClientForm.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors err) {

        ClientForm clientForm = (ClientForm) obj;
        ValidationUtils.rejectIfEmpty(err, "firstName", "firstName", "Не указано Имя");
        ValidationUtils.rejectIfEmpty(err, "lastName", "lastName", "Фамилия");
        ValidationUtils.rejectIfEmpty(err, "email", "email", "Пустой Логин");
        ValidationUtils.rejectIfEmpty(err, "password", "password", "Пароль");
        ValidationUtils.rejectIfEmpty(err, "consent", "consent", "Согласие");


        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE);
        if (!(pattern.matcher(clientForm.getEmail()).matches())) {
            err.reject("email", "Неправильный логин");
        }
        if (!clientService.emailDoesntExist(clientForm.getEmail())) {
            err.reject("email", "Пользователь с таким логином существует");
        }

    }

}