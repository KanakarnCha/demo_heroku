package com.example.BackendFinalProject.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity(name = "tb_volunteer")
public class VolunteerEntity {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false,unique = true)
    private String volunteerId;
    private String volunteerUsername;
    private String volunteerFirstname;
    private String volunteerLastname;
    private String volunteerEmail;
    private String volunteerPhone;
    private String volunteerAddress;
    private String volunteerImg;

    @OneToMany(mappedBy = "volunteerEntity")
    private List<Match> match;

}
