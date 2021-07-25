package com.example.java.dao;

import com.example.java.entity.Address;
import com.example.java.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

@Repository
public class AddressDAOJpaImpl implements AddressDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Address> findAll() {
        Query theQuery =
                entityManager.createQuery("from Address");
        return theQuery.getResultList();

    }

    @Override
    public Address findById(long id) {
        return entityManager.find(Address.class, id);
    }

    @Override
    public Address save(Address address) {
        // save or update
        Address savedAddress = entityManager.merge(address);
        address.setId(savedAddress.getId());
        return address;
    }

    @Override
    public long count() {
        BigInteger bigInteger = (BigInteger) entityManager.createNativeQuery("SELECT COUNT(*) FROM ADDRESS")
                .getSingleResult();
        return bigInteger.longValue();
    }

    @Override
    public void delete(long id) {
        entityManager.remove(findById(id));
    }

}
