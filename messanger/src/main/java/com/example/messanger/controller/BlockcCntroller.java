package com.example.messanger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.messanger.service.BlockService;
import com.example.messanger.service.ConversationService;

@RestController
@RequestMapping("block")
public class BlockcCntroller {
	
	
	@Autowired
	BlockService  blockService  ;
	
	
	@GetMapping("/blockTheUser/{userId}")
	public void blockTheUser(@PathVariable("userId") Long  userId ) {
		
		blockService.blockTheUser(userId)  ;
	}
	
	

}
