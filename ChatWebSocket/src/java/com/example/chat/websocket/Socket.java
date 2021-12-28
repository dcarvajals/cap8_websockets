/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.chat.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonObject;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author dcarvajals
 */
@ServerEndpoint(
        value = "/ws_sharing",
        encoders = {EncoderMessage.class},
        decoders = {DecoderMessage.class}
)
public class Socket {

    private static final List<Session> connected = new ArrayList<>();

    @OnOpen
    public void onOpen(Session sesion) {
        connected.add(sesion);
        System.out.println("new user");
    }

    @OnClose
    public void onClose(Session sesion, CloseReason reason) {
        connected.remove(sesion);
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("wsError:" + t.getMessage());
    }

    @OnMessage
    public void onMessage(Session ses, JsonObject message) throws EncodeException, IOException {
        System.out.println("nuevo message de:" + message);
        System.out.println("sesiones "+ connected.size());
        for (Session isession : connected) {
            if (isession != ses) {
                isession.getBasicRemote().sendObject(message);
            }
        };
    }

}
