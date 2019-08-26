package com.digits.mybreweryclient.web.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.digits.mybreweryclient.web.model.CustomerDto;

@SpringBootTest
class CustomerClientTest {

    @Autowired
    CustomerClient client;

    @Test
    void testGetCustomerById() {
	CustomerDto dto = client.getCustomerById(UUID.randomUUID());
	assertNotNull(dto);
	System.out.println(dto);
    }

    @Test
    void testRegisterNewCustomer() {
	CustomerDto dto = CustomerDto.builder().customerName("New Customer").build();
	URI uri = client.registerNewCustomer(dto);
	assertNotNull(uri);
	System.out.println(uri.toString());
    }

    @Test
    void testUpdateCustomer() {
	UUID customerId = UUID.randomUUID();
	CustomerDto customerDto = CustomerDto.builder().id(customerId).customerName("updated customer name").build();
	client.updateCustomer(customerId, customerDto);
    }

    @Test
    void testDeleteCustomer() {
	client.deleteCustomer(UUID.randomUUID());
    }

}
