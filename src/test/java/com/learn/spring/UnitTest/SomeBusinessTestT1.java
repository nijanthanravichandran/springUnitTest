package com.learn.spring.UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.learn.spring.UnitTest.BusinessLayer.TestBusinessAppT1;

@SpringBootTest
public class SomeBusinessTestT1 {

    @Autowired
    TestBusinessAppT1 testBusinessApp;

    @Test
    public void calculateSum_basic() {
        int actualValue = testBusinessApp.calculateSum(new int[] {1,2,3});
        int expectedValue = 6;
        assertEquals(expectedValue,actualValue);
    }

    @Test
    public void calculateSum_empty() {
        int actualValue = testBusinessApp.calculateSum(new int[] {});
        int expectedValue = 0;
        assertEquals(expectedValue,actualValue);
    }

    @Test
    public void calculateSum_oneValue() {
        int actualValue = testBusinessApp.calculateSum(new int[] {1});
        int expectedValue = 1;
        assertEquals(expectedValue,actualValue);
    }


}
