package com.digits.mybreweryclient.web.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.digits.mybreweryclient.web.model.BeerDto;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient client;

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void testGetBeerById() {
	BeerDto dto = client.getBeerById(UUID.randomUUID());
	assertNotNull(dto);
	System.out.println(dto);
    }

    @Test
    void testSaveNewBeer() {
	// BeerDto beerDto = BeerDto.builder().beerName("New Beer").build();
	BeerDto beerDto = BeerDto.builder().build();
	URI uri = client.saveNewBeer(beerDto);
	assertNotNull(uri);
	System.out.println(uri.toString());
    }

    @Test
    void testUpdateBeer() {
	UUID beerId = UUID.randomUUID();
	BeerDto beerDto = BeerDto.builder().id(beerId).beerName("updated beer name").build();
	client.updateBeer(beerId, beerDto);
    }

    @Test
    void testDeleteBeer() {
	client.deleteBeer(UUID.randomUUID());
    }

}
