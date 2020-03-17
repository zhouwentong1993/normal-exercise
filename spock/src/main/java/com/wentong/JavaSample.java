package com.wentong;

import java.util.ArrayList;
import java.util.List;

public class JavaSample {

    private static String s = "World";

    public static void main(String[] args) {
        System.out.println("hello" + s);
        List<String> list = new ArrayList<>();
        if (list.isEmpty()) {
            System.out.println("empty");
        }

        Company company = new Company();
        if (company.getContent() != null && company.getContent().getAddress() != null) {
            System.out.println(company.getContent().getAddress());
        }
    }

    static class Company {
        private Content content;

        public Content getContent() {
            return content;
        }
    }

    static class Content {
        private Address address;

        public Address getAddress() {
            return address;
        }
    }

    static class Address {
        String address;

        public String getAddress() {
            return address;
        }
    }
}
