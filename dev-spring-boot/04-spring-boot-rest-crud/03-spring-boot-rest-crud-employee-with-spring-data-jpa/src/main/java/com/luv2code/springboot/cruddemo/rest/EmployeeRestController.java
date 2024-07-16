package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    // Define a field for EmployeeDAO
    private EmployeeService employeeService;

    // Quick and dirty: Inject employee dao (use constructor injection)
    public EmployeeRestController(EmployeeService theEmployeeService){
        this.employeeService = theEmployeeService;
    }

    // Expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    // Expose "/employees/{employeeId}" and return a single employee
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        // Retrieve the employee using the employeeId
        Employee theEmployee = employeeService.findById(employeeId);
        // Exception handling
        if(theEmployee == null){
            throw new RuntimeException("Employee id not found - "+employeeId);
        }
        // Returning the found employee
        return theEmployee;
    }

    // Add mapping for POST /employee - add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        // Also just in case they pass an id in JSON ... set id to 0
        // This is to force a save of new employee
        theEmployee.setId(0);
        // Add the employee to the database
        Employee dbEmployee = employeeService.save(theEmployee);
        // return the added employee
        return dbEmployee;
    }


    // Add mapping for PUT /employees - update existing employee
    @PutMapping("/employees")
    public Employee epdateEmployee(@RequestBody Employee theEmployee){
        // update the employee
        Employee dbEmployee = employeeService.save(theEmployee);
        // returning the updated employee
        return dbEmployee;
    }

    // Add mapping for DELETE /employee/{employeeId} - deleting an employee using its id
    @DeleteMapping("/employees/{employeeId}")
    public Employee deleteEmployee(@PathVariable int employeeId){
        // Find the employee
        Employee theEmployee = employeeService.findById(employeeId);
        // exception handling
        if(theEmployee == null){
            throw new RuntimeException("Employee id not found - "+employeeId);
        }
        // deleting the employee if exists
        employeeService.deleteById(employeeId);

        // returning the deleted employee
        return theEmployee;
    }














}
