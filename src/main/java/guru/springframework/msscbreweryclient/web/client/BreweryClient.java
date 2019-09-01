package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
public class BreweryClient {

    private final String BEER_PATH_V1= "/api/v1/beer/";
    private final String CUSTOMER_PATH_V1 = "/api/v1/customer/";
    private String apihost;
    private RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }

    public BeerDto getBeerById(UUID beerId) {
        return restTemplate.getForObject(apihost + BEER_PATH_V1 + UUID.randomUUID(), BeerDto.class);
    }

    public URI saveBeer(BeerDto beerDto) {
        return restTemplate.postForLocation(apihost + BEER_PATH_V1, beerDto);
    }

    public void updateBeer(UUID beerId, BeerDto beerDto) {
        restTemplate.put(apihost + BEER_PATH_V1 + beerId, beerDto);
    }

    public void deleteBeer(UUID beerId) {
        restTemplate.delete(apihost + BEER_PATH_V1 + beerId);
    }


    public CustomerDto getCustomerById(UUID custId) {
        return restTemplate.getForObject(apihost + CUSTOMER_PATH_V1 + custId, CustomerDto.class);
    }

    public URI saveCustomer(CustomerDto customerDto) {
        return restTemplate.postForLocation(apihost + CUSTOMER_PATH_V1, customerDto);
    }

    public void updateCustomerById(UUID custId, CustomerDto customerDto) {
        restTemplate.put(apihost + CUSTOMER_PATH_V1 + custId, customerDto);
    }

    public void deleteCustomerById(UUID custId) {
        restTemplate.delete(apihost + CUSTOMER_PATH_V1 + custId);
    }
}
