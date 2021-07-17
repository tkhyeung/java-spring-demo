package com.example.java.container;


import com.opencsv.CSVParser;
import lombok.*;

import java.nio.charset.Charset;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CsvContainer<T> {
    private char separator = CSVParser.DEFAULT_SEPARATOR;
    private char escapeChar = CSVParser.DEFAULT_ESCAPE_CHARACTER;
    private char quoteChar = CSVParser.DEFAULT_QUOTE_CHARACTER;
    private String lineEnd = CSVParser.NEWLINE;
    private boolean isIgnoreQuotations = true;
    private boolean isWithHeader = true;
    private Charset charset = Charset.defaultCharset();
    private int rowStartIndex = -1;
    private int rowEndIndex = -1;
    @NonNull
    private Class<T> clazz;
    private List<T> data;
}
