//package com.wentong.config;
//
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.core.serializer.support.DeserializingConverter;
//import org.springframework.core.serializer.support.SerializingConverter;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.data.redis.serializer.SerializationException;
//
///**
// * Created by zhouwentong on 2018/7/25.
// */
//public class RedisObjectSerializer implements RedisSerializer<Object> {
//
//    private Converter<Object, byte[]> serializer = new SerializingConverter();
//    private Converter<byte[], Object> deserializer = new DeserializingConverter();
//
//    static final byte[] EMPTY_ARRAY = new byte[0];
//
//    @Override
//    public Object deserialize(byte[] bytes) {
//        if (isEmpty(bytes)) {
//            return null;
//        }
//
//        try {
//            return deserializer.convert(bytes);
//        } catch (Exception ex) {
//            throw new SerializationException("Cannot deserialize", ex);
//        }
//    }
//
//    @Override
//    // 序列化和反序列化的时候实体需要实现 Serializable 接口
//    public byte[] serialize(Object object) {
//        if (object == null) {
//            return EMPTY_ARRAY;
//        }
//
//        try {
//            return serializer.convert(object);
//        } catch (Exception ex) {
//            return EMPTY_ARRAY;
//        }
//    }
//
//    private boolean isEmpty(byte[] data) {
//        return (data == null || data.length == 0);
//    }
//}
