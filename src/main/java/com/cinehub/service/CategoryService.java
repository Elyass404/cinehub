package com.cinehub.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoryService {

    public List<String> categorySer(){
        return Arrays.asList("ayman", "ilyass");
    }
}
