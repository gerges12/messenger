package com.example.messanger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.messanger.dto.ConversationRequest;
import com.example.messanger.entity.Conversation;
import com.example.messanger.service.ConversationService;



@RestController
@RequestMapping("conversation")
public class ConversationController {
	
	@Autowired
	ConversationService  conversationService  ;
	
	@PostMapping("/createConversation")
    public void create (@RequestBody ConversationRequest  cnversationRequest) {
    	
		conversationService.save(cnversationRequest)  ; 
    	
    	
    }
	
	@GetMapping("/messages/{id}")
    public Conversation getConversation (@PathVariable("id") Long  receiverId) {
    	
		return conversationService.getConversation(receiverId)  ;
    	
    	
    }
	
	
	@DeleteMapping("/deleteConversation/{id}")
    public void delete (@PathVariable("id") Long  receiverId) {
    	
		conversationService.deleteConversation(receiverId)  ; 
    	
    	
    }
	
}
