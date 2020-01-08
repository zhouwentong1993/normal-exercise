package netty.definitive.protocol.util;

import io.netty.buffer.ByteBuf;
import netty.definitive.protocol.codec.ChannelBufferByteOutput;
import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

import java.io.IOException;

public final class MarshallingEncoder {

    // 间隔符
    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];

    private static Marshaller marshaller;

    public MarshallingEncoder() {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        try {
            marshaller = marshallerFactory.createMarshaller(configuration);
        } catch (IOException e) {
            System.exit(-1);
        }
    }

    public void encode(Object msg, ByteBuf out) throws IOException {
        int writerIndex = out.writerIndex();
        out.writeBytes(LENGTH_PLACEHOLDER);
        ChannelBufferByteOutput channelBufferByteOutput = new ChannelBufferByteOutput(out);
        marshaller.start(channelBufferByteOutput);
        marshaller.writeObject(msg);
        marshaller.finish();
        // 设置读取的内容，在 writeIndex 上，设置的值是 msg 大小。
        // 因为此时游标已经过了，所以只能通过 setInt 来在指定的位置设置
        out.setInt(writerIndex, out.writerIndex() - writerIndex - 4);
        marshaller.close();
    }

}
