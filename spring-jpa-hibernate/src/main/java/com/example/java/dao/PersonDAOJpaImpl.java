package com.example.java.dao;

import com.example.java.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.util.List;

@Repository
@Slf4j
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
    public Person findByIdWithAddress(long id) {
        TypedQuery<Person> query =  entityManager.createQuery("SELECT DISTINCT p from Person p " +
                "left join fetch p.addresses a" + " " +
                "where " + " " +
                " p.id = :id", Person.class);
        query.setParameter("id", id);

//        List<Person> result = query.getResultList();
//        if(result.size() != 1) return null;
//        return result.get(0);
        query.setFirstResult(0);
        query.setMaxResults(1);
        return query.getSingleResult();
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

    @Override
    public void delete(long id) {
        entityManager.remove(findById(id));
    }
}
