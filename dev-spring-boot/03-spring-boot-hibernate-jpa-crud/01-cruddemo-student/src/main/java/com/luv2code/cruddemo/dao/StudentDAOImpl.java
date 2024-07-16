package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{
    ////////////////////////////////////////////////////////////
    // Define field for entity manager
    private EntityManager entityManager;
    ////////////////////////////////////////////////////////////
    // Inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    ////////////////////////////////////////////////////////////
    // Implement save method (Create)
    @Override
    @Transactional // Be careful about using spring version of this annotation
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }


    // Implement findById method (Read)
    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }


    // Implement findAll method (Read multiple records)
    @Override
    public List<Student> findAll() {
        /*
        *   To accomplish this we will use JPA Query Language (JPQL)
        *   This language can be used to query MySQL syntax or queries like WHERE, OR, LIKE, JOIN, etc.
        *   all fields and entity names used here are all of are the JPA entities not the db names
        *   Student (Class) not student (DB Table name)
        * */

        // Create query
        TypedQuery<Student> theQuery= entityManager.createQuery("FROM Student order by lastName", Student.class);
        // Return query results
        return theQuery.getResultList();
    }


    // Implement findByLastName method (Read multiple records)
    @Override
    public List<Student> findByLastName(String theLastName) {
        // Create query
        TypedQuery<Student> theQuery= entityManager.createQuery("FROM Student WHERE lastName=:name", Student.class);
        // Set query parameters
        theQuery.setParameter("name",theLastName);
        // Return query results
        return theQuery.getResultList();
    }


    // Implement update() method
    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }


    // Implement delete() method
    @Override
    @Transactional
    public void delete(Integer id) {
        // Retrieve the student based on id
        Student myStudent = entityManager.find(Student.class,id);
        // Delete the student
        entityManager.remove(myStudent);
    }


    // Implement deleteAll() method
    @Override
    @Transactional
    public int deleteAll() {
        int numRowsDeleted= entityManager.createQuery("DELETE FROM Student ").executeUpdate();
        return numRowsDeleted;
    }
    ////////////////////////////////////////////////////////////
}
