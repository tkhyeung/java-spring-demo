package com.example.java.controller;

import com.example.java.controller.response.AlphabetResponse;
import com.example.java.exception.ApplicationException;
import com.example.java.exception.ErrorCode;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("alphabets")
public class DemoController {

    private static List<String> greekAlphabets = Arrays.asList("alpha", "beta", "gamma", "delta", "epsilon"
            , "zêta", "êta", "thêta", "iota", "kappa", "lambda", "mu", "nu", "xi", "omikron",
            "pi", "rho", "tau", "upsilon", "phi", "chi", "psi", "omega");

    @GetMapping("/greek/{id}")
    public AlphabetResponse getGreekAlphabet(@PathVariable(value = "id") String idStr) {
        if (!NumberUtils.isDigits(idStr)) {
            throw new ApplicationException(ErrorCode.ID_FORMAT_ERROR, "id format error " + idStr);
        }
        int id = NumberUtils.toInt(idStr);

        if(id == 999){
            throw new NullPointerException("999! hidden NullPointerException");
        }

        if (id > greekAlphabets.size() || id <= 0) {
            throw new ApplicationException(ErrorCode.NOT_FOUND, "id "+ idStr +" is out of range");
        }
        return AlphabetResponse.builder().id(id).letter(greekAlphabets.get(id - 1)).build();
    }

    @GetMapping("/greek/error")
    public void testError() {
        throw new ApplicationException(ErrorCode.OTHER, "test other error");
    }

    @PostMapping("/greek")
    public void testError2() {
        throw new NotImplementedException("/greek is not implemented");
    }

    @PostMapping("/runtimeException")
    public void testError3() {
        throw new RuntimeException("/runtimeException throws runtimeException");
    }

}
