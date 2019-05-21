package com.github.martinfrank.catansettler;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class HangManTest {


    @Test
    public void mainTest() {
        try {
            InputStream original = System.in;
            System.setIn(new ByteArrayInputStream("exit\n".getBytes()));
            App.main(new String[]{});
            System.setIn(original);
        } catch (Exception e) {
            Assert.fail();
        }
    }
}
