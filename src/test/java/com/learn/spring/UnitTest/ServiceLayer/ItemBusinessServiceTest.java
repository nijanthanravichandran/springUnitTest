package com.learn.spring.UnitTest.ServiceLayer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.learn.spring.UnitTest.Entity.ItemJPA;
import com.learn.spring.UnitTest.Repository.ItemJPARepository;


@SpringBootTest
public class ItemBusinessServiceTest {

    @Autowired
    ItemBusinessService itemBusinessService;

    @MockBean
    ItemJPARepository itemJPARepository;

    @Test
    void testRetriveAllValues() {
        when(itemJPARepository.findAll()).thenReturn(
            Arrays.asList(new ItemJPA(1, "nij", 10, 10),
            new ItemJPA(1,"aaa",20,30))
        );

        List<ItemJPA> items = itemBusinessService.retriveAllValues();

        assertEquals(100, items.get(0).getValue());
        assertEquals(600, items.get(1).getValue());;
        ;

    }

}
