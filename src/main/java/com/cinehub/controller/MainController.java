package com.cinehub.controller;

import com.cinehub.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ayman")
public class MainController {

    private CategoryService ser;

    public MainController(CategoryService ser) {
        this.ser = ser;
    }

    @GetMapping("/")
    public String testing(){
        return "seccess";
    }

    @GetMapping("/test")
    public ResponseEntity<List<String>> testingtwo(){
        return new ResponseEntity<>(ser.categorySer(), HttpStatus.OK);
    }

}
