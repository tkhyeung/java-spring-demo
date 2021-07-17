package com.example.java.exercise;

import com.example.java.model.A;
import com.example.java.model.B;
import com.example.java.model.AB;
import com.example.java.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.*;

@Service
@Slf4j
public class StreamService {

    public void test() {

        /**
         * Stream is a pipeline of operation
         * intermediate operation (0 or more)( producing stream object )
         * terminal operation ( producing void, boolean, Optional, List, … non stream things) (the last operation)
         */


        /**
         * Operations:
         * filtering            (intermediate)
         * mapping              (intermediate)
         * slicing              (intermediate)
         * matching&finding     (terminal)
         * reduction            (terminal)
         * collect              (terminal)
         */

        Person person1 = new Person("A", 10);
        Person person2 = new Person("B", 20);
        Person person3 = new Person("C", 30);
        Person person4 = new Person("D", 40);
        Person person5 = new Person("E", 50);
        Person person6 = new Person("F", 60);

        //filtering
        List<Person> personList = Arrays.asList(person1, person2, person3, person4, person5, person6);
        Predicate<Person> personPredicate = s -> s.getName().contains("A");
        //filter takes a Predicate
        log.info("filtering");
        log.info(personList.stream().filter(personPredicate).collect(Collectors.toList()).toString());

        //mapping
        //mapping takes a Function
        Function<Person, Person> personFunction = s -> new Person(s.getName(), s.getAge()+5); //age + 5
        log.info("mapping");
        log.info(personList.stream().map(personFunction).collect(Collectors.toList()).toString());

        //slicing
        //find second whose age > 35
        log.info("slicing");
        log.info(personList.stream().distinct().filter(p -> p.getAge() > 35).skip(1).
                limit(1).collect(Collectors.toList()).toString());


        //matching & finding
        log.info("matching & finding");
        log.info(String.valueOf(personList.stream().allMatch(s -> s.getAge()%2==0)));
        Optional<Person> nullPerson = personList.stream().filter(s -> s.getAge()>10).findFirst();
        log.info(nullPerson.orElse(createNewPerson()).toString());
        //orElse will always be executed
        //orElseGet will only be executed when not found (Lazy execution)
        log.info(nullPerson.orElseGet(this::createNewPerson).toString());


        //reduce()
        //reduces stream into single value
        //reduce() is immutable reduction
        log.info("reduce");
        //find sum/max or min
        log.info("max");
        personList.stream().reduce(
                (p1, p2) -> p1.getAge() >= p2.getAge() ? p1 : p2
        ).ifPresent(p -> log.info("max age person is {}", p));
        log.info("min");
        personList.stream().reduce(
                (p1, p2) -> p1.getAge() <= p2.getAge() ? p1 : p2
        ).ifPresent(p -> log.info("min age person is {}", p));
        log.info("sum");
        personList.stream().map(s->s.getAge()).reduce(
                (p1, p2) -> p1+p2
        ).ifPresent(p -> log.info("sum of ages is {}", p));

        //collect
        //reduces stream into a container (list/set/map, stringBuilder, summary obj(a container object containing sum/max/min value))
        //collect is mutable reduction
        String[] s1 = {"h", "e", "l", "l", "o", ","};
        String[] s2 = {"w", "o", "r", "l", "d", "!"};

        String result = Stream.of(Arrays.asList(s1), Arrays.asList(s2))
                .flatMap(l -> l.stream())
                .collect(
                () -> new StringBuilder(),
                (stringBuilder, s) -> stringBuilder.append(s),
                (stringBuilder1, stringBuilder2) -> stringBuilder1.append(stringBuilder2))
                .toString();
        log.info(result);

//        String result = Stream.concat(Stream.of(s1), Stream.of(s2)).collect(
//                StringBuilder::new, //supplier
//                StringBuilder::append, //accumulator
//                StringBuilder::append) //combiner
//                .toString();
        result = Stream.of(Arrays.asList(s1), Arrays.asList(s2))
                .flatMap(l -> l.stream())
                .collect(Collectors.joining()); //factory method
        log.info(result);

        //    <R, A> R collect(Collector<? super T, A, R> collector);
        //      T = Stream element type, A is accumulator type, R is container
        //    <R> R collect(Supplier<R> supplier,
        //                  BiConsumer<R, ? super T> accumulator,
        //                  BiConsumer<R, R> combiner);

        //Common Collections
        //Static Factory method: (toList(), toSet(), toCollection(<Supplier>))
        //Grouping : toMap(), groupingBy(), partitioningBy()
        //Reducing & Summarising (maxBy(), summingInt(), averagingInt(), summarizingInt(), joining())

        Map<String, Person> personMap = personList.stream().collect(Collectors.toMap(
                p -> p.getName(), //key
                p -> p            //value
        ));
        log.info(personMap.toString());


        Person person7 = new Person("A", 100);
        personList = Arrays.asList(person1, person2, person3, person4, person5, person6,person7);
        personMap = personList.stream().collect(Collectors.toMap(
                p -> p.getName(),           //key
                Function.identity(),        //value Function.identity() = p -> p
                (p1,p2) -> p1.getAge() >= p2.getAge() ? p1 : p2 // BinaryOperator<U> mergeFunction when key is duplicate
        ));
        log.info(personMap.toString());


        //Collectors.groupingBy = by design generate a key & value pair (value is a list)
        // group by key, if collision, then turn into a list
        log.info("Collectors.groupingBy");
        log.info("group by age");
        log.info(personList.stream().collect(Collectors.groupingBy(Person::getAge)).toString());
        log.info("group by name");
        log.info(personList.stream().collect(Collectors.groupingBy(Person::getName)).toString());
        log.info(personList.stream().collect(Collectors.groupingBy(Person::getName, Collectors.toSet())).toString());//downstream to other
        log.info("group by name, count #");
        log.info(personList.stream().collect(Collectors.groupingBy(Person::getName, Collectors.counting())).toString());
        log.info("group by name, count avg");
        log.info(personList.stream().collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge))).toString());
        log.info("group by name, count max");
        Comparator<Person> c = (p1 , p2) -> {
            if(p1.getAge() >= p2.getAge() ) return 100;
            return -1;
        };
        log.info(personList.stream().collect(Collectors.groupingBy(Person::getName, Collectors.maxBy(c))).toString());

        log.info("group by name, get summarystatistics");
        log.info(personList.stream().collect(Collectors.groupingBy(Person::getName, Collectors.summarizingDouble(Person::getAge))).toString());

        log.info("2 levels group by name then group by age ");
        log.info(personList.stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getName,
                                Collectors.groupingBy(Person::getAge, Collectors.toList())
                        )
                ).toString());



        log.info("\n");
        log.info("partitionBy");
        log.info("partition to 2 lists, age > 35 or <= 35"); //true , list; false, list
        log.info(personList.stream().collect(
                Collectors.partitioningBy(p -> p.getAge() > 35 )
        ).toString());

        log.info(personList.stream().collect(
                Collectors.partitioningBy(
                        p -> p.getAge() > 35 ,
                        Collectors.mapping(Person::getAge, Collectors.toList()) // downstream collector to turn value to other Collections
                )
        ).toString());


        log.info("\n");









        /**
         * Join Object A and Object B to Object AB where A1 == B1
         * like performing inner join as SQL
         */
        log.info("Join Object A and Object B to Object AB where A1 == B1");
        log.info("like performing inner join as SQL");
        List<A> aList = List.of(
                A.builder().a1(1).a2(1).a3(1).build(),
                A.builder().a1(2).a2(2).a3(2).build(),
                A.builder().a1(3).a2(3).a3(3).build(),
                A.builder().a1(3).a2(3).a3(4).build(),
                A.builder().a1(3).a2(3).a3(5).build()

        );

        List<B> bList = List.of(
//                B.builder().b1(2).build(),
                B.builder().b1(3).build()
        );

        //imperative way
        List<AB> abListImperative = null;
        for (A a : aList) {
            for(B b: bList){
                if(a.getA1() == b.getB1()){
                    if(abListImperative==null) abListImperative = new ArrayList<>();
                    abListImperative.add(AB.builder().a1(a.getA1()).a2(a.getA2()).a3(a.getA3()).b1(b.getB1()).build());
                }
            }
        }
        if(abListImperative==null) abListImperative = new ArrayList<>();
        log.info(abListImperative.toString());

        //declarative way
        List<AB> abListDeclarative = new ArrayList<>();
        aList.forEach(a -> bList.forEach(b -> {
            if(a.getA1() == b.getB1()){
                abListDeclarative.add(AB.builder().a1(a.getA1()).a2(a.getA2()).a3(a.getA3()).b1(b.getB1()).build());
            }
        }));
        log.info(abListDeclarative.toString());

        List<AB> abListDeclarative2 = aList.stream()
                .filter(a -> bList.stream().anyMatch(b -> b.getB1()==a.getA1()))
                .collect(
                        ArrayList::new,
                        ((objects, a) -> objects.add(useAfindB(a, bList))),
                        List::addAll);
        log.info(abListDeclarative2.toString());



        log.info("\n");
        log.info("Summary Statistic in Stream");
        /**
         * Summary Statistic in Stream
         */
        IntStream intStream = IntStream.range(0,100000);
        IntSummaryStatistics intSummaryStatistics = intStream.summaryStatistics();
        log.info(intSummaryStatistics.toString());
        log.info("Count: {}", intSummaryStatistics.getCount());
        log.info("Sum: {}", intSummaryStatistics.getSum());
        log.info("Min: {}", intSummaryStatistics.getMin());
        log.info("Max: {}", intSummaryStatistics.getMax());
        log.info("Avg: {}", intSummaryStatistics.getAverage());


        double[] doubles = {1.0,2.5,3.5,4.5,9.5};
        DoubleStream doubleStream = DoubleStream.of(doubles);
        DoubleSummaryStatistics doubleSummaryStatistics = doubleStream.summaryStatistics();
        log.info(doubleSummaryStatistics.toString());
        log.info("Count: {}", doubleSummaryStatistics.getCount());
        log.info("Sum: {}", doubleSummaryStatistics.getSum());
        log.info("Min: {}", doubleSummaryStatistics.getMin());
        log.info("Max: {}", doubleSummaryStatistics.getMax());
        log.info("Avg: {}", doubleSummaryStatistics.getAverage());
        return;
    }


    private AB useAfindB(A a, List<B> bList){
        for(B b: bList){
            if(b.getB1() == a.getA1()){
                return AB.builder().a1(a.getA1()).a2(a.getA2()).a3(a.getA3()).b1(b.getB1()).build();
            }
        }
        return null;
    }

    private Person createNewPerson(){
        log.info("createNewPerson called");
        return new Person("new Created!", 1000);
    }
}
