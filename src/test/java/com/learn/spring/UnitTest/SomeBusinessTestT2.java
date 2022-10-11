package com.learn.spring.UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.learn.spring.UnitTest.BusinessLayer.SomeBusinessImplT2;
import com.learn.spring.UnitTest.DataLayer.SomeDataService;

class SomeDataServiceStub implements SomeDataService {

    @Override
    public int[] retriveAllData() {
        return new int[] {1,2,3};
    }

}

class SomeDataServiceEmptyStub implements SomeDataService {

    @Override
    public int[] retriveAllData() {
        return new int[] {};
    }

}

class SomeDataServiceOneElementStub implements SomeDataService {

    @Override
    public int[] retriveAllData() {
        return new int[] {3};
    }

}

@SpringBootTest
public class SomeBusinessTestT2 {

    @Autowired
    SomeBusinessImplT2 someBusinessImplT2;

    @Test
    public void calculateSum_basic() {

        someBusinessImplT2.setSomeDataService(new SomeDataServiceStub());
        int actualValue = someBusinessImplT2.calculateSum();
        int expectedValue = 6;
        assertEquals(expectedValue,actualValue);
    }

    @Test
    public void calculateSum_empty() {
        someBusinessImplT2.setSomeDataService(new SomeDataServiceEmptyStub());
        int actualValue = someBusinessImplT2.calculateSum();
        int expectedValue = 0;
        assertEquals(expectedValue,actualValue);
    }

    @Test
    public void calculateSum_oneValue() {
        someBusinessImplT2.setSomeDataService(new SomeDataServiceOneElementStub());
        int actualValue = someBusinessImplT2.calculateSum();
        int expectedValue = 3;
        assertEquals(expectedValue,actualValue);
    }


}
