package com.example.java.model;

import com.example.java.config.ValidateAdult;
import com.example.java.config.ValidateChildren;
import com.example.java.config.ValidateList;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Data
@ToString
public class Person {

    //Only put NotBlank on String
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull
//    @DecimalMin(value = "18")
    @DecimalMax(value = "17", groups = ValidateChildren.class)
    @DecimalMin(value = "18", groups = ValidateAdult.class)
    private Integer age;

    @Past
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dob;

    @NotBlank(message = "Gender is mandatory")
    @Pattern(regexp = "M|F|Other", message = "Gender must be M or F or Other")
    private String gender;

    private String address;

    @Pattern(regexp = "[0-9]+")
    private String mobileNumber;

    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @ValidateList(pattern = "[1-5]")
    //[1,2,3,4,5,6,7,8]
    private List<String> preference;

}
