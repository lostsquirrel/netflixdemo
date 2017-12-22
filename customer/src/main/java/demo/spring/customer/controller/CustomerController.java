package demo.spring.customer.controller;

import demo.spring.customer.integration.FooIntegration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomerController {

    private FooIntegration fooIntegration;

    public CustomerController(FooIntegration fooIntegration) {
        this.fooIntegration = fooIntegration;
    }

    @GetMapping("/foo")
    public Object foo(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> parameters = new HashMap<>();
        parameterMap.forEach((key, value) -> parameters.put(key, value));
        return fooIntegration.getStores(parameters);
    }
}
