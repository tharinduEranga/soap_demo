
package io.spring.guides.gs_producing_web_service;

import io.spring.guides.gs_producing_web_service.wsdl.NumberToWordsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * @author Tharindu Eranga
 * @date Mon 14 Jun 2021
 */
@Configuration
public class CountryConfiguration {
    private static final Logger log = LoggerFactory.getLogger(CountryConfiguration.class);

    @Value("${number.wsdl.url}")
    private String numberWsdlUrl;

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("io.spring.guides.gs_producing_web_service.wsdl");
        return marshaller;
    }

    @Bean
    public CountryClient countryClient(Jaxb2Marshaller marshaller) {
        CountryClient client = new CountryClient();
        client.setDefaultUri(numberWsdlUrl);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

    @Bean
    public CommandLineRunner lookup(CountryClient quoteClient) {
        return args -> {
            String num = "500";

            if (args.length > 0) {
                num = args[0];
            }
            NumberToWordsResponse response = quoteClient
                    .getNum(numberWsdlUrl, num);

            log.info("Response: {}", response.getNumberToWordsResult());
        };
    }

}
