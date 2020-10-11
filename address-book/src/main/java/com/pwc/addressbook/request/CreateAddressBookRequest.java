package com.pwc.addressbook.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateAddressBookRequest {
    private String addressBookName;
    private List<CreateContactRequest> contacts;
}
