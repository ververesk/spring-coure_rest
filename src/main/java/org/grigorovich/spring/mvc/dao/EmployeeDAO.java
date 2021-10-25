package org.grigorovich.spring.mvc.dao;


import org.grigorovich.spring.mvc.entity.Employee;


import java.util.List;

public interface EmployeeDAO {
    List<Employee> getAllEmployees();

    void saveEmployee(Employee employee);

    Employee getEmployee(int id);

    void deleteEmployee(int id);
}
