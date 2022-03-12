package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "demo")
public class Demo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "demo_id_generator")
    @SequenceGenerator(name = "demo_id_generator", sequenceName = "demo_id_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String code;

}

