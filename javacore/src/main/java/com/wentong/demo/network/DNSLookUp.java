package com.wentong.demo.network;

import java.net.InetAddress;
import java.net.ServerSocket;

public class DNSLookUp {

    public static void main(String[] args) throws Exception {
        InetAddress[] allByName = InetAddress.getAllByName("www.baidu.com");
        System.out.println(allByName.length);
        System.out.println(allByName[0].getHostAddress());
        System.out.println(allByName[1].getHostAddress());

    }

}
