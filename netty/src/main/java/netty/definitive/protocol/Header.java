package netty.definitive.protocol;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.util.Map;

/**
 * 自定义协议 header 部分，长度不固定
 */
public class Header implements Serializable {

    private static final long serialVersionUID = 1L;

    private int crcCode = 0xcafebabe; // 校验码，类似于 Java 的 Magic Number
    private int length; // 消息头长度
    private long sessionId; // 请求唯一 id
    private byte type; // 业务类型
    private byte priority; // 请求优先级
    private Map<String, Object> attachment; // 自定义部分

    public int getCrcCode() {
        return crcCode;
    }

    public void setCrcCode(int crcCode) {
        this.crcCode = crcCode;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public Map<String, Object> getAttachment() {
        return attachment;
    }

    public void setAttachment(Map<String, Object> attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
