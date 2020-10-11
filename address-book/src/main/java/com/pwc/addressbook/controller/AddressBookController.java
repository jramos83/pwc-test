package com.pwc.addressbook.controller;

import com.pwc.addressbook.domain.AddressBook;
import com.pwc.addressbook.domain.Contact;
import com.pwc.addressbook.exception.AddressBookNotFoundException;
import com.pwc.addressbook.request.CreateAddressBookRequest;
import com.pwc.addressbook.request.CreateContactRequest;
import com.pwc.addressbook.response.AddressBookResponse;
import com.pwc.addressbook.response.ContactResponse;
import com.pwc.addressbook.response.UniqueContactsResponse;
import com.pwc.addressbook.service.AddressBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/address-book")
@Api(value = "Address Book Resource", description = "API for Address Book Resource")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @PostMapping
    @ApiOperation(value = "Creates an address book")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 500, message = "Internal Server Error"),
                    @ApiResponse(code = 200, message = "Successful")
            }
    )
    public void createAddressBook(@RequestBody CreateAddressBookRequest createReservationRequest) {

        AddressBook addressBook = new AddressBook();
        addressBook.setAddressBookName(createReservationRequest.getAddressBookName());

        for(CreateContactRequest createContactRequest : createReservationRequest.getContacts()) {
            Contact contact = new Contact();
            contact.setName(createContactRequest.getName());
            contact.setPhoneNumber(createContactRequest.getPhoneNumber());

            addressBook.getContacts().add(contact);
        }

        addressBookService.save(addressBook);
    }

    @GetMapping
    @ApiOperation(value = "Lists an address book with the contact sorted by name")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 404, message = "Not Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error"),
                    @ApiResponse(code = 200, message = "Successful")
            }
    )
    public AddressBookResponse createAddressBook(@RequestParam(name = "addressBookId") Long addressBookId) {

        Optional<AddressBook> addressBookOptional = addressBookService.getAddressBook(addressBookId);

        if(addressBookOptional.isEmpty()) {
            throw new AddressBookNotFoundException();
        }

        AddressBook addressBook = addressBookOptional.get();

        AddressBookResponse addressBookResponse = new AddressBookResponse();
        addressBookResponse.setId(addressBook.getId());
        addressBookResponse.setAddressBookName(addressBook.getAddressBookName());

        for(Contact contact : addressBook.getContacts()) {

            ContactResponse contactResponse = new ContactResponse();
            contactResponse.setId(contact.getId());
            contactResponse.setName(contact.getName());
            contactResponse.setPhoneNumber(contact.getPhoneNumber());

            addressBookResponse.getContactResponse().add(contactResponse);
        }

        Collections.sort(
                addressBookResponse.getContactResponse(), Comparator.comparing(ContactResponse::getName));

        return addressBookResponse;

    }

    @GetMapping(path = "uniqueContacts")
    @ApiOperation(value = "Get unique contacts between two address books")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 404, message = "Not Found"),
                    @ApiResponse(code = 500, message = "Internal Server Error"),
                    @ApiResponse(code = 200, message = "Successful")
            }
    )
    public UniqueContactsResponse getUniqueContacts(@RequestParam(name = "addressBook1") Long addressBook1Id, @RequestParam(name = "addressBook2") Long addressBook2Id) {

        List<Contact> diff = addressBookService.getUniqueContacts(addressBook1Id, addressBook2Id);

        UniqueContactsResponse uniqueContactsResponse = new UniqueContactsResponse();

        for(Contact contact : diff) {
            ContactResponse contactResponse = new ContactResponse();
            contactResponse.setId(contact.getId());
            contactResponse.setName(contact.getName());
            contactResponse.setPhoneNumber(contact.getPhoneNumber());

            uniqueContactsResponse.getUniqueContacts().add(contactResponse);
        }

        return uniqueContactsResponse;

    }


}
