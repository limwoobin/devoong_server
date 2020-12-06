package com.drogbalog.server;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ServerTest {
    private List<String> list;

    @BeforeEach
    public void before() {
        list = new ArrayList<>(Arrays.asList("a" , "b" , "c"));
        System.out.println("Before");
    }

    @Test
    public void simpleTest() {
        System.out.println(list.size());
        System.out.println("now");
    }

    @AfterEach
    public void after() {
        System.out.println("After");
    }
}
