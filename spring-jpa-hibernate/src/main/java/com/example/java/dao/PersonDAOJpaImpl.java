package com.example.java.dao;

import com.example.java.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

@Repository
public class PersonDAOJpaImpl implements PersonDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Person> findAll() {
        Query theQuery =
                entityManager.createQuery("from Person");
        return theQuery.getResultList();
    }

    @Override
    public Person findById(long id) {
        return entityManager.find(Person.class, id);
    }

    @Override
    public Person save(Person person) {
        // save or update
        Person savedPerson = entityManager.merge(person);
        person.setId(savedPerson.getId());
        return person;
    }

    @Override
    public long count() {
        BigInteger bigInteger = (BigInteger) entityManager.createNativeQuery("SELECT COUNT(*) FROM PERSON")
                .getSingleResult();
        return bigInteger.longValue();
    }
}
