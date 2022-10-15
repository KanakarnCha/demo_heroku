package com.example.BackendFinalProject.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
@Data
@Entity(name = "tb_user_address")
public class UserAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private long addressId;
    private String UserLocationAddress;
    private final LocalDateTime timeStamp = LocalDateTime.now(ZoneId.of("UTC+07:00"));
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}
