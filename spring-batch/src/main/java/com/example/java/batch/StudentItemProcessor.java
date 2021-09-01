package com.example.java.batch;

import com.example.java.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class StudentItemProcessor implements ItemProcessor<Student, Student> {

    @Override
    public Student process(Student student) {
//        String name = student.getName().toUpperCase();
//        String age = student.getAge();
//        String grade = student.getGrade();
//        String hobby = student.getHobby().toUpperCase();
//
//        Student transformedStudent = Student.builder()
//                                        .name(name)
//                                        .age(age)
//                                        .grade(grade)
//                                        .hobby(hobby)
//                                        .build();
//        log.info("Converting ( {} ) into ( {} )", student, transformedStudent);

//        return transformedStudent;
        //can do any processing
        log.info("Processing: student::{}", student);
        return student;
    }
}
