package com.learn.spring.UnitTest.ServiceLayer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.learn.spring.UnitTest.Entity.ItemJPA;
import com.learn.spring.UnitTest.Repository.ItemJPARepository;

/*
 * Annotation to perform unit test on data layer, but by default it looks for the default inmemory database
 * to exeucte the test cases 
 */
@DataJpaTest
/*
 * To override spring behaviour of using inmemory database. 
 * It let us connect to the database refined in application.yml
 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ItemJPALayerTest {
    
    @Autowired
    ItemJPARepository itemJPARepository;

    @Test
    public void retriveAllValues() {
        List<ItemJPA> items = itemJPARepository.findAll();
        assertEquals(1, items.size());

    }

}
