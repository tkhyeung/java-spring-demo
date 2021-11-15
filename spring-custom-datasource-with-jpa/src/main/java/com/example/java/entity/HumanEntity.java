package com.example.java.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "HUMAN")
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HumanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    Integer age;
}
