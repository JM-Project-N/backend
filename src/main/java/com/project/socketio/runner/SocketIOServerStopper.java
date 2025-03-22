package com.project.socketio.runner;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

// Application 종료되었을 때 실행
@Component
@AllArgsConstructor
public class SocketIOServerStopper implements DisposableBean {

    private final SocketIOServer socketIOServer;

    @Override
    public void destroy() throws Exception {
        socketIOServer.stop();
        System.out.println("Socket.IO server 구동 종료!");
    }
}