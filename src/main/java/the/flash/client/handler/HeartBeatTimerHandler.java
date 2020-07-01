package the.flash.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import the.flash.protocol.request.HeartBeatRequestPacket;
import the.flash.util.SessionUtil;

import java.util.concurrent.TimeUnit;

/**
 * 心跳处理器
 */
public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {

    private static final int HEARTBEAT_INTERVAL = 5;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        scheduleSendHeartBeat(ctx);

        super.channelActive(ctx);
    }

    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
        // 每隔5s发送一次心跳
        ctx.executor().schedule(() -> {
            if (ctx.channel().isActive()) {
                System.out.println("=====> 发送心跳, userId:" + SessionUtil.getSession(ctx.channel()).getUserId());
                ctx.writeAndFlush(new HeartBeatRequestPacket());
                scheduleSendHeartBeat(ctx);
            }

        }, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }
}
