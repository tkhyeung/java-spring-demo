package com.example.java.service;

import com.example.java.entity.Person;

import java.util.List;

public interface PersonService {

    public List<Person> findAll();

    public Person findById(long id);

    public Person findByIdWithAddress(long id);

    public Person save(Person person);

    public List<Person> saveAll(List<Person> personList);

    public long count();

    public void delete(long id);

}
