package com.example.DbKart.dto.request;

import com.example.DbKart.modal.Enum.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerRequest {

    String name;
    int age;
    String email;
    Gender gender;
}
