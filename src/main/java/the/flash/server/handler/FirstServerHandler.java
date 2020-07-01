package the.flash.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author chao.yu
 * chao.yu@dianping.com
 * @date 2018/08/04 06:21.
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 一种是正常的字符串输出。
     * 一种是多个字符串“粘”在了一起，我们定义这种 ByteBuf 为粘包。
     * 一种是一个字符串被“拆”开，形成一个破碎的包，我们定义这种 ByteBuf 为半包。
     *
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;

        System.out.println(new Date() + ": 服务端读到数据 -> " + byteBuf.toString(Charset.forName("utf-8")));
    }
}
