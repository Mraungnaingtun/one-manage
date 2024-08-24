package com.example.one_manage.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="USER_INFO")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private boolean isShownNoti;
}
