package netty.definitive.protocol.consant;

public final class Constant {

    private Constant(){}

    // 代表登录请求
    public static final byte LOGIN_REQUEST = 1;

    // 代表登录响应
    public static final byte LOGIN_RESPONSE = 2;

    // 未知请求
    public static final byte UNKNOWN_REQUEST = -1;

    // 心跳请求
    public static final byte HEART_BEAT_REQUEST = 3;

    // 心跳响应
    public static final byte HEART_BEAT_RESPONSE = 4;

}
