package com.learn.spring.UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.learn.spring.UnitTest.BusinessLayer.SomeBusinessImplT2;
import com.learn.spring.UnitTest.DataLayer.SomeDataService;

@SpringBootTest
public class SomeBusinessTestMockT3_1 {

    @Autowired
    SomeBusinessImplT2 someBusinessImplT2;

    @Test
    public void calculateSum_basic() {

        SomeDataService dataServiceMock = mock(SomeDataService.class);
        //<mockobject> <method to call> (retriveAllData()) <what to return> ( new int[] {1,2,3})
        when(dataServiceMock.retriveAllData()).thenReturn(new int[] {1,2,3});
        
        someBusinessImplT2.setSomeDataService(dataServiceMock);

        int actualValue = someBusinessImplT2.calculateSum();
        int expectedValue = 6;
        assertEquals(expectedValue,actualValue);
    }

    @Test
    public void calculateSum_empty() {

        SomeDataService dataServiceMock = mock(SomeDataService.class);
        when(dataServiceMock.retriveAllData()).thenReturn(new int[] {});

        someBusinessImplT2.setSomeDataService(dataServiceMock);
        int actualValue = someBusinessImplT2.calculateSum();
        int expectedValue = 0;
        assertEquals(expectedValue,actualValue);
    }

    @Test
    public void calculateSum_oneValue() {
        SomeDataService dataServiceMock = mock(SomeDataService.class);
        when(dataServiceMock.retriveAllData()).thenReturn(new int[] {3});

        someBusinessImplT2.setSomeDataService(dataServiceMock);
        int actualValue = someBusinessImplT2.calculateSum();
        int expectedValue = 3;
        assertEquals(expectedValue,actualValue);
    }


}
