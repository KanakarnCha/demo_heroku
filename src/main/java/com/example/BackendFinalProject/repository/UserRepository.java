package com.example.BackendFinalProject.repository;

import com.example.BackendFinalProject.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public  interface  UserRepository extends JpaRepository<UserEntity,String> {

    Optional<UserEntity> findByUserUsername(String username);
    boolean existsByUserUsername(String username);

}
