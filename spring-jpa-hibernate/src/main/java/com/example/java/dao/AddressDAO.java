package com.example.java.dao;

import com.example.java.entity.Address;
import com.example.java.entity.Person;

import java.util.List;

public interface AddressDAO {

    public List<Address> findAll();

    public Address findById(long id);

    public Address save(Address address);

    public long count();

    public void delete(long id);


}
