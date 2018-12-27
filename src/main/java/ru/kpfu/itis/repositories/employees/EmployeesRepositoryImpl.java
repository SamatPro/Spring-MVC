package ru.kpfu.itis.repositories.employees;

import lombok.SneakyThrows;
import org.postgresql.util.PSQLException;
import ru.kpfu.itis.mapper.RowMapper;
import ru.kpfu.itis.models.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class EmployeesRepositoryImpl implements EmployeesRepository{

    private Connection connection;

//    private static final String SQL_FIND_ONE_BY_ID_QUERY = "SELECT name FROM city WHERE id = ?";
    private static final String SQL_INSERT_QUERY = "INSERT INTO employee_db(id, login, hash_password, last_name, first_name, middle_name, position) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String DELETE_BY_ID = "DELETE FROM ONLY employee_db WHERE id=?;";
    private static final String SELECT_EMPLOYEE_BY_LOGIN = "SELECT * FROM employee_db WHERE login = ?;";


    public EmployeesRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String findAllByPosition(String position) {
        return null;
    }

    @Override
    @SneakyThrows
    public Optional<Employee> findOneByLogin(String login) {
        PreparedStatement statement = connection.prepareStatement(SELECT_EMPLOYEE_BY_LOGIN);
        statement.setString(1, login);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return Optional.empty();
        }
        return Optional.of(employeeWithoutOrdersRowMapper.rowMap(resultSet));
    }

    @Override
    @SneakyThrows
    public Optional<Employee> findOne(Long id) {

        Statement statement = connection.createStatement();
        ResultSet resultSet =
                statement.executeQuery("SELECT name FROM employee_db WHERE id = " + id);
        resultSet.next();
        return Optional.of(employeeRowMapper.rowMap(resultSet));
    }

    @Override
    @SneakyThrows
    public void save(Employee model) {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setLong(1, model.getId());
        statement.setString(2, model.getLogin());
        statement.setString(3, model.getHashPassword());
        statement.setString(4, model.getLastName());
        statement.setString(5, model.getFirstName());
        statement.setString(6, model.getMiddleName());
        statement.setString(7,model.getPosition());
//        statement.setLong(1, model.getId());
//        statement.setString(2, model.getName());
        //Можем создать в отдельном классе метод заменитель, куда будем передавать statement, по паттерну легковес

        try{
            statement.executeUpdate();
        }catch (PSQLException e){
//            PreparedStatement statement2 = connection.prepareStatement(DELETE_BY_ID);
//            statement2.setLong(1, model.getId());
//            statement2.executeUpdate();
            delete(model.getId());
            statement.executeUpdate();
        }
    }

    @Override
    @SneakyThrows
    public void delete(Long id) {
        PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID);
        statement.setLong(1, id);
        statement.executeUpdate();
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    private RowMapper<Employee> employeeRowMapper = new RowMapper<Employee>() {
        @Override
        @SneakyThrows
        public Employee rowMap(ResultSet resultSet){
            return Employee.builder()
                    .id(resultSet.getLong("id"))
                    .login(resultSet.getString("login"))
                    .hashPassword(resultSet.getString("hash_password"))
                    .firstName(resultSet.getString("first_name"))
                    .lastName(resultSet.getString("last_name"))
                    .middleName(resultSet.getString("middle_name"))
                    .position(resultSet.getString("position"))
                    .build();
        }
    };

    private RowMapper<Employee> employeeWithoutOrdersRowMapper = new RowMapper<Employee>() {
        @Override
        @SneakyThrows
        public Employee rowMap(ResultSet resultSet) {
            return Employee.builder()
                    .login(resultSet.getString("login"))
                    .hashPassword(resultSet.getString("hash_password"))
                    .firstName(resultSet.getString("first_name"))
                    .lastName(resultSet.getString("last_name"))
                    .id(resultSet.getLong("id"))
                    .build();
        }
    };
}
