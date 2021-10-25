package org.grigorovich.spring.mvc.dao;


import org.grigorovich.spring.mvc.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;
//должен иметь доступ к sessionFactory
 //тоде самое что и Component
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Employee> getAllEmployees() {
        Session session=sessionFactory.getCurrentSession();
        List<Employee> allEmployees=session.createQuery("from Employee", Employee.class).getResultList(); //пишем не название таблицы а название класса entity для таблицы
        return allEmployees;
    }

    @Override
    public void saveEmployee(Employee employee) {
        Session session=sessionFactory.getCurrentSession();
        session.saveOrUpdate(employee); //если id = 0 то есть мы добавляем нового работника, то в таблицу insert, а если мы изменяем сущ работника то в таблицу insert
    }

    @Override
    public Employee getEmployee(int id) {
        Session session=sessionFactory.getCurrentSession();
        Employee employee=session.get(Employee.class, id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        Session session=sessionFactory.getCurrentSession();
        Query<Employee> query=session.createQuery("delete from Employee where id=:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
