package com.picpal.framework.sample.controller;

import com.picpal.framework.sample.dto.SampleDTO;
import com.picpal.framework.sample.service.SampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Slf4j
@RequestMapping(value = "/api/v1/")
@RestController
@RequiredArgsConstructor
public class SampleController {

    private final SampleService sampleService;


    @GetMapping("/user/{userId}")
    public ResponseEntity<?> selectUser(@PathVariable String userId) {
        log.info("[userId] : " + userId);
        try {
            sampleService.selectUser();
            return ResponseEntity.ok("User Found");
        } catch (Exception e) {
            log.error("Error occurred while fetching user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Internal Server Error"));
        }
    }

    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@RequestBody SampleDTO params) {
        try {
            sampleService.saveUser(params);
            return ResponseEntity.ok(Collections.singletonMap("message", "User saved successfully"));
        } catch (Exception e) {
            log.error("Error occurred while saving user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Internal Server Error"));
        }
    }
}