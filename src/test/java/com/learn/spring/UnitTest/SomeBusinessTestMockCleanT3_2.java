package com.learn.spring.UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.learn.spring.UnitTest.BusinessLayer.SomeBusinessImplT2;
import com.learn.spring.UnitTest.DataLayer.SomeDataService;

/*
 * This is more cleaner code version of src/test/java/com/learn/spring/UnitTest/SomeBusinessTestMockT3.java
 * Read through the comments and compare this and earlier version
 */

@SpringBootTest
public class SomeBusinessTestMockCleanT3_2 {

    @Autowired
    SomeBusinessImplT2 someBusinessImplT2;
    /*
     * This object is common for all test, hence created outside
     */
    SomeDataService dataServiceMock = mock(SomeDataService.class);

    /*
     * This setter is common for all test cases, hence moved to separate and make it execute before
     * each test executions using @BeforeEach Execution
     */
    @BeforeEach
    public void before() {
        someBusinessImplT2.setSomeDataService(dataServiceMock);
    }

    @Test
    public void calculateSum_basic() {
        //<mockobject> <method to call> (retriveAllData()) <what to return> ( new int[] {1,2,3})
        when(dataServiceMock.retriveAllData()).thenReturn(new int[] {1,2,3});
        assertEquals(6,someBusinessImplT2.calculateSum());
    }

    @Test
    public void calculateSum_empty() {
        when(dataServiceMock.retriveAllData()).thenReturn(new int[] {});
        assertEquals(0,someBusinessImplT2.calculateSum());
    }

    @Test
    public void calculateSum_oneValue() {
        when(dataServiceMock.retriveAllData()).thenReturn(new int[] {3});
        assertEquals(3, someBusinessImplT2.calculateSum());
    }


}
