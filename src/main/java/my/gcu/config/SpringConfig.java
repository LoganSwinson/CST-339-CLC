package my.gcu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import my.gcu.services.LoginService;
import my.gcu.services.ProductService;
import my.gcu.services.RegisterService;

/**
 * Configuration class for setting up beans in the application. This class
 * ensures that services are available for dependency injection.
 */
@Configuration
public class SpringConfig {

    /**
     * Creates and initializes the LoginService bean.
     *
     * @return a new instance of LoginService
     */
    @Bean(name = "loginService", initMethod = "init", destroyMethod = "destroy")
    public LoginService getLoginService() {
        return new LoginService();
    }

    /**
     * Creates and initializes the ProductService bean.
     *
     * @return a new instance of ProductService
     */
    @Bean(name = "productService", initMethod = "init", destroyMethod = "destroy")
    public ProductService getProductService() {
        return new ProductService();
    }

    /**
     * Creates and initializes the RegisterService bean.
     *
     * @return a new instance of RegisterService
     */
    @Bean(name = "registerService", initMethod = "init", destroyMethod = "destroy")
    public RegisterService getRegisterService() {
        return new RegisterService();
    }
}
