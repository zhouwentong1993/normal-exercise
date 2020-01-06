package netty.definitive.messagepack;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.msgpack.annotation.Message;

/**
 * 为测试编解码的对象
 */
@Message
public class UserInfo {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
