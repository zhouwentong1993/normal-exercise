package com.finup.demo.registration;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class RegistrationCenter {

    private static final int MAX_RETRY_COUNT = 10;

    private List<Client> clients = new ArrayList<>();

    private Set<RegistrationServerInfo> serviceRegistration = new HashSet<>();

    public void serviceRegister(RegistrationServerInfo registrationServerInfo) {
        Objects.requireNonNull(registrationServerInfo, "注册信息不能为空");
        Objects.requireNonNull(registrationServerInfo.getServiceName(), "服务名称不能为空");
        Objects.requireNonNull(registrationServerInfo.getQualifiedName(), "全限定名不能为空");

        if (Utils.setReplace(serviceRegistration, registrationServerInfo)) {
            serviceAddEvent(registrationServerInfo);
        } else {
            serviceUpdateEvent(registrationServerInfo);
        }
    }

    /**
     * 服务更新事件
     */
    private void serviceUpdateEvent(RegistrationServerInfo registrationServerInfo) {
        clients.forEach(client -> {
            if (client.getSubscribedServiceName().equals(registrationServerInfo.getServiceName())) {
                notifyClient(registrationServerInfo, client);
            }
        });
    }

    /**
     * 服务新增事件
     */
    private void serviceAddEvent(RegistrationServerInfo registrationServerInfo) {
        clients.forEach(client -> notifyClient(registrationServerInfo, client));
    }

    private void notifyClient(RegistrationServerInfo registrationServerInfo, Client client) {
        CompletableFuture.runAsync(() -> {
            int retryCount = 0;
            boolean received = client.received(registrationServerInfo);
            while (!received && retryCount <= MAX_RETRY_COUNT) {
                retryCount = retryCount + 1;
                try {
                    TimeUnit.SECONDS.sleep(retryCount * 2L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                received = client.received(registrationServerInfo);
            }
        });
    }
}
