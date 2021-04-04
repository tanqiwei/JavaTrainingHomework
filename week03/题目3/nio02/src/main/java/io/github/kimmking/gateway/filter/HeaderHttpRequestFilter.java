package io.github.kimmking.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class HeaderHttpRequestFilter implements HttpRequestFilter {

    public static final String ACTION = "action";

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        String uri = fullRequest.uri();
        if (uri.startsWith("/hello")) {
            fullRequest.headers().set(ACTION, "hello");
        } else if (uri.contains("/test")) {
            fullRequest.headers().set(ACTION, "test");
        } else {
            fullRequest.headers().set(ACTION, "common");
        }
    }
}
