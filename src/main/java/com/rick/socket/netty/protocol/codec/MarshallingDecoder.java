package com.rick.socket.netty.protocol.codec;

import com.rick.socket.netty.protocol.util.MarshallingCodeCFactory;
import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.Unmarshaller;

import java.io.IOException;

public class MarshallingDecoder {

    private final Unmarshaller unmarshaller;

    public MarshallingDecoder() throws IOException {
        this.unmarshaller = MarshallingCodeCFactory.buildUnmarshalling();
    }

    public Object decode(ByteBuf in) throws IOException {
        int size = in.readInt();
        ByteBuf slice = in.slice(in.readerIndex(), size);
        ChannelBufferByteInput input = new ChannelBufferByteInput(slice);
        try {
            unmarshaller.start(input);
            Object obj = unmarshaller.readObject();
            unmarshaller.finish();
            in.readerIndex(in.readerIndex() + size);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            unmarshaller.close();
        }
        return null;
    }
}
