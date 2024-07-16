package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
    Employee findById(int theId);
    //This method will be responsible for updating or adding employee based on the ID == 0 ? Add : Update
    Employee save(Employee theEmployee);
    void deleteById(int theId);


}
