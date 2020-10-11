package com.pwc.addressbook.repository;

import com.pwc.addressbook.domain.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressBookRepository  extends JpaRepository<AddressBook, Long> {
}
