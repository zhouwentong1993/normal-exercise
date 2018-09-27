package com.finup.demo.registration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class RegistrationCenter {
    private Map<Client, String> clientSubscription = new ConcurrentHashMap<>();

    private List<RegistrationServerInfo> serviceRegistration = new ArrayList<>();

    public void serviceRegister(RegistrationServerInfo registrationServerInfo) {
        Objects.requireNonNull(registrationServerInfo, "注册信息不能为空");
        Objects.requireNonNull(registrationServerInfo.getServiceName(), "服务名称不能为空");
        Objects.requireNonNull(registrationServerInfo.getQualifiedName(), "全限定名不能为空");
        for (RegistrationServerInfo serverInfo : serviceRegistration) {
            if (serverInfo.equals(registrationServerInfo)) {

            }
        }
    }

    public void serviceUpdateEvent() {

    }

    public void serviceAddEvent() {

    }
}
