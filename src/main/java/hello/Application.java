package hello;

import java.net.MalformedURLException;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {
    
    public static void main(String[] args) throws Exception {
        Object[] sources = new Object[]{Application.class, "spring/kmodule-spring.xml"};

        SpringApplication springApplication = new SpringApplication(sources);
        ApplicationContext ctx = springApplication.run(args);
        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

}
