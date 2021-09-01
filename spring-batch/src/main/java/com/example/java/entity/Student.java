package com.example.java.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "STUDENT")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String age;
    private String grade;
    private String hobby;

}
