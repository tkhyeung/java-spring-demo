package com.example.java;

import com.example.java.model.Alphabet;
import com.example.java.service.AlphabetService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Slf4j
@SpringBootApplication
public class JavaApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(JavaApplication.class, args);
    }

    @Autowired
    CacheManager cacheManager;

    @Autowired
    AlphabetService service;

    @Override
    public void run(String... args) throws Exception {
        log.info("Start cache demo");
        IntStream.range(1, 20).forEach(service::createCache);
        IntStream.range(3, 7).forEach(this::getAlphabetDemo);
        IntStream.range(2, 6).forEach(this::getAlphabetDemo);

        log.info("All caches now {}", cacheManager.getCacheNames());
        Cache cache = cacheManager.getCache("alphabetMapCache");
        ConcurrentMap<Integer, Alphabet> cachedMap = (ConcurrentMap<Integer, Alphabet>) cache.getNativeCache();
        log.info("cachedMap {}", cachedMap);
        Alphabet beta = service.getAlphabet(2);
        Alphabet betaUpdated = Alphabet.createLatinAlphabet(beta.getId(), "b");
        log.info("Updating b {}", service.updateAlphabet(betaUpdated));
        log.info("cachedMap {}", cachedMap);
        getAlphabetDemo(2);
        log.info("cachedMap {}", cachedMap);
        service.deleteAlphabet(3);
        log.info("cachedMap {}", cachedMap);

    }

    private void getAlphabetDemo(int id) {
        StopWatch watch = new StopWatch();
        watch.start();
        Alphabet alphabet = service.getAlphabet(id);
        watch.stop();
        log.info("alphabet is {} for id {}, took {} microsecond(s)", alphabet, id, watch.getTime(TimeUnit.MICROSECONDS));
        watch.reset();
    }
}
