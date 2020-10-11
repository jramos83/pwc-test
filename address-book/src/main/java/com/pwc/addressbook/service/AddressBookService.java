package com.pwc.addressbook.service;

import com.pwc.addressbook.domain.AddressBook;
import com.pwc.addressbook.domain.Contact;
import com.pwc.addressbook.repository.AddressBookRepository;
import com.pwc.addressbook.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Transactional
    public void save(AddressBook addressBook) {

        List<Contact> resolvedContacts = new ArrayList<>();

        for (Contact contact : addressBook.getContacts()) {
            resolvedContacts.add(contactRepository.findByNameAndPhoneNumber(contact.getName(), contact.getPhoneNumber()).orElse(contact));
        }

        addressBook.getContacts().clear();

        addressBook.getContacts().addAll(resolvedContacts);

        addressBookRepository.save(addressBook);

    }

    public Optional<AddressBook> getAddressBook(Long id) {
        return addressBookRepository.findById(id);
    }

    public List<Contact> getUniqueContacts(Long addressBook1Id, Long addressBook2Id) {

        Optional<AddressBook> addressBook1 = addressBookRepository.findById(addressBook1Id);
        Optional<AddressBook> addressBook2 = addressBookRepository.findById(addressBook2Id);

        List<Contact> diff1 = difference(addressBook1.get().getContacts(), addressBook2.get().getContacts());
        List<Contact> diff2 = difference(addressBook2.get().getContacts(), addressBook1.get().getContacts());

        diff1.addAll(diff2);

        return diff1;
    }

    public List<Contact> difference(List<Contact> first, List<Contact> second) {
        List<Contact> toReturn = new ArrayList<>(first);
        toReturn.removeAll(second);
        return toReturn;
    }
}
