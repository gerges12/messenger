package com.example.messanger.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.messanger.entity.Block;
import com.example.messanger.entity.User;


public interface BlockRepository extends JpaRepository<Block, Long> {

	Block findByBlocksenderAndBlockreceiver(User current, User other);
	
	

  

}
