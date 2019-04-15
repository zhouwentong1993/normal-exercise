package com.wentong.demo;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestException {

    public static void main(String[] args) {

        try {

            testExceptions();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public static void testExceptions() throws IOException, FileNotFoundException {

    }

}
