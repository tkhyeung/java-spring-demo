package com.example.java.repository;

import com.example.java.model.Alphabet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@Slf4j
public class AlphabetRepository {

    private static List<String> greekAlphabets = Arrays.asList("alpha", "beta", "gamma", "delta", "epsilon"
            , "zêta", "êta", "thêta", "iota", "kappa", "lambda", "mu", "nu", "xi", "omikron",
            "pi", "rho", "tau", "upsilon", "phi", "chi", "psi", "omega");

    private static List<Alphabet> alphabetList;

    static {
        alphabetList = greekAlphabets.stream().map(a -> Alphabet.createGreekAlphabet(greekAlphabets.indexOf(a) + 1, a)).collect(Collectors.toList());
        log.info("alphabetList in repo is {}", alphabetList);
    }

    public Alphabet getAlphabet(int id) {
        log.info("Getting alphabet from repo!, id:{}", id);
        return alphabetList.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }

    public Alphabet updateAlphabet(Alphabet updated) {
        Alphabet alphabet = alphabetList.stream().filter(a -> a.getId() == updated.getId()).findFirst().orElse(null);
        if (alphabet != null) {
            alphabetList.set(alphabetList.indexOf(alphabet), updated);
            return updated;
        }
        throw new NoSuchElementException();
    }

    public void deleteAlphabet(int delete) {
        Alphabet alphabet = alphabetList.stream().filter(a -> a.getId() == delete).findFirst().orElse(null);
        log.info("deleting from repo{}", alphabet);
        if (alphabet != null) {
            int index = alphabetList.indexOf(alphabet);
            alphabetList.remove(index);
        }
    }


    public Alphabet getAlphabetSlow(int id) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getAlphabet(id);
    }


}
