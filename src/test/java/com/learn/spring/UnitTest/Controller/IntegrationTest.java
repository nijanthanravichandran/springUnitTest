package com.learn.spring.UnitTest.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void contextLoads() {
        String response = testRestTemplate.getForObject("/item-from-jpa-repository",String.class);
        assertEquals("[{\"id\":5,\"name\":\"iii\",\"price\":20,\"quantity\":10,\"value\":200}]",
         response);
    }
    
}
