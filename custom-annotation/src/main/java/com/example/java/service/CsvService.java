package com.example.java.service;

import com.example.java.annotation.CsvElement;
import com.example.java.container.CsvContainer;
import com.opencsv.*;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * Read Csv using Annotation
 */

@Service
public class CsvService {

    public <T> CsvContainer<T> readCsv(String filePath, CsvContainer<T> container) {
        if(container == null) throw new IllegalArgumentException("container not set");

        List<T> objList = new ArrayList<>();
        char separator = container.getSeparator();
        char escapeChar = container.getEscapeChar();
        char quoteChar = container.getQuoteChar();
        String lineEnd = container.getLineEnd();
        boolean isIgnoreQuotations = container.isIgnoreQuotations();
        boolean isWithHeader = container.isWithHeader();
        Charset charset = container.getCharset();
        int rowStartIndex = container.getRowStartIndex();
        int rowEndIndex = container.getRowEndIndex();
        Class<T> clazz = container.getClazz();
        CSVParser csvParser = new CSVParserBuilder()
                .withSeparator(separator)
                .withEscapeChar(escapeChar)
                .withQuoteChar(quoteChar)
                .withIgnoreQuotations(isIgnoreQuotations)
                .build();

        int skipline = 0;
        if(isWithHeader) skipline += 1;

        if(rowStartIndex >= 0 ) skipline += rowStartIndex;

        int rowCount = skipline;


        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(Paths.get(filePath).toAbsolutePath().toFile(), charset))
                .withCSVParser(csvParser)
                .withSkipLines(skipline) // skip header
                .build()
        ) {
            String[] values = null;
            Map<Integer, Field> indexFieldMap = getCsvElementIndexFieldMap(clazz);
            if(rowEndIndex < 0) rowEndIndex = Integer.MAX_VALUE;
            while ( ((values = csvReader.readNext()) != null ) && rowCount <= rowEndIndex) {
                Object obj = clazz.getConstructor().newInstance();
                for (Map.Entry<Integer, Field> entry : indexFieldMap.entrySet()) {
                    String ele = values[entry.getKey()];
                    entry.getValue().set(obj, ele);
                }
                objList.add(clazz.cast(obj));
//                records.add(Arrays.asList(values));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to read CSV", e);
        }
        container.setData(objList);
        return container;
    }

    private <T> Map<Integer, Field> getCsvElementIndexFieldMap(Class<T> clazz) {
        Map<Integer, Field> indexFieldMap = new HashMap<>();
        Field[] fields = FieldUtils.getAllFields(clazz);
        for (Field f : fields) {
            CsvElement csvElement = f.getAnnotation(CsvElement.class);
            int index = csvElement.index();
            if (index == -1) throw new IllegalArgumentException("index cannot be -1");
            if (indexFieldMap.containsKey(index)) throw new IllegalArgumentException("index is not unique");
            f.setAccessible(true);
            indexFieldMap.put(index, f);
        }
        return indexFieldMap;
    }

    public <T> void writeCsv(String outputPath, CsvContainer<T> container) {

        if(container == null || container.getData() == null) throw new IllegalArgumentException("container or data not set");
        char separator = container.getSeparator();
        char escapeChar = container.getEscapeChar();
        char quoteChar = container.getQuoteChar();
        String lineEnd = container.getLineEnd();
        boolean isIgnoreQuotations = container.isIgnoreQuotations();
        boolean isWithHeader = container.isWithHeader();
        Charset charset = container.getCharset();
        int rowStartIndex = container.getRowStartIndex();
        int rowEndIndex = container.getRowEndIndex();
        Class<T> clazz = container.getClazz();
        List<T> list = container.getData();

        CSVParser csvParser = new CSVParserBuilder()
                .withSeparator(separator)
                .withEscapeChar(escapeChar)
                .withQuoteChar(quoteChar)
                .withIgnoreQuotations(isIgnoreQuotations)

                .build();
        try (ICSVWriter writer = new CSVWriterBuilder(new FileWriter(Paths.get(outputPath).toAbsolutePath().toFile(), charset))
                .withParser(csvParser)
                .withLineEnd(lineEnd)
                .build()
        ) {
            if(isWithHeader){
                writer.writeNext(getCsvElementHeaderRow(clazz));
            }
            list.stream().forEach(r -> writer.writeNext(objToStringArray(r)));
        } catch (Exception e) {
            throw new RuntimeException("Failed to write CSV", e);
        }
        return;
    }

    private <T> String[] getCsvElementHeaderRow(Class<T> clazz){
        List<String> list = new ArrayList<>();
        Field[] fields = FieldUtils.getAllFields(clazz);
        for(Field f: fields){
            list.add(f.getName());
        }
        return list.stream().toArray(String[]::new);
    }

    private <T> String[] objToStringArray(T obj) {
        List<String> list = new ArrayList<>();
        Map<Integer, String> orderedMap = new TreeMap<>();

        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field f: fields){
            int index = f.getAnnotation(CsvElement.class).index();
            if (index == -1) throw new IllegalArgumentException("index cannot be -1");
            if (orderedMap.containsKey(index)) throw new IllegalArgumentException("index is not unique");
            f.setAccessible(true);
            try {
                String value = (String) f.get(obj);
                orderedMap.put(index, value);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("failed to write csv", e);
            }
        }
        list.addAll(orderedMap.values());
        return list.stream().toArray(String[]::new);
    }
}
