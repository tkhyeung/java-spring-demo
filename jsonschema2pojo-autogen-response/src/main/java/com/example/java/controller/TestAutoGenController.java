package com.example.java.controller;

import com.example.response.autogen.AutoGenTestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/test")
public class TestAutoGenController {


    @Operation(summary = "Test get endpoint for auto gen response class")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Test http get",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AutoGenTestResponse.class))})})
    @GetMapping("/autogenresponsebyjsonfile")
    public AutoGenTestResponse test(
            @RequestHeader(value = "requestId") String rqId) {
        log.info("test get received::{}", rqId);
        AutoGenTestResponse response = new AutoGenTestResponse();
        response.setId(rqId);
        response.setText("test 123 hellow world");
        return response;
    }
}
