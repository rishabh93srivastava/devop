package com.niit.controllers;



import java.util.ArrayList;

import java.util.List;


import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;

import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import org.springframework.messaging.simp.annotation.SendToUser;

//import org.springframework.messaging.simp.annotation.SubscribeEvent;

import org.springframework.messaging.simp.annotation.SubscribeMapping;

import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.stereotype.Controller;



import com.niit.dao.UserDao;
import com.niit.models.Chat;


@Controller
public class SockController {

	private static final Log logger = LogFactory.getLog(SockController.class);

	private final SimpMessagingTemplate messagingTemplate;

	private List<String> users = new ArrayList<String>();


	@Autowired

	public SockController(SimpMessagingTemplate messagingTemplate) {

		this.messagingTemplate = messagingTemplate;

	}

	@SubscribeMapping("/join/{username}")

	public List<String> join(@DestinationVariable("username") String username) {
        

		 System.out.println("username in sockcontroller" + username);
		 
		 if(!users.contains(username)) {//username not already exists
				users.add(username);
			}
        // message.body=username

		System.out.println("====JOIN==== " + username);

		// notify all subscribers of new user

		messagingTemplate.convertAndSend("/topic/join", username);// other prev users in chatroom

		return users;

	}

	@MessageMapping(value = "/chat")

	public void chatReveived(Chat chat) {


		if (chat.getTo().equals("all")) {

			System.out.println("IN CHAT REVEIVED " + chat.getMessage() + " " + chat.getFrom() + " to " + chat.getTo());

			messagingTemplate.convertAndSend("/queue/chats", chat);
			//$scope.stompClient.subscribe("/queue/chats",function(frame){frame.body= {}})

		}

		else {

			System.out.println("CHAT TO " + chat.getTo() + " From " + chat.getFrom() + " Message " + chat.getMessage());

			messagingTemplate.convertAndSend("/queue/chats/" + chat.getTo(), chat);

			messagingTemplate.convertAndSend("/queue/chats/" + chat.getFrom(), chat);

		}

	}

}