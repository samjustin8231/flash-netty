package the.flash.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * 实现空闲检测功能
 */
public class IMIdleStateHandler extends IdleStateHandler {

    private static final int READER_IDLE_TIME = 15;

    public IMIdleStateHandler() {
        // 读空闲时间: 指的是在这段时间内如果没有数据读到，就表示连接假死
        // 参数列表：读空闲时间, 写空闲时间, 读写空闲时间
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    /**
     * 连接假死之后会回调 channelIdle() 方法
     *
     * @param ctx
     * @param evt
     */
    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) {
        System.out.println(READER_IDLE_TIME + "秒内未读到数据，关闭连接");
        ctx.channel().close();
    }
}
