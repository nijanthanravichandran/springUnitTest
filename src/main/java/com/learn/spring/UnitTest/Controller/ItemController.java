package com.learn.spring.UnitTest.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.spring.UnitTest.Entity.ItemJPA;
import com.learn.spring.UnitTest.Model.Item;
import com.learn.spring.UnitTest.ServiceLayer.ItemBusinessService;

@RestController
public class ItemController {

    @Autowired
    ItemBusinessService businessService;
    

    @GetMapping("/hello-item")
    public Item listItem() {
        Item item = new Item(0, "Nijanthan", 0, 0);
        return item;
    }

    @GetMapping("/item-business-service")
    public Item itemFromBusinessService() {
        return businessService.retriveHardCodedItem();
    }
    
    @GetMapping("/item-from-jpa-repository")
    public List<ItemJPA> itemFromJPARepository() {
        return businessService.retriveAllValues(); 
    }
}
