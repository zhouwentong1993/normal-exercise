package com.wentong.normal;

import com.finup.demo.registration.RegistrationServerInfo;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class NormalTest {
    @Test
    public void testSet() {
        Set<RegistrationServerInfo> set = new HashSet<>();
        RegistrationServerInfo registrationServerInfo1 = new RegistrationServerInfo();
        registrationServerInfo1.setServiceName("aaa");
        registrationServerInfo1.setQualifiedName("aaa");
        boolean add1 = set.add(registrationServerInfo1);
        System.out.println(add1);
        RegistrationServerInfo registrationServerInfo2 = new RegistrationServerInfo();
        registrationServerInfo2.setServiceName("aaa");
        registrationServerInfo2.setQualifiedName("bbb");
        boolean add = set.add(registrationServerInfo2);
        System.out.println(add);

        System.out.println(set);
    }

    @Test
    public void testWeek() {

    }


}

