package by.maksim.curse_work.storage;

import by.maksim.curse_work.model.Employee;
import by.maksim.curse_work.util.EmployeeMapper;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class EmployeeStorage {
    public Employee add(Employee employee) {
        try (Connection connection = getNewConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO employee (surname, name, patronymic, job_title, card_condition) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, employee.getSurname());
            statement.setString(2, employee.getName());
            statement.setString(3, employee.getPatronymic());
            statement.setString(4, employee.getJobTitle());
            statement.setString(5, employee.getCardCondition());

            statement.executeUpdate();
            ResultSet key = statement.getGeneratedKeys();

            if (key.next()) {
                employee.setId(key.getInt(1));
            }
        }  catch (SQLException exception) {
            exception.printStackTrace();
        }

        return employee;

    }
    public Employee update(Employee employee) {
        StringBuilder query = new StringBuilder("UPDATE employee SET ");
        List<Object> values = new ArrayList<>();

        if (employee.getSurname() != null) {
            query.append("surname = ?, ");
            values.add(employee.getSurname());
        }

        if (employee.getName() != null) {
            query.append("name = ?, ");
            values.add(employee.getName());
        }

        if (employee.getPatronymic() != null) {
            query.append("patronymic = ?, ");
            values.add(employee.getPatronymic());
        }

        if (employee.getJobTitle() != null) {
            query.append("job_title = ?, ");
            values.add(employee.getJobTitle());
        }

        if (employee.getAccessLevel() != null) {
            query.append("access_level = ?, ");
            values.add(employee.getAccessLevel());
        }

        if (values.isEmpty()) {
            return employee;
        }

        query.delete(query.length() - 2, query.length());
        query.append(" WHERE id = ?");
        values.add(employee.getId());

        try (Connection connection = getNewConnection();
             PreparedStatement statement = connection.prepareStatement(query.toString())) {
            for (int i = 0; i < values.size(); i++) {
                statement.setObject(i + 1, values.get(i));
            }
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return employee;
    }
    public List<Employee> findAll(Employee employee) {
        StringBuilder query = new StringBuilder("SELECT * FROM employee ");
        List<Object> values = new ArrayList<>();

        boolean whereFound = false;

        if (employee.getSurname() != null && !employee.getSurname().isBlank()) {
            query.append(whereFound ? " AND surname = ?" : "WHERE name = ?");
            values.add(employee.getSurname());
            whereFound = true;
        }

        if (employee.getName() != null && !employee.getName().isBlank()) {
            query.append(whereFound ? " AND name = ?" : "WHERE name = ?");
            values.add(employee.getName());
            whereFound = true;
        }

        if (employee.getPatronymic() != null && !employee.getPatronymic().isBlank()) {
            query.append(whereFound ? " AND patronymic = ?" : "WHERE patronymic = ?");
            values.add(employee.getPatronymic());
            whereFound = true;
        }

        if (employee.getJobTitle() != null && !employee.getJobTitle().isBlank()) {
            query.append(whereFound ? " AND job_title = ?" : "WHERE job_title = ?");
            values.add(employee.getJobTitle());
            whereFound = true;
        }

        if (employee.getAccessLevel() != null) {
            query.append(whereFound ? " AND access_level = ?" : "WHERE access_level = ?");
            values.add(employee.getAccessLevel());
            whereFound = true;
        }

        try (Connection connection = getNewConnection();
             PreparedStatement statement = connection.prepareStatement(query.toString())) {
            for (int i = 0; i < values.size(); i++) {
                statement.setObject(i + 1, values.get(i));
            }
            ResultSet resultSet = statement.executeQuery();
            return new ArrayList<Employee>(EmployeeMapper.extractFieldsFromResultSetList(resultSet));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return Collections.emptyList();
    }

    public Employee turn(Employee employee) {
        try (Connection connection = getNewConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE devices SET condition = ? WHERE id = ?")) {
            statement.setString(1, employee.getCardCondition());
            statement.setInt(2, employee.getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return employee;
    }
    public Optional<Employee> findById(Integer id) {
        Optional<Employee> optionalEmployee = null;

       try (Connection connection = getNewConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM train WHERE id = ?")) {
           statement.setLong(1, id);
           ResultSet resultSet = statement.executeQuery();
           optionalEmployee = EmployeeMapper.extractFieldsFromResultSet(resultSet);
       } catch (SQLException exception) {
           exception.printStackTrace();
       }

       return optionalEmployee;
    }
    public void deleteById(Integer id) {
        try (Connection connection = getNewConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM employees WHERE id = ?")) {
        statement.setLong(1, id);
        statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    protected Connection getNewConnection() throws SQLException {
        return getDataSource().getConnection();
    }
    protected DataSource getDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setPortNumbers(new int[]{5432});
        dataSource.setDatabaseName("curse_work");
        dataSource.setUser("maksim");
        dataSource.setPassword("admin");
        return dataSource;
    }
}
