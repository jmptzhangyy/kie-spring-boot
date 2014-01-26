package hello;

import org.kie.api.runtime.StatelessKieSession;
import org.kie.spring.beans.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

@RestController
public class HelloController {

    @Inject
    ApplicationContext context;

    @RequestMapping("/")
    public String index() {
        if (context != null) {
            StatelessKieSession kieSession = (StatelessKieSession) context.getBean("ksession1");
            Person p = new Person("HAL");
            p.setHappy(false);
            kieSession.execute(p);
            //the DRL should set this to true
            if ( p.isHappy() ) {
                return "Kie Spring - Success!";
            } else{
                return "Kie Spring - Failed!";
            }
        }
        return "Greetings from Spring Boot!";
    }
    
}
