package com.pluralsight.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Customer {
    private int id;
    private String name;
    private String email;
    private String phone;

}