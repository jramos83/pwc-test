package com.pwc.addressbook.repository;

import com.pwc.addressbook.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository  extends JpaRepository<Contact, Long> {

    public Optional<Contact> findByNameAndPhoneNumber(String name, String phoneNumber);

}
