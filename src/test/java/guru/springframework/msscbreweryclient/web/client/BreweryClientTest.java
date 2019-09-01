package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    private BreweryClient breweryClient;

    @Test
    void getBeerById() {
        BeerDto beerDto = breweryClient.getBeerById(UUID.randomUUID());

        assertNotNull(beerDto);
    }

    @Test
    void saveBeer() {
        BeerDto beerDto = BeerDto.builder().bearName("New Beer").build();
        URI location = breweryClient.saveBeer(beerDto);
        assertNotNull(location);
        System.out.println(location);
    }

    @Test
    void updateBeer() {
        BeerDto beerDto = BeerDto.builder().build();
        breweryClient.updateBeer(UUID.randomUUID(), beerDto);
    }

    @Test
    void deleteBeer() {
        breweryClient.deleteBeer(UUID.randomUUID());
    }

    @Test
    void setApihost() {
    }

    @Test
    void getCustomerById() {
        CustomerDto customerDto = breweryClient.getCustomerById(UUID.randomUUID());

        assertNotNull(customerDto);
    }

    @Test
    void saveCustomer() {
        CustomerDto customerDto = CustomerDto.builder().build();
        URI location = breweryClient.saveCustomer(customerDto);

        assertNotNull(location);
        System.out.println(location);
    }

    @Test
    void updateCustomerById() {
        CustomerDto customerDto = CustomerDto.builder().build();
        breweryClient.updateCustomerById(UUID.randomUUID(), customerDto);
    }

    @Test
    void deleteCustomerById() {
        breweryClient.deleteCustomerById(UUID.randomUUID());
    }
}