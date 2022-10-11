package com.learn.spring.UnitTest.Controller;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.learn.spring.UnitTest.Entity.ItemJPA;
import com.learn.spring.UnitTest.Model.Item;
import com.learn.spring.UnitTest.ServiceLayer.ItemBusinessService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ItemBusinessService businessService;

    @Test
    void testListItem() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                            .get("/hello-item").accept(MediaType.APPLICATION_JSON);
        
        mockMvc.perform(request)
                    .andExpect(status().isOk())
                    .andExpect(content().json("{\"id\":0,\"name\":\"Nijanthan\",\"price\":0,\"quantity\":0}"))
                    .andReturn();
        // JSONAssert.assertEquals(expected, actual, strict);
    }

    @Test
    void testListItem_JSONAssert() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                            .get("/hello-item").accept(MediaType.APPLICATION_JSON);
        
        MvcResult mvcResult = mockMvc.perform(request)
                    .andExpect(status().isOk())
                    //.andExpect(content().json("{\"id\":0,\"name\":\"Nijanthan\",\"price\":0,\"quantity\":0}"))
                    .andReturn();
        
        String actualResult = mvcResult.getResponse().getContentAsString();
        System.out.println(actualResult);
        
        String expectedResults = "{\"id\":0,\"name\":\"Nijanthan\",\"price\":0,\"quantity\":0}";
        
        JSONAssert.assertEquals(expectedResults, actualResult, false);

        String expectedResults1 = "{\"id\":0,\"name\":\"Nijanthan\",\"price\":0,\"quantity\":0}";

        JSONAssert.assertEquals(expectedResults1, actualResult, true);
    }

    @Test
    void testItemFromBusinessService() throws Exception{

        when(businessService.retriveHardCodedItem()).thenReturn(new Item(1, "nij", 10, 0));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/item-business-service")
                                .content(MediaType.APPLICATION_JSON_VALUE);
        
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
        .andExpect(content().json("{id:1,name:nij,price:10}")).andReturn();
        
    }

    @Test
    void testRetriveAllItemsJPA_controlLayer() throws Exception{

        when(businessService.retriveAllValues()).thenReturn(
            Arrays.asList(new ItemJPA(1, "nij", 10, 10),
            new ItemJPA(1,"aaa",20,30))
            );

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/item-from-jpa-repository")
                                .content(MediaType.APPLICATION_JSON_VALUE);
        
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
        .andExpect(content().json("[{id:1,name:nij,price:10},{}]")).andReturn();
        
    }



}
