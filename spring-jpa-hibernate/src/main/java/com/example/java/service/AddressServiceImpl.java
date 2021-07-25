package com.example.java.service;

import com.example.java.dao.AddressDAO;
import com.example.java.dao.PersonDAO;
import com.example.java.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressDAO addressDAO;

    @Override
    @Transactional
    public List<Address> findAll() {
        return addressDAO.findAll();
    }

    @Override
    @Transactional
    public Address findById(long id) {
        return addressDAO.findById(id);
    }

    @Override
    @Transactional
    public Address save(Address address) {
        return addressDAO.save(address);
    }

    @Override
    @Transactional
    public List<Address> saveAll(List<Address> addresses) {
        return addresses.stream().map(this::save).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return addressDAO.count();
    }

    @Override
    @Transactional
    public void delete(long id) {
        addressDAO.delete(id);
    }
}
