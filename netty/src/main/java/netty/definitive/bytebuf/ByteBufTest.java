package netty.definitive.bytebuf;

import org.junit.Test;

import java.nio.ByteBuffer;

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
}
