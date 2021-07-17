package com.example.java;

import com.example.java.container.CsvContainer;
import com.example.java.model.CsvData;
import com.example.java.service.CsvService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Demonstrate how to create a custom annotation and its usage
 */
@SpringBootApplication
@Slf4j
public class JavaApplication implements CommandLineRunner {

    @Autowired
    private CsvService csvService;

    public static void main(String[] args) {
        SpringApplication.run(JavaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final String filePath = "src/main/resources/book.csv";
        CsvContainer<CsvData> csvContainer = new CsvContainer<>();
        csvContainer.setClazz(CsvData.class);
        csvContainer = csvService.readCsv(filePath, csvContainer);
        List<CsvData> l = csvContainer.getData();
        log.info(l.toString());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        Date d = new Date(System.currentTimeMillis());
        String date = format.format(d);

        csvContainer.setSeparator(';');
        csvContainer.setQuoteChar('\'');
        csvService.writeCsv("src/main/resources/output-"+date+".csv", csvContainer);

    }
}
