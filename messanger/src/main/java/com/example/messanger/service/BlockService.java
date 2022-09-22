package com.example.messanger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.messanger.dao.BlockRepository;
import com.example.messanger.dao.UserRepository;
import com.example.messanger.entity.Block;
import com.example.messanger.entity.Conversation;
import com.example.messanger.entity.User;
import com.example.messanger.exceptions.MassangereException;

@Service
public class BlockService {
	
	
	@Autowired
	UserRepository userRepository  ;
	
	@Autowired
	AuthService  authservice  ;
	
	
    @Autowired
	BlockRepository  blockRepository  ;

	public void blockTheUser(Long userId) {
		
		
		User other = userRepository.findById(userId)
		         .orElseThrow(  
      () -> new MassangereException("user not found") ) ;
		
		User current = authservice.getCurrentUser() ;
		if (other == current) {
			throw new MassangereException("you cannot send to youyrself") ;
		}
		
		Block  block  =  new Block()  ;
		
		block.setBlockreceiver(other);
		block.setBlocksender(current);
		blockRepository.save(block)  ;
	}
	
	public boolean isBlock(User other) {
		
		boolean isBlock ;
		User current = authservice.getCurrentUser() ;
		
		Block  Block_as_sender = blockRepository.findByBlocksenderAndBlockreceiver(current, other) ;
		Block  block_as_reciever = blockRepository.findByBlocksenderAndBlockreceiver(other, current) ;

		if(Block_as_sender ==null && block_as_reciever ==null ) {
			isBlock = false ;
			System.out.print("hhhhhh");
		}
		else {
			isBlock = true ;

		}
		
		return isBlock ;
	} 
	
	
	
	
	
	
	
	
	
	
	
	

}
