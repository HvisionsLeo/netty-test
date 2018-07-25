package com.unitill.netty;

import com.unitill.netty.client.Client;
import com.unitill.netty.server.Server;
import com.unitill.netty.thridserver.ThirdServer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: Leo
 * @Date: 2018-04-26 下午 2:03
 */
public class Main {

    private static ExecutorService executorService = Executors.newFixedThreadPool(4);

    public static void main(String[] args) {
        executorService.execute(() -> {
            ThirdServer thirdServer = new ThirdServer();
            thirdServer.run();
        });
        executorService.execute(() -> {
            Server server = new Server();
            server.run();
        });
        executorService.execute(() -> {
            Client client = new Client();
            client.run();
        });
    }
}
