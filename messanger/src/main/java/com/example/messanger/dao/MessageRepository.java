package com.example.messanger.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.messanger.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{

}
