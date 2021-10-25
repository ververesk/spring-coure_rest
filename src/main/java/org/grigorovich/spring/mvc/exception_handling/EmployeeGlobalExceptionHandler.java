package org.grigorovich.spring.mvc.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeGlobalExceptionHandler { //теперь любой контроллер может пользоваться обработкой исключений
    @ExceptionHandler //метод ответственный за обратботку исключений
    public ResponseEntity<EmployeeIncorrectData> handleException(NoSuchEmployeeException employeeException) {
        EmployeeIncorrectData data=new EmployeeIncorrectData();
        data.setInfo(employeeException.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler //метод ответственный за обратботку исключений
    public ResponseEntity <EmployeeIncorrectData> handleException(Exception exception) {
        EmployeeIncorrectData data=new EmployeeIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

}
