package com.wentong

class GroovySample {

    static def s = "World"

    static void main(String[] args) {
        println "hello $s"
        def list = []
        if (list) {
            println "not empty"
        } else {
            println "empty"
        }


        def company = new Company()
        company.name = "张三"
        company.name = "李四"
        company.name = "王五"
        println company.name
        println company.name
        if (company.content?.address) {
            println company.content.address
        }
    }

    static class Company {
        String name
        private JavaSample.Content content;
    }

    static class Content {
        private JavaSample.Address address;

    }

    static class Address {
        String address;
    }

}
