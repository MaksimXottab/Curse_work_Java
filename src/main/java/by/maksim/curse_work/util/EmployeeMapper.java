package by.maksim.curse_work.util;

import by.maksim.curse_work.model.Employee;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class EmployeeMapper {
    public static Employee extractFieldsFromURIQueryAndDecode(HttpServletRequest req) {
        Employee employee = new Employee();

        String id = req.getParameter("id"),
                surname = req.getParameter("surname"),
                name = req.getParameter("name"),
                patronymic = req.getParameter("patronymic"),
                jobTitle = req.getParameter("job_title"),
                accessLevel = req.getParameter("access_level"),
                cardCondition = req.getParameter("card_condition");

        if (id != null && !id.isBlank())
            employee.setId(Integer.valueOf(decode(id)));

        if (surname != null && !surname.isBlank())
            employee.setSurname(decode(surname));

        if (name != null && !name.isBlank())
            employee.setName(decode(name));

        if (patronymic != null && !patronymic.isBlank())
            employee.setPatronymic(decode(patronymic));

        if (jobTitle != null && !jobTitle.isBlank())
            employee.setPatronymic(decode(jobTitle));

        if (accessLevel != null && !accessLevel.isBlank())
            employee.setAccessLevel(Integer.valueOf(decode(accessLevel)));

        if (cardCondition != null && !cardCondition.isBlank())
            employee.setCardCondition(decode(cardCondition));

        return employee;
    }

    private static Employee mapResultSetToEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();

        employee.setId(resultSet.getInt("id"));
        employee.setSurname(resultSet.getString("surname"));
        employee.setName(resultSet.getString("name"));
        employee.setPatronymic(resultSet.getString("patronymic"));
        employee.setJobTitle(resultSet.getString("job_title"));
        employee.setAccessLevel(resultSet.getInt("access_level"));
        employee.setCardCondition(resultSet.getString("card_condition"));

        return employee;
    }

    public static List<Employee> extractFieldsFromResultSetList(ResultSet resultSet) {
        List<Employee> employees = new ArrayList<>();
        try {
            while (resultSet.next())
                employees.add(mapResultSetEmployee(resultSet));

            return employees;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static Optional<Employee> extractFieldsFromResultSet(ResultSet resultSet) {
        try {
            if (resultSet.next())
                return Optional.of(mapResultSetEmployee(resultSet));

            return Optional.empty();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return Optional.empty();
        }
    }

    private static Employee mapResultSetEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();

        employee.setId(resultSet.getInt("id"));
        employee.setSurname(resultSet.getString("surname"));
        employee.setName(resultSet.getString("name"));
        employee.setPatronymic(resultSet.getString("patronymic"));
        employee.setJobTitle(resultSet.getString("job_title"));
        employee.setAccessLevel(resultSet.getInt("access_level"));
        employee.setCardCondition(resultSet.getString("card_condition"));

        return employee;
    }

    private static String decode(String encoded) {
        return URLDecoder.decode(encoded, StandardCharsets.UTF_8);
    }
}
