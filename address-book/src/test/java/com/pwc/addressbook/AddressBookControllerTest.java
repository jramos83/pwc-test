package com.pwc.addressbook;

import org.junit.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

public class AddressBookControllerTest {

    @Test
    public void test_statuscode_is_ok() {
        get("/v1/address-book?addressBookId=1").then().statusCode(200);
    }

    @Test
    public void test_specific_reservations() {
        get("/v1/address-book?addressBookId=1").then().body("addressBookName", equalTo("Uni friends"));
    }
}
