package netty.definitive.protocol;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 自定义协议信息
 */
public class NettyMessage {

    private Header header;
    private Object body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
