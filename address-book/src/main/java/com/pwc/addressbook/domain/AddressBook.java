package com.pwc.addressbook.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class AddressBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address_book_name", nullable = false, unique = true)
    private String addressBookName;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Contact> contacts = new ArrayList<>();

}
