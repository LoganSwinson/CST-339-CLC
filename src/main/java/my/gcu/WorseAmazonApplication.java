package my.gcu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Spring Boot application.
 * This class runs the Spring Boot application, initializing the context and components.
 */
@SpringBootApplication
public class WorseAmazonApplication
{
    /**
     * The main method that launches the Spring Boot application.
     * It runs the application and initializes all necessary components and configurations.
     * 
     * @param args command-line arguments
     */
    public static void main(String[] args)
    {
        SpringApplication.run(WorseAmazonApplication.class, args);
    }
}
