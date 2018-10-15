package com.wentong.study.serialize;

import com.wentong.study.serialize.impl.JsonSerializer;

public interface Serializer {

    Serializer DEFAULT = new JsonSerializer();

    Byte getSerializerAlgorithm();

    byte[] serializer(Object object);

    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
