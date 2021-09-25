package com.example.java.dao;

import com.example.java.entity.Person;

import java.util.List;

public interface PersonDAO {

    public List<Person> findAll();

    public Person findById(long id);

    public Person save(Person person);

    public long count();

}
