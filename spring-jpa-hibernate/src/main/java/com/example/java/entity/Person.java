package com.example.java.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
//@Table(name = "PERSON")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    private String email;
    @Column(nullable = false)
    private String age;
    private String contactNumber;
    private String status;

    @OneToMany(
//            fetch = FetchType.EAGER, //default LAZY
            mappedBy = "person",
            cascade = CascadeType.ALL
//            orphanRemoval = true
                                                )
    @ToString.Exclude //exclude lazy loading items
    //https://thorben-janssen.com/lombok-hibernate-how-to-avoid-common-pitfalls/#Be_Careful_with_ToString
    private List<Address> addresses;


}