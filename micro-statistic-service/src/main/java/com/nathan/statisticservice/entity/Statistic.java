package com.nathan.statisticservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "statistic")
@Data
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message")
    private String message;

    @CreationTimestamp
    @Column(name = "created_date")
    private Date createdDate;

}
