package com.main.controllers;

import com.main.Service.NumberClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/classify-number")
public class NumberAPIController {
    @Autowired
    NumberClassificationService service;

    @GetMapping
    public ResponseEntity<String> numberClassification(@RequestParam String number){
        return service.classifyNumbers(number);
    }
}
