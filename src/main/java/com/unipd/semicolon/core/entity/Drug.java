package com.unipd.semicolon.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // will creat the uniq and respectively id
    private long id;
}
