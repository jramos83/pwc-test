package com.pwc.addressbook.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AddressBookResponse {
    private Long id;
    private String addressBookName;
    private List<ContactResponse> contactResponse = new ArrayList<>();
}
