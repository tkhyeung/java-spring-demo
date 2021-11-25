package com.example.java.service;

import com.example.java.model.Alphabet;
import com.example.java.repository.AlphabetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class AlphabetService {

    private final AlphabetRepository repository;

    //cacheable = to put into the cache if not exists, return from cache if exists, a0=first argument of the method
    @Cacheable(value = "alphabetMapCache", key = "#a0")
    public Alphabet getAlphabet(int id) {
        return repository.getAlphabetSlow(id);
    }

    //unless = not putting to cache
    @Cacheable(value = "alphabetMapCache", key = "#a0", unless = "#a0 % 2 != 0")
    public Alphabet createCache(int id) {
        return repository.getAlphabet(id);
    }

    //CachePut = put anyway
    @CachePut(value = "alphabetMapCache", key = "#a0.id")
    public Alphabet updateAlphabet(Alphabet updated) {
        return repository.updateAlphabet(updated);
    }

    //evict = remove from cache
    @CacheEvict(value = "alphabetMapCache", key = "#a0")
    public void deleteAlphabet(int delete) {
        repository.deleteAlphabet(delete);
    }


}
