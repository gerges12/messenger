package com.example.messanger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.messanger.entity.User;
import com.example.messanger.service.BlockService;
import com.example.messanger.service.ConversationService;

@RestController
@RequestMapping("block")
public class BlockcCntroller {
	
	
	@Autowired
	BlockService  blockService  ;
	
	
	@GetMapping("/blockTheUser/{userId}")
	public ResponseEntity<String> blockTheUser(@PathVariable("userId") Long  userId ) {
		
		User user = blockService.blockTheUser(userId)  ;
		
		return new ResponseEntity<>("you are block " + user.getUsername()+ " successfully"   , HttpStatus.ACCEPTED)  ;
	}
	
	@DeleteMapping("/DeleteUserBlock/{userId}")
	public ResponseEntity<String> DeleteUserBlock(@PathVariable("userId") Long  userId ) {
		
		User user = blockService.deleteBlockTheUser(userId)  ;
		
		return new ResponseEntity<>("you deleteing block of " + user.getUsername()+ " successfully"   , HttpStatus.ACCEPTED)  ;
	}
	

}
