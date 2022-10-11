package com.learn.spring.UnitTest.BusinessLayer;

import org.springframework.stereotype.Service;
import com.learn.spring.UnitTest.DataLayer.SomeDataService;
import lombok.Setter;

/*
 * In the first example, simple sum function is defined by getting data from function intput.
 * Here, the data is coming from data layer. Just to replicate the real world scenario, where data 
 * usally comes from data layer.
 */


@Service
@Setter
public class SomeBusinessImplT2 {

    //@Autowired
    SomeDataService someDataService;

    public int calculateSum() {
        int sum=0;
        int[] a = someDataService.retriveAllData();
        for (int i:a){
            sum += i;    
        }
        return sum;
    }



    
}
