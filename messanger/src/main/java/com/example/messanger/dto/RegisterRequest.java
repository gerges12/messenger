package com.example.messanger.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

import com.example.messanger.entity.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data

public class RegisterRequest {
    private String email;
    private String username;
    private String password;
    
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dob ;
    private Gender sex ;
    private String phone;
    private Integer age;


    


}
