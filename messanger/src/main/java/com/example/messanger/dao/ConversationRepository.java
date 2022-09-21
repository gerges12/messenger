package com.example.messanger.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.messanger.entity.Conversation;
import com.example.messanger.entity.User;




public interface ConversationRepository extends JpaRepository<Conversation, Long> {
	
	Conversation findByReceiverAndSender(User Receiver ,  User Sender);

  

}
