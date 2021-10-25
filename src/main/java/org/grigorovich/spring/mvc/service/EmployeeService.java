package org.grigorovich.spring.mvc.service;


import org.grigorovich.spring.mvc.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees(); //метод называем также как в EmployeeDAO

    void saveEmployee(Employee employee); //метод сохранения работника в базу данных

    Employee getEmployee(int id); //получаем работника по id

    void deleteEmployee(int id);
}
