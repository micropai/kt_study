package com.example.springbasic.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public int sum(int a, int b){
        return a + b;
    }

    public int minus(int a, int b){
        return a - b;
    }
}
