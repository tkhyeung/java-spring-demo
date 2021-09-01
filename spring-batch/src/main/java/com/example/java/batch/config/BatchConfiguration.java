package com.example.java.batch.config;

import com.example.java.batch.StudentItemProcessor;
import com.example.java.entity.Student;
import com.example.java.repository.StudentRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private StudentRepository repository;

    @Autowired
    private JobCompletionNotificationListener listener;

    @Value("${file.input}")
    private String fileInput;

//    @Bean
    public FlatFileItemReader<Student> reader() {
        return new FlatFileItemReaderBuilder<Student>().name("coffeeItemReader")
                .resource(new ClassPathResource(fileInput))
                .delimited()
                .names("name", "age", "grade", "hobby")
                .fieldSetMapper(new BeanWrapperFieldSetMapper() {{
                    setTargetType(Student.class);
                }})
                .build();
    }

//    @Bean
//    public JdbcBatchItemWriter writer(DataSource dataSource) {
//
//        return new JdbcBatchItemWriterBuilder()
//                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
//                .sql("INSERT INTO coffee (brand, origin, characteristics) VALUES (:brand, :origin, :characteristics)")
//                .dataSource(dataSource)
//                .build();
//    }
//    @Bean
    public RepositoryItemWriter<Student> writer() {
        return new RepositoryItemWriterBuilder<Student>()
                .repository(repository)
                .methodName("save")
                .build();
    }


    @Bean
    public Job importUserJob() {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .next(step2())
                .end()
                .build();
    }

    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Student, Student> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .faultTolerant()
                .build();
    }

    public Step step2() {
        return stepBuilderFactory.get("step2")
                .<Student, Student> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .faultTolerant()
                .build();
    }




    @Bean
    public StudentItemProcessor processor() {
        return new StudentItemProcessor();
    }





}
