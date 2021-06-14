
package io.spring.guides.gs_producing_web_service;

import io.spring.guides.gs_producing_web_service.wsdl.NumberToWords;
import io.spring.guides.gs_producing_web_service.wsdl.NumberToWordsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.math.BigInteger;

/**
 * @author Tharindu Eranga
 * @date Mon 14 Jun 2021
 */
public class CountryClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(CountryClient.class);


    public NumberToWordsResponse getNum(String url, String num) {

        NumberToWords request = new NumberToWords();
        request.setUbiNum(new BigInteger(num));

        log.info("Requesting location for: {}", request);

        return (NumberToWordsResponse) getWebServiceTemplate()
                .marshalSendAndReceive(url, request,
                        new SoapActionCallback(
                                "http://spring.io/guides/gs-producing-web-service/GetCountryRequest"));

    }


}
