package com.example.messanger.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="conversation")
@Data
public class Conversation {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    
	    
	    @Column(name = "time")
	    @CreationTimestamp
	    private Date time;

	    @OneToMany(mappedBy = "conversation"  ,  cascade = CascadeType.ALL)
	    private List<Message> messages ;

		@JsonIgnore
	    @ManyToOne
	    @JoinColumn(name = "sender")
	    private User sender;

		@JsonIgnore
	    @ManyToOne
	    @JoinColumn(name = "receiver")
	    private User receiver ;
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    

}
