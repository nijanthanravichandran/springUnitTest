package com.learn.spring.UnitTest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.spring.UnitTest.Entity.ItemJPA;

@Repository
public interface ItemJPARepository extends JpaRepository<ItemJPA,Integer>{
    
}
