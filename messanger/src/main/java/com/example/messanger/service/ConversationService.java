package com.example.messanger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.messanger.dao.ConversationRepository;
import com.example.messanger.dao.MessageRepository;
import com.example.messanger.dao.UserRepository;
import com.example.messanger.dto.ConversationRequest;
import com.example.messanger.entity.Conversation;
import com.example.messanger.entity.Message;
import com.example.messanger.entity.User;
import com.example.messanger.exceptions.BlockedUserException;
import com.example.messanger.exceptions.BlockedWordsException;
import com.example.messanger.exceptions.MassangereException;

@Service
public class ConversationService {
	
	
	@Autowired
	ConversationRepository conversationRepository  ;
	
	@Autowired
	UserRepository userRepository  ;
	
	@Autowired
	AuthService  authservice  ;
	
	@Autowired
	MessageRepository messageRepository  ;
	
 	 @Autowired
     BlockService    blockService  ;

	public void save(ConversationRequest conversationRequest) {
		

		String[] Block_words = {"bich", "fuck" , "porn"};

		User other = userRepository.findById(conversationRequest.getReciever())
		         .orElseThrow(  
       () -> new MassangereException("user not found") ) ;
		
		User current = authservice.getCurrentUser() ;
		if (other == current) {
			throw new MassangereException("you cannot send to youyrself") ;
		}
		
		if (blockService.isBlock(other)) {
			throw new BlockedUserException("you are block " + other.getUsername()) ;

		} ;
		
		String blockWord =  containsWords(conversationRequest.getMessage() , Block_words ) ;
		
		if (  blockWord != null ) {
			
			throw new BlockedWordsException("you entered " + blockWord + " which is against our politics") ;

		}
		
		Conversation  conversation_as_Sender = conversationRepository.findByReceiverAndSender(other, current) ;
		Conversation  conversation_as_reciever = conversationRepository.findByReceiverAndSender(current, other) ;

		
		if ( conversation_as_Sender == null && conversation_as_reciever == null)
		{

			Conversation new_conversation = new Conversation()  ;
		
			new_conversation.setReceiver(current);
			new_conversation.setSender(other);
		conversationRepository.save(new_conversation)  ;

		
		Message message = new Message()  ;
		message.setSender(current);
		message.setConversation(new_conversation);
		message.setContent(conversationRequest.getMessage());
		
		message = messageRepository.save(message)  ;
		
		
		}
		
		else 
			
		{
			Conversation old_conversation = conversation_as_Sender != null ? conversation_as_Sender :  conversation_as_reciever ;
			
			Message message = new Message()  ;
			message.setSender(current);
						
			message.setConversation(old_conversation);
			message.setContent(conversationRequest.getMessage());
			
			message = messageRepository.save(message)  ;
			
		}
		
	}

	public Conversation getConversation(Long receiverId) {
		
		

		return getConvById(receiverId)  ;
	}

	public void deleteConversation(Long receiverId) {
	

		Conversation old_conversation = getConvById(receiverId)	 ;
		
		conversationRepository.delete(old_conversation);
	}
	
	
	
	
	public Conversation getConvById(Long receiverId) {
		User other = userRepository.findById(receiverId)
		         .orElseThrow(  
    () -> new MassangereException("user not found") ) ;
		
		User current = authservice.getCurrentUser() ;
		
		if (other == current) {
			throw new MassangereException("no conversation exist") ;
		}
		
		Conversation  conversation_as_Sender = conversationRepository.findByReceiverAndSender(other, current) ;
		Conversation  conversation_as_reciever = conversationRepository.findByReceiverAndSender(current, other) ;

		Conversation old_conversation = conversation_as_Sender != null ? conversation_as_Sender :  conversation_as_reciever ;
		
		return old_conversation  ;
	}


	public static String containsWords(String inputString, String[] items) {
	    String blockWord  =  null;
	    for (String item : items) {
	        if (inputString.contains(item)) {
	        	blockWord = item;
	            break;
	        }
	    }
	    return blockWord;
	}
	
	


}
