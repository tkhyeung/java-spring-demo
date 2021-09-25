package com.example.java.service;

import com.example.java.entity.Address;

import java.util.List;

public interface AddressService {

    public List<Address> findAll();

    public Address findById(long id);

    public Address save(Address address);

    public List<Address> saveAll(List<Address> addresses);

    public long count();

    public void delete(long id);




}
