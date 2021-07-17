package com.example.java.model;

import com.example.java.annotation.Csv;
import com.example.java.annotation.CsvElement;
import lombok.Data;
import lombok.ToString;

@Csv
@Data
@ToString
public class CsvData {

    @CsvElement(index = 0)
    private String col1;
    @CsvElement(index = 3)
    private String col2;
    @CsvElement(index = 2)
    private String col3;
    @CsvElement(index = 1)
    private String col4;

}
