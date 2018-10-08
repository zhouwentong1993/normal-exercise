package com.finup.demo.registration;

import java.util.HashSet;
import java.util.Set;

public class Client {

    private Set<RegistrationServerInfo> set = new HashSet<>();

    private String clientId;
    private String subscribedServiceName;

    public String getSubscribedServiceName() {
        return subscribedServiceName;
    }

    public boolean received(RegistrationServerInfo registrationServerInfo) {
        return true;
    }

}
