package com.example.jpastarterdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaStarterDemoApplication {


    private static final Logger log =
            LoggerFactory.getLogger(JpaStarterDemoApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(JpaStarterDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository)
    {
        return (args) -> {
            // save a few customers
            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("Mitchelle", "Dessler"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");

            for (Customer customer : repository.findAll())
            {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Customer customer = repository.findById(1L);
            log.info("Customer found with findById(1L):");
            log.info("---------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customer by last name
            log.info("customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByLastName("Bauer").forEach(bauer ->
                    {
                        log.info(bauer.toString());
                    }
                    );
            log.info("");
        };
    }
}
