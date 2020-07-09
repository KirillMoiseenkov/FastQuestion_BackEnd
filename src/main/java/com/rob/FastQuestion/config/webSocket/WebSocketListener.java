package com.rob.FastQuestion.config.webSocket;

import com.rob.FastQuestion.models.Answer;
import com.rob.FastQuestion.service.FileSaverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import static java.lang.String.format;

@Component
public class WebSocketListener {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    FileSaverService fileSaverService;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        Answer answer = new Answer();
        answer.setText("Wow");
        messagingTemplate.convertAndSend("/topic/user", answer);
        fileSaverService.saveFile();
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        System.out.println("kurlik");
    }


}
