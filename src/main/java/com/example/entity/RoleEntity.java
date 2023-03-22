package com.example.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "roles")
@Entity
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role")
    private String role;
}
