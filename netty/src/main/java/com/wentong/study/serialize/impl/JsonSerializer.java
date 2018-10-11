package com.wentong.study.serialize.impl;

import com.alibaba.fastjson.JSONObject;
import com.wentong.study.serialize.Serializer;
import com.wentong.study.serialize.SerializerAlgorithm;

public class JsonSerializer implements Serializer {
    @Override
    public Byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON_ALGORITHM;
    }

    @Override
    public byte[] serializer(Object object) {
        return JSONObject.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSONObject.parseObject(bytes, clazz);
    }
}
