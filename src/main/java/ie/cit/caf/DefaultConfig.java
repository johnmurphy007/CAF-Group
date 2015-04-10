package ie.cit.caf;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author
 * 
 * This class is used by JUnit test for application configuration.  
 * 
 * The main application uses Spring-Boot for application configuration (scan for beans, create application context etc). 
 * 
 * Including the following annotations @Configuration, @EnableAutoConfiguration and @ComponentScan to mimic the setup that
 * Spring-Boot uses
 *  */

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class DefaultConfig {

}