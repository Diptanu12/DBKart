package com.example.DbKart.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class CustomerResponse {
    String name;
    String email;
    String age;
}
