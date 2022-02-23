package com.example.springbasic.entities;

import com.example.springbasic.define.type.MajorType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private MajorType major;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}