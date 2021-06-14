package io.spring.guides.gs_producing_web_service;

import io.spring.guides.gs_producing_web_service.wsdl.NumberToWordsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * @author Tharindu Eranga
 * @date Mon 14 Jun 2021
 */
@Endpoint
public class CountryEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryEndpoint.class);
    private final CountryRepository countryRepository;
    private final CountryClient countryClient;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository,
                           CountryClient countryClient) {
        this.countryRepository = countryRepository;
        this.countryClient = countryClient;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        LOGGER.info("URL Is: {}", request.getUrl());
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));

        NumberToWordsResponse numberToWordsResponse = countryClient
                .getNum(request.getUrl(), "500");

        LOGGER.info("NumberToWordsResponse Is: {}", numberToWordsResponse.getNumberToWordsResult());
        return response;
    }

}