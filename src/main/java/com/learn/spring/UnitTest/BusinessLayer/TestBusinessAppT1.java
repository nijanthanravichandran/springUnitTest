package com.learn.spring.UnitTest.BusinessLayer;

import org.springframework.stereotype.Service;

@Service
public class TestBusinessAppT1 {

    public int calculateSum(int[] a) {
        int sum=0;
        for (int i:a){
            sum += i;    
        }
        return sum;
    }

}
