package the.flash.server.handler.outbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * ChannelOutboundHandlerAdapter 处理写数据的逻辑
 */
public class OutBoundHandlerA extends ChannelOutboundHandlerAdapter {

    /**
     * 父类的 write() 方法会自动调用到下一个 outBoundHandler 的 write() 方法，
     * 并且会把当前 outBoundHandler 里处理完毕的对象传递到下一个 outBoundHandler
     *
     * @param ctx
     * @param msg
     * @param promise
     * @throws Exception
     */
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("OutBoundHandlerA: " + msg);
        super.write(ctx, msg, promise);
    }
}
