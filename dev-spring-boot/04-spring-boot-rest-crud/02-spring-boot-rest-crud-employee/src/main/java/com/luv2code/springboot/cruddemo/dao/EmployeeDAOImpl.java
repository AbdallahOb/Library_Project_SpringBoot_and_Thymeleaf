package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{
    // Define field for entitymanager
    private EntityManager entityManager;

    // Set up constructor injection
    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Implement DAI interface methods
    @Override
    public List<Employee> findAll() {
        // Create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee", Employee.class);
        // Execute query and get result list
        List<Employee> employees = theQuery.getResultList();
        // Return the results
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        // Find the employee
        Employee theEmployee = entityManager.find(Employee.class,theId);
        // Return the employee
        return theEmployee;
    }

    //This method will be responsible for updating or adding employee based on the ID == 0 ? Add(insert) : Update
    @Override
    public Employee save(Employee theEmployee) {
        // same employee
        Employee dbEmployee = entityManager.merge(theEmployee);
        // Return the employee
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {
        // Find the employee
        Employee theEmployee = entityManager.find(Employee.class, theId);

        // Delete the employee
        entityManager.remove(theEmployee);
    }

}
