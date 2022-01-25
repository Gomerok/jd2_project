package by.academy.it.config;

import lombok.Getter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration("controllerConfiguration")
@ComponentScan(basePackages = {"by.academy.it.service",
        "by.academy.it.validator"})
@PropertySource("classpath:/service.properties")
public class ServiceConfiguration {

}
