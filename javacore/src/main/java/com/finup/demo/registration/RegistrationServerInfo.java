package com.finup.demo.registration;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Objects;

public class RegistrationServerInfo {
    private String serviceName;
    private String qualifiedName;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getQualifiedName() {
        return qualifiedName;
    }

    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RegistrationServerInfo that = (RegistrationServerInfo) o;
        return Objects.equals(serviceName, that.serviceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceName);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
