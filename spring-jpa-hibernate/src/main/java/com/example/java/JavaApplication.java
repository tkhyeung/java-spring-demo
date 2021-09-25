package com.example.java;

import com.example.java.entity.Address;
import com.example.java.entity.Person;
import com.example.java.service.AddressService;
import com.example.java.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;


/**
 * Default Fetch Types:
 * - @OneToOne: FetchType.EAGER
 * - @OneToMany: FetchType.LAZY
 * - @ManyToOne: FetchType.EAGER
 * - @ManyToMany: FetchType.LAZY
 *
 * CascadeType: ALL, PERSIST, MERGE, REMOVE, REFRESH, DETACH
 *
 * Entity lifecycle: new, managed, removed, detached
 *
 * Persistence Context
 *
 * https://www.objectdb.com/java/jpa/persistence/managed
 * https://www.baeldung.com/hibernate-lazy-loading-workaround
 * spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true
 * may have performance issue
 */


@SpringBootApplication
@Slf4j
public class JavaApplication implements CommandLineRunner {

    @Autowired
    private PersonService personService;

    @Autowired
    private AddressService addressService;

    public static void main(String[] args) {
        SpringApplication.run(JavaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Person> personList = Arrays.asList(
                Person.builder().name("A").email("a@a.com").age("10").contactNumber("12345678").status("0").build(),
                Person.builder().name("B").age("20").contactNumber("23456789").status("1").build(),
                Person.builder().name("C").email("c@c.com").age("30").contactNumber("34567890").status("1").build()
        );
        Address a1 = Address.builder()
                .street1("a1-1")
                .street2("a1-2")
                .person(personList.get(0))
                .build();
        Address a2 = Address.builder()
                .street1("a2-1")
                .street2("a2-2")
                .person(personList.get(0))
                .build();

        List<Address> addresses = Arrays.asList(a1, a2);

        personList.get(0).setAddresses(addresses);
        personList = personService.saveAll(personList);
//        addressService.saveAll(addresses); not needed, save person will save address

//        Person p1 = personService.findByIdWithAddress(personList.get(0).getId());
//        log.info(p1.toString());
//        log.info("get p1 addresses");
//        log.info(p1.getAddresses().toString());

        log.info("findAll");
        personService.findAll().forEach(s -> log.info(s.toString()));

        log.info("There are total of {} persons", personService.count());
        log.info("There are total of {} addresses", addressService.count());
        log.info(addressService.findAll().toString());

        log.info("---- ----");
//        Person p1 = personService.findByIdWithAddress(1);
//        log.info("p1: {}, addresses: {}", p1, p1.getAddresses());
//        log.info("Removing person id = 1 and its address id = 1");
//        addressService.delete(1);
//        p1 = personService.findByIdWithAddress(1);
//        log.info("p1: {}, addresses: {}", p1, p1.getAddresses());

        log.info("Removing person id = 1");
        personService.delete(1); //addresses associated will be removed as well
        log.info("There are total of {} persons", personService.count());
        log.info("There are total of {} addresses", addressService.count());
        log.info(addressService.findAll().toString());
    }
}
