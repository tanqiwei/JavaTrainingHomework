package io.github.kimmking.gateway;


import io.github.kimmking.gateway.inbound.HttpInboundServer;

import java.util.Arrays;
import java.util.Collections;

public class NettyServerApplication {
    
    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "3.0.0";
    
    public static void main(String[] args) {

        String proxyPort = System.getProperty("proxyPort","8888");

        // 这是之前的单个后端url的例子
        String proxyServer = System.getProperty("proxyServer","http://localhost:8088");
//          //  http://localhost:8888/api/hello  ==> gateway API
//          //  http://localhost:8088/api/hello  ==> backend service
        int port = Integer.parseInt(proxyPort);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" starting...");
        HttpInboundServer server = new HttpInboundServer(port, Collections.singletonList(proxyServer));
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" started at http://localhost:" + port + " for server:" + server.toString());
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
