package com.example.java.entity;

import lombok.*;

import javax.persistence.*;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String name;
    private String email;
    @Column(nullable = false)
    private String age;
    private String contactNumber;
    private String status;
}