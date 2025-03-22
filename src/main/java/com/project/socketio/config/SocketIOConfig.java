package com.project.socketio.config;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocketIOConfig {

    //application.yml에 명시된 host 호출
    @Value("${socketio.host}")
    private String host;

    //application.yml에 명시된 port 호출
    @Value("${socketio.port}")
    private int port;

    //application.yml에 작성된 옵션들을 socketIOServer에 적용하기 위한 생성자. + bean 등록
    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname(host);
        config.setPort(port);
        // 추가 옵션 설정 가능 (예: config.setOrigin(...))
        return new SocketIOServer(config);
    }
}


