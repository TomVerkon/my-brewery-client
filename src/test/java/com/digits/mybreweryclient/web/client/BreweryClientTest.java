package com.digits.mybreweryclient.web.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.net.URI;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.digits.mybreweryclient.web.model.BeerDto;
import com.digits.mybreweryclient.web.model.BeerStyleEnum;

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
	BeerDto beerDto = BeerDto.builder().beerName("New Beer").beerStyle(BeerStyleEnum.LAGER)
		.price(new BigDecimal("3.99")).upc("123456666654321").build();
	// BeerDto beerDto = BeerDto.builder().build();
	URI uri = client.saveNewBeer(beerDto);
	assertNotNull(uri);
	System.out.println(uri.toString());
    }

    @Test
    void testUpdateBeer() {
	UUID beerId = UUID.randomUUID();
	BeerDto beerDto = BeerDto.builder().id(beerId).beerName("updated beer name").beerStyle(BeerStyleEnum.LAGER)
		.price(new BigDecimal("3.99")).upc("098765431").build();
	client.updateBeer(beerId, beerDto);
    }

    @Test
    void testDeleteBeer() {
	client.deleteBeer(UUID.randomUUID());
    }
    
//    @Test
//    void getAllBeers() {
//	client
//    }

}
