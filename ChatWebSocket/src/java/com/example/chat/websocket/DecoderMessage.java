/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.chat.websocket;

import java.io.IOException;
import java.io.Reader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * 
 * @author dcarvajals
 */
public class DecoderMessage implements Decoder.TextStream<JsonObject>{

    @Override
    public JsonObject decode(Reader reader) throws DecodeException, IOException {
        
        javax.json.JsonObject json = Json.createObjectBuilder().build();
        javax.json.stream.JsonParser parser = Json.createParser(reader);
        if (parser.hasNext()) {
            parser.next();
            try {
                json = parser.getObject();

                parser.close();
            } catch (Exception e) {
                System.out.println("deconding say:" + e.getMessage());
                return Json.createObjectBuilder().build();
            }
        }

        return json;
        
    }

    @Override
    public void init(EndpointConfig ec) {
        
    }

    @Override
    public void destroy() {
        
    }

}
