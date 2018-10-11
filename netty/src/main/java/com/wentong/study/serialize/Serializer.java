package com.wentong.study.serialize;

public interface Serializer {

    Byte getSerializerAlgorithm();

    byte[] serializer(Object object);

    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
