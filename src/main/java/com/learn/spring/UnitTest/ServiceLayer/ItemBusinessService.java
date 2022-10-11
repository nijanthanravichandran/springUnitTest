package com.learn.spring.UnitTest.ServiceLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.learn.spring.UnitTest.Entity.ItemJPA;
import com.learn.spring.UnitTest.Model.Item;
import com.learn.spring.UnitTest.Repository.ItemJPARepository;

@Component
public class ItemBusinessService {

    @Autowired
    ItemJPARepository itemJPARepository;    

    public Item retriveHardCodedItem() {

        Item item = new Item(1, "Nij", 10, 0);
        return item;
    }

    public List<ItemJPA> retriveAllValues() {
        List<ItemJPA> itemJPAs = itemJPARepository.findAll();

        for (ItemJPA items : itemJPAs) {
            items.setValue(items.getPrice()*items.getQuantity());
        }

       return itemJPARepository.findAll();
    }
    
}
