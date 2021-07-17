package com.example.java.exercise;

import com.example.java.JavaApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.*;

@Service
@Slf4j
public class LambdaService {

    public void test(){
        String testStr = "123";
        String contains = "1";
        Predicate<String> p = s -> s.contains(contains);
        log.info("Predicate interface: boolean-valued function of one argument");
        log.info(String.valueOf(filterByPredicate(testStr, p)));

        Function<String, String> f = s -> s.replace("\\", "");
        log.info("Function interface: accepts one argument and produces a result");
        log.info(String.valueOf(transformByFunction("1\\2\\3", f)));

        BiFunction<String, String, String> concatF = (s, s2) -> s.concat(s2);
        log.info("BiFunction interface: accepts two arguments and produces a result");
        log.info(concatF.apply("hello ", "world!"));

        Consumer<String> consumer = s -> log.info(s);
        log.info("Consumer interface: accepts a single input argument and returns no result. " +
                "Unlike most other functional interfaces, " +
                "Consumer is expected to operate via sideimport java.util.function.BinaryOperator; + -effects.");
        consumer.accept("consumer");

        Supplier<String> supplier = () -> "Supplier!";

        log.info("Supplier interface: supply something, return something");
        log.info(supplier.get());

        log.info("\n\n");
        JavaApplication javaApplication = new JavaApplication();
        Arrays.asList("1","2","3").forEach(LambdaService::staticPrint); //(i) Method References (ClassName::staticMethod)
        Arrays.asList("1","2","3").forEach(this::nonStaticPrint); //(ii) Method References (objectRef::instanceMethod)
        Supplier<String> supplier2 = String::new; //constructor reference. same as () -> new String();
        BiFunction<Integer, Float, HashMap> biFunction = HashMap::new; //constructor reference same as (c, lf) -> new HashMap(c, lf)
        //c -> capacity, lf = load factor

    }



    public static <T> boolean filterByPredicate(T s, Predicate<T> predicate){
        return predicate.test(s);
    }

    public static <T, R>  R transformByFunction(T s, Function<T, R> function){
        return function.apply(s);
    }

    public static void staticPrint(String s){
        log.info(s);
    }

    public void nonStaticPrint(String s){
        log.info(s);
    }

}
