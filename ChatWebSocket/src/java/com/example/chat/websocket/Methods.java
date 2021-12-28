/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.chat.websocket;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * 
 * @author dcarvajals
 */
public class Methods {
    
    public static JsonElement securGetJSON(JsonObject jso, String param) {
        try {
            JsonElement res = jso.get(param);//request.getParameter(param);
            return res;
        } catch (Exception e) {
            return null;
        }
    }
    
    public static String JsonToString(JsonObject jso, String param, String defaulx) {
        try {
            JsonElement res = securGetJSON(jso, param);
            if (res != null) {
                return res.getAsString();
            } else {
                return defaulx;
            }
        } catch (Exception e) {
            System.out.println("erro json a string");
            return defaulx;
        }
    }
    
    public static String JsonToSub(JsonObject jso, String param, String defaulx) {
        try {
            JsonElement res = securGetJSON(jso, param);
            if (res != null) {
                return res.toString();
            } else {
                return defaulx;
            }
        } catch (Exception e) {
            System.out.println("erro json a string");
            return defaulx;
        }
    }

}
