package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // that's it ... no need to write any code LOL!
    // Using this you have get all the crud operations without DAO and DAOImpl
}


/*
*   VIP Note:
*   When you use JpaRepository the spring boot will automatically search for a JpaRepository and give you
*   REST Controller for that JpaRepository so you dont need to specify any services, DAO, or controller
*
*   Note: You need to add this dependency to your pom.xml
*   <!-- Add dependency for spring data REST -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-rest</artifactId>
		</dependency>
*
*   Note: No Request mapping using this way just do that
*   [localhost:8080/employees] make sure you use the plural form of the entity type
*
*   Note: You can customize the request path check application.properties
*
*   Note: Using spring Data REST passing the ID in the request URL not in the JSON body
*
*   Note: To customize the path of the default endpoint you need to use @RepositoryRestResource(path="endpoint Name") at the Repository class
*   Example: you dont need /employees you need /members -> @RepositoryRestResource(path="members")
*
*   spring Data Rest also do automatic pagination so by default returns page [ size = 20 ]
*   Example if you have 50 members
*   localhost:8080/magic-api/members?page=0 -> [1-20]
*   localhost:8080/magic-api/members?page=1 -> [21-40]
*   etc ...
*
*
*   You can customize those properties - spring Data REST properties
*   spring.data.rest.base-path
*   spring.data.rest.default-page-size
*   spring.data.rest.max-page-size
*   etc ... visit [www.luv2code/spring-boot-props] -> spring.data.rest.*
*
*   Note: For sorting check lec 136
*
*  */