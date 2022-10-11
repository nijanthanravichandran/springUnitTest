package com.learn.spring.UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ListMockTest {

    List<String> mock = mock(List.class);

    @Test
    public void size_basic() {
        when(mock.size()).thenReturn(5);
        assertEquals(5, mock.size());
    }

    @Test
    public void returnDifferentValues() {
        when(mock.size()).thenReturn(5).thenReturn(10);
        assertEquals(5, mock.size());
        assertEquals(10, mock.size());
    }

    /*
     * If the index value doesn't contain any declared values, it returns null
     */
    @Test
    public void returnWithParams() {
        when(mock.get(0)).thenReturn("1stObject");
        assertEquals("1stObject", mock.get(0));
        assertEquals(null, mock.get(1));
    }

    /*
     * Mockito has set of argument matchers which helps to pass generic param.
     * 
     * one of the argument matcher is anyInt(), it matches any integer value passed and 
     * returns result
     * 
     * Now for 0 & 1st posititon its returns same value
     */

    @Test
    public void returnWithGenericParams() {
        when(mock.get(anyInt())).thenReturn("1stObject");
        assertEquals("1stObject", mock.get(0));
        assertEquals("1stObject", mock.get(1));
    }

    /*
     * Mockito also provides option to test weather a particular method is called or not.
     * 
     * Ex: Lets i've a repository class, i want to verify weather the save method is executed or not.
     */

    @Test
    public void verificationBasics() {
        //Get is my method to check weather it is called
        String val1 = mock.get(0);
        String val2 = mock.get(1);

        //verifcation
        /*
         * Verifying get method is called on with argument 0
         */
        verify(mock).get(0);
        
        /*
         * Verifying get method is called, as we are using anyInt(), it is considering
         * line 67 & 68 calls as two calls for get method. Verify by default expects only one,
         * in such scenario, we need define the times of the call
         */
        verify(mock,times(2)).get(anyInt());

        verify(mock, atLeast(1)).get(anyInt());

        verify(mock, atLeastOnce()).get(anyInt());

        verify(mock, atMost(2)).get(anyInt());

        /*
         * Get method is never called with argument 2, this test confirms it
         */
        verify(mock, never()).get(2);
        
    }

    @Test
    public void argumentCapturing() {
        mock.add("Something");

        /*
         * Defining captor to store the type of argument.
         * In our example, we are going to store string value in captor
         */
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        /*
         * Here, whatever argument i pass to add method will get captured in the captor
         * variable created above
         */
        verify(mock).add(captor.capture());

        /*
         * Validating the captured value against actual value
         */
        assertEquals("Something", captor.getValue());

    }

    @Test
    public void multipleArgumentCapturing() {
        
        mock.add("Something1");
        mock.add("Something2");

       
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        /*
         * Here, the mock method is called twice, hence change the times to 2
         */
        verify(mock, times(2)).add(captor.capture());

        /*
         * Validating the captured value against actual value
         */
        List<String> allValues = captor.getAllValues();

        assertEquals("Something1", allValues.get(0));
        assertEquals("Something2", allValues.get(1));

    }

    @Test
    public void mocking() {
        /*
         * Here the mockito creates a dummy array list from arraylist class to test
         */
        ArrayList arrayMockList = mock(ArrayList.class);

        /*
         * as mockito already created dummy list for us, mock.get(0) is not throwing
         * any error, even though we didn't add any elements to the list
         */
        System.out.println(arrayMockList.get(0));

    }

    /*
     * Spying is another function in mockito will let you test a class without
     * impacting/chaning/dummying its behaviour
     */

    @Test
    public void spying() {

        ArrayList arraySpyList = spy(ArrayList.class);

        /*
         * Here the below statement will throw array out of bound exception
         * as we didn't add any element to the list yet
         */
       // System.out.println(arraySpyList.get(0));

       arraySpyList.add(1);
       System.out.println(arraySpyList.get(0));

    }

}

