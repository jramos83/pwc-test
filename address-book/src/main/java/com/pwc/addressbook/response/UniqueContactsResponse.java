package com.pwc.addressbook.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UniqueContactsResponse {
    private List<ContactResponse> uniqueContacts = new ArrayList<>();
}
