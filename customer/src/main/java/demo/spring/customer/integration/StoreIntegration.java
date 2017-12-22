package demo.spring.customer.integration;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import demo.spring.customer.integration.fallback.StoreIntegrationFallback;
import demo.spring.customer.integration.po.Store;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "stores", fallback = StoreIntegrationFallback.class) // an arbitrary client name, which is used to create a Ribbon load balancer
public interface StoreIntegration {

    @RequestMapping(method = RequestMethod.GET, value = "/stores/stores")
    List<Store> getStores();

    @RequestMapping(method = RequestMethod.POST, value = "/stores/stores/{storeId}", consumes = "application/json")
    Store update(@PathVariable("storeId") String storeId, Store store);

    @RequestMapping(method = RequestMethod.GET, value = "/stores/stores/{storeId}", consumes = "application/json")
    Store get(@PathVariable("storeId") String storeId);
}
