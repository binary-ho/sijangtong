package com.binaryho.sijangtong.controller;

import com.binaryho.sijangtong.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message") // app/message
    @SendTo("/chatroom/public") // chatroom/public 이라는 채널을 구독중인 사람들에게  매핑할 떄마다 보냅니다.
    public Message receivePublicMessage(@Payload Message message) {
        return message;
    }

    @MessageMapping("/private-message")
    public Message receivePrivateMessage(@Payload Message message) {

        // 2. i need to sent to the message dot getReceiver
        // this is the prefix i want to listen and i want to add private with this prefix
        // and then we need to send the message the user want to listen to this particular topic
        // he need to listen to /user/username (followed by the username)
        // here in this case we will make this as david and then you need to listen to private
        // if you want to listen to this particular username
        // so this david is dynamic here so, this will be sending to dynamic topics
        // based on the receiver name which is present in the message

        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message);
        return message;
    }
}
