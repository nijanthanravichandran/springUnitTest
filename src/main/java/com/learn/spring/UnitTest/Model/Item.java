package com.learn.spring.UnitTest.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Item {

    private int id;
    private String name;
    private int price;
    private int quantity;

}
