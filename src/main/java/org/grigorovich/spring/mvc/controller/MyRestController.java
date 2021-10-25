package org.grigorovich.spring.mvc.controller;

import org.grigorovich.spring.mvc.entity.Employee;
import org.grigorovich.spring.mvc.exception_handling.EmployeeIncorrectData;
import org.grigorovich.spring.mvc.exception_handling.NoSuchEmployeeException;
import org.grigorovich.spring.mvc.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }

    @GetMapping("/employees/{id}") //получаем работника по id
    public Employee getEmployee(@PathVariable int id) { //@PathVariable используется для получения значения переменной из адреса запроса
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            throw new NoSuchEmployeeException("There is no Employee with id=" + id + " in DataBase");
        }
        return employee;
    }
    @PostMapping("/employees") //для добавдения нового работника используется метод пост, потому что в теле будет передаваться инф о новом работнике
    public Employee addNewEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }
    @PutMapping("/employees") //для изменения нового работника используется метод put, потому что в теле будет передаваться инф о новом работнике
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }
    @DeleteMapping ("/employees/{id}") //@DeleteMapping связывает http запрос, использующий http метод delete c методом контроллера
    public String deleteEmployee(@PathVariable int id) {
        Employee employee=employeeService.getEmployee(id);
        if(employee==null) {
            throw new NoSuchEmployeeException("There is no Employee with id=" + id + " in DataBase");
        }
        employeeService.deleteEmployee(id);
        return "Employee with id="+id+" was deleted";
    }

}
