package com.example.BackendFinalProject.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
@Data
@Entity(name = "tb_match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long matchId;
    private String userId;
    private int status;
    private final LocalDateTime timeStamp = LocalDateTime.now(ZoneId.of("UTC+07:00"));
    @ManyToOne
    @JoinColumn(name = "order_id")
    private WorkOrder workOrder;

    @ManyToOne
    @JoinColumn(name = "volunteer_id")
    private VolunteerEntity volunteerEntity;


}
