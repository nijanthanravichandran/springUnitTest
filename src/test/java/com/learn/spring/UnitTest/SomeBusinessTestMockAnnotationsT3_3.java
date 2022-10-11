package com.learn.spring.UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.learn.spring.UnitTest.BusinessLayer.SomeBusinessImplT2;
import com.learn.spring.UnitTest.DataLayer.SomeDataService;

/*
 * Here we have used mock annotations to inject mocks instead of creating manually
 */

 /*
  * Use annotation extend with mockitoextension
  */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SomeBusinessTestMockAnnotationsT3_3 {

    /*
     * InjectMocks annonation is to inject mocks into the given class
     */
    @Autowired
    @InjectMocks
    SomeBusinessImplT2 someBusinessImplT2;

    /*
     * Mock annonation is to inject mocks into given method
     */
    @Mock
    SomeDataService dataServiceMock;

    @BeforeEach
    public void before() {
        someBusinessImplT2.setSomeDataService(dataServiceMock);
    }

    @Test
    public void calculateSum_basic() {
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
