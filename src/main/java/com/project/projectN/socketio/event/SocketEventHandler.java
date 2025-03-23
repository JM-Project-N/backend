package com.project.projectN.socketio.event;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.stereotype.Component;

@Component
public class SocketEventHandler {

    public SocketEventHandler(SocketIOServer server) {
        // 클라이언트 연결 시
        server.addConnectListener(client -> {
            System.out.println("Client connected: " + client.getSessionId());
        });

        // 클라이언트 연결 해제 시
        server.addDisconnectListener(client -> {
            System.out.println("Client disconnected: " + client.getSessionId());
        });

        // 예시: "chat" 이벤트 처리
        server.addEventListener("chat", String.class, (client, data, ackSender) -> {
            System.out.println("Received chat message: " + data);
        });
    }
}
