package com.example.java;

import com.example.java.entity.HumanEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanRepository extends CrudRepository<HumanEntity, Long> {
}
