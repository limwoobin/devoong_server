package com.drogbalog.server;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerTest {

    @Test
    public void simpleTest() {
        assertEquals("z" , "z");
        assertThat("A" , is("A"));
    }

}
