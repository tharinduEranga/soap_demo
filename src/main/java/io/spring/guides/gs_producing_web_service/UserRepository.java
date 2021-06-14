//package io.spring.guides.gs_producing_web_service;
//
///**
// * @author Tharindu Eranga
// * @date Mon 14 Jun 2021
// */
//
//import org.springframework.stereotype.Component;
//import org.springframework.util.Assert;
//
//import javax.annotation.PostConstruct;
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class UserRepository {
//    private static final Map<String, User> countries = new HashMap<>();
//
//    @PostConstruct
//    public void initData() {
//        User spain = new User();
//        spain.setName("Spain");
//        spain.setCapital("Madrid");
//        spain.setCurrency(Currency.EUR);
//        spain.setPopulation(46704314);
//
//        countries.put(spain.getName(), spain);
//
//        User poland = new User();
//        poland.setName("Poland");
//        poland.setCapital("Warsaw");
//        poland.setCurrency(Currency.PLN);
//        poland.setPopulation(38186860);
//
//        countries.put(poland.getName(), poland);
//
//        User uk = new User();
//        uk.setName("United Kingdom");
//        uk.setCapital("London");
//        uk.setCurrency(Currency.GBP);
//        uk.setPopulation(63705000);
//
//        countries.put(uk.getName(), uk);
//    }
//
//    public User findUser(String name) {
//        Assert.notNull(name, "The country's name must not be null");
//        return countries.get(name);
//    }
//}