package com.indelpa.visitas.websocket;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.indelpa.visitas.models.Visitas;
import com.indelpa.visitas.services.VisitasService;

@Component
public class VisitaWebSocketHandler extends TextWebSocketHandler {

    private final VisitasService visitasService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    public VisitaWebSocketHandler(VisitasService visitasService) {
        this.visitasService = visitasService;
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        enviarListarVisitas(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        enviarListarVisitas(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) {
        sessions.remove(session);
    }

    public void enviarListarVisitas() {
        List<Visitas> visitas = visitasService.getAllVisitas();
        try {
            String json = objectMapper.writeValueAsString(visitas);
            for (WebSocketSession session : sessions) {
                if (session.isOpen()) {
                    session.sendMessage(new TextMessage(json));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void enviarListarVisitas(WebSocketSession session) {
        List<Visitas> visitas = visitasService.getAllVisitas();
        try {
            String json = objectMapper.writeValueAsString(visitas);
            session.sendMessage(new TextMessage(json));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
