package com.example.java.service;

import com.example.java.model.Person;
import com.example.java.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public List<Person> findByName(String name){
        return repository.findByName(name);
    }

    public List<Person> findByCustomQuery1(){
        return repository.findByCustomQuery1();
    }

    public List<Person> findByCustomQuery2(){
        return repository.findByCustomQuery2();
    }

    public List<Person> findAll(){
        return repository.findAll();
    }

    public List<Person> findAll(Sort sort){
        return repository.findAll(sort);
    }


    public void saveAll(List<Person> personList){
        repository.saveAll(personList);
    }

    public long count(){
        return repository.count();
    }

}
