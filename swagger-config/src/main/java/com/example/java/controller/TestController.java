package com.example.java.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@Api(tags = "Demo Controller")
@RestController
@RequestMapping(value = "/test")
@Slf4j
@Validated //required to make @Pattern works
public class TestController {

    @ApiOperation(value = "test http get", notes = "returns a OK and request id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Testing message", response = TestResponse.class)
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public TestResponse test(
            @RequestHeader(value = "requestId", required = true) @Pattern(regexp = "[A-Za-z]-\\d{5}") String rqId) {
        log.info("test get received::{}",rqId);
        return TestResponse.builder().id(rqId).text("OK").build();
    }
}
