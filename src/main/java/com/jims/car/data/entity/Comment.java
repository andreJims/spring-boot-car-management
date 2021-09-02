package com.jims.car.data.entity;

import com.jims.car.data.entity.user_auth.UserAuth;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String comment;
    private LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserAuth user;
    @ManyToOne(fetch = FetchType.LAZY)
    private Car car;
}
