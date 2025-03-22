package com.project.socketio.runner;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

// Application 동작할 때 실행
@Component
@AllArgsConstructor
public class SocketIOServerRunner implements ApplicationRunner {

    //의존성 추가
    private final SocketIOServer socketIOServer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        socketIOServer.start();
        System.out.println("Socket.IO server 구동 성공! ");
        System.out.println("host Ip " + socketIOServer.getConfiguration().getHostname());
        System.out.println("port : " + socketIOServer.getConfiguration().getPort());
    }
}
