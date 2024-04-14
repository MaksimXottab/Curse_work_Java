package by.maksim.curse_work.service;

import by.maksim.curse_work.exception.EntityNotFoundException;
import by.maksim.curse_work.model.Employee;
import by.maksim.curse_work.storage.EmployeeStorage;
import by.maksim.curse_work.util.EmployeeMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class EmployeeService {
    private final EmployeeStorage employeeStorage;

    public EmployeeService() {
        employeeStorage = new EmployeeStorage();
    }

    public Employee create(HttpServletRequest request) {
        Employee employee = EmployeeMapper.extractFieldsFromURIQueryAndDecode(request);
        employee = employeeStorage.add(employee);

        return employee;
    }

    public Employee change(HttpServletRequest request) {
        Employee employee = EmployeeMapper.extractFieldsFromURIQueryAndDecode(request);
        employeeStorage.update(employee);
        return checkIfEmployeeExists(employee.getId());
    }

    public Employee getById(Integer id) {
        return checkIfEmployeeExists(id);
    }

    public List<Employee> getALL(HttpServletRequest request) {
        Employee employee = EmployeeMapper.extractFieldsFromURIQueryAndDecode(request);
        List<Employee> employees = employeeStorage.findAll(employee);

        if (employees.isEmpty())
            throw new EntityNotFoundException(("Не найдено ни одного сотрудника"));

        return employees;
    }

    public Employee turn(HttpServletRequest request) {
        Employee employee = EmployeeMapper.extractFieldsFromURIQueryAndDecode(request);
        employeeStorage.turn(employee);

        return checkIfEmployeeExists(employee.getId());
    }
    public void deleteById(Integer id) {
        checkIfEmployeeExists(id);
        employeeStorage.deleteById(id);
    }

    private Employee checkIfEmployeeExists(Integer id) {
        return employeeStorage.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Сотрудник с id = " + id + " не найдено"));
    }
}
