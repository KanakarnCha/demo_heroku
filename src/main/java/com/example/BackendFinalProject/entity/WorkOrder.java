package com.example.BackendFinalProject.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "tb_work_order")
@Data
public class WorkOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long workOrderId;
    private String workTitle;
    private String workDescription;
    private String workLocationStart;
    private String workLocationEnd;
    private int workStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "workOrder")
    private List<Match> match;

}
