package demo.spring.customer.integration;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class FooIntegration {

    private static final Logger log = LoggerFactory.getLogger(FooIntegration.class);

    @HystrixCommand(fallbackMethod = "defaultStores")
    public Object getStores(Map<String, Object> parameters) {
        //do stuff that might fail
        if (parameters.containsKey("sleep")) {
            try {
                long timeout = 10L;
                log.debug("going to sleep {}s", timeout);
                TimeUnit.SECONDS.sleep(timeout);
                log.debug("sleep finished");
                return "slept result";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "interrupted result";
            }
        } else {
            return "normal result";
        }
    }

    public Object defaultStores(Map<String, Object> parameters) {
        return "default result";
    }
}
