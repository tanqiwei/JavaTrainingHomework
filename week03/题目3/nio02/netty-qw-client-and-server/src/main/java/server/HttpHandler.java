package server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.ReferenceCountUtil;

import java.util.Objects;
import java.util.Optional;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaderNames.KEEP_ALIVE;

/**
 * @author tqw
 */
public class HttpHandler extends ChannelInboundHandlerAdapter {


    public static final String ACTION = "action";
    public static final String COMMON = "common";
    public static final String HELLO = "hello";

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpResponse response = null;
        FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
        try {
            String action = Optional.ofNullable(fullHttpRequest.headers())
                    .map(headers -> headers.get(ACTION)).orElse(COMMON);

            if (Objects.equals(HELLO, action)) {
                response = doAction("hello qiwei.tan");
            } else if (Objects.equals(action, "test")) {
                response = doAction("hello test");
            } else {
                response = doAction("hello common");
            }
        } catch (Exception e) {
            System.out.println("处理出错:" + e.getMessage());
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NO_CONTENT);
        } finally {
            if (Objects.nonNull(fullHttpRequest)) {
                if (!HttpUtil.isKeepAlive(fullHttpRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    if (Objects.nonNull(response)) {
                        response.headers().set(HttpHeaderNames.CONNECTION, KEEP_ALIVE);
                        ctx.write(response);
                    }
                }
            }
            ReferenceCountUtil.release(msg);
        }
    }


    public FullHttpResponse doAction(String msg) {
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
                Unpooled.wrappedBuffer(msg.getBytes())
        );
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
        response.headers().set(CONTENT_LENGTH, response.content().readableBytes());
        return response;
    }


}
