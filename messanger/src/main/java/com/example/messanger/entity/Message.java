package com.example.messanger.entity;

import static javax.persistence.FetchType.LAZY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity (name = "message")
@Table(name = "message")
public class Message {
	
	
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Message_id")
    private Long id;
	
	@JsonIgnoreProperties({"userId"  ,"password","description" ,"email" ,"created", "dob" , "sex" , "age" , "Messages" , "roles" , "phone" })
	@JoinColumn(name = "userId")
    @ManyToOne()
    private User sender;
	
	@Column(name = "content")
    private String content;
 
	
    @ManyToOne(fetch = LAZY)  
	@JoinColumn(name = "conversationId")
	@JsonIgnore
    private Conversation conversation;
 
	
	
	

	
	
}
