package ru.nanikon.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "APP_USERS")
@Getter
@Setter
public class UserEntity {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "LOGIN", nullable = false)
    private String login;

    @Column(name = "PASSWORD", nullable = false, length = 32)
    private String password;
}