package com.example.java.service;

import com.example.java.dao.PersonDAO;
import com.example.java.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    private PersonDAO personDAO;

    @Override
    @Transactional
    public List<Person> findAll() {
        return personDAO.findAll();
    }

    @Override
    @Transactional
    public Person findById(long id) {
        return personDAO.findById(id);
    }

    @Override
    @Transactional
    public Person save(Person person) {
        return personDAO.save(person);
    }

    @Override
    @Transactional
    public List<Person> saveAll(List<Person> personList) {
        return personList.stream().map(p -> p = save(p)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return personDAO.count();
    }
}
