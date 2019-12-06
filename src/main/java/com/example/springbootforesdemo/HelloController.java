package com.example.springbootforesdemo;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloController {

    @Resource
    private EsDemoApplicationTests esDemoApplicationTests;

    @RequestMapping("/hello")
    public String hello(){
        return "hello,this is springboot.";
    }

    @RequestMapping("/search")
    public Page<Item> search(){
        return esDemoApplicationTests.testWildCardQuery();
    }
}
