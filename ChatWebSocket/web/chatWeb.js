/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global angular, tempoTranscurrido */

app = angular.module('app', []);
app.controller('chatweb', function ($scope, $http) {

    $scope.messagesend = [];
    $scope.user = "";

    $scope.viewChat = false;

    onOpen();

    $scope.acceder = () => {

        if ($("#nameUser").val().trim() === "") {
            alert("Ingresa tu nombre por fa :v");
            return;
        }


        let message = {
            "config": "init",
            "user": {
                "name": $scope.user,
                content: ""
            }
        };

        $scope.user = $("#nameUser").val();
        $scope.viewChat = true;

        sendMessage(message);
    };

    $scope.sendChat = () => {
        
        const tiempoTranscurrido = Date.now();
        const hoy = new Date(tiempoTranscurrido);

        let message = {
            "config": "chat",
            "user": {
                "name": $scope.user,
                content: $("#message_input").val(),
                date: hoy.toUTCString()
            }
        };

        let message_front = {
            "header": $scope.user,
            content: $("#message_input").val(),
            "type": "R",
            date: hoy.toUTCString()
        };

        $scope.messagesend.push(message_front);

        console.log(message);
        sendMessage(message);
        $("#message_input").val("");
    };

    $scope.responseChat = (response) => {
        $scope.$apply(() => {
            $scope.messagesend.push(response);
        });
    };

});


