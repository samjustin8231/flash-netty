package the.flash.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import the.flash.protocol.PacketCodeC;

/**
 * 拆包器， 基于长度域拆包器 LengthFieldBasedFrameDecoder
 */
public class Spliter extends LengthFieldBasedFrameDecoder {

    /**
     * 协议：魔数(4byte) + 版本(1byte) + 序列化算法(1bype) + 指令(1byte) + 数据长度(4bype) + 数据(nbyte)
     * 偏移量：4 + 1 + 1 + 1 = 7
     */
    private static final int LENGTH_FIELD_OFFSET = 7;
    /**
     * 长度域：4
     */
    private static final int LENGTH_FIELD_LENGTH = 4;

    public Spliter() {
        //  maxFrameLength：数据包的最大长度，lengthFieldOffset：长度域的偏移量，lengthFieldLength：长度域的长度
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        // 根据魔数判断是否是本协议的内容
        if (in.getInt(in.readerIndex()) != PacketCodeC.MAGIC_NUMBER) {
            ctx.channel().close();
            return null;
        }

        return super.decode(ctx, in);
    }
}
