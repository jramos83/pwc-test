package com.pwc.addressbook.response;

import lombok.Data;

@Data
public class ContactResponse {
    private Long id;
    private String name;
    private String phoneNumber;
}
