/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global myDiagram, angular, obj */
"use strict";
var url_socket = "ws://"+window.location.host+"/ChatWebSocket/ws_sharing";
var websocket = new WebSocket(url_socket);

websocket.onopen = onOpen;
websocket.onclose = onClose;
websocket.onmessage = onMessage;
websocket.onerror = onError;

var onOpen = () => {
    console.log("Websocket concetado");
};

var onClose = (evt) => {
    console.log("Websocket cerrado");
    console.log(evt);
};

var onError = (evt) => {
    console.log("Error: ", evt);
};

function onMessage (evt) {
    let response = JSON.parse(evt.data);
    console.log(evt);
     let message_front = {
            "header": response.user.name,
             content: response.user.content,
             "type": "L",
             date: response.user.date
        };
        
    if(response.config === "chat"){
        // colocar codigo
        angular.element($('[ng-controller="chatweb"]')).scope().responseChat(message_front);
        console.log(message_front.content);
    }
};

var sendMessage = (param) => {
    let obj_json = JSON.stringify(param);
    if(obj_json.length < 10000500){
        websocket.send(obj_json);
    }else {
        console.log("Mensaje es demasiado largo...");
    }
};


