package netty.definitive.protocol.util;

import io.netty.buffer.ByteBuf;
import netty.definitive.protocol.codec.ChannelBufferByteInput;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;
import org.jboss.marshalling.Unmarshaller;

import java.io.IOException;

public class MarshallingDecoder {

    private static Unmarshaller unmarshaller;

    public MarshallingDecoder() {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        try {
            unmarshaller = marshallerFactory.createUnmarshaller(configuration);
        } catch (IOException e) {
            System.exit(-1);
        }
    }

    public Object decode(ByteBuf in) throws Exception {
        int objectSize = in.readInt();
        ByteBuf byteBuf = in.slice(in.readerIndex(), objectSize);
        ChannelBufferByteInput input = new ChannelBufferByteInput(byteBuf);
        unmarshaller.start(input);
        Object object = unmarshaller.readObject();
        unmarshaller.finish();
        in.readerIndex(in.readerIndex() + objectSize);
        unmarshaller.close();
        return object;
    }
}
