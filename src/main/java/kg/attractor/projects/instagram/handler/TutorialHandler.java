package kg.attractor.projects.instagram.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.*;

@Slf4j
public class TutorialHandler implements WebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Connected to instagram", session.getId());
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String tutorial = message.getPayload().toString();
        session.sendMessage(new TextMessage("Started processing"+session+" "+tutorial));
        Thread.sleep(1000);
        session.sendMessage(new TextMessage("Finished processing"+session+" "+tutorial));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("Transport error", exception.getMessage()+" "+session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.info("Disconnected from instagram", session.getId(), closeStatus.getCode());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
