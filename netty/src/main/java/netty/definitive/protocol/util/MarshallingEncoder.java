package netty.definitive.protocol.util;

import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

import java.io.IOException;

public final class MarshallingEncoder {

    // 间隔符
    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];

    private static Marshaller marshaller = getInstance();

    private static Marshaller getInstance() {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        try {
            return marshallerFactory.createMarshaller(configuration);
        } catch (IOException e) {
            System.exit(-1);
            // unreachable
            return null;
        }
    }

    public void encode(Object msg, ByteBuf out) {
        int writerIndex = out.writerIndex();
        out.writeBytes(LENGTH_PLACEHOLDER);
    }

}
