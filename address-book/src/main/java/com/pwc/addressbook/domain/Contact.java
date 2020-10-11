package com.pwc.addressbook.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "contact", uniqueConstraints = @UniqueConstraint(columnNames = { "name", "phone_number" }))
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

}
