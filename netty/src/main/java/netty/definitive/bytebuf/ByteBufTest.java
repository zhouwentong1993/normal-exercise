package netty.definitive.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

import java.nio.ByteBuffer;

/*
    在 ByteBuffer 中，切换读写模式需要调用 flip，但是在 ByteBuf 中，不需要，只需要调用 read write skip 等方法就行
 */
public class ByteBufTest {

    @Test
    public void testByteBuffer() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(80);
        byteBuffer.put("你好，周文童".getBytes());
        System.out.println(byteBuffer);
        byteBuffer.flip();
        System.out.println(byteBuffer);
        byte[] bytes = new byte[byteBuffer.remaining()];
        byteBuffer.get(bytes);
        System.out.println(byteBuffer);
        String s = new String(bytes);
        System.out.println(s);
    }

    @Test
    public void testByteBuf() {
        ByteBuf buffer = Unpooled.buffer(10);
        System.out.println(buffer);
        String s = "你好，周文童";
        buffer.writeBytes(s.getBytes());
        System.out.println(buffer);
        byte[] bytes = new byte[s.getBytes().length];
        buffer.readBytes(bytes);
        System.out.println(new String(bytes));
        System.out.println(buffer);
    }
}
