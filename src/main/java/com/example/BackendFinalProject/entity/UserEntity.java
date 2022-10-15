package com.example.BackendFinalProject.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;


@Data
@Entity(name = "tb_user")
public class UserEntity {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false,unique = true)
    private String userId;
    @Column(length = 36, nullable = false)
    private String userFirstname;
    @Column(length = 36, nullable = false)
    private String userLastname;
    @Column(length = 10, nullable = false)
    private String userPhone;
    @Column(length = 100, nullable = false,unique = true)
    private String userUsername;
    @Column(length = 254, nullable = false)
    private String userPassword;
    private String userProfileImg;
    @OneToMany(mappedBy = "userEntity",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<UserAddressEntity> userAddressEntities;
    @OneToMany(mappedBy = "userEntity",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<WorkOrder> workOrders;

}
