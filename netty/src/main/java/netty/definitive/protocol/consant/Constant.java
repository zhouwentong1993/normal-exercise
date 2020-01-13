package netty.definitive.protocol.consant;

public final class Constant {

    private Constant(){}

    // 代表登录请求
    public static byte LOGIN_REQUEST = 1;

    // 代表登录响应
    public static byte LOGIN_RESPONSE = 2;

    // 未知请求
    public static byte UNKNOWN_REQUEST = -1;

}
