package com.example.java.repository;

import com.example.java.model.Person;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Transactional: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#transactions
 */

public interface PersonRepository extends JpaRepository<Person, Long> {

    //https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
    List<Person> findByName(String name);

    @Query("select u from Person u where u.status = 1")
    List<Person> findByCustomQuery1();

    @Query("select u from Person u where u.status = 1 and u.email is not NULL")
    List<Person> findByCustomQuery2();

}
