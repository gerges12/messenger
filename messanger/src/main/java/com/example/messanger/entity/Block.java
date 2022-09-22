package com.example.messanger.entity;

import java.util.Date;
import java.util.List;

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
@Table(name="block")
@Data
public class Block {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    
	    
	    @Column(name = "time")
	    @CreationTimestamp
	    private Date time;

	 

		@JsonIgnore
	    @ManyToOne
	    @JoinColumn(name = "sender")
	    private User blocksender;

		@JsonIgnore
	    @ManyToOne
	    @JoinColumn(name = "receiver")
	    private User blockreceiver ;
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    

}